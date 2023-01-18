package testComponents;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import pageObjects.LandingPage;
import resources.ExtentReporterNG;

public class Listeners  extends BaseTest implements ITestListener
{
	@Override
	public WebDriver initializeDriver() throws IOException {
		// TODO Auto-generated method stub
		//return super.initializeDriver();
	}

	@Override
	public LandingPage LaunchApplication() throws IOException {
		// TODO Auto-generated method stub
		//return super.LaunchApplication();
	}

	@Override
	public int Random_Number_Generator() {
		// TODO Auto-generated method stub
		//return super.Random_Number_Generator();
	}

	@Override
	public String Random_Text() {
		// TODO Auto-generated method stub
		//return super.Random_Text();
	}

	@Override
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		// TODO Auto-generated method stub
		//return super.getScreenshot(testCaseName, driver);
	}

	@Override
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		// TODO Auto-generated method stub
		//return super.getJsonDataToMap(filePath);
	}

	@Override
	public void closeCurrent_and_Refresh() {
		// TODO Auto-generated method stub
		//super.closeCurrent_and_Refresh();
	}

	@Override
	public LandingPage clickSignOut() {
		// TODO Auto-generated method stub
		//return super.clickSignOut();
	}

	@Override
	public String logoutMessage() {
		// TODO Auto-generated method stub
		//return super.logoutMessage();
	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub
		//super.tearDown();
	}

	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);//unique thread id(ErrorValidationTest)->test
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		extentTest.get().log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
        extentTest.get().fail(result.getThrowable());//
		
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver")
					.get(result.getInstance());
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
				
		String filePath = null;
		try {
			
			filePath = getScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();
	}

	

}


