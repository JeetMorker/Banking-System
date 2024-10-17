import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BankTest {

	public static final String FIRST_ID = "12345678";
	public static final String SECOND_ID = "11111111";

	Bank bank;
	CheckingAccount checking;
	CDAccount cd;

	@BeforeEach
	public void setUp() {
		bank = new Bank();
		checking = new CheckingAccount(FIRST_ID, 2.1);
		cd = new CDAccount(SECOND_ID, 3, 500);
	}

	@Test
	public void when_bank_is_created_it_has_no_accounts() {
		assertTrue(bank.getAccounts().isEmpty());
	}

	@Test
	public void when_account_is_added_bank_has_one_account() {
		bank.addAccount(checking);
		int actual = bank.getAccounts().size();

		assertEquals(1, actual);
	}

	@Test
	public void when_two_accounts_added_bank_has_two_accounts() {
		bank.addAccount(checking);
		bank.addAccount(cd);
		int actual = bank.getAccounts().size();

		assertEquals(2, actual);
	}

	// I added two accounts for these tests to make sure it can differentiate
	// between the accounts
	@Test
	public void when_retrieving_one_account_correct_account_is_retrieved() {
		bank.addAccount(checking);
		bank.addAccount(cd);
		AllAccounts actual = bank.getAccount(SECOND_ID);

		assertEquals(cd, actual);
	}

	@Test
	public void depositing_by_id_puts_money_into_the_right_account() {
		bank.addAccount(checking);
		bank.addAccount(cd);
		bank.deposit(FIRST_ID, 300);
		double actual = bank.getAccount(FIRST_ID).getBalance();

		assertEquals(300, actual);
	}

	@Test
	public void withdrawing_by_id_puts_money_into_the_right_account() {
		bank.addAccount(checking);
		bank.addAccount(cd);
		bank.withdraw(SECOND_ID, 200);
		double actual = bank.getAccount(SECOND_ID).getBalance();

		assertEquals(300, actual);
	}

	@Test
	public void depositing_twice_through_bank_works() {
		bank.addAccount(checking);
		bank.deposit(FIRST_ID, 20);
		bank.deposit(FIRST_ID, 50.5);
		double actual = bank.getAccount(FIRST_ID).getBalance();

		assertEquals(70.5, actual);
	}

	@Test
	public void withdrawing_twice_through_bank_works() {
		bank.addAccount(cd);
		bank.withdraw(SECOND_ID, 100);
		bank.withdraw(SECOND_ID, 50);
		double actual = bank.getAccount(SECOND_ID).getBalance();

		assertEquals(350, actual);

	}

}
