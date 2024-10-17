import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CheckingAccountTest {
	CheckingAccount checkingAccount;

	@Test
	public void checking_created_with_0_starting_balance() {
		checkingAccount = new CheckingAccount("12345678", 1);
		double actual = checkingAccount.getBalance();

		assertEquals(0, actual);
	}

}
