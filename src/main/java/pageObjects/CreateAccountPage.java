package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import abstractComponents.AbstractComponent;

public class CreateAccountPage extends AbstractComponent {

	WebDriver driver;
	public CreateAccountPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	//Input fields
	@FindBy(id="firstname")
	WebElement cfname;
	@FindBy(id="lastname")
	WebElement clname ;
	@FindBy(id="is_subscribed")
	WebElement check;
	@FindBy(name="email")
	WebElement cemail ;
	@FindBy(id="password")
	WebElement cpassword;
	@FindBy(id="password-confirmation")
	WebElement cpassc;
	@FindBy(xpath="//button[@title='Create an Account']/span")
	WebElement csubmit;
	
	//Errors fields
	@FindBy(id="password-error")
	WebElement passwordLenError;
	@FindBy(id="password-confirmation-error")
	WebElement samepassError;
	
	//Required field errors
	@FindBy(id="firstname-error")
	WebElement fnameerror;
	@FindBy(id="lastname-error")
	WebElement lnameerror; 
	@FindBy(id="email_address-error")
	WebElement emailerror;
	@FindBy(id="password-error")
	WebElement passerror;
	@FindBy(xpath="//div[@for='password-confirmation']")
	WebElement confirmpasserror;
	
		
	public void createAccount(String newFirstName,String newLastName, String newAccountEmailId, String newPassword, String newcPassword)
	{
	cfname.sendKeys(newFirstName);
	clname.sendKeys(newLastName);
	check.click();
	cemail.sendKeys(newAccountEmailId);
	cpassword.sendKeys(newPassword);
	cpassc.sendKeys(newcPassword);
	csubmit.click();
		
	}
	
