package tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.WelcomePage;
import pageObjects.SignInPage;
import testComponents.BaseTest;

public class TryScript2 extends BaseTest 
{
	
	//@Test(dataProvider="get_Invalid_SignInData")
	@Test(dataProvider="get_Random_Invalid_SignInData")
	public void SignIn_ErrorValidation(HashMap<String,String> input)
	
	{

		SignInPage signinPage= landingPage.clickSignIn();
		signinPage.Validate_SignIn_Errors(input.get("email"),input.get("pass"));
		this.captureScreenShot(driver,"LoginErrors");
		
	}
	
	@DataProvider

	public Object[][] get_Invalid_SignInData() throws IOException
	{
	  
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\Data\\InvalidAccountDetails.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
		
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