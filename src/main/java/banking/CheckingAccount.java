package banking;

public class CheckingAccount extends AllAccounts {

	public CheckingAccount(String accID, double apr) {
		super(accID, apr, 0);
	}

	@Override
	public boolean checkMaxDeposit(Double amount) {
		return amount >= 0 && amount <= 1000;

	}

	@Override
	public boolean checkWithdrawAmount(double amount) {
		return amount >= 0 && amount <= 400;
	}

}