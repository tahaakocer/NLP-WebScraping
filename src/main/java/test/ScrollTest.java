package test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;

import component.Page;
import utilities.BrowserFactory;
import utilities.DriverFactory;
import utilities.WaitMethods;

public class ScrollTest {

	public static WebDriver driver;
	public static Page page;
	private JavascriptExecutor js;
	private int sayac;

	

	@Before
	public void setup() {
		if(driver == null) {
			BrowserFactory browserFactory = new BrowserFactory();
			driver = DriverFactory.createDriver();
			System.out.println("test driver baslatildi. \n" + driver);
		//	driver.get("https://www.reddit.com/r/movies/comments/155ag1m/official_discussion_oppenheimer_spoilers/");
			DriverFactory.setimplicitlyWait(driver);
			System.out.println(driver.getTitle());
			js = (JavascriptExecutor) driver;
			sayac = 0;
		}	
	}

	@Test
	public void test() {

		page = new Page(driver);

		for (int i = 0; i < 3; i++) {
			js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
			WaitMethods.bekle(2);
		}

		while (true) {
			js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
			List<WebElement> buttonElements = driver.findElements(By.xpath(page.buttonElementString));
			if (buttonElements.isEmpty() == false) {
				System.out.println(sayac++ +": sayfanin devami var. Devam ediliyor.");
				WaitMethods.waitForClickablility(driver, page.buttonElement, 10);
				js.executeScript("arguments[0].click();", page.buttonElement);
				WaitMethods.bekle(2);
				continue;
			} else if (buttonElements.isEmpty() == true) {
				System.out.println("Scroll islemi bitti.");
				break;
			}
		}
		System.out.println("sayfa sonuna gelindi.");
		

	}
	
	@After
	public void tearDown() {
		DriverFactory.closeDriver(driver);
		System.out.println("test driver durduruldu.");
	}

}
