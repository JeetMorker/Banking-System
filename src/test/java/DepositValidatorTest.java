import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DepositValidatorTest {
	DepositValidator depositValidator;
	Bank bank;
	CheckingAccount checking;
	SavingsAccount savings;
	CDAccount cd;

	@BeforeEach
	void setUp() {
		bank = new Bank();
		depositValidator = new DepositValidator(bank);
		checking = new CheckingAccount("12345678", 2.5);
		savings = new SavingsAccount("87654321", 1.2);
		cd = new CDAccount("44445555", 5, 5000);
		bank.addAccount(checking);
		bank.addAccount(savings);
		bank.addAccount(cd);

	}

	@Test
	void valid_command() {
		boolean actual = depositValidator.validate("deposit 12345678 500");

		assertTrue(actual);
	}

	@Test
	void account_id_not_in_bank_is_invalid() {
		boolean actual = depositValidator.validate("deposit 33333333 500");

		assertFalse(actual);
	}

	@Test
	void checking_deposit_amount_1000_is_valid() {
		boolean actual = depositValidator.validate("deposit 12345678 1000");

		assertTrue(actual);
	}

	@Test
	void checking_deposit_amount_above_1000_is_invalid() {
		boolean actual = depositValidator.validate("deposit 12345678 1001");

		assertFalse(actual);
	}

	@Test
	void checking_deposit_amount_0_is_valid() {
		boolean actual = depositValidator.validate("deposit 12345678 0");

		assertTrue(actual);
	}

	@Test
	void checking_deposit_amount_negative_is_invalid() {
		boolean actual = depositValidator.validate("deposit 12345678 -100");

		assertFalse(actual);
	}

	@Test
	void checking_deposit_amount_with_letters_is_invalid() {
		boolean actual = depositValidator.validate("deposit 12345678 five");

		assertFalse(actual);
	}

	@Test
	void valid_savings_deposit() {
		boolean actual = depositValidator.validate("deposit 87654321 500");

		assertTrue(actual);
	}

	@Test
	void savings_deposit_amount_2500_is_valid() {
		boolean actual = depositValidator.validate("deposit 87654321 2500");

		assertTrue(actual);
	}

	@Test
	void savings_deposit_amount_above_2500_is_invalid() {
		boolean actual = depositValidator.validate("deposit 87654321 2501");

		assertFalse(actual);
	}

	@Test
	void savings_deposit_amount_0_is_valid() {
		boolean actual = depositValidator.validate("deposit 87654321 0");

		assertTrue(actual);
	}

	@Test
	void savings_deposit_amount_negative_is_invalid() {
		boolean actual = depositValidator.validate("deposit 87654321 -100");

		assertFalse(actual);
	}

	@Test
	void savings_deposit_amount_with_letters_invalid() {
		boolean actual = depositValidator.validate("deposit 87654321 ten");

		assertFalse(actual);
	}

	@Test
	void cd_deposit_is_invalid() {
		boolean actual = depositValidator.validate("deposit 44445555 500");

		assertFalse(actual);
	}

	@Test
	void deposit_in_capitals_is_valid() {
		boolean actual = depositValidator.validate("DEPOSIT 12345678 500");

		assertTrue(actual);
	}

	@Test
	void deposit_lowercase_and_capitals_is_valid() {
		boolean actual = depositValidator.validate("DePosIt 87654321 500");

		assertTrue(actual);
	}

	@Test
	void deposit_spelled_wrong_is_invalid() {
		boolean actual = depositValidator.validate("dep 87654321 500");

		assertFalse(actual);
	}

	@Test
	void deposit_with_numbers_is_invalid() {
		boolean actual = depositValidator.validate("dep0sit 87654321 500");

		assertFalse(actual);
	}

	@Test
	void id_with_letters_invalid() {
		boolean actual = depositValidator.validate("deposit AAAAAAAA 500");

		assertFalse(actual);
	}

	@Test
	void id_too_long_invalid() {
		boolean actual = depositValidator.validate("deposit 123456789 500");

		assertFalse(actual);
	}

	@Test
	void id_missing_is_invalid() {
		boolean actual = depositValidator.validate("deposit 500");

		assertFalse(actual);
	}

	@Test
	void amount_missing_is_invalid() {
		boolean actual = depositValidator.validate("deposit 12345678");

		assertFalse(actual);
	}

	@Test
	void deposit_missing_is_invalid() {
		boolean actual = depositValidator.validate("12345678 500");

		assertFalse(actual);
	}

	@Test
	void deposit_command_in_wrong_order_is_invalid() {
		boolean actual = depositValidator.validate("12345678 deposit 500");

		assertFalse(actual);
	}

}
