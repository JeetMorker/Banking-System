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
	public boolean checkValidWithdraw(double amount) {
		if (time >= 12) {
			return amount >= balance;
		}
		return false;
	}

	@Override
	public boolean canTransfer() {
		return false;
	}

	@Override
	public String getAccType() {
		return "Cd";
	}

}
