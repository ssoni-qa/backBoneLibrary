package stagingQA1;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import browserStackTestNG.BrowserStackTestNGTest;
import page.OpenStreetMapLocatorPage;

public class QA3_To_veirfy_on_Mobile_device_is_GPS_location_detected extends BrowserStackTestNGTest {

	ExtentTest testqa3;
	@BeforeMethod
	public void handleTestMethodName(Method method)
	{
		testName = method.getName(); 
	}
	@Test
	public void to_veirfy_on_Mobile_device_is_GPS_location_detected() throws InterruptedException {
		
		testqa3 =extent.startTest("GPS Detected.","To veirfy on Mobile device is GPS location detected.");
		testqa3.log(LogStatus.INFO, "1. Open locator in mobile browser. 2. System should ask if it Is okay to use location services.");
		//Getting aut url.
		driver.get(OpenStreetMapLocatorPage.staging_url_qa1);
		Thread.sleep(300000);
	}
	@AfterMethod
	public void getResult(ITestResult result)
	{
		extent.endTest(testqa3);
		testqa3.log(LogStatus.INFO, "TC 1 executed succesfully.");
		//driver.quit();
		

	}
}
