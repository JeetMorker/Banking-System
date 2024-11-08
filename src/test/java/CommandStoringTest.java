import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandStoringTest {
	public static final String INVALID_COMMAND = "crate checking 12345678 2";
	public static final String ANOTHER_INVALID_COMMAND = "depo 50 12345678";
	CommandStoring commandStoring;

	@BeforeEach
	void setUp() {
		commandStoring = new CommandStoring();
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
}
