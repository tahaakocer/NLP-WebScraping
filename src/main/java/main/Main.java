package main;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import component.Page;
import utilities.DriverFactory;
import org.junit.After;
import utilities.WaitMethods;

public class Main {
	

	public static void main(String[] args) {
		WebDriver driver = DriverFactory.createDriver();
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		DriverFactory.setimplicitlyWait(driver);
		driver.get(
				"https://www.reddit.com/r/history/comments/2tikvq/how_is_wwii_remembered_or_viewed_in_your_country/");
		System.out.println(driver);
		System.out.println(driver.getTitle());
		Page page = new Page(driver);
		
		for (int i = 0; i < 3; i++) {
			js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
			WaitMethods.bekle(2);
		}
		
		while (true) {
			js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
			List<WebElement> buttonElements = driver.findElements(By.xpath(page.buttonElementString));
			if(buttonElements.isEmpty() == false) {
				System.out.println("sayfanin devami var. Devam ediliyor.");
				WaitMethods.waitForClickablility(driver, page.buttonElement, 10);
				js.executeScript("arguments[0].click();", page.buttonElement);
				WaitMethods.bekle(2);
				continue;
			} else if(buttonElements.isEmpty() == true) {
				System.out.println("Scroll islemi bitti.");
				break;
			}
			
			
		}
		System.out.println("sayfa sonuna gelindi.");
		WaitMethods.bekle(3);
		
		
		
	}
}
