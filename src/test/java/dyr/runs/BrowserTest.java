package dyr.runs;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import browser.utilites.Browser;

public class BrowserTest {
	
	@Test
	public void LaunchBrowser() throws InterruptedException {
		
		Browser br= new Browser("ChrOme");
		br.open("https://swift.techwithjatin.com/login");
		Thread.sleep(2000);
		
		System.out.println(br.isVisible(By.xpath("//buttaaaon[text()]")));
		
		
		
		System.out.println(br.isVisible(By.xpath("//butaton[text()]"),5));
		
		
		System.out.println(br.click(By.xpath("//button[text()='Sign In']")));
		System.out.println(br.click(By.xpath("//button[text()='Sign In']"),6));
		
	}

}
 