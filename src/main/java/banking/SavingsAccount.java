package banking;

public class SavingsAccount extends AllAccounts {

	public SavingsAccount(String accID, double apr) {
		super(accID, apr, 0);
	}

	@Override
	public boolean checkMaxDeposit(Double amount) {
		return amount >= 0 && amount <= 2500;
	}
}
