package banking;

public class PassTimeValidator {
	private Bank bank;

	public PassTimeValidator(Bank bank) {
		this.bank = bank;
	}

	public boolean validate(String command) {
		String[] parsedCommand = command.split(" ");
		if (parsedCommand[0].equalsIgnoreCase("pass") && parsedCommand.length == 2) {
			return checkMonthNum(parsedCommand);
		}
		return false;
	}

	public boolean checkMonthNum(String[] parsedCommand) {
		int month;
		try {
			month = Integer.parseInt(parsedCommand[1]);
		} catch (NumberFormatException e) {
			return false;
		}
		return month >= 1 && month <= 60;
	}
}
