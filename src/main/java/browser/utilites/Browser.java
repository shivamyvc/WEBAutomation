package browser.utilites;

import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import browser.exceptions.BrowserException;

public class Browser {

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	private static Logger logger = LogManager.getLogger(Browser.class);
	private static WebDriverWait wait;
	private static Actions actions;
	private static final Duration TIMEOUT_IN_SECONDS = Duration.ofSeconds(10);

	public WebDriver getDriver() {
		return driver.get();
	}

	public Browser(WebDriver Driver) {
		this.driver.set(Driver);
		wait = new WebDriverWait(Driver, TIMEOUT_IN_SECONDS);
		actions = new Actions(Driver);

	}

	public Browser(String BrowserName) {
		if (BrowserName.equalsIgnoreCase("chrome")) {
			logger.info("Creating ChromeDriver");
			this.driver.set(new ChromeDriver());
			actions = new Actions(this.getDriver());
		} else if (BrowserName.equalsIgnoreCase("edge")) {
			logger.info("Creating EdgeDriver");
			this.driver.set(new EdgeDriver());
			actions = new Actions(this.getDriver());
		} else if (BrowserName.equalsIgnoreCase("firefox")) {
			logger.info("Creating FirefoxDriver");
			this.driver.set(new FirefoxDriver());
			actions = new Actions(this.getDriver());
		} else {
			logger.info(String.format("Inavlid Browser Name [%s] creating default ChromeDriver", BrowserName));
			this.driver.set(new ChromeDriver());
			actions = new Actions(this.getDriver());
		}

		wait = new WebDriverWait(driver.get(), TIMEOUT_IN_SECONDS);
	}

	public void open(String URL) {
		logger.info(String.format("Opening URL [%s]", URL));
		driver.get().get(URL);
	}

	public void maximize() {
		logger.info("Maximizing window");
		driver.get().manage().window().maximize();
	}

	public void closeTab() {

		int totalActiveTabs = 0;
		if (driver.get() == null) {
			logger.warn("driver is null cannot use closeTab()");
			return;
		}
		totalActiveTabs = driver.get().getWindowHandles().size();
		if (totalActiveTabs == 1) {
			logger.warn(String.format("[%s] This is the only tab in browser closing and quiting driver",
					driver.get().getTitle()));
			logger.info(String.format("Closing current tab with title [%s]", driver.get().getTitle()));
			driver.get().quit();
			driver.remove();
			return;
		} else if (totalActiveTabs > 1) {
			driver.get().close();
			logger.info(String.format("Closing current tab with title [%s]", driver.get().getTitle()));

		} else {
			logger.error("Something went wrong while closing current tab");
		}

	}

	public void closeTab(String tabWindowHandle) {

		String mainWindowHandle = driver.get().getWindowHandle();
		// Can be used to point to which tab is opened as main tab
		String mainWindowTitle = driver.get().getTitle();
		String mainWindowURL = driver.get().getCurrentUrl();

		driver.get().switchTo().window(tabWindowHandle);
		logger.info(String.format("Closing tab with title [%s]", driver.get().getTitle()));
		driver.get().close();

		logger.info(String.format("Switching to main tab with title [%s]", mainWindowTitle));
		driver.get().switchTo().window(mainWindowHandle);

	}

	public void quit() {

		if (driver.get() == null) {
			logger.warn("driver is null cannot use quit()");
			return;
		}
		driver.get().quit();
		logger.info("Quitting browser");
		driver.remove();
	}

	// Element Interactions

