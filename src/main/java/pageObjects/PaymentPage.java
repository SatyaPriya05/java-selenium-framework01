package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.OrderSuccessPage;
import abstractComponents.AbstractComponent;

public class PaymentPage extends AbstractComponent{
	
	WebDriver driver;

	public PaymentPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}

	@FindBy(xpath="//td[@data-th='Order Total']/strong/span")
	WebElement orderTotal;
	
	@FindBy(xpath="//button[@title='Place Order']")
	WebElement placeOrder;
	
	public String get_OrderTotal()
	{
		String total=orderTotal.getText();
		return total;
	}
	
	public OrderSuccessPage click_PlaceOrder() throws InterruptedException
	{
		Thread.sleep(5000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(placeOrder));
		placeOrder.click();
		return new OrderSuccessPage(driver);
		
	}


}
