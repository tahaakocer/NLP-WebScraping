package test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
import org.openqa.selenium.support.ui.WebDriverWait;

import component.Page;
import utilities.BrowserFactory;
import utilities.DriverFactory;

public class WriteToDocTest {
	
	private WebDriver driver;
	private JavascriptExecutor js;
	public List<WebElement> pList;
	public List<String> pStrings;
	private Page page;

	
	private static void writeListToDocx(List<String> pStrings, String outputPath) throws IOException {
		XWPFDocument document = new XWPFDocument();
		FileOutputStream out = new FileOutputStream(new File(outputPath));

		for (String text : pStrings) {
			XWPFParagraph paragraph = document.createParagraph();
			XWPFRun run = paragraph.createRun();
			run.setText(text);
		}

		document.write(out);
		out.close();
		document.close();
	}
	
	@Before
	public void setup() {
		if(driver == null) {
			BrowserFactory browserFactory = new BrowserFactory();
			driver = DriverFactory.createDriver();
			System.out.println("test driver baslatildi. \n" + driver);
			System.out.println(driver.getTitle());
			js = (JavascriptExecutor) driver;
		}	
	}
	
	@Test
	public void test2() {
		page = new Page(driver);
		pList = new ArrayList<WebElement>();
		pStrings = new ArrayList<String>();
		pList = driver.findElements(By.xpath(page.pElementsXpaths));
		for (WebElement itemElement : pList) {
			pStrings.add(itemElement.getText());
		}
		for (String itemString : pStrings) {
			System.out.println(itemString);
		}
		try {
			String projectDirectory = System.getProperty("user.dir");
			String outputPath = projectDirectory + File.separator + "output.docx";
			writeListToDocx(pStrings, outputPath);
			System.out.println("DOCX dosyası proje klasörüne kaydedildi: " + outputPath);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	@After
	public void tearDown() {
		DriverFactory.closeDriver(driver);
		System.out.println("test driver durduruldu.");
	}
}
