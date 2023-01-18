package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import abstractComponents.AbstractComponent;

public class WatchesPage extends AbstractComponent {

	WebDriver driver;

	public WatchesPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//div[contains(text(),'Price')]")
	WebElement priceOptions;
	
	@FindBy(xpath="//span[contains(text(),'$40.00')]/parent::*")
	WebElement price;
	
	@FindBy(xpath="//div[contains(text(),'Material')]")
	WebElement materialOptions;
	
	@FindBy(xpath="//a[contains(text(),'Rubber')]")
	WebElement rubber;
	
	@FindBy(css=".product-item")
	List<WebElement> watches;
	
	By productsBy=By.cssSelector(".product-item");
	
	public void select_PriceFilter() 
	{
		priceOptions.click();
		price.click();
	}
	public void select_MaterialFilter() 
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(materialOptions));
		materialOptions.click();
		rubber.click();
	}
		
	
	public WebElement watchByName(String watchName) throws InterruptedException
	{
		
		JavascriptExecutor js=(JavascriptExecutor)driver;
	    js.executeScript("window.scrollBy(0,300)");
		
		//WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
		//wait.until(ExpectedConditions.visibilityOfAllElements(watches));
		
		WebElement prod= watches.stream().filter(product->
		product.findElement(By.cssSelector("div.product.details.product-item-details:nth-child(2) strong.product.name.product-item-name > a.product-item-link")).getText().trim().equals(watchName)).findFirst().orElse(null);
		
		return prod;
	}
	
	public void watch_AddToCart(String productName) throws InterruptedException
	{
    	WebElement prod = watchByName(productName);
		Actions a= new Actions(driver);
		a.moveToElement(prod).build().perform();
		prod.findElement(By.cssSelector(" button[title='Add to Cart']")).click();
	}
	
	
	
	
}
