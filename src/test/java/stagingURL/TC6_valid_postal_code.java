package stagingURL;

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

public class TC6_valid_postal_code extends BrowserStackTestNGTest
{
	ExtentTest test6 ;
	@BeforeMethod
	public void handleTestMethodName(Method method)
	{
		testName = method.getName(); 
	}
	@Test
	public void valid_postal_code() throws InterruptedException, IOException
	{

		System.out.println(
				"Test Objective :To verify whether the correct search results are shown or not when valid ZIP codes are given into the search textbox.");

		System.out.println(
				"Verification Step : The search results should be displayed for that postaclcode area "
						+ "or within the appropriate proximity of that postal code area "
						+ "or an error message should be displayed if the store is not there in that area.");
		for (String stagingURL:OpenStreetMapLocatorPage.stagingURL) {
			try {

				test6 = extent.startTest("Valid Postal Code.",
						"To verify whether the correct search results are shown or not when valid ZIP codes are given into the search textbox.");

				test6.log(LogStatus.INFO,
						"The search results should be displayed for that postaclcode area or within the appropriate proximity of that postal code area or an error message should be displayed if the store is not there in that area.");

				test6.log(LogStatus.INFO, "Staging URL: "+stagingURL);

				System.out.println("Staging URL :"+stagingURL);

				//Initializing WebElemrnt from Page Factory.
				OpenStreetMapLocatorPage onPage = PageFactory.initElements(driver, OpenStreetMapLocatorPage.class);

				//Getting aut url.
				driver.get(stagingURL);

				WebElement addtxt = onPage.addressSearch;

				wc.until(ExpectedConditions.textToBePresentInElementValue(addtxt, "Arlington Heights  IL 60006"));

				addtxt.click();

				addtxt.clear();

				addtxt.sendKeys("48158");

				onPage.buttonSearch.click();

				Thread.sleep(3000);

				//Test Cases.

				System.out.println("TC 6.1 :To verify whether the correct search results are shown or not when valid ZIP codes are given into the search textbox. ");
				test6.log(LogStatus.INFO, "TC 6.1 :To verify whether the correct search results are shown or not when valid ZIP codes are given into the search textbox. ");
				if (driver.findElement(By.xpath("//h3")).getText().contains("locations found")) {

					System.out.println("Pass");

					test6.log(LogStatus.PASS, "Pass.");

				} else {

					System.out.println("Fail.");
					test6.log(LogStatus.FAIL, "Fail." 
							+ test6.addScreenCapture(captureScreenMethod(dest)));				}

				extent.endTest(test6);

			} catch (Exception e) {

				e.printStackTrace();

				test6.log(LogStatus.FAIL, "Exception Occured. : " + e.getMessage() + " "
						+ test6.addScreenCapture(captureScreenMethod(dest)));
			} 
		}
	}
	@AfterMethod
	public void getResult(ITestResult result)
	{
		test6.log(LogStatus.INFO, "Test cases executed.");

		System.out.println("------------------------------------------------------------------------------------");

		driver.quit();

	}
}
