package dyr.runs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import browser.utilites.Browser;

public class BrowserTest {
	
	@Test
	public void LaunchBrowser() throws InterruptedException {
		
		Browser br= new Browser("ChrOme");
		br.open("https://swift.techwithjatin.com/login");
		Thread.sleep(2000);
		br.type(By.id("username"),"shivammavii");
		
		br.type(By.id("password"),"Learn@2000");
		
		br.getWebElement(By.xpath("//button[text()='Sign In']")).click();
		
		
		
		
		System.out.println(br.isVisible(By.xpath("//butaton[text()]"),5));
		
		
		System.out.println(br.click(By.xpath("//button[text()='Sign In']")));
		System.out.println(br.click(By.xpath("//button[text()='Sign In']"),6));
		
		
		
		br.getDriver().navigate().to("https://www.testmuai.com/selenium-playground/select-dropdown-demo/");
		
		Thread.sleep(5000);
		
		br.selectByVisibleText(By.id("select-demo"), "Monday");
		
		List<String> values= new ArrayList<String>();
		values.add("Ohio");
		values.add("Texas");
		values.add("Washington");

		
		
		br.selectMultiByVisibleText(By.id("multiselect"), values);
		
	}

}
 
