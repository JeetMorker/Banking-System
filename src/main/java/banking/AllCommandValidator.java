package banking;

public class AllCommandValidator {

	private CreateValidator createValidator;
	private DepositValidator depositValidator;

	public AllCommandValidator(Bank bank) {
		this.createValidator = new CreateValidator(bank);
		this.depositValidator = new DepositValidator(bank);
	}

	public boolean validate(String command) {
		String[] parsedCommand = command.split(" ");
		if (parsedCommand[0].equalsIgnoreCase("create")) {

			return createValidator.validate(command);
		} else if (parsedCommand[0].equalsIgnoreCase("deposit")) {
			return depositValidator.validate(command);
		}
		return false;
	}
}