	public String thankyouMessage()
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.urlToBe("https://magento.softwaretestingboard.com/customer/account/"));
		WebElement thankyou=driver.findElement(By.xpath("//div[contains(text(),'Thank you for registering')]"));
		String message=thankyou.getText();
		return message;
	}
	
	public void Errors_Validation(String newFirstName,String newLastName, String newAccountEmailId, String newPassword, String newcPassword)
	{
		createAccount(newFirstName,newLastName,newAccountEmailId,newPassword,newcPassword);
		firstNameError(newFirstName);
		lastNameError(newLastName);
		emailIdErrors(newAccountEmailId);
		passwordErrors(newPassword);
		confirmPassError(newPassword,newcPassword);
	}
	
	
	
		
     public void firstNameError(String newFirstName)
     {
    	 String expectedReq="This is a required field.";
    	 //first name is null
    	 if(newFirstName=="")
    	 {
    		 Assert.assertTrue(fnameerror.isDisplayed());
    		 Assert.assertEquals(fnameerror.getText(), expectedReq);    		 
    		 
    	 }
    	
     }
     
     public void lastNameError(String newLastName)
     {
    	 String expectedReq="This is a required field.";
    	 //last name is null
    	 if(newLastName=="")
    	 {
    		 Assert.assertTrue(lnameerror.isDisplayed());
    		 Assert.assertEquals(lnameerror.getText(), expectedReq);    		 
    		 
    	 }
    	 
     }
     
     public void emailIdErrors(String newAccountEmailId)
     {
    	 String expectedReq="This is a required field.";
    	 String expectedPattern="Please enter a valid email address (Ex: johndoe@domain.com).";
    	 //email is null
    	 if(newAccountEmailId=="")
    	 {
    		 Assert.assertTrue(emailerror.isDisplayed());
    		 Assert.assertEquals(emailerror.getText(), expectedReq); 
    		 
    	 }
    	 //email not null
    	 else
    	 {
    		 Boolean email_valid;
    		  
    		 // checks for words,numbers before @symbol and between "@" and ".".
             // Checks only 2 or 3 alphabets after "."
             if (newAccountEmailId.matches("[\\w]+@[\\w]+\\.[a-zA-Z]{2,3}"))
             {
                email_valid = true;
             }
             else
             {
                 email_valid = false;
                 Assert.assertTrue(emailerror.isDisplayed());
        		 Assert.assertEquals(emailerror.getText(), expectedPattern); 
             }
           
    	 }
     }
    
     public void passwordErrors(String newPassword)
     {
    	 String expectedReq="This is a required field.";
    	 String lengthError="Minimum length of this field must be equal or greater than 8 symbols. "
    	 		                               + "Leading and trailing spaces will be ignored.";
    	 String patternError="Minimum of different classes of characters in password is 3."
    	 		                + " Classes of characters: Lower Case, Upper Case, Digits, Special Characters.";
    	 
    	
    	String pattern1="^(?=\\D*\\d)(?=[^a-z]*[a-z])(?=[^A-Z]*[A-Z])[\\w~@#$%^&*+=`|{}:;!.?\\\"()\\[\\]-]{8,}$"
    			+ "|^(?=\\\\D*\\\\d)(?=[^a-z]*[a-z])(?=[^A-Z]*[A-Z]){8,}$"
    			 +"^(?=\\D*\\d)(?=[^a-z]*[a-z])[\\w~@#$%^&*+=`|{}:;!.?\\\"()\\[\\]-]{8,}$"
    			 +"^(?=\\D*\\d)(?=[^A-Z]*[A-Z])[\\w~@#$%^&*+=`|{}:;!.?\\\"()\\[\\]-]{8,}$"
    			 +"^(?=[^a-z]*[a-z])(?=[^A-Z]*[A-Z])[\\w~@#$%^&*+=`|{}:;!.?\\\"()\\[\\]-]{8,}$"
    			 +"^(?=\\D*\\d)(?=[^a-z]*[a-z])(?=[^A-Z]*[A-Z])[\\w~@#$%^&*+=`|{}:;!.?\\\"()\\[\\]-]{8,}$";
    	
    	 Boolean password_valid;
    	
    	 if(newPassword=="")
    	 {
    		 Assert.assertTrue(passerror.isDisplayed());
    		 Assert.assertEquals(passerror.getText(),expectedReq);
    	 }
    	 else
    	 {
    	 if(newPassword.length()>=8)
    	{
    	
    		 if(newPassword.matches(pattern1))
    		 {
    	      password_valid=true;
    		 }
    		 else
    		 {
    			 password_valid=false;
    			 Assert.assertTrue(passerror.isDisplayed());
    			 Assert.assertEquals(passerror.getText(),patternError);
    		 }
    	 
    	}
    	 
    	 else
    	 {
    		 password_valid=false;
    		 
    		 Assert.assertTrue(passerror.isDisplayed());
    		 Assert.assertEquals(passerror.getText(),lengthError); 
    		 
    	 }
    	 }
    	 //Password must have atleast one uppercase character: (.*[A-Z].*)  .*[a-zA-Z].*
    	 //Password must have atleast one lowercase character: (.*[a-z].*)
    	 //Password must have atleast one number: (.*[0-9].*)
    	 //Password must have atleast one special character among @#$%: (.*[@,#,$,%].*$)  (.*[,~,!,@,#,$,%,^,&,*,(,),-,_,=,+,[,{,],},|,;,:,<,>,/,?].*$)
     }
 
 
     public void confirmPassError(String newPassword, String newcPassword)
		{
			String expectedReq="This is a required field.";
			//confirm pass null
			if(newcPassword=="")
			{
				Assert.assertTrue(confirmpasserror.isDisplayed());
				Assert.assertEquals(confirmpasserror.getText(), expectedReq);
				
			}
			//confirm pass not null
			else  
			{
				//pass not null
				if(newPassword!="") 
				
				 // if(newPassword!="" && newcPassword!="")
			     {
				 // if(newPassword.equals(newcPassword))
					if(!(newPassword.equals(newcPassword)))
				    {
					
					 String expectedSame="Please enter the same value again.";
					 Assert.assertTrue(samepassError.isDisplayed());
					 Assert.assertEquals(samepassError.getText(),expectedSame);
					//System.out.println(" p n cp same error");
				    }
			     }
		         
				 //else if(newPassword=="")
				//pass is null 
				else 
				{
				Assert.assertTrue(samepassError.isDisplayed());
			    }
				
			} 
			
		}
}

		
	


