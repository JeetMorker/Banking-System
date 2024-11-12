package banking;

public class CDAccount extends AllAccounts {

	public CDAccount(String accID, double apr, double balance) {
		super(accID, apr, balance);

	}

	@Override
	public boolean checkMaxDeposit(Double amount) {
		return false;
	}
}
