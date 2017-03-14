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

public class QA10_valid_cityName_invalid_state extends BrowserStackTestNGTest
{
	ExtentTest testqa10 ;
	@BeforeMethod
	public void handleTestMethodName(Method method)
	{
		testName = method.getName(); 
	}
	@Test
	public void valid_cityName_invalid_state() throws InterruptedException, IOException
	{
		try {
			testqa10=extent.startTest("Valid city name and invalid or not state/province provided.",
					"To verify whether the appropriate error message is displayed or not, if we give valid city name and invalid state name.");
			testqa10.log(LogStatus.INFO, "1. System should attempt based on GeoIP to determine appropraite valid city "
					+ "or suggest a list of multiple cities that match the input for user to select. "
					+ "2. If system is unable to determine city with invalid state/province provided or left off, An error message"
					+ " 'No locations were found using your search criteria. Please try another input address to search for locations.' should be displayed.");
			//Initializing WebElemrnt from Page Factory.
			OpenStreetMapLocatorPage onPage=PageFactory.initElements(driver, OpenStreetMapLocatorPage.class);

			System.out.println("Test Objective :To verify whether the appropriate error message is displayed or not, if we give valid city name and invalid state name.");

			System.out.println("Verification : 1. System should attempt based on GeoIP to determine appropraite valid city "
					+ "or suggest a list of multiple cities that match the input for user to select. "
					+ "2. If system is unable to determine city with invalid state/province provided or left off, An error message"
					+ " 'No locations were found using your search criteria. Please try another input address to search for locations.' should be displayed.");
			//Getting aut url.
			driver.get(OpenStreetMapLocatorPage.staging_url_qa1);

			WebElement addtxt=onPage.addressSearch;

			wc.until(ExpectedConditions.textToBePresentInElementValue(addtxt, "Arlington Heights  IL 60006"));

			addtxt.click();

			addtxt.clear();

			addtxt.sendKeys("JONESBORO, NONAME");
			onPage.buttonSearch.click();

			wc.until(ExpectedConditions.textToBePresentInElement(By.xpath("//h3"), "Please select your location"));


			if(driver.findElement(By.xpath("//*[@class='content-list address-suggestions']/li[1]")).getText().contains("Jonesboro"))
			{
				System.out.println("Pass: Test QA10");
				testqa10.log(LogStatus.PASS, "Suggested Location is found. ");
			}
			else
			{
				System.out.println("Fails :Test QA10");
				testqa10.log(LogStatus.FAIL, "Suggested Location is not found. ");
			}


		} catch (Exception e) {
			e.printStackTrace();
			testqa10.log(LogStatus.FAIL, "Exception Occured. : "+e.getMessage()+ " " +testqa10.addScreenCapture(captureScreenMethod(dest)));
		}
	}
	@AfterMethod
	public void getResult(ITestResult result)
	{
		extent.endTest(testqa10);
		testqa10.log(LogStatus.INFO, "TC QA10 executed succesfully.");
		driver.quit();


	}
}
