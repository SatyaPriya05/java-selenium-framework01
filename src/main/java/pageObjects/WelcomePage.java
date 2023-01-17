package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import abstractComponents.AbstractComponent;
import pageObjects.WatchesPage;
import pageObjects.WhatsNew_MainPage;

public class WelcomePage extends AbstractComponent{
	
	WebDriver driver;

	public WelcomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
    
	@FindBy(xpath="//header/div[1]/div[1]/ul[1]/li[1]/span[1]")
    WebElement greetinglocator;
	
	@FindBy(id="ui-id-3")
	WebElement whatsnewlocator;
	
	@FindBy(xpath="//a[@id='ui-id-6']")
	WebElement gear;
	
	@FindBy(linkText="Watches")
	WebElement watches;
	
	public String grabGreeting() throws InterruptedException 
	{
		String message;
		message=greetinglocator.getText();
		return message;
		
	}
	
	public LandingPage clickSignOut()
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(15));
		WebElement optionsdropdown=driver.findElement(By.xpath("//div[1]/ul[1]/li[2]/span[1]/button[1]"));
		
		Actions a=new Actions(driver);
		wait.until(ExpectedConditions.elementToBeClickable(optionsdropdown));
	    a.moveToElement(optionsdropdown).click().build().perform();
	    WebElement signout=driver.findElement(By.linkText("Sign Out"));
	    wait.until(ExpectedConditions.visibilityOf(signout));
	    a.moveToElement(signout).click().build().perform();
	    return new LandingPage(driver);
			
	}
	public String logoutMessage()
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.urlToBe("https://magento.softwaretestingboard.com/customer/account/logoutSuccess/"));
		WebElement signedout=driver.findElement(By.xpath("//span[contains(text(),'You are signed out')]"));
		String message=signedout.getText();
		return message;
		
	}
	public WhatsNew_MainPage goTo_WhatsNew()
	{
		 whatsnewlocator.click();	
	     return new WhatsNew_MainPage(driver);  
	}
	
	
	public WatchesPage goto_Gear_WatchesPage()
	
	{
		Actions a= new Actions(driver);
		a.moveToElement(gear).build().perform();	
		a.moveToElement(watches).click().build().perform();
			
		return new WatchesPage(driver);
			
	}


}
