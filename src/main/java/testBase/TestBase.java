package testBase;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.EnterSearchBoxPageObjects;
import reusableComponents.PropertiesOperations;

public class TestBase extends ObjectsRepo{
	public static WebDriver driver;
	@SuppressWarnings("deprecation")
	public void LaunchBrowserAndNavigate() throws Exception {
		String browser=PropertiesOperations.getPropertyValueByKey("browser");
		String url=PropertiesOperations.getPropertyValueByKey("url");
		
		switch(browser) {
		case "chrome" :{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	
			driver.get(url);		
			driver.manage().window().maximize();
		break;	
		}
		default:
			break;
		}
	}
	
	@BeforeMethod
	public void setupMethod() throws Exception {
		LaunchBrowserAndNavigate();
		enterSearch=new EnterSearchBoxPageObjects();
		
	}
	@AfterMethod
	public void cleanUp() {
		driver.quit();
	}
}
