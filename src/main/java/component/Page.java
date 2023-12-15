package component;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Page {
	
	private WebDriver driver;
	public String pElementsXpaths = "//*[@id='-post-rtjson-content']/p";
	public String buttonElementString = "//div[@class='inline-block mt-[2px] ml-px']";
	

	public Page(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath = "//div[@class='inline-block mt-[2px] ml-px']")
	public WebElement buttonElement;	
	 // //faceplate-tracker[@source='post']//shreddit-comment-tree
	// //div[@class='w-fit inline-block mt-[2px] ml-px']

}

