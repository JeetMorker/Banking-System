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

	@Test
	void valid_savings() {
		boolean actual = createValidator.validate("create savings 12345678 2.5");

		assertTrue(actual);
	}

	@Test
	void capital_letter_savings_is_valid() {
		boolean actual = createValidator.validate("create SAVINGS 12345678 2.5");

		assertTrue(actual);
	}

	@Test
	void savings_spelling_invalid() {
		boolean actual = createValidator.validate("create sav 12345678 2.5");

		assertFalse(actual);
	}

	@Test
	void number_in_savings_invalid() {
		boolean actual = createValidator.validate("create s2ving3 12345678 2.5");

		assertFalse(actual);
	}

	@Test
	void savings_no_apr_is_invalid() {
		boolean actual = createValidator.validate("create savings 12345678");

		assertFalse(actual);
	}

	@Test
	void savings_no_id_is_invalid() {
		boolean actual = createValidator.validate("create savings 1.2");

		assertFalse(actual);
	}

	@Test
	void savings_with_initial_amount_is_invalid() {
		boolean actual = createValidator.validate("create savings 12345678 1.2 500");

		assertFalse(actual);
	}

	@Test
	void valid_checking() {
		boolean actual = createValidator.validate("create checking 12345678 3");

		assertTrue(actual);
	}

	@Test
	void capital_letter_checking_is_valid() {
		boolean actual = createValidator.validate("create CHECKING 12345678 3");

		assertTrue(actual);
	}

	@Test
	void checking_spelling_invalid() {
		boolean actual = createValidator.validate("create check 12345678 3");

		assertFalse(actual);
	}

	@Test
	void number_in_checking_invalid() {
		boolean actual = createValidator.validate("create ch3cking 12345678 3");

		assertFalse(actual);
	}

	@Test
	void checking_no_apr_is_invalid() {
		boolean actual = createValidator.validate("create checking 12345678");

		assertFalse(actual);
	}

	@Test
	void checking_no_id_is_invalid() {
		boolean actual = createValidator.validate("create checking 2.5");

		assertFalse(actual);
	}

	@Test
	void checking_with_initial_amount_is_invalid() {
		boolean actual = createValidator.validate("create checking 12345678 3 500");

		assertFalse(actual);
	}

	@Test
	void valid_cd() {
		boolean actual = createValidator.validate("create cd 12345678 2.5 5000");

		assertTrue(actual);
	}

	@Test
	void capital_letter_cd_is_valid() {
		boolean actual = createValidator.validate("create CD 12345678 2.5 5000");

		assertTrue(actual);
	}

	@Test
	void cd_spelling_invalid() {
		boolean actual = createValidator.validate("create c 12345678 2.5 5000");

		assertFalse(actual);
	}

	@Test
	void number_in_cd_invalid() {
		boolean actual = createValidator.validate("create c1 12345678 2.5 5000");

		assertFalse(actual);
	}

	@Test
	void cd_no_apr_is_invalid() {
		boolean actual = createValidator.validate("create cd 12345678 5000");

		assertFalse(actual);
	}

	@Test
	void cd_no_id_is_invalid() {
		boolean actual = createValidator.validate("create cd 1.2 5000");

		assertFalse(actual);
	}

	@Test
	void cd_without_initial_amount_is_invalid() {
		boolean actual = createValidator.validate("create cd 12345678 1.2");

		assertFalse(actual);
	}

	@Test
	void cd_amount_1000_is_valid() {
		boolean actual = createValidator.validate("create cd 12345678 1.2 1000");

		assertTrue(actual);
	}

	@Test
	void cd_amount_10000_is_valid() {
		boolean actual = createValidator.validate("create cd 12345678 1.2 10000");

		assertTrue(actual);
	}

	@Test
	void cd_amount_below_1000_invalid() {
		boolean actual = createValidator.validate("create cd 12345678 1.2 999");

		assertFalse(actual);
	}

	@Test
	void cd_amount_above_10000_invalid() {
		boolean actual = createValidator.validate("create cd 12345678 1.2 10001");

		assertFalse(actual);
	}

	@Test
	void cd_amount_negative_invalid() {
		boolean actual = createValidator.validate("create cd 12345678 1.2 -5000");

		assertFalse(actual);
	}

	@Test
	void cd_amount_with_letters_invalid() {
		boolean actual = createValidator.validate("create cd 12345678 1.2 five");

		assertFalse(actual);
	}

	@Test
	void account_type_missing_invalid() {
		boolean actual = createValidator.validate("create 12345678 2.5");

		assertFalse(actual);
	}

	@Test
	void create_in_capitals_is_valid() {
		boolean actual = createValidator.validate("CREATE savings 12345678 2.5");

		assertTrue(actual);
	}

	@Test
	void create_spelling_invalid() {
		boolean actual = createValidator.validate("crte savings 12345678 2.5");

		assertFalse(actual);
	}

	@Test
	void create_missing_invalid() {
		boolean actual = createValidator.validate("savings 12345678 2.5");

		assertFalse(actual);
	}

	@Test
	void acc_with_different_id_is_valid() {
		SavingsAccount savingsAccount;
		savingsAccount = new SavingsAccount("12345678", 1.5);
		bank.addAccount(savingsAccount);
		boolean actual = createValidator.validate("create checking 87654321 2.5");

		assertTrue(actual);
	}

	@Test
	void command_in_wrong_order_is_invalid() {
		boolean actual = createValidator.validate("checking create 12345678 2.5");

		assertFalse(actual);
	}

}
