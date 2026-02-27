package ui.BaseTest;

import org.testng.annotations.BeforeSuite;

import ui.pages.LoginPage;

public class BaseTest {
	protected LoginPage loginPage;
	@BeforeSuite(description ="Suite Setup")
	public void suiteSetup() {
		loginPage= new LoginPage("Chrome");
		loginPage.maximize();
	}
	
	
	
	@BeforeSuite(description ="Suite TearDown")
	public void suiteTearDown() {
		
	}
	

}
