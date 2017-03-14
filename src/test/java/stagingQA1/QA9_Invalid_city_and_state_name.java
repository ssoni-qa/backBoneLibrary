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

public class QA9_Invalid_city_and_state_name extends BrowserStackTestNGTest
{
	ExtentTest testqa9 ;
	@BeforeMethod
	public void handleTestMethodName(Method method)
	{
		testName = method.getName(); 
	}
	@Test
	public void invalid_postal_code() throws InterruptedException, IOException
	{
		try {
			testqa9=extent.startTest("Invalid city name and valid state name.","To verify whether the appropriate error message is displayed or not, if we give invalid city name and valid state name.");
			testqa9.log(LogStatus.INFO, "An error message 'No locations were found using your search criteria. Please try another input address to search for locations.' should be displayed.");
			//Initializing WebElemrnt from Page Factory.
			OpenStreetMapLocatorPage onPage=PageFactory.initElements(driver, OpenStreetMapLocatorPage.class);

			System.out.println("Test Objective :To verify whether the appropriate error message is displayed or not, if we give invalid city name and valid state name.");

			System.out.println("Verification : An error message 'No locations were found using your search criteria. Please try another input address to search for locations.' should be displayed..");
			//Getting aut url.
			driver.get(OpenStreetMapLocatorPage.staging_url_qa1);

			WebElement addtxt=onPage.addressSearch;

			wc.until(ExpectedConditions.textToBePresentInElementValue(addtxt, "Arlington Heights  IL 60006"));

			addtxt.click();

			addtxt.clear();

			addtxt.sendKeys("NONAME");
			onPage.buttonSearch.click();

			wc.until(ExpectedConditions.elementToBeClickable(onPage.closeButton));


			if(onPage.modalmsg.getText().contains("No locations were found using your search criteria [ noname US ]. Please try another input address to search for locations."))
			{
				System.out.println("Pass: Test QA9");
				testqa9.log(LogStatus.PASS, "Modal Message is displayed fro invalid city state name.");
			}
			else
			{
				System.out.println("Fails :Test QA9");
				testqa9.log(LogStatus.FAIL, "Modal Message is not displayed fro invalid city state name.");
			}


		} catch (Exception e) {
			e.printStackTrace();
			testqa9.log(LogStatus.FAIL, "Exception Occured. : "+e.getMessage()+ " " +testqa9.addScreenCapture(captureScreenMethod(dest)));
		}
	}
	@AfterMethod
	public void getResult(ITestResult result)
	{
		extent.endTest(testqa9);
		testqa9.log(LogStatus.INFO, "TC QA9 executed succesfully.");
		driver.quit();


	}
}
