package tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.LandingPage;
import pageObjects.SignInPage;
import pageObjects.WelcomePage;
import testComponents.BaseTest;
import testComponents.Retry;

public class SignIn_FunctionalTest extends BaseTest {
	
	@Test(dataProvider="get_Valid_SignInData")
	public void Valid_Login_Logout(HashMap<String,String> input) throws InterruptedException 
	
	{
		SignInPage signinPage= landingPage.clickSignIn();
		WelcomePage welcomePage= signinPage.SignInSubmit(input.get("email"),input.get("pass"));
		Thread.sleep(5000);
		String welcomeMessage=welcomePage.grabGreeting();
		Assert.assertTrue(welcomeMessage.equalsIgnoreCase("Welcome, "+input.get("fname")+" "+input.get("lname")+"!"));
		//logout
		LandingPage landingPage=welcomePage.clickSignOut();
		String signoutMessage=welcomePage.logoutMessage();
		String expectedMessage="You are signed out";
		Assert.assertEquals(signoutMessage, expectedMessage);
	}
	

	@Test(dataProvider="get_Random_Invalid_SignInData",groups= {"Error Handling"},retryAnalyzer=Retry.class)
	public void SignIn_ErrorValidation(HashMap<String,String> input)
	
	{

		SignInPage signinPage= landingPage.clickSignIn();
		signinPage.Validate_SignIn_Errors(input.get("email"),input.get("pass"));
		this.captureScreenShot(driver,"LoginErrors");
		
	}
	
	
	@DataProvider

	public Object[][] get_Valid_SignInData() throws IOException
	{
	  
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+File.separator+"src"+File.separator+"test"+File.separator+"java"+File.separator+"Data"+File.separator+"ValidAccountDetails.json");
		return new Object[][] {{data.get(0)},{data.get(1)},{data.get(2)},{data.get(3)}};
		
	}
	
	
	@DataProvider

	public Object[][] get_Random_Invalid_SignInData() throws IOException
	{

	    String newAccountEmailId=Random_Number_Generator() + "." + Random_Number_Generator() + "user@email.com";
		String newPassword="Pass"+Random_Text() + "X"+ Random_Number_Generator();
		
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("email", newAccountEmailId);
		map.put("pass",newPassword);
		
		HashMap<String,String> map1 = new HashMap<String,String>();
    	map1.put("email", newAccountEmailId);
		map1.put("pass","");
		
		HashMap<String,String> map2 = new HashMap<String,String>();
    	map2.put("email", "");
		map2.put("pass",newPassword);
		
		HashMap<String,String> map3 = new HashMap<String,String>();
    	map3.put("email", "");
		map3.put("pass","");
		
		return new Object[][] {{map},{map1},{map2},{map3}};
		
	}


	
}
