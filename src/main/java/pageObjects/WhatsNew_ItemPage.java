package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pageObjects.PantsPage;
import pageObjects.WelcomePage;

public class WhatsNew_ItemPage {

	WebDriver driver;

	public WhatsNew_ItemPage(WebDriver driver) 
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//span[contains(text(),'Men')]")
	WebElement mens;
	
	@FindBy(linkText="Bottoms")
	WebElement bottoms;
	
	@FindBy(partialLinkText="Pants")
	WebElement pants;
	
	@FindBy(xpath="//div[contains(text(),'Category')]")
	WebElement category;
	
    public WelcomePage openNewTab()
    {
    	driver.switchTo().newWindow(WindowType.TAB);
    	driver.get("https://magento.softwaretestingboard.com/");
    	return new WelcomePage(driver);
    }
	
    
    
    public PantsPage navigate_to_Mens_Bottoms_Pants() 
    {
    	Actions a= new Actions(driver);
		a.moveToElement(mens).click().build().perform();	
		
		bottoms.click();
		category.click();
		pants.click();
		
		return new PantsPage(driver);
    }

}
