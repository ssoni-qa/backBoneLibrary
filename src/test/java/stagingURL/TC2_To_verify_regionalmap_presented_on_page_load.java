package stagingURL;

import static org.testng.Assert.assertEquals;

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

public class TC2_To_verify_regionalmap_presented_on_page_load extends BrowserStackTestNGTest
{
	ExtentTest test2 ;
	@BeforeMethod
	public void handleTestMethodName(Method method)
	{
		testName = method.getName(); 
	}
	@Test
	public void verify_regionalmap_presented_on_page_load() throws InterruptedException, IOException,Exception
	{
		System.out.println("Test Objective: To verify if the regional map is presented on page load. ");

		System.out.println("Verification Step: 1. The map should reflect your current geographical location "
				+ "or the geographical location of your IP address "
				+ "( please note due to the VPN connection, "
				+ "your IP address might not match your current location )");
		for (String stagingURL:OpenStreetMapLocatorPage.stagingURL) {
			
			try {
				test2 = extent.startTest("GEOIP detected Map",
						"To verify if the regional map is presented on page load");
				test2.log(LogStatus.INFO, "Staging URL :"+stagingURL);
				test2.log(LogStatus.INFO, "Verification Steps: The map should reflect your current geographical location or "
						+ "the geographical location of your IP address " + "( please note due to the VPN connection, "
						+ "your IP address might not match your current office location )");
				System.out.println("Staging URL :"+stagingURL);

				//Initializing WebElemrnt from Page Factory.
				OpenStreetMapLocatorPage onPage = PageFactory.initElements(driver, OpenStreetMapLocatorPage.class);

				//Getting aut url.
				driver.get(stagingURL);

				WebElement addtxt = onPage.addressSearch;

				wc.until(ExpectedConditions.textToBePresentInElementValue(addtxt, "Arlington Heights  IL 60006"));

				Select countrySelect = new Select(onPage.searchCountryOption);
				WebElement selectedCountry = countrySelect.getFirstSelectedOption();

				System.out.println("TC 2 : Check map should reflect your current geographical location.");
				test2.log(LogStatus.INFO, "TC 2 : Check map should reflect your current geographical location.");

				try {
					assertEquals(onPage.addressSearch.getAttribute("value"), "Arlington Heights  IL 60006");
					assertEquals(selectedCountry.getText(), "United States Of America");
					System.out.println("Pass.");
					test2.log(LogStatus.PASS, "Pass.");
				} catch (AssertionError e) {
					System.out.println("Fail.");
					test2.log(LogStatus.FAIL, "Fail : " + e.getMessage() + " "
							+ test2.addScreenCapture(captureScreenMethod(dest)));
				}


			} catch (Exception e) {
				System.out.println("Exception Occured :"+e.getMessage());
				test2.log(LogStatus.FAIL, "Exception Occured. : " + e.getMessage() + " "
						+ test2.addScreenCapture(captureScreenMethod(dest)));
			} 
		}
		extent.endTest(test2);

	}
	@AfterMethod
	public void getResult()
	{
		System.out.println("------------------------------------------------------------------------------------");
		driver.quit();


	}
}
