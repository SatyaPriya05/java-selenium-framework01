package tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.CreateAccountPage;
import testComponents.BaseTest;

public class CreateAccount_FunctionalTest extends BaseTest {
	
	@Test(dataProvider="getRandomData")
	public void createNewAccount(HashMap<String,String> input) 
	{
		CreateAccountPage createaccountPage= landingPage.clickCreateAccount();
		createaccountPage.createAccount(input.get("fname"), input.get("lname"),input.get("email"),input.get("pass"),input.get("pass"));
		String actualMessage=createaccountPage.thankyouMessage();
		String expectedMessage="Thank you for registering with Fake Online Clothing Store.";
		Assert.assertEquals(actualMessage, expectedMessage);
	}
	
	
	@Test(dataProvider="get_Invalid_CreateAccountData",groups= {"Error Handling"})
	public void Validate_createNewAccountErrors(HashMap<String,String> input) throws InterruptedException 
	 
	{
		CreateAccountPage createaccountPage= landingPage.clickCreateAccount();
		createaccountPage.Errors_Validation(input.get("fname"), input.get("lname"),input.get("emailid"),input.get("pass"),input.get("cpass"));
		
		
		this.captureScreenShot(driver, "CreateAccount_RequiredField_Errors");
		
	}
	
	@DataProvider

	public Object[][] getRandomData() throws IOException
	{
	    String newAccountEmailId=Random_Number_Generator() + "." + Random_Number_Generator() + "user@email.com";
		String newPassword="Pass"+Random_Text() + "X"+ Random_Number_Generator();
		String newFirstName="Satya"+Random_Text();
		String newLastName="P"+Random_Text();

		HashMap<String,String> map = new HashMap<String,String>();
		map.put("fname", newFirstName);
		map.put("lname", newLastName);
		map.put("email", newAccountEmailId);
		map.put("pass",newPassword);
		
		return new Object[][] {{map}};
		
	}

	
	@DataProvider

	public Object[][] get_Invalid_CreateAccountData() throws IOException
	{
	  
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+File.separator+"src"+File.separator+"test"+File.separator+"java"+File.separator+"Data"+File.separator+"CreateAccountErrors.json");
		return new Object[][] {{data.get(0)},{data.get(1)},{data.get(2)},{data.get(3)},{data.get(4)}};
		
	}


}


