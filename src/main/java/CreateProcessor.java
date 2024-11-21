public class CreateProcessor {

	private Bank bank;

	public CreateProcessor(Bank bank) {
		this.bank = bank;
	}

	public void process(String command) {
		String[] parsedCommand = command.split(" ");
		if (parsedCommand[1].equalsIgnoreCase("checking")) {
			createChecking(parsedCommand);
		} else if (parsedCommand[1].equalsIgnoreCase("savings")) {
			createSavings(parsedCommand);
		} else if (parsedCommand[1].equalsIgnoreCase("cd")) {
			createCD(parsedCommand);
		}

	}

	public void createChecking(String[] parsedCommand) {
		CheckingAccount checkingAccount;
		double apr = Double.parseDouble(parsedCommand[3]);
		checkingAccount = new CheckingAccount(parsedCommand[2], apr);
		bank.addAccount(checkingAccount);
	}

	public void createSavings(String[] parsedCommand) {
		SavingsAccount savingsAccount;
		double apr = Double.parseDouble(parsedCommand[3]);
		savingsAccount = new SavingsAccount(parsedCommand[2], apr);
		bank.addAccount(savingsAccount);
	}

	public void createCD(String[] parsedCommand) {
		CDAccount cdAccount;
		double apr = Double.parseDouble(parsedCommand[3]);
		double balance = Double.parseDouble(parsedCommand[4]);
		cdAccount = new CDAccount(parsedCommand[2], apr, balance);
		bank.addAccount(cdAccount);
	}

}
