package atm.test.main;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import atm.main.ATMUtil;

@RunWith(MockitoJUnitRunner.class)
public class ATMUtilTest {

	private ATMUtil atmUtil;
	
	@BeforeEach                                         
	public void setUp() throws Exception {
		atmUtil = new ATMUtil();
	}
	
	@Test                                               
	@DisplayName("Simple Calculation Should work!!")   
	public void testCallDeposit() {
		assertEquals(4, atmUtil.valueCalculation(130, 20, 4));
	}
}
