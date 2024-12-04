package banking;

public class WithdrawValidator {
	private final Bank bank;

	public WithdrawValidator(Bank bank) {
		this.bank = bank;
	}

	public boolean validate(String command) {
		String[] parsedCommand = command.split(" ");
		if (parsedCommand.length == 3 && parsedCommand[0].equalsIgnoreCase("withdraw")) {
			return checkValidWithdraw(parsedCommand);
		}
		return false;
	}

	public boolean checkValidWithdraw(String[] parsedCommand) {
		double amount;
		try {
			amount = Double.parseDouble(parsedCommand[2]);
		} catch (NumberFormatException e) {
			return false;
		}
		if (bank.checkExistingID(parsedCommand[1])) {
			return bank.checkValidWithdraw(parsedCommand[1], amount);
		}
		return false;
	}

}
