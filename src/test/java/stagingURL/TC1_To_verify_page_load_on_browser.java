package stagingURL;

import java.io.IOException;
import java.lang.reflect.Method;

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

public class TC1_To_verify_page_load_on_browser extends BrowserStackTestNGTest
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

		for (String stagingURL:OpenStreetMapLocatorPage.stagingURL) {
			try {
				//
				test1 = extent.startTest("QA 1: Page Load, GeoIP and Cursor position", 
						"To verify page loads on browser.");
				test1.log(LogStatus.INFO,
						"Verification Steps :The page should load, if GeoIP detection on, " + "should attemp to determine location, "
								+ "if geoIP off, then request address information."
								+ "2. By default the cursor position should be in the search text box.");

				test1.log(LogStatus.INFO, "Staging Url :"+stagingURL);

				System.out.println("Staging URL :"+stagingURL);

				//Initializing WebElement from Page Factory.

				OpenStreetMapLocatorPage onPage = PageFactory.initElements(driver, OpenStreetMapLocatorPage.class);


				//Getting aut url.
				driver.get("http://zeus.dev.where2getit.com/yong_test/backbone.QA1.html");

				WebElement addtxt = onPage.addressSearch;

				wc.until(ExpectedConditions.textToBePresentInElementValue(addtxt, "Arlington Heights  IL 60006"));

				//TC 1.1

				System.out.println("TC 1 : Check page loaded on browser.");

				test1.log(LogStatus.INFO, "TC 1 : Check page loaded on browser.");

				if (driver.getTitle().contains("Problem in loading page.")) {
					System.out.println("Fail.");

					test1.log(LogStatus.FAIL,
							"Failure ScrrenShots:" + test1.addScreenCapture(captureScreenMethod(dest)));

				} else {

					System.out.println("Pass");

					test1.log(LogStatus.PASS, "Pass.");

				}

				//TC 1.2

				System.out.println("TC 1.2 :Validate if GEoIP is 'ON' , location is detected.");
				test1.log(LogStatus.INFO, "TC 1.2 :Validate if GEoIP is 'ON' , location is detected.");

				if (!onPage.addressSearch.getAttribute("value").equals("")) {
                    System.out.println(onPage.addressSearch.getAttribute("value"));
					System.out.println("Pass.");

					test1.log(LogStatus.PASS, "Pass.");

				} else {

					System.out.println("Fail");

					test1.log(LogStatus.FAIL, "Failure ScreenShots :"
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
		System.out.println("TC 1 executed.");

		System.out.println("------------------------------------------------------------------------------------");

		driver.quit();

	}

}
