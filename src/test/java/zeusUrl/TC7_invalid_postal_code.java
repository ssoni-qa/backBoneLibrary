package zeusUrl;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;


import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import browserStackTestNG.BrowserStackTestNGTest;
import page.OpenStreetMapLocatorPage;

public class TC7_invalid_postal_code extends BrowserStackTestNGTest
{
	ExtentTest test7 ;
	@BeforeMethod
	public void handleTestMethodName(Method method)
	{
		testName = method.getName(); 
	}
	@Test
	public void valid_postal_code() throws InterruptedException, IOException
	{
		System.out.println(
				"Test Objective :To verify whether the appropriate error message is displayed or not, if we give invalid postal code message.");

		System.out.println(
				"Verification : You entered an address which could not be geocoded [ 00000 ]. Please try another input address to search for locations.");
		for (String zeusUrl:OpenStreetMapLocatorPage.zeusUrl) {

			try {
				test7 = extent.startTest("Invalid Postal Code.",
						"To verify whether the appropriate error message is displayed or not, if we give invalid postal code message");

				test7.log(LogStatus.INFO,
						"The search result should display message ,'You entered an address which could not be geocoded [ 00000 ]. Please try another input address to search for locations'.");

				test7.log(LogStatus.INFO, "Zeus URL: "+zeusUrl);

				System.out.println("Zeus URL :"+zeusUrl);
				test7.log(LogStatus.INFO, zeusUrl);

				//Initializing WebElemrnt from Page Factory.
				OpenStreetMapLocatorPage onPage = PageFactory.initElements(driver, OpenStreetMapLocatorPage.class);

				
				//Getting aut url.
				driver.get(zeusUrl);

				WebElement addtxt = onPage.addressSearch;

				wc.until(ExpectedConditions.textToBePresentInElementValue(addtxt, "Arlington Heights  IL 60006"));

				addtxt.click();

				addtxt.clear();

				addtxt.sendKeys("0123456789");

				onPage.buttonSearch.click();

				wc.until(ExpectedConditions.elementToBeClickable(onPage.closeButton));

				//Test Cases
				System.out.println("TC 7.1 :Check for Invalid Geo Code Validation message."
						+ "You entered an address which could not be geocoded [ 00000 ]. Please try another input address to search for locations.");
				test7.log(LogStatus.INFO, "TC 7.1 :Check for Invalid Geo Code Validation message."
						+ "You entered an address which could not be geocoded [ 00000 ]. Please try another input address to search for locations.");

				try {
					AssertJUnit.assertEquals(driver.findElement(By.cssSelector("p")).getText(), "You entered an address which could not be geocoded [ 0123456789 US ]. Please try another input address to search for locations.");
					System.out.println("Pass.");
					test7.log(LogStatus.PASS, "Pass.");

				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Fail.");
					test7.log(LogStatus.FAIL, "Fail." +e.getMessage()
					+ test7.addScreenCapture(captureScreenMethod(dest)));	
				}


			} catch (Exception e) {
				e.printStackTrace();
				test7.log(LogStatus.FAIL, "Exception Occured. : " + e.getMessage() + " "
						+ test7.addScreenCapture(captureScreenMethod(dest)));
			} 
		}
	}
	@AfterMethod
	public void getResult(ITestResult result)
	{
		test7.log(LogStatus.INFO, "TC 7 executed succesfully.");

		System.out.println("------------------------------------------------------------------------------------");

		driver.quit();


	}
}
