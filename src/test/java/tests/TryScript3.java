package tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import pageObjects.CartPage;
import pageObjects.CheckoutPage;
import pageObjects.OrderSuccessPage;
import pageObjects.PantsPage;
import pageObjects.PaymentPage;
import pageObjects.SelectedPantPage;
import pageObjects.SignInPage;
import pageObjects.WatchesPage;
import pageObjects.WelcomePage;
import pageObjects.WhatsNew_ItemPage;
import pageObjects.WhatsNew_MainPage;
import resources.ExtentReporterNG;
import testComponents.BaseTest;

public class TryScript3 extends BaseTest {
	@Test(dataProvider="getCheckout_Detais")
	public void Submit_Order(HashMap<String,String> input) throws InterruptedException
	{
		SignInPage signinPage= landingPage.clickSignIn();
		
		WelcomePage welcomePage= signinPage.SignInSubmit("loony1234@gmail.com","Loony@555");
		WhatsNew_MainPage whatsnewMainPage=welcomePage.goTo_WhatsNew();
		whatsnewMainPage.scrollDown();
		WhatsNew_ItemPage whatsnewItemPage=whatsnewMainPage.add_New_ProductToCart("Phoebe Zipper Sweatshirt");
		
		welcomePage=whatsnewItemPage.openNewTab();
		
		WatchesPage watchesPage=welcomePage.goto_Gear_WatchesPage();
		watchesPage.select_PriceFilter();
		watchesPage.select_MaterialFilter();
		this.captureScreenShot(driver,"Filtered_RubberWatches");
		
		watchesPage.watch_AddToCart("Endurance Watch");
		
		closeCurrent_and_Refresh();
		
		PantsPage pantsPage=whatsnewItemPage.navigate_to_Mens_Bottoms_Pants();
		pantsPage.sort_by_LowPrice();
		
		SelectedPantPage selectedPantPage=pantsPage.add_FirstItem_to_Cart();
		selectedPantPage.select_size_colour();
		selectedPantPage.Add_To_Cart();
		
		CartPage cartPage=selectedPantPage.goto_Cart();
		//cartPage.Edit_PantQuantity();
		cartPage.Increase_PantQuantity();
		cartPage.Assert_Total_CartValue();
		
		CheckoutPage checkoutPage=cartPage.Click_Checkout();
		
		PaymentPage paymentPage=checkoutPage.Fill_Required_Details(input.get("street"),input.get("city"),input.get("state"),input.get("zip"),input.get("country"),input.get("phone"));
		String cartItemsTotal=cartPage.get_cartTotal();
		//PaymentPage paymentPage=new PaymentPage(driver);
		String orderTotal=paymentPage.get_OrderTotal();
		Assert.assertEquals(orderTotal, cartItemsTotal);
		
		OrderSuccessPage ordersuccessPage=paymentPage.click_PlaceOrder();
		ordersuccessPage.Validate_SuccessMessage();
		ordersuccessPage.Validate_OrderId();
		
		
	}


@DataProvider
public Object[][] getCheckout_Detais() throws IOException
{
  
	List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\Data\\CheckoutDetails.json");
	return new Object[][] {{data.get(0)}};
	
}




}

