import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DepositProcessorTest {
	DepositProcessor depositProcessor;
	Bank bank;
	CheckingAccount checkingAccount;

	@BeforeEach
	void setup() {
		bank = new Bank();
		depositProcessor = new DepositProcessor(bank);
		checkingAccount = new CheckingAccount("12345678", 2.5);
		bank.addAccount(checkingAccount);
	}

	@Test
	void correctly_deposits_right_amount() {
		depositProcessor.process("deposit 12345678 100");
		double actual = bank.getAccount("12345678").getBalance();

		assertEquals(100, actual);
	}

	@Test
	void depositing_twice_adds_amount_correctly() {
		depositProcessor.process("deposit 12345678 100");
		depositProcessor.process("deposit 12345678 200");
		double actual = bank.getAccount("12345678").getBalance();

		assertEquals(300, actual);
	}

	@Test
	void deposits_to_correct_account_when_there_are_multiple_accounts() {
		SavingsAccount savingsAccount = new SavingsAccount("87654321", 2.0);
		bank.addAccount(savingsAccount);
		depositProcessor.process("deposit 12345678 100");
		double checkingActual = bank.getAccount("12345678").getBalance();
		double savingsActual = bank.getAccount("87654321").getBalance();

		assertEquals(100, checkingActual);
		assertEquals(0, savingsActual);
	}

	@Test
	void can_deposit_to_multiple_accounts() {
		SavingsAccount savingsAccount = new SavingsAccount("87654321", 2.0);
		bank.addAccount(savingsAccount);
		depositProcessor.process("deposit 12345678 100");
		depositProcessor.process("deposit 87654321 200");
		double checkingAmount = bank.getAccount("12345678").getBalance();
		double savingsAmount = bank.getAccount("87654321").getBalance();

		assertEquals(100, checkingAmount);
		assertEquals(200, savingsAmount);
	}

	@Test
	void deposit_works_with_capital_letters() {
		depositProcessor.process("DePOsiT 12345678 500");
		double actual = bank.getAccount("12345678").getBalance();

		assertEquals(500, actual);
	}

	@Test
	void deposit_to_checking_correctly() {
		depositProcessor.process("deposit 12345678 200.5");
		double actual = bank.getAccount("12345678").getBalance();

		assertEquals(200.5, actual);
	}

	@Test
	void deposit_to_savings_correctly() {
		SavingsAccount savingsAccount = new SavingsAccount("56781234", 2);
		bank.addAccount(savingsAccount);
		depositProcessor.process("deposit 56781234 300");
		double actual = bank.getAccount("56781234").getBalance();

		assertEquals(300, actual);

	}

	@Test
	void deposit_with_decimals() {
		depositProcessor.process("deposit 12345678 200.5");
		double actual = bank.getAccount("12345678").getBalance();

		assertEquals(200.5, actual);

	}

	@Test
	void depositing_zero_works() {
		depositProcessor.process("deposit 12345678 0");
		double actual = bank.getAccount("12345678").getBalance();

		assertEquals(0, actual);
	}

}
