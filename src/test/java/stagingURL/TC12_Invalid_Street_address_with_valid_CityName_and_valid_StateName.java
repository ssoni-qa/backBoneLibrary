package stagingURL;

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

public class TC12_Invalid_Street_address_with_valid_CityName_and_valid_StateName extends BrowserStackTestNGTest
{
	ExtentTest test12 ;
	@BeforeMethod
	public void handleTestMethodName(Method method)
	{
		testName = method.getName(); 
	}
	@Test
	public void invalid_Street_address_with_valid_CityName_and_valid_StateName() throws InterruptedException, IOException
	{
		System.out.println(
				"Test Objective :To verify whether the appropriate error message or suggestions are displayed,"
				+ " if we give invalid street address with valid city and state name.");

		System.out.println(
				"Verification :The results should be based on valid city and state/province");
		for (String stagingURL:OpenStreetMapLocatorPage.stagingURL) {

			try {
				test12 = extent.startTest("Invalid Street address with valid City name and valid State name.",
						"To verify whether the appropriate error message or suggestions are displayed, if we give invalid street address with valid city and state name.");

				test12.log(LogStatus.INFO,
						"Verification :The results should be based on valid city and state/province.");
				
				test12.log(LogStatus.INFO, "Staging Url :"+stagingURL);

				System.out.println("Staging URL :"+stagingURL);
				//Initializing WebElemrnt from Page Factory.
				OpenStreetMapLocatorPage onPage = PageFactory.initElements(driver, OpenStreetMapLocatorPage.class);


				//Getting aut url.
				driver.get(stagingURL);

				WebElement addtxt = onPage.addressSearch;

				wc.until(ExpectedConditions.textToBePresentInElementValue(addtxt, "Arlington Heights  IL 60006"));

				//Test Cases 

				System.out.println("TC 12.1 :Check system suggest the results should be based on valid city and state/province");
				test12.log(LogStatus.INFO, "TC 12.1 :Check system suggest the results should be based on valid city and state/province.");

				addtxt.click();

				addtxt.clear();

				addtxt.sendKeys("INVALID, Anaheim, Ca");

				onPage.buttonSearch.click();

				wc.until(ExpectedConditions.presenceOfAllElementsLocatedBy((By.cssSelector("h3"))));


				try {
					assertEquals(driver.findElement(By.xpath("//li/div[2]/div")).getText(), "Anaheim, CA 92807");					System.out.println("Pass.");
					test12.log(LogStatus.PASS, "Pass.");

				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Fail.");
					test12.log(LogStatus.FAIL, "Fail." +e.getMessage()
					+ test12.addScreenCapture(captureScreenMethod(dest)));	
				}

				extent.endTest(test12);

			} catch (Exception e) {
				e.printStackTrace();
				test12.log(LogStatus.FAIL, "Exception Occured. : " + e.getMessage() + " "
						+ test12.addScreenCapture(captureScreenMethod(dest)));
			} 
		}
	}
	@AfterMethod
	public void getResult(ITestResult result)
	{

		test12.log(LogStatus.INFO, "TC 10 executed succesfully.");

		System.out.println("------------------------------------------------------------------------------------");

		driver.quit();


	}
}
