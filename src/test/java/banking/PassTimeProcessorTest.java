package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PassTimeProcessorTest {
	PassTimeProcessor passTimeProcessor;
	Bank bank;
	CheckingAccount checkingAccount;
	SavingsAccount savingsAccount;
	CDAccount cdAccount;

	@BeforeEach
	void setUp() {
		bank = new Bank();
		passTimeProcessor = new PassTimeProcessor(bank);
		checkingAccount = new CheckingAccount("12345678", 3.0);
		savingsAccount = new SavingsAccount("87654321", 0.6);
		cdAccount = new CDAccount("56781234", 2.1, 2000);
		bank.addAccount(checkingAccount);
		bank.addAccount(savingsAccount);
		bank.addAccount(cdAccount);
	}

	@Test
	void pass_time_correctly_adds_months() {
		bank.deposit("12345678", 200);
		bank.deposit("87654321", 200);
		passTimeProcessor.process("pass 2");
		int checkingMonths = bank.getTime("12345678");
		int savingsMonths = bank.getTime("87654321");
		int cdMonths = bank.getTime("56781234");

		assertEquals(2, checkingMonths);
		assertEquals(2, savingsMonths);
		assertEquals(2, cdMonths);

	}

	@Test
	void checking_apr_accrues_correctly() {
		bank.deposit("12345678", 1000);
		passTimeProcessor.process("pass 1");
		double actual = bank.getAccount("12345678").getBalance();

		assertEquals(1002.5, actual);
	}

	@Test
	void savings_apr_accrues_correctly() {
		bank.deposit("87654321", 5000);
		passTimeProcessor.process("pass 1");
		double actual = bank.getAccount("87654321").getBalance();

		assertEquals(5002.50, actual);
	}

	@Test
	void cd_apr_accrues_correctly() {
		passTimeProcessor.process("pass 1");

		double actual = bank.getAccount("56781234").getBalance();

		assertEquals(2014.0367928937578, actual);

	}

	@Test
	void cd_apr_accrues_multiple_months_correctly() {
		passTimeProcessor.process("pass 2");

		double actual = bank.getAccount("56781234").getBalance();

		assertEquals(2028.1721015648866, actual);

	}

	@Test
	void account_closes_if_balance_is_0() {
		passTimeProcessor.process("pass 1");

		int actual = bank.getAccounts().size();

		assertEquals(1, actual);
	}

	@Test
	void deduction_occurs_if_low_balance() {
		bank.deposit("12345678", 45);
		passTimeProcessor.process("pass 1");
		double actual = bank.getAccount("12345678").getBalance();

		assertEquals(20.05, actual);
	}

}
