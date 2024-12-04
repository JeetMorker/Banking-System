package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CDAccountTest {
	CDAccount cdAccount;

	@Test
	public void cd_starting_balance_is_supplied_balance() {
		cdAccount = new CDAccount("12345678", 10, 5);
		double actual = cdAccount.getBalance();

		assertEquals(5, actual);
	}
}
