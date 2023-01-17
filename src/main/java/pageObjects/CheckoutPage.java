package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.PaymentPage;
import abstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
		
	@FindBy(xpath="//div[contains(@name,'street.0')]/div/input")
	WebElement streetInput;
	
	@FindBy(xpath="//div[contains(@name,'city')]/div/input")
	WebElement cityInput;
	
	@FindBy(xpath="//div[contains(@name,'postcode')]/div/input")
	WebElement zipInput;
	
	@FindBy(xpath="//div[contains(@name,'telephone')]/div/input")
	WebElement phoneInput;

	@FindBy(xpath="//div[contains(@name,'region_id')]/div/select")
	WebElement stateInput;
	
	@FindBy(xpath="//div[contains(@name,'country_id')]/div/select")
	WebElement countryInput;
	
	@FindBy(xpath="//td/input[@value='tablerate_bestway']")
	WebElement tableRate;
	
	@FindBy(xpath="//div[@id='shipping-method-buttons-container']/div/button")
	WebElement next;

	
	public PaymentPage Fill_Required_Details(String street,String city,String state,String zip,String country,String phone) throws InterruptedException
	{
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.urlToBe("https://magento.softwaretestingboard.com/checkout/#shipping"));
		Thread.sleep(3000);
		streetInput.sendKeys(street);
		cityInput.sendKeys(city);
		SelectFromDropdownByVisibleText(stateInput,state);
		zipInput.sendKeys(zip);
		SelectFromDropdownByVisibleText(countryInput,country);
		phoneInput.sendKeys(phone);
		wait.until(ExpectedConditions.elementToBeClickable(tableRate));
		tableRate.click();
		next.click();
		
		return new PaymentPage(driver);
	}
	
	
	


}
