package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CreateProcessorTest {
	CreateProcessor createProcessor;
	Bank bank;

	@BeforeEach
	void setUp() {
		bank = new Bank();
		createProcessor = new CreateProcessor(bank);
	}

	@Test
	void bank_has_checking_with_correct_id_and_apr() {
		createProcessor.process("create checking 12345678 1.0");
		String actualID = bank.getAccount("12345678").getID();
		double actualAPR = bank.getAccount("12345678").getAPR();

		assertEquals("12345678", actualID);
		assertEquals(1.0, actualAPR);
		assertTrue(bank.getAccount("12345678") instanceof CheckingAccount);
	}

	@Test
	void bank_has_savings_with_correct_id_and_apr() {
		createProcessor.process("create savings 87654321 2.5");
		String actualID = bank.getAccount("87654321").getID();
		double actualAPR = bank.getAccount("87654321").getAPR();

		assertEquals("87654321", actualID);
		assertEquals(2.5, actualAPR);
		assertTrue(bank.getAccount("87654321") instanceof SavingsAccount);
	}

	@Test
	void bank_has_cd_with_correct_id_apr_and_balance() {
		createProcessor.process("create cd 56781234 3.1 1000");
		String actualID = bank.getAccount("56781234").getID();
		double actualAPR = bank.getAccount("56781234").getAPR();
		double actualBalance = bank.getAccount("56781234").getBalance();

		assertEquals("56781234", actualID);
		assertEquals(3.1, actualAPR);
		assertEquals(1000, actualBalance);
		assertTrue(bank.getAccount("56781234") instanceof CDAccount);

	}

	@Test
	void creates_multiple_accounts_correctly() {
		createProcessor.process("create checking 12345678 2.5");
		createProcessor.process("create cd 87654321 5.0 5000");
		String firstID = bank.getAccount("12345678").getID();
		double firstAPR = bank.getAccount("12345678").getAPR();
		String secondID = bank.getAccount("87654321").getID();
		double secondAPR = bank.getAccount("87654321").getAPR();

		assertEquals("12345678", firstID);
		assertEquals(2.5, firstAPR);
		assertTrue(bank.getAccount("12345678") instanceof CheckingAccount);
		assertEquals("87654321", secondID);
		assertEquals(5.0, secondAPR);
		assertTrue(bank.getAccount("87654321") instanceof CDAccount);

	}

	@Test
	void works_with_capital_letters() {
		createProcessor.process("CrEAtE ChEcking 12345678 7.8");
		String actualID = bank.getAccount("12345678").getID();
		double actualAPR = bank.getAccount("12345678").getAPR();

		assertEquals("12345678", actualID);
		assertEquals(7.8, actualAPR);
		assertTrue(bank.getAccount("12345678") instanceof CheckingAccount);
	}

	@Test
	void does_not_create_duplicate_account() {
		createProcessor.process("create checking 12345678 2.4");
		createProcessor.process("create checking 12345678 2.4");
		int actual = bank.getAccounts().size();

		assertEquals(1, actual);

	}
}
