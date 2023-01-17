package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;
import pageObjects.WatchesPage;

public class GearPage extends AbstractComponent{
	WebDriver driver;

	public GearPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(linkText="Watches")
	WebElement watches;
	
	public WatchesPage goto_WatchesPage()
	{
		watches.click();
		return new WatchesPage(driver);
	}

}
