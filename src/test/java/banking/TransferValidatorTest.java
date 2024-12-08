package banking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransferValidatorTest {
	TransferValidator transferValidator;
	Bank bank;
	CheckingAccount checking;
	SavingsAccount savings;
	CDAccount cd;
	TransferProcessor transferProcessor;

	@BeforeEach
	void setUp() {
		bank = new Bank();
		transferValidator = new TransferValidator(bank);
		transferProcessor = new TransferProcessor(bank);
		checking = new CheckingAccount("12345678", 2.5);
		savings = new SavingsAccount("87654321", 2.0);
		cd = new CDAccount("56781234", 1.0, 500);
		bank.addAccount(checking);
		bank.addAccount(savings);
		bank.addAccount(cd);
	}

	@Test
	void valid_transfer_command() {
		boolean actual = transferValidator.validate("transfer 12345678 87654321 200");

		assertTrue(actual);
	}

	@Test
	void invalid_amount() {
		boolean actual = transferValidator.validate("transfer 12345678 87654321 ok");

		assertFalse(actual);
	}

	@Test
	void cannot_transfer_with_cd() {
		boolean actual = transferValidator.validate("transfer 56781234 12345678 200");

		assertFalse(actual);
	}

	@Test
	void cannot_transfer_to_acd() {
		boolean actual = transferValidator.validate("transfer 12345678 56781234 300");

		assertFalse(actual);
	}

	@Test
	void transfer_in_capitals_is_valid() {
		boolean actual = transferValidator.validate("TRANSFER 87654321 12345678 200");

		assertTrue(actual);
	}

	@Test
	void nonexistent_account_invalid() {
		boolean actual = transferValidator.validate("transfer 11111111 12345678 200");

		assertFalse(actual);
	}

	@Test
	void can_transfer_between_two_checking() {
		CheckingAccount check = new CheckingAccount("22222222", 2.0);
		bank.addAccount(check);

		boolean actual = transferValidator.validate("transfer 22222222 12345678 200");

		assertTrue(actual);

	}

	@Test
	void can_transfer_from_checking_to_savings() {
		boolean actual = transferValidator.validate("transfer 12345678 87654321 400");

		assertTrue(actual);
	}

	@Test
	void can_transfer_from_savings_to_checking() {
		boolean actual = transferValidator.validate("transfer 87654321 12345678 200");

		assertTrue(actual);
	}

	@Test
	void can_transfer_from_savings_to_savings() {
		SavingsAccount save = new SavingsAccount("22222222", 2);
		bank.addAccount(save);
		boolean actual = transferValidator.validate("transfer 22222222 87654321 200");

		assertTrue(actual);
	}

	@Test
	void cannot_transfer_over_400_from_checking() {
		boolean actual = transferValidator.validate("Transfer 12345678 87654321 500");

		assertFalse(actual);
	}

	@Test
	void cannot_transfer_over_1000_from_savings() {
		boolean actual = transferValidator.validate("transfer 87654321 12345678 2000");

		assertFalse(actual);
	}

	@Test
	void cannot_transfer_to_cd() {
		boolean actual = transferValidator.validate("transfer 12345678 56781234 200");

		assertFalse(actual);
	}

	@Test
	void transfer_missing_invalid() {
		boolean actual = transferValidator.validate("12345678 87654321 200");

		assertFalse(actual);
	}

	@Test
	void id_missing_invalid() {
		boolean actual = transferValidator.validate("transfer 200");

		assertFalse(actual);
	}

	@Test
	void amount_missing_invalid() {
		boolean actual = transferValidator.validate("transfer 12345678 87654321");

		assertFalse(actual);
	}

	@Test
	void negative_amount_invalid() {
		boolean actual = transferValidator.validate("transfer 12345678 87654321 -100");

		assertFalse(actual);
	}

	@Test
	void extra_argument_invalid() {
		boolean actual = transferValidator.validate("transfer 12345678 87654321 200 8");

		assertFalse(actual);
	}

	@Test
	void can_transfer_0() {
		boolean actual = transferValidator.validate("transfer 12345678 87654321 0");

		assertTrue(actual);
	}

	@Test
	void cannot_transfer_from_savings_more_than_once_per_month() {
		transferProcessor.process("transfer 87654321 12345678 200");
		boolean actual = transferValidator.validate("transfer 87654321 12345678 100");

		assertFalse(actual);
	}

	@Test
	void cannot_transfer_to_self() {
		boolean actual = transferValidator.validate("transfer 12345678 12345678 200");

		assertFalse(actual);
	}

	@Test
	void cannot_transfer_to_acc_that_doesnt_exist() {
		boolean actual = transferValidator.validate("transfer 12345678 11111111 200");

		assertFalse(actual);
	}

	@Test
	void only_transfer_invalid() {
		boolean actual = transferValidator.validate("transfer");

		assertFalse(actual);
	}

}
