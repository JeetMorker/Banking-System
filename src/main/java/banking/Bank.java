package banking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {
	private final Map<String, AllAccounts> accounts;

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

	public boolean checkMaxDeposit(String accID, double amount) {
		AllAccounts account = accounts.get(accID);
		return account.checkMaxDeposit(amount);

	}

	public boolean checkValidWithdraw(String accID, double amount) {
		AllAccounts account = accounts.get(accID);
		return account.checkValidWithdraw(amount);
	}

	public void addValidCommand(String command, String accID) {
		AllAccounts account = accounts.get(accID);
		account.addCommand(command);
	}

	public void passTime(int months) {
		while (months > 0) {
			List<String> removal = new ArrayList<>();

			for (String accID : accounts.keySet()) {
				AllAccounts account = accounts.get(accID);
				if (account.balance == 0) {
					removal.add(accID);
				}
				if (account.balance < 100) {
					account.balance -= 25;
				}
				account.addTime();
				account.accrueAPR();
			}
			for (String accID : removal) {
				accounts.remove(accID);
			}
			months -= 1;

		}

	}

	public String getAccType(String accID) {
		AllAccounts account = accounts.get(accID);

		return account.getAccType();

	}

	public int getTime(String accID) {
		AllAccounts account = accounts.get(accID);
		return account.getTime();
	}

	public boolean canTransfer(String accID) {
		AllAccounts account = accounts.get(accID);
		return account.canTransfer();
	}
}
