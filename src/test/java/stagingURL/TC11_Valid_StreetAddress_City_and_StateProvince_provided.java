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

public class TC11_Valid_StreetAddress_City_and_StateProvince_provided extends BrowserStackTestNGTest
{
	ExtentTest test11 ;
	@BeforeMethod
	public void handleTestMethodName(Method method)
	{
		testName = method.getName(); 
	}
	@Test
	public void valid_cityName_invalid_state() throws InterruptedException, IOException
	{
		System.out.println(
				"Test Objective :To verify whether the correct search results are shown or not, "
				+ "when valid Street Address, City and State name is given into the search textbox.");

		System.out.println(
				"Verification :The search results should be displayed for that area entered.");
		for (String stagingURL:OpenStreetMapLocatorPage.stagingURL) {

			try {
				test11 = extent.startTest("Valid Street Address, City and State/Province provided.",
						"To verify whether the correct search results are shown or not, "
				+ "when valid Street Address, City and State name is given into the search textbox.");

				test11.log(LogStatus.INFO,
						"Verification :The search results should be displayed for that area entered.");
				
				test11.log(LogStatus.INFO, "Staging Url :"+stagingURL);

				System.out.println("Staging URL :"+stagingURL);
				
				//Initializing WebElemrnt from Page Factory.
				OpenStreetMapLocatorPage onPage = PageFactory.initElements(driver, OpenStreetMapLocatorPage.class);


				//Getting aut url.
				driver.get(stagingURL);

				WebElement addtxt = onPage.addressSearch;

				wc.until(ExpectedConditions.textToBePresentInElementValue(addtxt, "Arlington Heights  IL 60006"));

				//Test Cases 

				System.out.println("TC 11.1 :Check search results should be displayed for that area entered.");
				test11.log(LogStatus.INFO, "TC 11.1 :Check search results should be displayed for that area entered.");

				addtxt.click();

				addtxt.clear();

				addtxt.sendKeys("5101 E. La Palma Ave. Anaheim, CA");

				onPage.buttonSearch.click();

				wc.until(ExpectedConditions.presenceOfAllElementsLocatedBy((By.cssSelector("h3"))));


				try {
					assertEquals(driver.findElement(By.xpath("//li/div[2]/div")).getText(), "Anaheim, CA 92807");					System.out.println("Pass.");
					test11.log(LogStatus.PASS, "Pass.");

				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Fail.");
					test11.log(LogStatus.FAIL, "Fail." +e.getMessage()
					+ test11.addScreenCapture(captureScreenMethod(dest)));	
				}

				extent.endTest(test11);

			} catch (Exception e) {
				e.printStackTrace();
				test11.log(LogStatus.FAIL, "Exception Occured. : " + e.getMessage() + " "
						+ test11.addScreenCapture(captureScreenMethod(dest)));
			} 
		}
	}
	@AfterMethod
	public void getResult(ITestResult result)
	{

		test11.log(LogStatus.INFO, "TC 11 executed succesfully.");

		System.out.println("------------------------------------------------------------------------------------");

		driver.quit();


	}
}
