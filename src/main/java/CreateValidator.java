public class CreateValidator {

	private Bank bank;

	public CreateValidator(Bank bank) {
		this.bank = bank;
	}

	public boolean validate(String command) {
		String[] parsedCommand = command.split(" ");
		if (bank.checkExistingID(parsedCommand[2])) {
			return false;
		} else {
			return true;
		}
	}

}
