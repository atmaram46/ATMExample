package atm.test.driver;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import atm.driver.ATMMachine;
import atm.main.ATMStatus;

@RunWith(MockitoJUnitRunner.class)
public class ATMMachineTest {
	
	private ATMMachine atmMachine;
	
	ATMStatus atmStatus = mock(ATMStatus.class);

	@BeforeEach                                         
	public void setUp() throws Exception {
		atmMachine = new ATMMachine();
	}

	@Test                                               
	@DisplayName("Simple Add Money Should work!!")   
	public void testCallDeposit() {
		Map<Integer, Integer> testData = new LinkedHashMap<Integer, Integer>();
		testData.put(10, 8);
		testData.put(5, 20);
		doNothing().when(atmStatus).AddMoney(testData);
		atmMachine.callDeposit("Deposit 1: 10s: 8, 5s: 20");
		verify(atmStatus,times(1)).AddMoney(testData);
	}
	
	@Test                                               
	@DisplayName("Simple Remove Money Should work!!")   
	public void testCallWithdraw() {
		doNothing().when(atmStatus).RemoveMoney(75);
		atmMachine.callWithdraw("Withdraw 1: 75");
		verify(atmStatus,times(1)).RemoveMoney(75);
	}

	
}
