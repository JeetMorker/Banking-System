package banking;

public abstract class AllAccounts {
	double balance;
	double apr;
	String accID;

	public AllAccounts(String accID, double apr, double balance) {
		this.accID = accID;
		this.apr = apr;
		this.balance = balance;

	}

	public double getBalance() {
		return balance;
	}

	public double getAPR() {
		return apr;
	}

	public String getID() {
		return accID;
	}

	public void deposit(double amount) {
		balance += amount;
	}

	public void withdraw(double amount) {
		balance -= amount;
		if (balance < 0) {
			balance = 0;
		}
	}

	public abstract boolean checkMaxDeposit(Double amount);

	public abstract boolean checkWithdrawAmount(double amount);
}
