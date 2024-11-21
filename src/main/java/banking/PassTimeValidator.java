package banking;

public class PassTimeValidator {
	private Bank bank;

	public PassTimeValidator(Bank bank) {
		this.bank = bank;
	}

	public boolean validate(String command) {
		String[] parsedCommand = command.split(" ");
		return checkMonthNum(parsedCommand);
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
