package atm.main;

import java.util.LinkedHashMap;
import java.util.Map;

import atm.constants.ATMConstants;
import atm.def.CurrencyDenom;

public class ATMStatus {

	private static ATMStatus instance;
	
	Map<Integer, Integer> currentCurrencryDenom;
	int currentBalance;
	
	private ATMStatus() {
		currentCurrencryDenom = initialize();
		currentBalance = 0;
	}
	
	public static ATMStatus GetATMInstance() {
		if (instance == null)
			instance = new ATMStatus();
        return instance;
	}
	
	private Map<Integer, Integer> initialize() {
		Map<Integer, Integer> dataVal = new LinkedHashMap<Integer, Integer>();
		dataVal.put(CurrencyDenom.Twenty.getDenom(), 0);
		dataVal.put(CurrencyDenom.Ten.getDenom(), 0);
		dataVal.put(CurrencyDenom.Five.getDenom(), 0);
		dataVal.put(CurrencyDenom.One.getDenom(), 0);
		return dataVal;
	}
	
	public int getCurrentBalance() {
		return currentBalance;
	}
	
	public String getCurrentCurrDenom() {
		StringBuilder result = new StringBuilder();
		result.append("Balance: ");
		for (Map.Entry<Integer, Integer> entry : currentCurrencryDenom.entrySet()) {
			result.append(entry.getKey() + "s=" + entry.getValue() + ", ");
	    }
		result.append("Total:" + currentBalance);
		return result.toString();
	}
	
	public void AddMoney(Map<Integer, Integer> currencyAdded) {
		int balAdded = 0;
		for(Map.Entry<Integer, Integer> currVal : currencyAdded.entrySet()) {
			int Val = currVal.getValue();
			if(currVal.getKey().equals(CurrencyDenom.Twenty.getDenom())) {
				balAdded += Val * CurrencyDenom.Twenty.getDenom();
				int currNum = checkValuePresent(CurrencyDenom.Twenty.getDenom());
				currentCurrencryDenom.put(CurrencyDenom.Twenty.getDenom(), currNum+Val);
			} else if(currVal.getKey().equals(CurrencyDenom.Ten.getDenom())) {
				balAdded += Val * CurrencyDenom.Ten.getDenom();
				int currNum = checkValuePresent(CurrencyDenom.Ten.getDenom());
				currentCurrencryDenom.put(CurrencyDenom.Ten.getDenom(), currNum+Val);
			} else if(currVal.getKey().equals(CurrencyDenom.Five.getDenom())) {
				balAdded += Val * CurrencyDenom.Five.getDenom();
				int currNum = checkValuePresent(CurrencyDenom.Five.getDenom());
				currentCurrencryDenom.put(CurrencyDenom.Five.getDenom(), currNum+Val);
			} else if(currVal.getKey().equals(CurrencyDenom.One.getDenom())) {
				balAdded += Val * CurrencyDenom.One.getDenom();
				int currNum = checkValuePresent(CurrencyDenom.One.getDenom());
				currentCurrencryDenom.put(CurrencyDenom.One.getDenom(), currNum+Val);
			}
		}
		currentBalance += balAdded;
	}
	
	private int checkValuePresent(int key) {
		if(currentCurrencryDenom.containsKey(key)) {
			return currentCurrencryDenom.get(key);
		}
		return 0;
	}

	public Map<Integer, Integer> RemoveMoney(int amount) {
		int twentyValue = currentCurrencryDenom.get(CurrencyDenom.Twenty.getDenom());
		int tenValue = currentCurrencryDenom.get(CurrencyDenom.Ten.getDenom());
		int fiveValue = currentCurrencryDenom.get(CurrencyDenom.Five.getDenom());
		int oneValue = currentCurrencryDenom.get(CurrencyDenom.One.getDenom());
		int diffAmount = amount;
		ATMUtil atmUtil = new ATMUtil();
		Map<Integer, Integer> result = new LinkedHashMap<Integer, Integer>();
		if(twentyValue > 0) {
			int valRet = atmUtil.valueCalculation(amount, CurrencyDenom.Twenty.getDenom(), twentyValue);
			result.put(CurrencyDenom.Twenty.getDenom(), valRet);
			currentCurrencryDenom.put(CurrencyDenom.Twenty.getDenom(), twentyValue-valRet);
			amount = amount - (valRet*20);
		}
		if(tenValue > 0) {
			int valRet = atmUtil.valueCalculation(amount, CurrencyDenom.Ten.getDenom(), tenValue);
			result.put(CurrencyDenom.Ten.getDenom(), valRet);
			currentCurrencryDenom.put(CurrencyDenom.Ten.getDenom(), tenValue-valRet);
			amount = amount - (valRet*10);
		}
		if(fiveValue > 0) {
			int valRet = atmUtil.valueCalculation(amount, CurrencyDenom.Five.getDenom(), fiveValue);
			result.put(CurrencyDenom.Five.getDenom(), valRet);
			currentCurrencryDenom.put(CurrencyDenom.Five.getDenom(), fiveValue-valRet);
			amount = amount - (valRet*5);
		}
		if(oneValue > 0) {
			int valRet = atmUtil.valueCalculation(amount, CurrencyDenom.One.getDenom(), oneValue);
			result.put(CurrencyDenom.One.getDenom(), valRet);
			currentCurrencryDenom.put(CurrencyDenom.One.getDenom(), oneValue-valRet);
			amount = amount - (valRet*1);
		}
		
		if(amount != 0) {
			System.out.println(ATMConstants.LOW_DENOM_PRESENT);
			return new LinkedHashMap<Integer, Integer>();
		} else {
			currentBalance -= diffAmount;
		}
		return result;
	}
}