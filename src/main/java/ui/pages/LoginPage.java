package ui.pages;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import browser.utilites.BrowserUtility;
import ui.pages.HomePage;

public class LoginPage extends BrowserUtility {
	private static String URL="https://swift.techwithjatin.com/login";
	
	
	//By Locators
	
	private static By USERNAME_INPUT= By.id("username");
	private static By USERPASS_INPUT= By.id("password");
	private static By LOGIN_BUTTON= By.xpath("//button[text()='Sign In']");
	
	
	public LoginPage(String BrowserName) {
		super(BrowserName);
		open(URL);
	}
	
	public LoginPage(WebDriver Driver) {
		super(Driver);
		open(URL);
	}
	
	public HomePage doLogin(String userName,String password) {
		type(USERNAME_INPUT, userName);
		type(USERPASS_INPUT,password);
		click(LOGIN_BUTTON);
		return new HomePage(getDriver());
	}

	
	

}
