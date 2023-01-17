package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import abstractComponents.AbstractComponent;

public class OrderSuccessPage extends AbstractComponent {
	WebDriver driver;

	public OrderSuccessPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//span[contains(text(),'Thank you')]")
	WebElement message;
	
	@FindBy(xpath="//a/strong")
	WebElement orderLoc;
	
	public void Validate_SuccessMessage()
	{
		String successPageurl="https://magento.softwaretestingboard.com/checkout/onepage/success/";
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.urlToBe(successPageurl));
		
		String actualMessage=message.getText();
		Boolean thankyouMessage=message.isDisplayed();
		String expectedMessage="Thank you for your purchase!";
		Assert.assertTrue(thankyouMessage);
		Assert.assertEquals(actualMessage, expectedMessage);
		
	}
	
	public void Validate_OrderId()
	{
		Boolean orderId=orderLoc.isDisplayed();
	    Assert.assertTrue(orderId);	
	}


}
