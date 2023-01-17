package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

import pageObjects.CartPage;
import abstractComponents.AbstractComponent;

public class SelectedPantPage extends AbstractComponent{
	
	WebDriver driver;

	public SelectedPantPage(WebDriver driver) 
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(id="option-label-size-143-item-177")
	WebElement size;
	
	@FindBy(id="option-label-color-93-item-53")
	WebElement green;
	
	@FindBy(id="product-addtocart-button")
	WebElement addToCart;
	
	@FindBy(xpath="//div[@class='header content']/div/a")
	WebElement minicart;
	
	By mincartBy=By.xpath("//div[@class='header content']/div/a");
	
	@FindBy(id="ui-id-1")
	WebElement minicartDrop;
	
	
	public void select_size_colour()
	{
		
		size.click();
		green.click();
	}
	public void Add_To_Cart()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(addToCart));
		addToCart.click();
		
	}
	
	public CartPage goto_Cart() throws InterruptedException
	{
		Thread.sleep(3000);
		minicart.click();
		
		WebElement edit=minicartDrop.findElement(By.xpath("//span[contains(text(),'View and Edit Cart')]"));
		edit.click();
		return new CartPage(driver);
		
		
	}

	


}
