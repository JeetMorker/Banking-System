package banking;

import java.util.ArrayList;
import java.util.List;

public class CommandStoring {
	private final List<String> invalidCommands;
	private final Bank bank;
	private final List<String> outputList;

	CommandStoring(Bank bank) {
		invalidCommands = new ArrayList<>();
		outputList = new ArrayList<>();
		this.bank = bank;
	}

	public void addInvalidCommand(String command) {
		invalidCommands.add(command);
	}

	public List<String> getInvalidCommands() {
		return invalidCommands;
	}

	public void addValidCommand(String command) {
		String[] parsedCommand = command.split(" ");
		if (parsedCommand[0].equalsIgnoreCase("pass") || parsedCommand[0].equalsIgnoreCase("create")) {
			return;
		} else if (parsedCommand[0].equalsIgnoreCase("transfer")) {
			bank.addValidCommand(command, parsedCommand[1]);
			bank.addValidCommand(command, parsedCommand[2]);
		} else {
			bank.addValidCommand(command, parsedCommand[1]);
		}
	}

	public String getAccState(String accID) {
		String accType = bank.getAccType(accID);
		double bal = bank.getAccount(accID).getBalance();
		String balance = String.format("%.2f", bal);
		double aprVal = bank.getAccount(accID).getAPR();
		String apr = String.format("%.2f", aprVal);
		return accType + " " + accID + " " + balance + " " + apr;
	}

	public List<String> getOutput() {
		for (AllAccounts account : bank.getAccounts().values()) {
			String accID = account.getID();
			String accState = getAccState(accID);
			outputList.add(accState);
			outputList.addAll(account.getCommands());
		}
		outputList.addAll(getInvalidCommands());
		return outputList;

	}

}
