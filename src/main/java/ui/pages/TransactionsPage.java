package ui.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import browser.utilites.BrowserUtility;
import browser.utilites.CustomBy;

public class TransactionsPage extends HomePage {

	public TransactionsPage(String BrowserName) {
		super(BrowserName);
	}

	public TransactionsPage(WebDriver Driver) {
		super(Driver);
	}

	private static final By SELECT_ACCOUNT_DROPDOWN = By
			.xpath("//label[text()='Select Account']//following-sibling::div /div");
	private static final CustomBy ACCOUNT_NUMBER = CustomBy.xpath("//ul[@role='listbox']//li[contains(text(),'%s')]");

	// Transaction Table
	private static final By TRXN_TABLE_ROWS = By.xpath("//table //tr");
	private static final CustomBy TRXN_ROW_CELL = CustomBy.xpath("//table //tr[%s]//td[%s]");

	public TransactionsPage selectAccount(String accountNumber) {
		click(SELECT_ACCOUNT_DROPDOWN, 10);
		click(ACCOUNT_NUMBER.format(accountNumber));
		return this;
	}

	public TransactionsPage getTransactionHistory(String accountNumber) {

		selectAccount(accountNumber);
		List<WebElement> tabelRows = getWebElements(TRXN_TABLE_ROWS);
		for (int row = 0; row < tabelRows.size(); row++) {
			System.out.println("Transaction Ref No: " + getText(TRXN_ROW_CELL.format(3, 2)));
			System.out.print("  Amount: " + getText(TRXN_ROW_CELL.format(3, 5)));
		}

		return this;
	}

}
