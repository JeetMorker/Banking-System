package banking;

public class AllCommandProcessor {
	Bank bank;
	CreateProcessor createProcessor;
	DepositProcessor depositProcessor;
	WithdrawProcessor withdrawProcessor;
	PassTimeProcessor passTimeProcessor;
	TransferProcessor transferProcessor;

	public AllCommandProcessor(Bank bank) {
		this.transferProcessor = new TransferProcessor(bank);
		this.withdrawProcessor = new WithdrawProcessor(bank);
		this.passTimeProcessor = new PassTimeProcessor(bank);
		this.createProcessor = new CreateProcessor(bank);
		this.depositProcessor = new DepositProcessor(bank);
	}

	public void process(String command) {
		String[] parsedCommand = command.split(" ");
		if (parsedCommand[0].equalsIgnoreCase("create")) {
			createProcessor.process(command);
		} else if (parsedCommand[0].equalsIgnoreCase("deposit")) {
			depositProcessor.process(command);
		} else if (parsedCommand[0].equalsIgnoreCase("withdraw")) {
			withdrawProcessor.process(command);
		} else if (parsedCommand[0].equalsIgnoreCase("pass")) {
			passTimeProcessor.process(command);
		} else if (parsedCommand[0].equalsIgnoreCase("transfer")) {
			transferProcessor.process(command);

		}

	}

}
