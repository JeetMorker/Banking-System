package banking;

public class CreateValidator {

	private Bank bank;

	public CreateValidator(Bank bank) {
		this.bank = bank;
	}

	public boolean validate(String command) {
		String[] parsedCommand = command.split(" ");
		if (parsedCommand[0].equalsIgnoreCase("create")) {
			return checkAccType(parsedCommand);
		}
		return false;
	}

	public boolean validID(String accID) {
		if (idIsNum(accID) && (accID.length() == 8)) {
			return !bank.checkExistingID(accID);
		}
		return false;
	}

	public boolean validAPR(String apr) {
		if (isDouble(apr)) {
			double aprVal = Double.parseDouble(apr);
			return aprVal >= 0 && aprVal <= 10;
		}
		return false;
	}

	public boolean checkAccType(String[] parsedCommand) {
		if (parsedCommand[1].equalsIgnoreCase("savings") || parsedCommand[1].equalsIgnoreCase("checking")) {
			return checkSavingsAndChecking(parsedCommand);
		} else if (parsedCommand[1].equalsIgnoreCase("cd")) {
			return checkCD(parsedCommand);
		}
		return false;
	}

	public boolean checkCommonFeatures(String[] parsedCommand) {
		return validID(parsedCommand[2]) && validAPR(parsedCommand[3]);
	}

	public boolean checkAmountCD(String[] parsedCommand) {
		if (isDouble(parsedCommand[4])) {
			double amount = Double.parseDouble((parsedCommand[4]));
			return amount >= 1000 && amount <= 10000;
		}
		return false;
	}

	public boolean checkSavingsAndChecking(String[] parsedCommand) {
		if (parsedCommand.length == 4) {
			return checkCommonFeatures(parsedCommand);
		} else {
			return false;
		}
	}

	public boolean checkCD(String[] parsedCommand) {
		if (parsedCommand.length == 5 && checkAmountCD(parsedCommand)) {
			return checkCommonFeatures(parsedCommand);
		} else {
			return false;
		}
	}

	private boolean idIsNum(String accID) {
		for (char num : accID.toCharArray()) {
			if (!Character.isDigit(num)) {
				return false;
			}
		}
		return true;
	}

	private boolean isDouble(String num) {
		try {
			Double.parseDouble(num);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
