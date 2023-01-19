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
	@Test
	public void Submit_Order() throws InterruptedException
	{
		SignInPage signinPage= landingPage.clickSignIn();
		
		WelcomePage welcomePage= signinPage.SignInSubmit("loony1234@gmail.com","Loony@555");
		WhatsNew_MainPage whatsnewMainPage=welcomePage.goTo_WhatsNew();
		whatsnewMainPage.scrollDown();
		WhatsNew_ItemPage whatsnewItemPage=whatsnewMainPage.add_New_ProductToCart("Phoebe Zipper Sweatshirt");
		
		welcomePage=whatsnewItemPage.openNewTab();
		
	}

}

