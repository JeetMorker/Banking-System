public class CreateValidator {

	private Bank bank;

	public CreateValidator(Bank bank) {
		this.bank = bank;
	}

	public boolean validate(String command) {
		String[] parsedCommand = command.split(" ");
		return validID(parsedCommand[2]);
	}

	public boolean validID(String accID) {
		if (isNum(accID) && (accID.length() == 8)) {
			return !bank.checkExistingID(accID);
		}
		return false;
	}

	private boolean isNum(String value) {
		for (char num : value.toCharArray()) {
			if (!Character.isDigit(num)) {
				return false;
			}
		}
		return true;
	}
}
