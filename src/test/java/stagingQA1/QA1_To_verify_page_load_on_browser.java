package stagingQA1;

import java.io.IOException;
import java.lang.reflect.Method;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import browserStackTestNG.BrowserStackTestNGTest;
import page.OpenStreetMapLocatorPage;

public class QA1_To_verify_page_load_on_browser extends BrowserStackTestNGTest
{
	ExtentTest test1 ;

	@BeforeMethod
	public void handleTestMethodName(Method method)
	{
		testName = method.getName(); 

	}
	@Test
	public void verify_page_load_on_browser() throws InterruptedException, IOException
	{
		System.out.println("QA 1 : To Verify page loads on browser.");
		System.out.println("Verification Steps: The page should load, "
				+ "if GeoIP detection on, should attemp to determine location, "
				+ "if geoIP off, then request address information.");
		for (String stagingURL:OpenStreetMapLocatorPage.url) {
			try {
				//
				test1 = extent.startTest("QA 1: Page Load, GeoIP and Cursor position", 
						"To verify page loads on browser.");
                test1.log(LogStatus.INFO, "Staging Url :"+stagingURL);
				test1.log(LogStatus.INFO,
						"Verification Steps :The page should load, if GeoIP detection on, " + "should attemp to determine location, "
								+ "if geoIP off, then request address information."
								+ "2. By default the cursor position should be in the search text box.");
				//Initializing WebElement from Page Factory.
				OpenStreetMapLocatorPage onPage = PageFactory.initElements(driver, OpenStreetMapLocatorPage.class);
				System.out.println("Staging URL :"+stagingURL);
				
				//Getting aut url.
				driver.get(stagingURL);
				WebElement addtxt = onPage.addressSearch;
				wc.until(ExpectedConditions.textToBePresentInElementValue(addtxt, "Arlington Heights  IL 60006"));
				//Verify Page Load on browser
				if (driver.getTitle().equals("Problem loading page")) {
					System.out.println("Fail: Error in loading Page.");
					test1.log(LogStatus.FAIL,
							"Error in loading Page. : " + test1.addScreenCapture(captureScreenMethod(dest)));

				} else {
					System.out.println("Pass:Page loaded succesfully.");
					test1.log(LogStatus.PASS, "Page loaded succesfully.");

				}
				if (!onPage.addressSearch.getAttribute("value").equals("")) {
					System.out.println("Pass: GeoIP is on , location is detected.");
					test1.log(LogStatus.PASS, "GeoIP is on , location is detected.");
				} else {
					System.out.println("Fail:GeoIP is on , location is detected.");
					test1.log(LogStatus.FAIL, "GeoIP is on , location is detected. : "
							+ test1.addScreenCapture(captureScreenMethod(dest)));
				}
				extent.endTest(test1);


			} catch (Exception e) {
				e.printStackTrace();
				test1.log(LogStatus.FAIL, "Exception Occured. : " + e.getMessage() + " "
						+ test1.addScreenCapture(captureScreenMethod(dest)));
			} 
		}

	}

	@AfterMethod
	public void getResult(ITestResult result)
	{
		test1.log(LogStatus.INFO, "TC QA1 executed succesfully.");
		System.out.println("------------------------------------------------------------------------------------");
		driver.quit();

	}

}
