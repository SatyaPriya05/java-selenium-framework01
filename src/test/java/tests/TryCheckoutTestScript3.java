package tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.CartPage;
import pageObjects.CheckoutPage;
import pageObjects.LandingPage;
import pageObjects.OrderSuccessPage;
import pageObjects.PantsPage;
import pageObjects.PaymentPage;
import pageObjects.SelectedPantPage;
import pageObjects.SignInPage;
import pageObjects.WatchesPage;
import pageObjects.WelcomePage;
import pageObjects.WhatsNew_ItemPage;
import pageObjects.WhatsNew_MainPage;
import testComponents.BaseTest;
import testComponents.Retry;

public class TryCheckoutTestScript3 extends BaseTest{
	
	@Test(dataProvider="getCheckout_Detais",retryAnalyzer=Retry.class)
	public void Order_Checkout(HashMap<String,String> input) throws InterruptedException
	{
		SignInPage signinPage= landingPage.clickSignIn();
		
		WelcomePage welcomePage= signinPage.SignInSubmit("roony1234@gmail.com","Tweety@55");
		WhatsNew_MainPage whatsnewMainPage=welcomePage.goTo_WhatsNew();
		whatsnewMainPage.scrollDown();
		WhatsNew_ItemPage whatsnewItemPage=whatsnewMainPage.add_New_ProductToCart(input.get("newItem"));
		
		welcomePage=whatsnewItemPage.openNewTab();
		
		WatchesPage watchesPage=welcomePage.goto_Gear_WatchesPage();
		watchesPage.select_PriceFilter();
		watchesPage.select_MaterialFilter();
		this.captureScreenShot(driver,"Filtered_RubberWatches");
		
	
		
	}


@DataProvider
public Object[][] getCheckout_Detais() throws IOException
{
  
	List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\Data\\CheckoutDetails.json");
	return new Object[][] {{data.get(0)}};
	
}


	
}



