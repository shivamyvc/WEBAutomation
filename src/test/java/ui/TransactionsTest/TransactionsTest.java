package ui.TransactionsTest;

import ui.BaseTest.BaseTest;
import ui.pages.AccountsPage;

import org.testng.Assert;
import org.testng.annotations.Test;
public class TransactionsTest extends BaseTest {
	
	@Test(description="Testing of Accounts types")
	public void testAccount() {
		
		AccountsPage accPage= loginPage.doLogin("shivammavii", "Learn@2000")
				.goToAccounts();
		
		String accountBal=accPage
		.getAccountBalance("0401766304").replace("$", "").replace(",", "");
//		.createAccount("Current Account", "South Branch");
		System.out.println(accountBal);
		Assert.assertEquals(accountBal, "1398","Account balance mismatch ");
		
		//Account Page to Transaction Page
		accPage.goToTransations().selectAccount("0401766304").getTransactionHistory("0401766304");
		
	}

}
