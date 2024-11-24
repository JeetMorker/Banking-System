package banking;

public abstract class AllAccounts {
	double balance;
	double apr;
	String accID;
	int time;

	public AllAccounts(String accID, double apr, double balance) {
		this.accID = accID;
		this.apr = apr;
		this.balance = balance;
		this.time = 0;

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

	public void addTime() {
		time += 1;
	}

	public void accrueAPR() {
		double decAPR = apr / 100;
		decAPR = decAPR / 12;
		double tempBal = balance * decAPR;
		balance = balance + tempBal;

	}

	public abstract boolean checkMaxDeposit(Double amount);

	public abstract boolean checkWithdrawAmount(double amount);

	public int getTime() {
		return time;
	}
}
