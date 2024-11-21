package banking;

public class WithdrawValidator {
	private Bank bank;

	public WithdrawValidator(Bank bank) {
		this.bank = bank;
	}

	public boolean validate(String command) {
		String[] parsedCommand = command.split(" ");
		return checkIfCorrectAmount(parsedCommand);
	}

	public boolean checkIfCorrectAmount(String[] parsedCommand) {
		double amount;
		try {
			amount = Double.parseDouble(parsedCommand[2]);
		} catch (NumberFormatException e) {
			return false;
		}
		return bank.checkWithdrawAmount(parsedCommand[1], amount);
	}

}
