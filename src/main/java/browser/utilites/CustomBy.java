package browser.utilites;

import org.openqa.selenium.By;

public class CustomBy {

    private String locator;
    private LocatorType type;

    private enum LocatorType {
        XPATH,
        ID,
        NAME,
        CSS,
        CLASS_NAME,
        TAG_NAME,
        LINK_TEXT,
        PARTIAL_LINK_TEXT
    }

    private CustomBy(LocatorType type, String locator) {
        this.type = type;
        this.locator = locator;
    }

    // Factory Methods
    public static CustomBy xpath(String locator) {
        return new CustomBy(LocatorType.XPATH, locator);
    }

    public static CustomBy id(String locator) {
        return new CustomBy(LocatorType.ID, locator);
    }

    public static CustomBy css(String locator) {
        return new CustomBy(LocatorType.CSS, locator);
    }

    public static CustomBy name(String locator) {
        return new CustomBy(LocatorType.NAME, locator);
    }

    // Format Method
    public By format(Object... values) {
        String formattedLocator = String.format(this.locator, values);
        return buildBy(formattedLocator);
    }

    // Convert to Selenium By
    public By getBy() {
        return buildBy(this.locator);
    }

    private By buildBy(String locatorValue) {
        switch (this.type) {
            case XPATH:
                return By.xpath(locatorValue);
            case ID:
                return By.id(locatorValue);
            case NAME:
                return By.name(locatorValue);
            case CSS:
                return By.cssSelector(locatorValue);
            case CLASS_NAME:
                return By.className(locatorValue);
            case TAG_NAME:
                return By.tagName(locatorValue);
            case LINK_TEXT:
                return By.linkText(locatorValue);
            case PARTIAL_LINK_TEXT:
                return By.partialLinkText(locatorValue);
            default:
                throw new RuntimeException("Invalid Locator Type");
        }
    }
}