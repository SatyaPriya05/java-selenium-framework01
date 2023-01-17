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
import pageObjects.SelectedPantPage;

public class PantsPage extends AbstractComponent{
	
	WebDriver driver;

	public PantsPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(id="sorter")
	WebElement sort;
	
	@FindBy(xpath="//div[@class='products wrapper grid products-grid']/ol/li[1]")
	WebElement firstItem;
	
	public void sort_by_LowPrice()
	{
		SelectFromDropdownByVisibleText(sort,"Price");
	}
	
	public SelectedPantPage add_FirstItem_to_Cart()
	{
		
		Actions a= new Actions(driver);
		a.moveToElement(firstItem).build().perform();
		
		WebElement addItem=firstItem.findElement(By.xpath("//div[@class='product-item-inner']/div/div/form/button"));
		addItem.click();
		
		String itemPageTitle=firstItem.findElement(By.xpath("//a[@class='product-item-link']")).getText();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.titleContains(itemPageTitle));
				
		return new SelectedPantPage(driver);
	}

}
