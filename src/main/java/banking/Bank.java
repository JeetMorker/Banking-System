package banking;

import java.util.HashMap;
import java.util.Map;

public class Bank {
	private Map<String, AllAccounts> accounts;

	Bank() {
		accounts = new HashMap<>();
	}

	public Map<String, AllAccounts> getAccounts() {
		return accounts;
	}

	public void addAccount(AllAccounts account) {
		accounts.put(account.getID(), account);
	}

	public AllAccounts getAccount(String accID) {
		return accounts.get(accID);
	}

	public void deposit(String accID, double amount) {
		accounts.get(accID).deposit(amount);
	}

	public void withdraw(String accID, double amount) {
		accounts.get(accID).withdraw(amount);
	}

	public boolean checkExistingID(String accID) {
		return accounts.get(accID) != null;

	}

	public boolean checkMaxDesposit(String accID, double amount) {
		AllAccounts account = accounts.get(accID);
		return account.checkMaxDeposit(amount);

	}

	public boolean checkWithdrawAmount(String accID, double amount) {
		AllAccounts account = accounts.get(accID);
		return account.checkWithdrawAmount(amount);
	}
}
