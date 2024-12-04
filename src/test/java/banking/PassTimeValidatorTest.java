package banking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PassTimeValidatorTest {
	PassTimeValidator passTimeValidator;
	Bank bank;

	@BeforeEach
	void setUp() {
		bank = new Bank();
		passTimeValidator = new PassTimeValidator(bank);
	}

	@Test
	void valid_pass_time_command() {
		boolean actual = passTimeValidator.validate("pass 10");

		assertTrue(actual);
	}

	@Test
	void month_below_1_is_invalid() {
		boolean actual = passTimeValidator.validate("pass 0");

		assertFalse(actual);
	}

	@Test
	void month_value_1_is_valid() {
		boolean actual = passTimeValidator.validate("pass 1");

		assertTrue(actual);
	}

	@Test
	void month_above_60_is_invalid() {
		boolean actual = passTimeValidator.validate("pass 61");

		assertFalse(actual);
	}

	@Test
	void month_value_60_is_valid() {
		boolean actual = passTimeValidator.validate("pass 60");

		assertTrue(actual);
	}

	@Test
	void negative_month_is_invalid() {
		boolean actual = passTimeValidator.validate("pass -5");

		assertFalse(actual);
	}

	@Test
	void decimal_month_is_invalid() {
		boolean actual = passTimeValidator.validate("pass 2.5");

		assertFalse(actual);
	}

	@Test
	void month_with_letters_is_invalid() {
		boolean actual = passTimeValidator.validate("pass five");

		assertFalse(actual);
	}

	@Test
	void pass_in_capitals_is_valid() {
		boolean actual = passTimeValidator.validate("PASS 10");

		assertTrue(actual);
	}

	@Test
	void pass_spelled_wrong_is_invalid() {
		boolean actual = passTimeValidator.validate("ps 10");

		assertFalse(actual);
	}

	@Test
	void month_missing_is_invalid() {
		boolean actual = passTimeValidator.validate("pass");

		assertFalse(actual);
	}

	@Test
	void pass_missing_is_invalid() {
		boolean actual = passTimeValidator.validate("10");

		assertFalse(actual);
	}

}
