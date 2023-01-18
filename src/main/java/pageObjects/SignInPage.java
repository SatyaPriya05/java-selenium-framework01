package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import pageObjects.WelcomePage;
import abstractComponents.AbstractComponent;

public class SignInPage extends AbstractComponent{
	
	WebDriver driver;
	public SignInPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(id="email")
	WebElement emailId;
	@FindBy(id="pass")
	WebElement password;
	@FindBy(id="send2")
	WebElement submit;
	//@FindBy(xpath="//div[@class='message-error error message']/div")
	//WebElement errorMessage;
	@FindBy(xpath="//div[@class='message-error error message']/div")
	WebElement invalidCredentialsError;
	@FindBy(id="email-error")
	WebElement blankEmailError;
	@FindBy(id="pass-error")
	WebElement blankPasswordError;
	
		
	public WelcomePage SignInSubmit(String email,String pass)
	{	
	
	emailId.sendKeys(email);
	password.sendKeys(pass);
	submit.click();
	return  new WelcomePage(driver);
	}
	
	public void Validate_SignIn_Errors(String email,String pass)
	{
		emailId.sendKeys(email);
		password.sendKeys(pass);
		submit.click();
		
		String expectedReqMessage="This is a required field.";
		String expectedMessage="The account sign-in was incorrect or your account is disabled temporarily."
				                                                    + " Please wait and try again later.";
		//boolean valid_data;
		
		if(email=="")
		{
			Assert.assertTrue(blankEmailError.isDisplayed());
			Assert.assertEquals(blankEmailError.getText(),expectedReqMessage);
		}
		
		else if(pass=="")
		{
			Assert.assertTrue(blankPasswordError.isDisplayed());
			Assert.assertEquals(blankPasswordError.getText(),expectedReqMessage);
		}
		else 
		{
			
			Assert.assertTrue(invalidCredentialsError.isDisplayed());
        	Assert.assertEquals(invalidCredentialsError.getText(), expectedMessage);
			
		}
	}
		
			
	


}
