package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pageObjects.WhatsNew_ItemPage;
import abstractComponents.AbstractComponent;

public class WhatsNew_MainPage extends AbstractComponent {
	
	WebDriver driver;

	public WhatsNew_MainPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(css=".product-item-info ")
	List<WebElement> products;
	
	By productsBy=By.cssSelector(".product-item-info ");
	 
	public void scrollDown()
	{
	JavascriptExecutor js=(JavascriptExecutor)driver;
    js.executeScript("window.scrollBy(0,1200)");
	}
		
	public WhatsNew_ItemPage add_New_ProductToCart(String productName) throws InterruptedException
	{
		 List<WebElement> products=driver.findElements(By.cssSelector(".product-item-info "));
		   
		   WebElement prod =products.stream().filter(product->
		   product.findElement(By.cssSelector("div.product-item-details strong.product-item-name > a.product-item-link")).getText().equals(productName)).findFirst().orElse(null);
			
		   Actions a= new Actions(driver);
	       a.moveToElement(prod).build().perform();
		   prod.findElement(By.cssSelector("button[title='Add to Cart']")).click();
		
		return new WhatsNew_ItemPage(driver);
		
	}


}
