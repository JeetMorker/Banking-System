public class CreateValidator {

	private Bank bank;

	public CreateValidator(Bank bank) {
		this.bank = bank;
	}

	public boolean validate(String command) {
		String[] parsedCommand = command.split(" ");
		return validID(parsedCommand[2]) && validAPR(parsedCommand[3]);
	}

	public boolean validID(String accID) {
		if (idIsNum(accID) && (accID.length() == 8)) {
			return !bank.checkExistingID(accID);
		}
		return false;
	}

	public boolean validAPR(String apr) {
		if (aprIsDouble(apr)) {
			double aprVal = Double.parseDouble(apr);
			return aprVal >= 0 && aprVal <= 10;
		}
		return false;
	}

	private boolean idIsNum(String accID) {
		for (char num : accID.toCharArray()) {
			if (!Character.isDigit(num)) {
				return false;
			}
		}
		return true;
	}

	private boolean aprIsDouble(String apr) {
		try {
			Double.parseDouble(apr);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
