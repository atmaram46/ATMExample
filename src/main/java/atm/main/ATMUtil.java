package atm.main;

public class ATMUtil {

	public int valueCalculation(int amount, int denom, int currDenom) {
		int twenReq = amount/denom;
		int val = currDenom - twenReq;
		if(val >= 0) { 
			return twenReq;
		} else {
			return currDenom;
		}
	}
}
