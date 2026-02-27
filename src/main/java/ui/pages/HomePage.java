package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import browser.utilites.BrowserUtility;

public class HomePage extends BrowserUtility {

	public HomePage(String BrowserName) {
		super(BrowserName);
	}

	public HomePage(WebDriver Driver) {
		super(Driver);
	}

	private static final By DASHBOARD_TAB = By.xpath("//div[@id='root']//span[text()='Dashboard']");

	private static final By ACCOUNTS_TAB = By.xpath("//div[@id='root']//span[text()='Accounts']");

	private static final By TRANSFER_TAB = By.xpath("//div[@id='root']//span[text()='Transfer']");

	private static final By TRANSACTIONS_TAB = By.xpath("//div[@id='root']//span[text()='Transactions']");

	public AccountsPage goToAccounts() {
		click(ACCOUNTS_TAB, 20);
		return new AccountsPage(getDriver());

	}

	public void goToDashbaord() {
		click(DASHBOARD_TAB, 20);

	}

	public TransferPage goToTransfer() {
		click(TRANSFER_TAB, 20);
		return new TransferPage(getDriver());

	}

	public void goToTransations() {
		click(TRANSACTIONS_TAB, 20);

	}

}
