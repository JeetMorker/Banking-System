package banking;

public class CDAccount extends AllAccounts {

	public CDAccount(String accID, double apr, double balance) {
		super(accID, apr, balance);

	}

	@Override
	public void accrueAPR() {
		for (int i = 0; i < 4; i++) {
			super.accrueAPR();
		}
	}

	@Override
	public boolean checkMaxDeposit(Double amount) {
		return false;
	}

	@Override
	public boolean checkWithdrawAmount(double amount) {
		return amount >= balance;
	}
}
