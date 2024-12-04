package banking;

public class TransferProcessor {
	private final Bank bank;

	TransferProcessor(Bank bank) {
		this.bank = bank;
	}

	public void process(String command) {
		String[] parsedCommand = command.split(" ");
		String withdrawID = parsedCommand[1];
		String depositID = parsedCommand[2];
		double amount = Double.parseDouble(parsedCommand[3]);
		double transferAmount;
		if (amount > bank.getAccount(withdrawID).getBalance()) {
			transferAmount = bank.getAccount(withdrawID).getBalance();
		} else {
			transferAmount = amount;
		}
		bank.withdraw(withdrawID, transferAmount);
		bank.deposit(depositID, transferAmount);
	}

}
