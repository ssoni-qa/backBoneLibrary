package stagingQA1;

import java.io.IOException;
import java.lang.reflect.Method;

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

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import browserStackTestNG.BrowserStackTestNGTest;
import page.OpenStreetMapLocatorPage;

public class QA7_invalid_postal_code extends BrowserStackTestNGTest
{
	ExtentTest testqa7 ;
	@BeforeMethod
	public void handleTestMethodName(Method method)
	{
		testName = method.getName(); 
	}
	@Test
	public void valid_postal_code() throws InterruptedException, IOException
	{
		try {
			testqa7=extent.startTest("Invalid Postal Code.","To verify whether the appropriate error message is displayed or not, if we give invalid postal code message");
			testqa7.log(LogStatus.INFO, "The search result should display message ,'You entered an address which could not be geocoded [ 00000 ]. Please try another input address to search for locations'.");
			//Initializing WebElemrnt from Page Factory.
			OpenStreetMapLocatorPage onPage=PageFactory.initElements(driver, OpenStreetMapLocatorPage.class);

			System.out.println("Test Objective :To verify whether the appropriate error message is displayed or not, if we give invalid postal code message.");

			System.out.println("Verification : 2. You entered an address which could not be geocoded [ 00000 ]. Please try another input address to search for locations.");
			//Getting aut url.
			driver.get(OpenStreetMapLocatorPage.staging_url_qa1);

			WebElement addtxt=onPage.addressSearch;

			wc.until(ExpectedConditions.textToBePresentInElementValue(addtxt, "Arlington Heights  IL 60006"));

			addtxt.click();

			addtxt.clear();

			addtxt.sendKeys("0123456789");

			onPage.buttonSearch.click();
			
			wc.until(ExpectedConditions.elementToBeClickable(onPage.closeButton));

			if(onPage.modalmsg.getText().contains("You entered an address which could not be geocoded [ 0123456789"))
			{
				System.out.println("Pass: Test QA7");
				testqa7.log(LogStatus.PASS, "Modal Message is displayed fro invalid geoCode.");
			}
			else
			{
				System.out.println("Fails :Test QA7");
				testqa7.log(LogStatus.FAIL, "Modal Message is not displayed for invalid geoCode.");
			}


		} catch (Exception e) {
			e.printStackTrace();
			testqa7.log(LogStatus.FAIL, "Exception Occured. : "+e.getMessage()+ " " +testqa7.addScreenCapture(captureScreenMethod(dest)));
		}
	}
	@AfterMethod
	public void getResult(ITestResult result)
	{
		extent.endTest(testqa7);
		testqa7.log(LogStatus.INFO, "TC QA7 executed succesfully.");
		driver.quit();


	}
}
