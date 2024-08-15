package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SerachProductLandingPage extends BasePage{

	public SerachProductLandingPage(WebDriver driver) {
		super(driver);
		
	}
	
	@FindBy(xpath="//div[@class='caption']//h4")
	WebElement searchResult;
	
	public boolean isSearchProductPageExists() {
		try {
		return (searchResult.isDisplayed());
		}
		catch(Exception e) {
			return false;
		}
	}
}
