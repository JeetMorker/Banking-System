package banking;

import java.util.ArrayList;
import java.util.List;

public class CommandStoring {
	private List<String> invalidCommands;

	CommandStoring() {
		invalidCommands = new ArrayList<>();
	}

	public void addInvalidCommand(String command) {
		invalidCommands.add(command);
	}

	public List<String> getInvalidCommands() {
		return invalidCommands;
	}

}
