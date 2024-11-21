package banking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WithdrawValidatorTest {
	WithdrawValidator withdrawValidator;
	Bank bank;
	CheckingAccount checking;

	@BeforeEach
	void setUp() {
		bank = new Bank();
		withdrawValidator = new WithdrawValidator(bank);
		checking = new CheckingAccount("12345678", 2.5);
		bank.addAccount(checking);
	}

	@Test
	void valid_withdraw_command() {
		boolean actual = withdrawValidator.validate("withdraw 12345678 300");

		assertTrue(actual);
	}

	@Test
	void checking_400_withdraw_is_valid() {
		boolean actual = withdrawValidator.validate("withdraw 12345678 400");

		assertTrue(actual);
	}

	@Test
	void checking_withdraw_above_400_is_invalid() {
		boolean actual = withdrawValidator.validate("withdraw 12345678 401");

		assertFalse(actual);
	}

	@Test
	void negative_withdraw_is_invalid() {
		boolean actual = withdrawValidator.validate("withdraw 12345678 -100");

		assertFalse(actual);
	}
}
