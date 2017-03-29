package zeusUrl;

import org.testng.annotations.Test;
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

public class TC13_Valid_Street_address_with_invalid_City_name_and_valid_StateProvince_name extends BrowserStackTestNGTest
{
	ExtentTest test13 ;
	@BeforeMethod
	public void handleTestMethodName(Method method)
	{
		testName = method.getName(); 
	}
	@Test
	public void invalid_postal_code() throws InterruptedException, IOException
	{
		System.out.println(
				"Test Objective :To verify whether the appropriate error message is displayed or not,"
						+ " if we give invalid city name and valid state name.");

		System.out.println(
				"Verification : An error message 'No locations were found using your search criteria. "
						+ "Please try another input address to search for locations.' should be displayed..");
		for (String stagingURL:OpenStreetMapLocatorPage.zeusUrl) {

			try {
				test13 = extent.startTest(
						"Valid Street address with invalid City name and valid State/Province name.",
						"To verify whether the appropriate error message or suggestions are displayed, if we give invalid City name with valid street address and valid state name.");

				test13.log(LogStatus.INFO, "1. An error message 'No locations were found using your search criteria."
						+ " Please try another input address to search for locations.' should be displayed.");

				test13.log(LogStatus.INFO, "Staging Url :"+stagingURL);

				System.out.println("Staging URL :"+stagingURL);

				//Initializing WebElemrnt from Page Factory.
				OpenStreetMapLocatorPage onPage = PageFactory.initElements(driver, OpenStreetMapLocatorPage.class);


				//Getting aut url.
				driver.get(stagingURL);

				WebElement addtxt = onPage.addressSearch;

				wc.until(ExpectedConditions.textToBePresentInElementValue(addtxt, "Arlington Heights  IL 60006"));

				addtxt.click();

				addtxt.clear();

				addtxt.sendKeys("674 W WASHINGTON RD, INVALIDNAME, GA");

				onPage.buttonSearch.click();

				wc.until(ExpectedConditions.elementToBeClickable(onPage.closeButton));

				if (onPage.modalmsg.getText().contains(
						"No locations were found using your search criteria [ 674 W WASHINGTON RD, INVALIDNAME, GA US ]. Please try another input address to search for locations.")) {

					System.out.println("Pass: Test QA10");

					test13.log(LogStatus.PASS, "Modal Message is displayed.");

				} else {

					System.out.println("Fails :Test QA10");

					test13.log(LogStatus.FAIL, "Modal Message is not displayed.");

					test13.log(LogStatus.FAIL, "Modal Message is not displayed. :"
							+ test13.addScreenCapture(captureScreenMethod(dest)));
				}

				extent.endTest(test13);

			} catch (Exception e) {

				e.printStackTrace();

				test13.log(LogStatus.FAIL, "Exception Occured. : " + e.getMessage() + " "
						+ test13.addScreenCapture(captureScreenMethod(dest)));
			} 
		}
	}
	@AfterMethod
	public void getResult(ITestResult result)
	{
		test13.log(LogStatus.INFO, "TC QA10 executed succesfully.");

		System.out.println("------------------------------------------------------------------------------------");

		driver.quit();


	}
}
