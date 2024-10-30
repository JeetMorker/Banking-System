import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CreateValidatorTest {
	CreateValidator createValidator;
	Bank bank;

	@BeforeEach
	void setUp() {
		bank = new Bank();
		createValidator = new CreateValidator(bank);
	}

	@Test
	void valid_command() {
		boolean actual = createValidator.validate("create savings 12345678 1.5");

		assertTrue(actual);
	}

	@Test
	void duplicate_acc_id_is_invalid() {
		SavingsAccount savingsAccount;
		savingsAccount = new SavingsAccount("12345678", 1.5);
		bank.addAccount(savingsAccount);
		boolean actual = createValidator.validate("create checking 12345678 2.5");

		assertFalse(actual);
	}

	@Test
	void id_above_eight_digits_invalid() {
		boolean actual = createValidator.validate("create checking 123456789 2.5");

		assertFalse(actual);
	}

	@Test
	void id_with_letters_invalid() {
		boolean actual = createValidator.validate("create checking 12345ABC 2.5");

		assertFalse(actual);
	}

	@Test
	void id_less_than_eight_digits_invalid() {
		boolean actual = createValidator.validate("create checking 1234567 2.5");

		assertFalse(actual);
	}

	@Test
	void apr_above_10_invalid() {
		boolean actual = createValidator.validate("create checking 12345678 10.1");

		assertFalse(actual);
	}

	@Test
	void apr_of_0_is_valid() {
		boolean actual = createValidator.validate("create checking 12345678 0");

		assertTrue(actual);
	}

	@Test
	void apr_of_10_is_valid() {
		boolean actual = createValidator.validate("create checking 12345678 10");

		assertTrue(actual);
	}

	@Test
	void decimal_apr_is_valid() {
		boolean actual = createValidator.validate("create checking 12345678 5.5");

		assertTrue(actual);
	}

	@Test
	void negative_apr_is_invalid() {
		boolean actual = createValidator.validate("create checking 12345678 -5");

		assertFalse(actual);
	}

	@Test
	void apr_cant_have_letters() {
		boolean actual = createValidator.validate("create checking 12345678 five");

		assertFalse(actual);
	}

}
