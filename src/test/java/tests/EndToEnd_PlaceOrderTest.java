package tests;

import java.io.IOException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.CartPage;
import pageObjects.CheckoutPage;
import pageObjects.CreateAccountPage;
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

public class EndToEnd_PlaceOrderTest  extends BaseTest{
	@Test (dataProvider="getRandomData")
	public void Order_Placement(HashMap<String,String> input) throws InterruptedException, IOException 
	{
		
		String firstName=input.get("fname");
		String lastName=input.get("lname");
		String emailId=input.get("email");
		String password=input.get("pass");
		
		//Create Account and validate message
		CreateAccountPage createaccountPage= landingPage.clickCreateAccount();
		createaccountPage.createAccount(firstName,lastName,emailId,password,password);
		String actualMessage=createaccountPage.thankyouMessage();
		String expectedMessage="Thank you for registering with Fake Online Clothing Store.";
		Assert.assertEquals(actualMessage, expectedMessage);
		driver.close();
		
		//Sign In with account created and validate message
		LandingPage landingPage=LaunchApplication();		
		SignInPage signinPage= landingPage.clickSignIn();
		WelcomePage welcomePage= signinPage.SignInSubmit(emailId,password);
		Thread.sleep(5000);
        String welcomeMessage=welcomePage.grabGreeting();
		Assert.assertTrue(welcomeMessage.equalsIgnoreCase("Welcome, "+firstName+" "+lastName+"!"));
		
		//SignOut and validate message
		landingPage=welcomePage.clickSignOut();
		String signoutMessage=welcomePage.logoutMessage();
		String expMessage="You are signed out";
		Assert.assertEquals(signoutMessage, expMessage);
		
		//SignIn
        signinPage= landingPage.clickSignIn();
		welcomePage= signinPage.SignInSubmit(emailId,password);
		
		//Add any item from  Whats New section
		WhatsNew_MainPage whatsnewMainPage=welcomePage.goTo_WhatsNew();
		whatsnewMainPage.scrollDown();
		WhatsNew_ItemPage whatsnewItemPage=whatsnewMainPage.add_New_ProductToCart(input.get("newItem"));
		
		//Open New Tab from current tab
		welcomePage=whatsnewItemPage.openNewTab();
		
		//Navigate to Gear-Watches //Apply Filters// Screenshot
		WatchesPage watchesPage=welcomePage.goto_Gear_WatchesPage();
		watchesPage.select_PriceFilter();
		watchesPage.select_MaterialFilter();
		this.captureScreenShot(driver,"Filtered_RubberWatches");
		
		// Select any Watch
		watchesPage.watch_AddToCart(input.get("watch"));
		
		//Close current tab and refresh dafault page
		closeCurrent_and_Refresh();
		
		//Navigate to Mens-Bottoms-Pants //Apply sort
		PantsPage pantsPage=whatsnewItemPage.navigate_to_Mens_Bottoms_Pants();
		pantsPage.sort_by_LowPrice();
		
		//Select first Item //Select size and colour// Add to cart
		SelectedPantPage selectedPantPage=pantsPage.add_FirstItem_to_Cart();
		selectedPantPage.select_size_colour();
		selectedPantPage.Add_To_Cart();
		
		//Increase Pants quantity // Assert cart value
		CartPage cartPage=selectedPantPage.goto_Cart();
		cartPage.Increase_PantQuantity();
		cartPage.Assert_Total_CartValue();
		String cartItemsTotal=cartPage.get_cartTotal();
		
		//Checkout
		CheckoutPage checkoutPage=cartPage.Click_Checkout();
		
		//Fill required details //select table rate radio
		PaymentPage paymentPage=checkoutPage.Fill_Required_Details(input.get("street"),input.get("city"),input.get("state"),input.get("zip"),input.get("country"),input.get("phone"));
		
		//Validate order total with cart value
		String orderTotal=paymentPage.get_OrderTotal();
		Assert.assertEquals(orderTotal, cartItemsTotal);
		
		//Place order and validate success message and order id
		OrderSuccessPage ordersuccessPage=paymentPage.click_PlaceOrder();
		ordersuccessPage.Validate_SuccessMessage();
		ordersuccessPage.Validate_OrderId();
		
	}
	
	
	@DataProvider

	public Object[][] getRandomData() throws IOException
	{
	    String newAccountEmailId=Random_Number_Generator() + "." + Random_Number_Generator() + "user@email.com";
		String newPassword="Pass"+Random_Text() + "X"+ Random_Number_Generator();
		String newFirstName="Satya"+Random_Text();
		String newLastName="P"+Random_Text();
		String newAccountEmailId1=Random_Number_Generator() + "." + Random_Number_Generator() + "user@email.com";
		String newPassword1="Pass"+Random_Text() + "X"+ Random_Number_Generator();
		String newFirstName1="Satya"+Random_Text();
		String newLastName1="P"+Random_Text();

		HashMap<String,String> map = new HashMap<String,String>();
		map.put("fname", newFirstName);
		map.put("lname", newLastName);
		map.put("email", newAccountEmailId);
		map.put("pass",newPassword);
		map.put("newItem", newLastName);
		map.put("newItem","Gobi HeatTec® Tee");
		map.put("watch","Endurance Watch");
		map.put("street","Gandhi Nagar");
		map.put("city","Hyderabad");
		map.put("state","California");
		map.put("zip","1234-6589");
		map.put("country","United States");
		map.put("phone","9876543210");
		
		HashMap<String,String> map1 = new HashMap<String,String>();
		map1.put("fname", newFirstName1);
		map1.put("lname", newLastName1);
		map1.put("email", newAccountEmailId1);
		map1.put("pass",newPassword1);
		map1.put("newItem", newLastName1);
		map1.put("newItem","Stellar Solar Jacket");
		map1.put("watch","Aim Analog Watch");
		map1.put("street","Richie Rich street");
		map1.put("city","Amsterdam");
		map1.put("state","New York");
		map1.put("zip","1784-6589");
		map1.put("country","United States");
		map1.put("phone","9888843210");
		
		return new Object[][] {{map},{map1}};
		
	}


}
