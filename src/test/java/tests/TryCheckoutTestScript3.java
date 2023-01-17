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

public class TryCheckoutTestScript3 extends BaseTest{
	
	@Test(dataProvider="getCheckout_Detais")
	public void Order_Checkout(HashMap<String,String> input) throws InterruptedException
	{
		SignInPage signinPage= landingPage.clickSignIn();
		
		WelcomePage welcomePage= signinPage.SignInSubmit("tweety12345@gmail.com","Tweety@55");
		WhatsNew_MainPage whatsnewMainPage=welcomePage.goTo_WhatsNew();
		whatsnewMainPage.scrollDown();
		WhatsNew_ItemPage whatsnewItemPage=whatsnewMainPage.add_New_ProductToCart(input.get("newItem"));
		
		welcomePage=whatsnewItemPage.openNewTab();
		
		WatchesPage watchesPage=welcomePage.goto_Gear_WatchesPage();
		watchesPage.select_PriceFilter();
		watchesPage.select_MaterialFilter();
		this.captureScreenShot(driver,"Filtered_RubberWatches");
		
		// Select any Watch
		watchesPage.watch_AddToCart(input.get("watch"));
		
		//Close current tab and refresh dafault page
		closeCurrent_and_Refresh();
		
		closeCurrent_and_Refresh();
		
		PantsPage pantsPage=whatsnewItemPage.navigate_to_Mens_Bottoms_Pants();
		pantsPage.sort_by_LowPrice();
		
		SelectedPantPage selectedPantPage=pantsPage.add_FirstItem_to_Cart();
		selectedPantPage.select_size_colour();
		selectedPantPage.Add_To_Cart();
		
		CartPage cartPage=selectedPantPage.goto_Cart();
		cartPage.Increase_PantQuantity();
		cartPage.Assert_Total_CartValue();
		String cartItemsTotal=cartPage.get_cartTotal();
		
		CheckoutPage checkoutPage=cartPage.Click_Checkout();
		
		//PaymentPage paymentPage=checkoutPage.Fill_Required_Details(input.get("street"),input.get("city"),input.get("state"),input.get("zip"),input.get("country"),input.get("phone"));
		//String cartItemsTotal=cartPage.get_cartTotal();
		//PaymentPage paymentPage=new PaymentPage(driver);
		//String orderTotal=paymentPage.get_OrderTotal();
		//Assert.assertEquals(orderTotal, cartItemsTotal);
		
		//OrderSuccessPage ordersuccessPage=paymentPage.click_PlaceOrder();
		//ordersuccessPage.Validate_SuccessMessage();
		//ordersuccessPage.Validate_OrderId();
		
		
	}


@DataProvider
public Object[][] getCheckout_Detais() throws IOException
{
  
	List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\Data\\CheckoutDetails.json");
	return new Object[][] {{data.get(0)}};
	
}


	
}



