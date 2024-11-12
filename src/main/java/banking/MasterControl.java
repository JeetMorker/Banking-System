package banking;

import java.util.List;

public class MasterControl {
	private AllCommandValidator commandValidator;
	private AllCommandProcessor commandProcessor;
	private CommandStoring commandStorage;

	public MasterControl(AllCommandValidator commandValidator, AllCommandProcessor commandProcessor,
			CommandStoring commandStorage) {
		this.commandValidator = commandValidator;
		this.commandProcessor = commandProcessor;
		this.commandStorage = commandStorage;
	}

	public List<String> start(List<String> input) {
		for (String command : input) {
			if (commandValidator.validate(command)) {
				commandProcessor.process(command);
			} else {
				commandStorage.addInvalidCommand(command);
			}
		}
		return commandStorage.getInvalidCommands();
	}
}
