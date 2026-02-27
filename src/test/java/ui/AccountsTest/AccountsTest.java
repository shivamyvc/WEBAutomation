package ui.AccountsTest;

import ui.BaseTest.BaseTest;

import org.testng.Assert;
import org.testng.annotations.Test;
public class AccountsTest extends BaseTest {
	
	@Test(description="Testing of Accounts types")
	public void testAccount() {
		
		String accountBal=loginPage.doLogin("shivammavii", "Learn@2000")
		.goToAccounts()
		.getAccountBalance("0401766304").replace("$", "").replace(",", "");
//		.createAccount("Current Account", "South Branch");
		System.out.println(accountBal);
		Assert.assertEquals(accountBal, "1398","Account balance mismatch ");
	}

}
