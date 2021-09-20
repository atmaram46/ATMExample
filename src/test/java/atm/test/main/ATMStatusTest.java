package atm.test.main;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import atm.main.ATMStatus;

@RunWith(MockitoJUnitRunner.class)
public class ATMStatusTest {

	private ATMStatus atmStatus;
	
	@BeforeEach                                         
	public void setUp() throws Exception {
		atmStatus = ATMStatus.GetATMInstance();
	}
	
	@Test                                               
	@DisplayName("Simple Add Money Should work!!")   
	public void testCallDeposit() {
		Map<Integer, Integer> testData = new LinkedHashMap<Integer, Integer>();
		testData.put(10, 8);
		testData.put(5, 20);
		atmStatus.AddMoney(testData);
		verify(atmStatus,times(1)).AddMoney(testData);
	}
}
