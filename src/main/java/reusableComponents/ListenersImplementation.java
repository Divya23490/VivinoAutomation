package reusableComponents;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

import testBase.ObjectsRepo;

public class ListenersImplementation extends ObjectsRepo implements ITestListener {
	
	public static WebDriver driver;

	@Override
	public void onTestStart(ITestResult result) {
		//before each test case
		test=extent.createTest(result.getMethod().getMethodName());
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		//before each test case
		test.log(Status.PASS, "Test Case: "+result.getMethod().getMethodName()+"is Passed.");
	}
	@Override
	public void onTestFailure(ITestResult result) {
		test.log(Status.FAIL, "Test Case: " +result.getMethod().getMethodName()+"is Failed.");
		test.log(Status.FAIL, result.getThrowable());
		
		//add screenshot for failed test
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		SimpleDateFormat format=new SimpleDateFormat("dd-MM-yyy HH-mm-ss");
		Date date=new Date();
		String actualDate=format.format(date);	
		String screenshotPath=System.getProperty("user.dir")+"/Reports/Screenshots/"+actualDate+".jpeg";
		File dest=new File(screenshotPath);
		try {
			FileUtils.copyFile(src, dest);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		test.addScreenCaptureFromPath(screenshotPath, "Test case failure screenshot");
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
	
	}

	@Override
	public void onStart(ITestContext context) {
		//setup method call
		extent=ExtentSetup.setupExtentReport();
	}

	@Override
	public void onFinish(ITestContext context) {
		//close extent
		extent.flush();
	}

}
