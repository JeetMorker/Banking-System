package banking;

public class AllCommandValidator {

	private final CreateValidator createValidator;
	private final DepositValidator depositValidator;
	private final WithdrawValidator withdrawValidator;
	private final PassTimeValidator passTimeValidator;
	private final TransferValidator transferValidator;

	public AllCommandValidator(Bank bank) {
		this.createValidator = new CreateValidator(bank);
		this.depositValidator = new DepositValidator(bank);
		this.withdrawValidator = new WithdrawValidator(bank);
		this.passTimeValidator = new PassTimeValidator(bank);
		this.transferValidator = new TransferValidator(bank);
	}

	public boolean validate(String command) {
		String[] parsedCommand = command.split(" ");
		if (parsedCommand[0].equalsIgnoreCase("create")) {
			return createValidator.validate(command);
		} else if (parsedCommand[0].equalsIgnoreCase("deposit")) {
			return depositValidator.validate(command);
		} else if (parsedCommand[0].equalsIgnoreCase("withdraw")) {
			return withdrawValidator.validate(command);
		} else if (parsedCommand[0].equalsIgnoreCase("pass")) {
			return passTimeValidator.validate(command);
		} else if (parsedCommand[0].equalsIgnoreCase("transfer")) {
			return transferValidator.validate(command);
		}
		return false;
	}
}
