package banking;

public class TransferValidator {
	private final WithdrawValidator withdrawValidator;
	private final DepositValidator depositValidator;
	private final Bank bank;

	public TransferValidator(Bank bank) {
		this.bank = bank;
		withdrawValidator = new WithdrawValidator(bank);
		depositValidator = new DepositValidator(bank);
	}

	public boolean validate(String command) {
		String[] parsedCommand = command.split(" ");
		if (parsedCommand[0].equalsIgnoreCase("transfer") && parsedCommand.length == 4
				&& !(parsedCommand[1].equalsIgnoreCase(parsedCommand[2]))) {
			return checkAllFeatures(parsedCommand);
		}
		return false;
	}

	public boolean checkDepositAndWithdraw(String[] parsedCommand) {
		String withdrawCommand = "withdraw " + parsedCommand[1] + " " + parsedCommand[3];
		String depositCommand = "deposit " + parsedCommand[2] + " " + parsedCommand[3];
		return withdrawValidator.validate(withdrawCommand) && depositValidator.validate(depositCommand);

	}

	public boolean checkIfAccCanTransfer(String[] parsedCommand) {
		if (bank.checkExistingID(parsedCommand[1]) && bank.checkExistingID(parsedCommand[2])) {
			return bank.canTransfer(parsedCommand[1]) && bank.canTransfer(parsedCommand[2]);
		}
		return false;
	}

	public boolean checkAllFeatures(String[] parsedCommand) {
		if (checkIfAccCanTransfer(parsedCommand)) {
			return checkDepositAndWithdraw(parsedCommand);
		}
		return false;

	}

}
