package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import browser.utilites.BrowserUtility;
import browser.utilites.CustomBy;

public class AccountsPage extends HomePage {

	public AccountsPage(String BrowserName) {
		super(BrowserName);
	}

	public AccountsPage(WebDriver Driver) {
		super(Driver);
	}

	private static final By CREATE_ACCOUNT_BTN = By.xpath("//button[text()='Create Account']");
	private static final By ACCOUNT_TYPE_DROPDOWN = By
			.xpath("//label[text()='Account Type']//following-sibling:: div //div");
	private static final By BRANCH_NAME_DROPDOWN = By.xpath("//label[text()='Branch']//following-sibling:: div //div");

	// Create New Account Screen
	private static final By CREATE_BTN = By.xpath("//button[text()='Create']");

	// Account Cards
	private static final CustomBy ACCOUNT_BALANCE_TEXT = CustomBy.xpath("//h6[text()='%s']//following-sibling::h4");

	public AccountsPage createAccount(String accountType, String branchName) {
		click(CREATE_ACCOUNT_BTN);
		click(ACCOUNT_TYPE_DROPDOWN);
		selectDropdown(accountType);
		click(BRANCH_NAME_DROPDOWN);
		selectDropdown(branchName);
		click(CREATE_BTN);

		return this;
	}

	public String getAccountBalance(String accountNumber) {

		return getText(ACCOUNT_BALANCE_TEXT.format(accountNumber));

	}
	
	
	

}
