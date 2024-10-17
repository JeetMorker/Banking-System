import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AllAccountsTest {

	// Only need one account since all account types share these features
	SavingsAccount savingsAccount;

	@BeforeEach
	public void setup() {
		savingsAccount = new SavingsAccount("12345678", 5.7);
	}

	@Test
	public void apr_is_the_supplied_apr() {
		double actual = savingsAccount.getAPR();

		assertEquals(5.7, actual);
	}

	@Test
	public void depositing_causes_balance_increase_by_amount() {
		savingsAccount.deposit(50);
		double actual = savingsAccount.getBalance();

		assertEquals(50, actual);
	}

	@Test
	public void withdrawing_causes_balance_decrease_by_amount() {
		savingsAccount.deposit(50);
		savingsAccount.withdraw(25);
		double actual = savingsAccount.getBalance();

		assertEquals(25, actual);
	}

	@Test
	public void balance_cant_go_below_zero() {
		savingsAccount.deposit(20);
		savingsAccount.withdraw(50);
		double actual = savingsAccount.getBalance();

		assertEquals(0, actual);
	}

	@Test
	public void depositing_twice_into_same_account() {
		savingsAccount.deposit(10);
		savingsAccount.deposit(20);
		double actual = savingsAccount.getBalance();

		assertEquals(30, actual);
	}

	@Test
	public void withdrawing_twice_into_same_account() {
		savingsAccount.deposit(30);
		savingsAccount.withdraw(10);
		savingsAccount.withdraw(5);
		double actual = savingsAccount.getBalance();

		assertEquals(15, actual);
	}

}
