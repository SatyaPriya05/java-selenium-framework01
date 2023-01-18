package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import pageObjects.CheckoutPage;
import abstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{

	WebDriver driver;

	public CartPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//table[@id='shopping-cart-table']/tbody")
	List<WebElement> cartItems;
	
	@FindBy(xpath="//tbody[2]/tr[1]/td[3]/div/div/label/input[@title='Qty']")
	WebElement inputPantNum;
	
	@FindBy(xpath="//button[@class='action update']")
	WebElement update;
	
	@FindBy(xpath="//tr[@class='grand totals']/td/strong/span")
	WebElement carttotal;
	
	@FindBy(xpath="//table[1]/tbody[1]/tr[1]/td[4]/span/span")
	WebElement watch;
	
	@FindBy(xpath="//table[1]/tbody[2]/tr[1]/td[4]/span/span")
	WebElement pants;
	
	@FindBy(xpath="//li/button[@title='Proceed to Checkout']")
	WebElement checkout;
	
	public void Increase_PantQuantity() throws InterruptedException
	{
		driver.navigate().refresh();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.elementToBeClickable(inputPantNum));
		inputPantNum.clear();
		inputPantNum.sendKeys("4");
		update.click();
		Thread.sleep(3000);
	}
	public String get_cartTotal() 
	{
		String total=carttotal.getText();
		return total;
		
	}
	
	public void Assert_Total_CartValue()
	{
		Assert.assertTrue(carttotal.isDisplayed());
			
	}
	public CheckoutPage Click_Checkout() throws InterruptedException 
	{
		try {
			checkout.click();
		}
		catch(Exception e)
		{
			
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.elementToBeClickable(checkout));
			checkout.click();
			
		}
		return new CheckoutPage(driver);
		
	}
	
}
