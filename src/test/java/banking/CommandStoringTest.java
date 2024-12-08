package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandStoringTest {
	public static final String INVALID_COMMAND = "crate checking 12345678 2";
	public static final String ANOTHER_INVALID_COMMAND = "depo 50 12345678";
	CommandStoring commandStoring;
	Bank bank;
	CheckingAccount checkingAccount;

	@BeforeEach
	void setUp() {
		bank = new Bank();
		commandStoring = new CommandStoring(bank);
		checkingAccount = new CheckingAccount("12345678", 2.2);
		bank.addAccount(checkingAccount);
	}

	@Test
	void correctly_stores_one_invalid_command() {
		commandStoring.addInvalidCommand(INVALID_COMMAND);
		String actual = commandStoring.getInvalidCommands().get(0);
		int size = commandStoring.getInvalidCommands().size();

		assertEquals(INVALID_COMMAND, actual);
		assertEquals(1, size);

	}

	@Test
	void correctly_stores_multiple_invalid_commands() {
		commandStoring.addInvalidCommand(INVALID_COMMAND);
		commandStoring.addInvalidCommand(ANOTHER_INVALID_COMMAND);
		String command1 = commandStoring.getInvalidCommands().get(0);
		String command2 = commandStoring.getInvalidCommands().get(1);
		int size = commandStoring.getInvalidCommands().size();

		assertEquals(INVALID_COMMAND, command1);
		assertEquals(ANOTHER_INVALID_COMMAND, command2);
		assertEquals(2, size);
	}

	@Test
	void invalid_command_list_initially_empty() {
		int actual = commandStoring.getInvalidCommands().size();

		assertEquals(0, actual);
	}

	@Test
	void specific_command_is_stored() {
		commandStoring.addInvalidCommand(INVALID_COMMAND);

		assertTrue(commandStoring.getInvalidCommands().contains(INVALID_COMMAND));
	}

	@Test
	void tester() {
		CheckingAccount check = new CheckingAccount("56781234", 2.4);
		bank.addAccount(check);

		List<String> actual = commandStoring.getOutput();
		assertEquals("Checking 12345678 0.00 2.20", actual.get(0));
		assertEquals("Checking 56781234 0.00 2.40", actual.get(1));
	}

}
