package stagingUrl;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import browserStackTestNG.BrowserStackTestNGTest;
import page.OpenStreetMapLocatorPage;

public class TC10_valid_cityName_invalid_state extends BrowserStackTestNGTest
{
	ExtentTest test10 ;
	@BeforeMethod
	public void handleTestMethodName(Method method)
	{
		testName = method.getName(); 
	}
	@Test
	public void valid_cityName_invalid_state() throws InterruptedException, IOException
	{
		System.out.println(
				"Test Objective :To verify whether the appropriate error message is displayed or not, if we give valid city name and invalid state name.");

		System.out.println(
				"Verification : 1. System should attempt based on GeoIP to determine appropraite valid city "
						+ "or suggest a list of multiple cities that match the input for user to select. "
						+ "2. If system is unable to determine city with invalid state/province provided or left off, An error message"
						+ " 'No locations were found using your search criteria. Please try another input address to search for locations.' should be displayed.");
		for (String stagingURL:OpenStreetMapLocatorPage.stagingURL) {

			try {
				test10 = extent.startTest("Valid city name and invalid or not state/province provided.",
						"To verify whether the appropriate error message is displayed or not, if we give valid city name and invalid state name.");

				test10.log(LogStatus.INFO,
						"1. System should attempt based on GeoIP to determine appropraite valid city "
								+ "or suggest a list of multiple cities that match the input for user to select. "
								+ "2. If system is unable to determine city with invalid state/province provided or left off, An error message"
								+ " 'No locations were found using your search criteria. Please try another input address to search for locations.' should be displayed.");
				
				test10.log(LogStatus.INFO, "Staging Url :"+stagingURL);

				System.out.println("Staging URL :"+stagingURL);
				//Initializing WebElemrnt from Page Factory.
				OpenStreetMapLocatorPage onPage = PageFactory.initElements(driver, OpenStreetMapLocatorPage.class);


				//Getting aut url.
				driver.get(stagingURL);


				try {
					wc.until(ExpectedConditions.elementToBeClickable(By.linkText("Close")));
					onPage.closeButton.click();
					
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				
				WebElement addtxt = onPage.addressSearch;

				//Test Cases 

				System.out.println("TC 10.1 :Check system suggest a list of multiple cities that match the input for user to select.");
				test10.log(LogStatus.INFO, "TC 10.1 :Check system suggest a list of multiple cities that match the input for user to select.");



				addtxt.sendKeys("San Dimas, NONAME");

				Thread.sleep(2000);
				onPage.buttonSearch.click();

				wc.until(ExpectedConditions.elementToBeClickable(onPage.closeButton));


				try {
					AssertJUnit.assertEquals(driver.findElement(By.xpath("//*[@class='modal']/p")).getText(), "No locations were found using your search criteria [ San Dimas, NONAME ]. Please try another address to search for locations.");
					System.out.println("Pass.");
					test10.log(LogStatus.PASS, "Pass.");

				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Fail.");
					test10.log(LogStatus.FAIL, "Fail." +e.getMessage()
					+ test10.addScreenCapture(captureScreenMethod(dest)));	
				}

				extent.endTest(test10);

			} catch (Exception e) {
				e.printStackTrace();
				test10.log(LogStatus.FAIL, "Exception Occured. : " + e.getMessage() + " "
						+ test10.addScreenCapture(captureScreenMethod(dest)));
			} 
		}
	}
	@AfterMethod
	public void getResult(ITestResult result)
	{

		test10.log(LogStatus.INFO, "TC 10 executed succesfully.");

		System.out.println("------------------------------------------------------------------------------------");

		driver.quit();


	}
}
