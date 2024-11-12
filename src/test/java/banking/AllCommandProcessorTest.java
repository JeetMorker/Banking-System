package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AllCommandProcessorTest {
	AllCommandProcessor allCommandProcessor;
	Bank bank;

	@BeforeEach
	void setUp() {
		bank = new Bank();
		allCommandProcessor = new AllCommandProcessor(bank);
	}

	@Test
	void correctly_delegates_to_create_processor() {
		allCommandProcessor.process("create checking 12345678 2.4");
		String actual = bank.getAccount("12345678").getID();

		assertEquals("12345678", actual);

	}

	@Test
	void correctly_delegates_to_create_in_capitals() {
		allCommandProcessor.process("CREATE checking 12345678 2.5");
		String actual = bank.getAccount("12345678").getID();

		assertEquals("12345678", actual);
	}

	@Test
	void correctly_delegates_to_deposit_processor() {
		CheckingAccount checkingAccount = new CheckingAccount("12345678", 2.4);
		bank.addAccount(checkingAccount);
		allCommandProcessor.process("deposit 12345678 400");
		double actual = checkingAccount.getBalance();

		assertEquals(400, actual);

	}

	@Test
	void correctly_delegates_to_deposit_in_capitals() {
		CheckingAccount checkingAccount = new CheckingAccount("12345678", 2.4);
		bank.addAccount(checkingAccount);
		allCommandProcessor.process("DEPOSIT 12345678 300");
		double actual = checkingAccount.getBalance();

		assertEquals(300, actual);

	}

}
