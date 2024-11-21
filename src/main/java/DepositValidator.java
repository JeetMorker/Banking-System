public class DepositValidator {
	private Bank bank;

	public DepositValidator(Bank bank) {
		this.bank = bank;
	}

	public boolean validate(String command) {
		String[] parsedCommand = command.split(" ");
		if (parsedCommand[0].equalsIgnoreCase("deposit")) {
			return checkAllFeatures(parsedCommand);
		}
		return false;
	}

	public boolean checkAllFeatures(String[] parsedCommand) {
		if (parsedCommand.length == 3) {
			return checkValidAccExistsByID(parsedCommand) && checkValidDepositAmount(parsedCommand);
		}
		return false;
	}

	public boolean checkValidAccExistsByID(String[] parsedCommand) {
		String accID = parsedCommand[1];
		return bank.checkExistingID(accID);
	}

	public boolean checkValidDepositAmount(String[] parsedCommand) {
		if (isDouble(parsedCommand[2])) {
			double amount = Double.parseDouble(parsedCommand[2]);
			return bank.checkMaxDesposit(parsedCommand[1], amount);
		}
		return false;
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
