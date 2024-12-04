package banking;

public class WithdrawProcessor {
	private final Bank bank;

	public WithdrawProcessor(Bank bank) {
		this.bank = bank;
	}

	public void process(String command) {
		String[] parsedCommand = command.split(" ");
		bank.withdraw(parsedCommand[1], Double.parseDouble(parsedCommand[2]));
	}
}
