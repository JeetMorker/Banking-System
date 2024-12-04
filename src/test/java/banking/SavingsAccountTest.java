package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SavingsAccountTest {
	SavingsAccount savingsAccount;

	@Test
	public void savings_created_with_0_starting_balance() {
		savingsAccount = new SavingsAccount("1234578", 1);
		double actual = savingsAccount.getBalance();

		assertEquals(0, actual);
	}
}
