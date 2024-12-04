package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransferProcessorTest {
	public static final String CHECKING_ID = "12345678";
	public static final String SAVINGS_ID = "87654321";
	TransferProcessor transferProcessor;
	Bank bank;
	CheckingAccount checking;
	SavingsAccount savings;

	@BeforeEach
	void setUp() {
		bank = new Bank();
		transferProcessor = new TransferProcessor(bank);
		checking = new CheckingAccount(CHECKING_ID, 2.5);
		savings = new SavingsAccount(SAVINGS_ID, 2.0);
		bank.addAccount(checking);
		bank.addAccount(savings);
	}

	@Test
	void transfer_from_checking_to_savings_works() {
		bank.deposit(CHECKING_ID, 400);
		bank.deposit(SAVINGS_ID, 100);
		transferProcessor.process("transfer 12345678 87654321 200");
		double actual1 = bank.getAccount(CHECKING_ID).getBalance();
		double actual2 = bank.getAccount(SAVINGS_ID).getBalance();

		assertEquals(200, actual1);
		assertEquals(300, actual2);
	}

	@Test
	void transfer_from_savings_to_checking_works() {
		bank.deposit(SAVINGS_ID, 600);
		bank.deposit(CHECKING_ID, 200);
		transferProcessor.process("transfer 87654321 12345678 400");
		double actual1 = bank.getAccount(SAVINGS_ID).getBalance();
		double actual2 = bank.getAccount(CHECKING_ID).getBalance();

		assertEquals(200, actual1);
		assertEquals(600, actual2);
	}

	@Test
	void transfer_from_checking_to_checking_works() {
		bank.deposit(CHECKING_ID, 200);
		CheckingAccount check = new CheckingAccount("56781234", 2.3);
		bank.addAccount(check);
		bank.deposit("56781234", 500);
		transferProcessor.process("transfer 12345678 56781234 100");
		double actual1 = bank.getAccount("56781234").getBalance();
		double actual2 = bank.getAccount(CHECKING_ID).getBalance();

		assertEquals(600, actual1);
		assertEquals(100, actual2);
	}

	@Test
	void transfer_from_savings_to_savings_works() {
		bank.deposit(SAVINGS_ID, 200);
		SavingsAccount save = new SavingsAccount("56781234", 2.3);
		bank.addAccount(save);
		bank.deposit("56781234", 500);
		transferProcessor.process("transfer 87654321 56781234 100");
		double actual1 = bank.getAccount("56781234").getBalance();
		double actual2 = bank.getAccount(SAVINGS_ID).getBalance();

		assertEquals(600, actual1);
		assertEquals(100, actual2);
	}

	@Test
	void if_transfer_amount_higher_than_actual_the_correct_amount_is_transfered() {
		bank.deposit(CHECKING_ID, 100);
		bank.deposit(SAVINGS_ID, 400);
		transferProcessor.process("transfer 12345678 87654321 200");
		double actual1 = bank.getAccount(CHECKING_ID).getBalance();
		double actual2 = bank.getAccount(SAVINGS_ID).getBalance();

		assertEquals(0, actual1);
		assertEquals(500, actual2);
	}

}
