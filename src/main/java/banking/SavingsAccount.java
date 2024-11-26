package banking;

public class SavingsAccount extends AllAccounts {
	boolean withdrawAlready = false;

	public SavingsAccount(String accID, double apr) {
		super(accID, apr, 0);
	}

	@Override
	public boolean checkMaxDeposit(Double amount) {
		return amount >= 0 && amount <= 2500;
	}

	@Override
	public boolean checkValidWithdraw(double amount) {
		if (!withdrawAlready) {
			return amount >= 0 && amount <= 1000;
		}
		return false;
	}

	@Override
	public void addTime() {
		super.addTime();
		withdrawAlready = false;
	}

	@Override
	public void withdraw(double amount) {
		super.withdraw(amount);
		withdrawAlready = true;
	}

}
