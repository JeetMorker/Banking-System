package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MasterControlTest {

	MasterControl masterControl;
	List<String> input;

	@BeforeEach
	void setUp() {
		input = new ArrayList<>();
		Bank bank = new Bank();
		masterControl = new MasterControl(new AllCommandValidator(bank), new AllCommandProcessor(bank),
				new CommandStoring(bank));

	}

	private void assertSingleCommand(String command, List<String> actual) {
		assertEquals(1, actual.size());
		assertEquals(command, actual.get(0));
	}

	@Test
	void typo_in_create_command_is_invalid() {
		input.add("creat checking 12345678 1.0");

		List<String> actual = masterControl.start(input);

		assertSingleCommand("creat checking 12345678 1.0", actual);
	}

	@Test
	void typo_in_deposit_command_is_invalid() {
		input.add("depositt 12345678 100");

		List<String> actual = masterControl.start(input);

		assertSingleCommand("depositt 12345678 100", actual);
	}

	@Test
	void two_typo_commands_both_invalid() {
		input.add("creat checking 12345678 1.0");
		input.add("depositt 12345678 100");

		List<String> actual = masterControl.start(input);

		assertEquals(2, actual.size());
		assertEquals("creat checking 12345678 1.0", actual.get(0));
		assertEquals("depositt 12345678 100", actual.get(1));
	}

	@Test
	void sample_make_sure_this_passes_unchanged_or_you_will_fail() {
		input.add("Create savings 12345678 0.6");
		input.add("Deposit 12345678 700");
		input.add("Deposit 12345678 5000");
		input.add("creAte cHecKing 98765432 0.01");
		input.add("Deposit 98765432 300");
		input.add("Transfer 98765432 12345678 300");
		input.add("Pass 1");
		input.add("Create cd 23456789 1.2 2000");
		List<String> actual = masterControl.start(input);

		assertEquals(5, actual.size());
		assertEquals("Savings 12345678 1000.50 0.60", actual.get(0));
		assertEquals("Deposit 12345678 700", actual.get(1));
		assertEquals("Transfer 98765432 12345678 300", actual.get(2));
		assertEquals("Cd 23456789 2000.00 1.20", actual.get(3));
		assertEquals("Deposit 12345678 5000", actual.get(4));
	}

	@Test
	void output_tests() {
		input.add("create checking 12345678 2.0");
		input.add("Deposit 12345678 800");
		input.add("create SAvings 87654321 3.0");
		input.add("deposit 87654321 900");
		input.add("transfer 12345678 87654321 200");
		input.add("transfer 12345678 11111111 500");
		input.add("create cd 56781234 0.1 5000");
		input.add("transfer 56781234 12345678 200");
		input.add("pass 2");

		List<String> actual = masterControl.start(input);

		assertEquals(9, actual.size());
		assertEquals("Checking 12345678 602.00 2.00", actual.get(0));
		assertEquals("Deposit 12345678 800", actual.get(1));
		assertEquals("transfer 12345678 87654321 200", actual.get(2));
		assertEquals("Savings 87654321 1105.50 3.00", actual.get(3));
		assertEquals("deposit 87654321 900", actual.get(4));
		assertEquals("transfer 12345678 87654321 200", actual.get(5));
		assertEquals("Cd 56781234 5003.33 0.10", actual.get(6));
		assertEquals("transfer 12345678 11111111 500", actual.get(7));
		assertEquals("transfer 56781234 12345678 200", actual.get(8));

	}

	@Test
	void pass_time_tests() {
		input.add("create checking 12345678 2.0");
		input.add("create savings 87654321 3.0");
		input.add("create cd 56781234 2.1 2000");
		input.add("deposit 87654321 1000");
		input.add("pass 1");
		input.add("crte checking 99999999 3.0");
		input.add("create  checking 18273645 4.0");
		input.add("pass 61");
		input.add("transfer 87654321 87654321 200");

		List<String> actual = masterControl.start(input);

		assertEquals(7, actual.size());
		assertEquals("Savings 87654321 1002.50 3.00", actual.get(0));
		assertEquals("Cd 56781234 2014.03 2.10", actual.get(2));
		assertEquals("crte checking 99999999 3.0", actual.get(3));
		assertEquals("create  checking 18273645 4.0", actual.get(4));
		assertEquals("pass 61", actual.get(5));
		assertEquals("transfer 87654321 87654321 200", actual.get(6));
	}

}
