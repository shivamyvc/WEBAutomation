package ui.LoginTest;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ui.pages.LoginPage;

public class LoginTest {
	private LoginPage loginPage;
	
	@BeforeMethod()
	public void setup() {
		loginPage= new LoginPage("ChRome");
	}

	@Test(description = "UI Bank Login Test")
	public void LogintestWith() {
		
		 loginPage.doLogin("shivammavii", "Learn@2000");
	}

}
