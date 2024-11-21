public class AllCommandProcessor {
	Bank bank;
	CreateProcessor createProcessor;
	DepositProcessor depositProcessor;

	public AllCommandProcessor(Bank bank) {
		this.createProcessor = new CreateProcessor(bank);
		this.depositProcessor = new DepositProcessor(bank);
	}

	public void process(String command) {
		String[] parsedCommand = command.split(" ");
		if (parsedCommand[0].equalsIgnoreCase("create")) {
			createProcessor.process(command);
		} else if (parsedCommand[0].equalsIgnoreCase("deposit")) {
			depositProcessor.process(command);
		}

	}

}
