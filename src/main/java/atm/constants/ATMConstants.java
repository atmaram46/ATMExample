package atm.constants;

public class ATMConstants {

	private ATMConstants() {
    }
	
	public static final String CREDIT = "Credit";
	
	
	public static final String EXIT = "Exit";
	public static final String DEPOSIT = "Deposit";
	public static final String WITHDRAW = "Withdraw";
	
	
	public static final String INVALID_INPUT = "Invalid Input, Please try again!!!";
	public static final String INVALID_AMOUNT = "Incorrect or insufficient funds!!";
	public static final String INVALID_DEPOSIT_AMOUNT = "Incorrect deposit amount, Please try again!!!";
	public static final String ZERO_DEPOSIT_AMOUNT = "Deposit amount cannot be zero, Please try again!!!";
	public static final String LOW_DENOM_PRESENT = "Requested withdraw amount is not dispensable!!";
}