	public boolean isVisible(By elementLocator) {

		try {
			// Use a short explicit wait to see if it appears
			WebDriverWait wait = new WebDriverWait(driver.get(), TIMEOUT_IN_SECONDS);
			return wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator)).isDisplayed();
		} catch (Exception e) {
			// If it times out or isn't in DOM, return false instead of throwing exception
			logger.error(String.format("WebElement [%s] is not visible", elementLocator.toString()));
			logger.debug(String.format("WebElement [%s] is not visible", elementLocator.toString()), e);
			return false;
		}

	}

	public boolean isVisible(By elementLocator, int TIMEOUT) {

		try {
			// Use a short explicit wait to see if it appears
			WebDriverWait wait = new WebDriverWait(driver.get(), Duration.ofSeconds(TIMEOUT));
			return wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator)).isDisplayed();
		} catch (Exception e) {
			// If it times out or isn't in DOM, return false instead of throwing exception
			logger.error(String.format("WebElement [%s] is not visible waited for %s seconds",
					elementLocator.toString(), TIMEOUT));
			logger.debug(String.format("WebElement [%s] is not visible waited for %s seconds",
					elementLocator.toString(), TIMEOUT), e);
			return false;
		}

	}

	public boolean isClckable(By elementLocator) {

		try {
			// Use a short explicit wait to see if it appears
			WebDriverWait wait = new WebDriverWait(driver.get(), TIMEOUT_IN_SECONDS);
			return wait.until(ExpectedConditions.elementToBeClickable(elementLocator)).isDisplayed();
		} catch (Exception e) {
			// If it times out or isn't in DOM, return false instead of throwing exception
			logger.error(String.format("WebElement [%s] is not clickable", elementLocator.toString()));
			logger.debug(String.format("WebElement [%s] is not clickable", elementLocator.toString()), e);
			return false;
		}

	}

	public boolean isClckable(By elementLocator, int TIMEOUT) {

		try {
			// Use a short explicit wait to see if it appears
			WebDriverWait wait = new WebDriverWait(driver.get(), Duration.ofSeconds(TIMEOUT));
			return wait.until(ExpectedConditions.elementToBeClickable(elementLocator)).isDisplayed();
		} catch (Exception e) {
			// If it times out or isn't in DOM, return false instead of throwing exception
			logger.error(String.format("WebElement [%s] is not clickable waited for %s seconds",
					elementLocator.toString(), TIMEOUT));
			logger.debug(String.format("WebElement [%s] is not clickable waited for %s seconds",
					elementLocator.toString(), TIMEOUT), e);
			return false;
		}

	}

	public boolean click(By elementLocator) {

		if (driver.get() == null) {
			logger.warn(String.format("driver is null cannot use click() on [%s]", elementLocator.toString()));
			return false;
		}

		if (!isClckable(elementLocator)) {
			return false;
		}

		try {
			driver.get().findElement(elementLocator).click();
			return true;
		} catch (Exception e) {
			logger.error(String.format("WebElement [%s] not clicked", elementLocator.toString()));
			logger.debug(String.format("WebElement [%s] not clicked", elementLocator.toString()), e);
			return false;
		}

	}

	public boolean click(By elementLocator, int TIMEOUT) {

		if (driver.get() == null) {
			logger.warn(String.format("driver is null cannot use click() on [%s]", elementLocator.toString()));
			return false;
		}

		if (!isClckable(elementLocator, TIMEOUT)) {
			return false;
		}

		try {
			driver.get().findElement(elementLocator).click();
			return true;
		} catch (Exception e) {
			logger.error(String.format("WebElement [%s] not clicked", elementLocator.toString()));
			logger.debug(String.format("WebElement [%s] not clicked", elementLocator.toString()), e);
			return false;
		}

	}

	public boolean clearInput(By elementLocator) {
		if (driver.get() == null) {
			logger.warn(String.format("driver is null cannot use click() on [%s]", elementLocator.toString()));
			return false;
		}

		if (!isVisible(elementLocator)) {
			return false;
		}
		try {
			driver.get().findElement(elementLocator).clear();
			;
			return true;
		} catch (Exception e) {
			logger.error(String.format("Input WebElement [%s] not clear", elementLocator.toString()));
			logger.debug(String.format("Input WebElement [%s] not clear", elementLocator.toString()), e);
			return false;
		}

	}

	public boolean type(By elementLocator, CharSequence value) {
		if (driver.get() == null) {
			logger.warn(String.format("driver is null cannot use click() on [%s]", elementLocator.toString()));
			return false;
		}

		if (!isVisible(elementLocator)) {
			return false;
		}
		try {
			if (!clearInput(elementLocator)) {
				return false;
			}
			driver.get().findElement(elementLocator).sendKeys(value);
			return true;
		} catch (Exception e) {
			logger.error(
					String.format("Unable to type [%s] at input WebElement [%s] ", value, elementLocator.toString()));
			logger.debug(
					String.format("Unable to type [%s] at input WebElement [%s] ", value, elementLocator.toString()),
					e);
			return false;
		}

	}

	public boolean sendKeys(By elementLocator, CharSequence value) {
		if (driver.get() == null) {
			logger.warn(String.format("driver is null cannot use click() on [%s]", elementLocator.toString()));
			return false;
		}

		if (!isVisible(elementLocator)) {
			return false;
		}
		try {
			driver.get().findElement(elementLocator).sendKeys(value);
			return true;
		} catch (Exception e) {
			logger.error(
					String.format("Unable to type [%s] at input WebElement [%s] ", value, elementLocator.toString()));
			logger.debug(
					String.format("Unable to type [%s] at input WebElement [%s] ", value, elementLocator.toString()),
					e);
			return false;
		}

	}

	public WebElement getWebElement(By elementLocator) {

		if (driver.get() == null) {
			logger.warn(String.format("driver is null cannot use click() on [%s]", elementLocator.toString()));
			return null;
		}
		try {

			return wait.until(ExpectedConditions.presenceOfElementLocated(elementLocator));

		} catch (Exception e) {
			logger.error(String.format("Uanble to locate WebElement [%s] ", elementLocator.toString()));
			logger.debug(String.format("Uanble to locate WebElement [%s] ", elementLocator.toString()), e);

			return null;
		}
	}

	public boolean selectByVisibleText(By elementLocator,String value) {
		
		if (driver.get() == null) {
			logger.warn(String.format("driver is null cannot use click() on [%s]", elementLocator.toString()));
			return false;
		}

		if (!isVisible(elementLocator)) {
			return false;
		}
		try {
			Select select= new Select(getWebElement(elementLocator));
			select.selectByVisibleText(value);	
			return true;
		}catch(Exception e) {
			logger.error(String.format("Uanble to select [%s] in WebElement [%s] ", value,elementLocator.toString()));
			logger.debug(String.format("Uanble to select [%s] in WebElement [%s] ", value,elementLocator.toString()), e);
			return false;
		}
		
	}
	
	public boolean selectMultiByVisibleText(By elementLocator,List<String> values) {
		
		if (driver.get() == null) {
			logger.warn(String.format("driver is null cannot use click() on [%s]", elementLocator.toString()));
			return false;
		}

		if (!isVisible(elementLocator)) {
			return false;
		}
		try {
			Select select= new Select(getWebElement(elementLocator));
			for(String value: values) {
				select.selectByVisibleText(value);
			}
				
			return true;
		}catch(Exception e) {
			logger.error(String.format("Uanble to select [%s] in WebElement [%s] ", values.toArray(),elementLocator.toString()));
			logger.debug(String.format("Uanble to select [%s] in WebElement [%s] ", values.toArray(),elementLocator.toString()), e);
			return false;
		}
		
	}
	
	
	

}
