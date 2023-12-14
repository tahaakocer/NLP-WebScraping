package component;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Page {
	
	private WebDriver driver;
	private String pElementsXpaths = "//*[@id='-post-rtjson-content']/p";

	public Page(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath = "//faceplate-partial/div[1]/button/span/span[2]")
	WebElement buttonElement;	
	
}

