package banking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WithdrawValidatorTest {
	WithdrawValidator withdrawValidator;
	Bank bank;
	CheckingAccount checking;
	SavingsAccount savings;
	CDAccount cd;

	@BeforeEach
	void setUp() {
		bank = new Bank();
		withdrawValidator = new WithdrawValidator(bank);
		checking = new CheckingAccount("12345678", 2.5);
		savings = new SavingsAccount("87654321", 2.0);
		cd = new CDAccount("56781234", 1.0, 500);
		bank.addAccount(checking);
		bank.addAccount(savings);
		bank.addAccount(cd);
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

	@Test
	void savings_0_withdraw_is_valid() {
		boolean actual = withdrawValidator.validate("withdraw 87654321 0");

		assertTrue(actual);
	}

	@Test
	void saving_negative_withdraw_invalid() {
		boolean actual = withdrawValidator.validate("withdraw 87654321 -1");

		assertFalse(actual);
	}

	@Test
	void savings_1000_withdraw_is_valid() {
		boolean actual = withdrawValidator.validate("withdraw 87654321 1000");

		assertTrue(actual);
	}

	@Test
	void savings_withdraw_above_1000_is_invalid() {
		boolean actual = withdrawValidator.validate("withdraw 87654321 1001");

		assertFalse(actual);
	}

	@Test
	void savings_cannot_withdraw_more_than_once_per_month() {
		bank.withdraw("87654321", 500);
		boolean actual = withdrawValidator.validate("withdraw 87654321 500");

		assertFalse(actual);
	}

	@Test
	void savings_can_withdraw_again_after_time_passes() {
		bank.deposit("87654321", 500);
		bank.withdraw("87654321", 200);
		bank.passTime(1);
		boolean actual = withdrawValidator.validate("withdraw 87654321 100");

		assertTrue(actual);
	}

	@Test
	void cd_cannot_withdraw_before_12_months() {
		boolean actual = withdrawValidator.validate("withdraw 56781234 500");

		assertFalse(actual);
	}

	@Test
	void cd_can_withdraw_after_12_months() {
		bank.passTime(12);
		boolean actual = withdrawValidator.validate("withdraw 56781234 1000");

		assertTrue(actual);
	}

	@Test
	void cd_cannot_withdraw_less_than_balance() {
		bank.passTime(12);
		boolean actual = withdrawValidator.validate("withdraw 56781234 100");

		assertFalse(actual);
	}

	@Test
	void cd_can_withdraw_exact_balance() {
		bank.passTime(12);
		boolean actual = withdrawValidator.validate("withdraw 56781234 520.3967185608326");

		assertTrue(actual);
	}

	@Test
	void withdraw_in_capitals_is_valid() {
		boolean actual = withdrawValidator.validate("WITHDRAW 12345678 300");

		assertTrue(actual);
	}

	@Test
	void withdraw_missing_is_invalid() {
		boolean actual = withdrawValidator.validate("12345678 200");

		assertFalse(actual);
	}

	@Test
	void id_missing_is_invalid() {
		boolean actual = withdrawValidator.validate("withdraw 200");

		assertFalse(actual);
	}

	@Test
	void amount_missing_is_invalid() {
		boolean actual = withdrawValidator.validate("withdraw 12345678");

		assertFalse(actual);
	}

	@Test
	void withdraw_spelled_wrong_is_invalid() {
		boolean actual = withdrawValidator.validate("with 12345678 200");

		assertFalse(actual);
	}

	@Test
	void id_that_does_not_exist_is_invalid() {
		boolean actual = withdrawValidator.validate("withdraw 11111111 200");

		assertFalse(actual);
	}

	@Test
	void id_with_letters_invalid() {
		boolean actual = withdrawValidator.validate("withdraw ABCDEFGH 200");

		assertFalse(actual);
	}

	@Test
	void amount_with_letters_invalid() {
		boolean actual = withdrawValidator.validate("withdraw 12345678 five");

		assertFalse(actual);
	}

}
