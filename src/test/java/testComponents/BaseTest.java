package testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import pageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	
	public WebDriver driver;
	public LandingPage landingPage;
	
	public WebDriver initializeDriver() throws IOException
	 {
	  Properties prop=new Properties();
	  FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\GlobalData.properties");
	  prop.load(fis);
	  String browserName=prop.getProperty("browser");
	  
	  if(browserName.equalsIgnoreCase("chrome"))
	  {
		  WebDriverManager.chromedriver().setup();
		  driver= new ChromeDriver();
	  }
	  else if(browserName.equalsIgnoreCase("firefox"))
	  {
		  WebDriverManager.firefoxdriver().setup();
		  driver= new FirefoxDriver();
	  }
	  else if(browserName.equalsIgnoreCase("edge"))
	  {
		  WebDriverManager.edgedriver().setup();
		  driver= new EdgeDriver();
	  }
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	  driver.manage().window().maximize();
	  return driver;
	 }
	@BeforeMethod(alwaysRun=true)
	public LandingPage LaunchApplication() throws IOException
	{
		driver=initializeDriver();
		landingPage=new LandingPage(driver);
		landingPage.goToWebsite();
		return landingPage;
	}
	public int Random_Number_Generator() {
		int RandNum = (int) (Math.random() * 999 + 100);
		return RandNum;
	}
	
	public String Random_Text() 
	{
	 String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	    // create random string builder
	    StringBuilder sb = new StringBuilder();

	    // create an object of Random class
	    Random random = new Random();

	    // specify length of random string
	    int length = 4;

	    for(int i = 0; i < length; i++) {

	      // generate random index number
	      int index = random.nextInt(alphabet.length());

	      // get character specified by index
	      // from the string
	      char randomChar = alphabet.charAt(index);

	      // append the character to string builder
	      sb.append(randomChar);
	    }

	    String randomString = sb.toString();
	    //System.out.println("Random String is: " + randomString);
		return randomString;

	  }
	
	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
			
	}
	
	
	public static void captureScreenShot(WebDriver driver,String filePath){

		File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File dest=new File(System.getProperty("user.dir") + "//reports//"+filePath+System.currentTimeMillis()+".png");
		try {

		 FileUtils.copyFile(src, dest);
		   }

		catch (IOException e)

		{

		System.out.println(e.getMessage());

		    }

		}

	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException
	{
		//read json to string
	String jsonContent = 	FileUtils.readFileToString(new File(filePath), 
			StandardCharsets.UTF_8);
	
	//String to HashMap- Jackson Databind
	
	ObjectMapper mapper = new ObjectMapper();
	  List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
      });
	  return data;

	}
	
	public void closeCurrent_and_Refresh()
	{
		Set<String> handlesSet = driver.getWindowHandles();
        List<String> handlesList = new ArrayList<String>(handlesSet);
        driver.switchTo().window(handlesList.get(1));
        driver.close();
        driver.switchTo().window(handlesList.get(0));
        driver.navigate().refresh();
	}
	
	public LandingPage clickSignOut()
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(15));
		WebElement optionsdropdown=driver.findElement(By.xpath("//div[1]/ul[1]/li[2]/span[1]/button[1]"));
		
		Actions a=new Actions(driver);
		wait.until(ExpectedConditions.elementToBeClickable(optionsdropdown));
	    a.moveToElement(optionsdropdown).click().build().perform();
	    WebElement signout=driver.findElement(By.linkText("Sign Out"));
	    wait.until(ExpectedConditions.visibilityOf(signout));
	    a.moveToElement(signout).click().build().perform();
	    return new LandingPage(driver);
			
	}
	
	public String logoutMessage()
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.urlToBe("https://magento.softwaretestingboard.com/customer/account/logoutSuccess/"));
		WebElement signedout=driver.findElement(By.xpath("//span[contains(text(),'You are signed out')]"));
		String message=signedout.getText();
		return message;
		
	}
	
	
	//driver.close();
			//driver.switchTo().window(driver.getWindowHandle());
			//driver.switchTo().defaultContent();
	@AfterMethod
	public  void tearDown()
	{
		driver.close();
		driver.quit();
	 }
}

