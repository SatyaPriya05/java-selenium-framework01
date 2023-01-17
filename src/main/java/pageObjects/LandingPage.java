package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;
import pageObjects.CreateAccountPage;
import pageObjects.SignInPage;

public class LandingPage extends AbstractComponent{

	 WebDriver driver;
		public LandingPage(WebDriver driver)
		{
			this.driver=driver;
			PageFactory.initElements(driver,this);
			
		}
		
		@FindBy(linkText="Sign In")
		WebElement signinLink;
		
		@FindBy(linkText="Create an Account")
		WebElement createlink;
		
		public void goToWebsite()
		{
			driver.get("https://magento.softwaretestingboard.com/");
		}
		
		public SignInPage clickSignIn()
		{
		
		   signinLink.click();
		   return new SignInPage(driver);
		}
		
		public CreateAccountPage clickCreateAccount()
		{
			WebElement createlink=driver.findElement(By.linkText("Create an Account"));
			createlink.click();
			return new CreateAccountPage(driver);
		}
		
		
		
	
}
