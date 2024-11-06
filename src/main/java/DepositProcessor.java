public class DepositProcessor {
	private Bank bank;

	public DepositProcessor(Bank bank) {
		this.bank = bank;
	}

	public void process(String command) {
		String[] parsedCommand = command.split(" ");
		String accID = parsedCommand[1];
		double amount = Double.parseDouble(parsedCommand[2]);
		bank.deposit(accID, amount);
	}
}
