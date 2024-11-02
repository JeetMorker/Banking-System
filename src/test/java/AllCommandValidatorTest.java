import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AllCommandValidatorTest {
	AllCommandValidator allCommandValidator;
	Bank bank;
	SavingsAccount savingsAccount;

	@BeforeEach
	void setUp() {
		bank = new Bank();
		allCommandValidator = new AllCommandValidator(bank);
		savingsAccount = new SavingsAccount("87654321", 2.5);
		bank.addAccount(savingsAccount);
	}

	@Test
	void valid_create_command() {
		boolean actual = allCommandValidator.validate("create savings 12345678 2.5");

		assertTrue(actual);
	}

	@Test
	void create_spelled_wrong_is_invalid() {
		boolean actual = allCommandValidator.validate("crte savings 12345678 2.5");

		assertFalse(actual);
	}

	@Test
	void create_in_capitals_is_valid() {
		boolean actual = allCommandValidator.validate("CREATE savings 12345678 2.5");

		assertTrue(actual);
	}

	@Test
	void valid_deposit_command() {
		boolean actual = allCommandValidator.validate("deposit 87654321 500");

		assertTrue(actual);
	}

	@Test
	void deposit_spelled_wrong_is_invalid() {
		boolean actual = allCommandValidator.validate("dep 87654321 500");

		assertFalse(actual);
	}

	@Test
	void deposit_in_capitals_is_valid() {
		boolean actual = allCommandValidator.validate("DEPOSIT 87654321 500");

		assertTrue(actual);
	}

	@Test
	void command_missing_is_invalid() {
		boolean actual = allCommandValidator.validate("12345678 2.3");

		assertFalse(actual);
	}

	@Test
	void empty_command_is_invalid() {
		boolean actual = allCommandValidator.validate("");

		assertFalse(actual);
	}
}
