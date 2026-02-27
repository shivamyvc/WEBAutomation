package dyr.runs;

import org.testng.annotations.Test;

import browser.utilites.BrowserUtility;

public class ZBankTest {
	
	@Test
	public void LaunchBrowser() throws InterruptedException {
		
		BrowserUtility br= new BrowserUtility("ChrOme");
		br.open("https://swift.techwithjatin.com/login");
		br.captureScreenshot("LoginScreen.png");
		
	

}
	
}
