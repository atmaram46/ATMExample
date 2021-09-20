package atm.driver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import atm.main.ATMStatus;
import atm.constants.ATMConstants;

public class ATMMachine {
	
	ATMStatus atmStaus = ATMStatus.GetATMInstance();

	public static void main(String[] args) throws IOException {
		ATMMachine atmMachineOn = new ATMMachine();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String inputData = br.readLine().trim();
        while (!inputData.contains(ATMConstants.EXIT)) {
        	if(inputData.contains(ATMConstants.DEPOSIT)) {
        		inputData = inputData.substring(inputData.indexOf(":")+1).trim();
        		atmMachineOn.callDeposit(inputData);
        	} else if(inputData.contains(ATMConstants.WITHDRAW)) {
        		inputData = inputData.substring(inputData.indexOf(":")+1).trim();
        		atmMachineOn.callWithdraw(inputData);
        	} else {
        		System.out.println(ATMConstants.INVALID_INPUT);
        	}
        	inputData = br.readLine().trim();
        }
	}
	
	//10s: 8, 5s: 20
	public void callDeposit(String inputData) {
		String[] amountUnits = inputData.split(",");
		Map<Integer, Integer> currencyAdded = new HashMap<Integer, Integer>();
		for(String incomingData : amountUnits) {
			String[] dataSplit = incomingData.split(":");
			String currDomStr = dataSplit[0].trim();
			String removingCurrDomStrS = currDomStr.substring(0, currDomStr.length()-1);
			int currDomain = Integer.parseInt(removingCurrDomStrS);
			int numberOfCurr = Integer.parseInt(dataSplit[1].trim());
			if(numberOfCurr < 0) {
				System.out.println(ATMConstants.INVALID_DEPOSIT_AMOUNT);
				return;
			} else if(numberOfCurr > 0) {
				currencyAdded.put(currDomain, numberOfCurr);
			}
		}
		if(currencyAdded.size() == 0) {
			System.out.println(ATMConstants.ZERO_DEPOSIT_AMOUNT);
		} else {
			atmStaus.AddMoney(currencyAdded);
			displayATMCurrentStatus();
		}
	}
	
	//Withdraw 1: 75
	public void callWithdraw(String inputData) {
		int withDrawAmount = Integer.parseInt(inputData);
		if(withDrawAmount <= 0) {
			System.out.println(ATMConstants.INVALID_AMOUNT);
		} else if(withDrawAmount > atmStaus.getCurrentBalance()) {
			System.out.println(ATMConstants.INVALID_AMOUNT);
		} else {
			Map<Integer, Integer> currencryDisb = atmStaus.RemoveMoney(withDrawAmount);
			if(currencryDisb.isEmpty()) {
				return;
			}
			displayWithDrawData(currencryDisb);
			displayATMCurrentStatus();
		}
	}

//	Dispensed: 20s=3, 10s=1, 5s=1
	private void displayWithDrawData(Map<Integer, Integer> currencryDisb) {
		StringBuilder result = new StringBuilder();
		result.append("Dispensed: : ");
		for (Map.Entry<Integer, Integer> entry : currencryDisb.entrySet()) {
			result.append(entry.getKey() + "s:" + entry.getValue() + ", ");
	    }
		result.deleteCharAt(result.length()-1);
		result.deleteCharAt(result.length()-1);
		System.out.println(result.toString());
	}
	
	private void displayATMCurrentStatus() {
		System.out.println(atmStaus.getCurrentCurrDenom());
	}
}
