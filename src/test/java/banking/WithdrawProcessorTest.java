package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WithdrawProcessorTest {
	WithdrawProcessor withdrawProcessor;
	Bank bank;
	CheckingAccount checkingAccount;
	SavingsAccount savingsAccount;
	CDAccount cdAccount;

	@BeforeEach
	void setUp() {
		bank = new Bank();
		withdrawProcessor = new WithdrawProcessor(bank);
		checkingAccount = new CheckingAccount("12345678", 3.0);
		savingsAccount = new SavingsAccount("87654321", 0.6);
		cdAccount = new CDAccount("56781234", 2.1, 2000);
		bank.addAccount(checkingAccount);
		bank.addAccount(savingsAccount);
		bank.addAccount(cdAccount);
	}

	@Test
	void checking_withdraws_correct_amount() {
		checkingAccount.deposit(500);
		withdrawProcessor.process("withdraw 12345678 100");
		double amount = checkingAccount.getBalance();

		assertEquals(400, amount);
	}

	@Test
	void savings_withdraws_correct_amount() {
		savingsAccount.deposit(500);
		withdrawProcessor.process("withdraw 87654321 100");
		double amount = savingsAccount.getBalance();

		assertEquals(400, amount);
	}

	@Test
	void cd_accounts_withdraws_full_amount() {
		bank.passTime(12);
		withdrawProcessor.process("withdraw 56781234 3000");
		double amount = cdAccount.getBalance();

		assertEquals(0, amount);
	}

	@Test
	void withdrawing_two_different_accounts_works() {
		checkingAccount.deposit(500);
		savingsAccount.deposit(500);
		withdrawProcessor.process("withdraw 12345678 100");
		withdrawProcessor.process("withdraw 87654321 200");
		double amount1 = checkingAccount.getBalance();
		double amount2 = savingsAccount.getBalance();

		assertEquals(amount1, 400);
		assertEquals(amount2, 300);

	}

	@Test
	void withdrawing_twice_works() {
		checkingAccount.deposit(500);
		withdrawProcessor.process("withdraw 12345678 100");
		withdrawProcessor.process("withdraw 12345678 200");
		double amount = checkingAccount.getBalance();

		assertEquals(200, amount);
	}

	@Test
	void withdrawing_0_works() {
		checkingAccount.deposit(500);
		withdrawProcessor.process("withdraw 12345678 0");
		double amount = checkingAccount.getBalance();

		assertEquals(500, amount);
	}

}
