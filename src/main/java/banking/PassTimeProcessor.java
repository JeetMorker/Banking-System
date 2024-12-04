package banking;

public class PassTimeProcessor {

	private final Bank bank;

	public PassTimeProcessor(Bank bank) {
		this.bank = bank;
	}

	public void process(String command) {
		String[] parsedCommand = command.split(" ");
		bank.passTime(Integer.parseInt(parsedCommand[1]));
	}
}
