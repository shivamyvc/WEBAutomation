package dyr.runs;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestRun1 {
	
	
	@Test(retryAnalyzer = RetryAnalyzer.class)
	public void run1() {
		
		SoftAssert softAssert= new SoftAssert();
		
		softAssert.assertEquals(1, 2, "Mismatch in 1 vs 2");
//		Assert.fail();
		System.out.println("Test Passed1111");
		softAssert.assertAll();
	}

}
