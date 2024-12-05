package banking;

import java.util.ArrayList;
import java.util.List;

public abstract class AllAccounts {
	double balance;
	double apr;
	String accID;
	int time;
	List<String> commands;

	protected AllAccounts(String accID, double apr, double balance) {
		this.accID = accID;
		this.apr = apr;
		this.balance = balance;
		this.time = 0;
		commands = new ArrayList<>();

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

	public void addCommand(String command) {
		commands.add(command);
	}

	public List<String> getCommands() {
		return commands;
	}

	public abstract boolean checkMaxDeposit(Double amount);

	public abstract boolean checkValidWithdraw(double amount);

	public int getTime() {
		return time;
	}

	public abstract boolean canTransfer();

	public abstract String getAccType();
}
