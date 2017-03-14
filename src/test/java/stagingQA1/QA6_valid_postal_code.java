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

public class QA6_valid_postal_code extends BrowserStackTestNGTest
{
	ExtentTest testqa6 ;
	@BeforeMethod
	public void handleTestMethodName(Method method)
	{
		testName = method.getName(); 
	}
	@Test
	public void valid_postal_code() throws InterruptedException, IOException
	{
		try {
			testqa6=extent.startTest("Valid Postal Code.","To verify whether the correct search results are shown or not when valid ZIP codes are given into the search textbox.");
			testqa6.log(LogStatus.INFO, "The search results should be displayed for that postaclcode area or within the appropriate proximity of that postal code area or an error message should be displayed if the store is not there in that area.");
			//Initializing WebElemrnt from Page Factory.
			OpenStreetMapLocatorPage onPage=PageFactory.initElements(driver, OpenStreetMapLocatorPage.class);

			System.out.println("Test Objective :To verify whether the correct search results are shown or not when valid ZIP codes are given into the search textbox.");

			System.out.println("Verification : The search results should be displayed for that postaclcode area or within the appropriate proximity of that postal code area or an error message should be displayed if the store is not there in that area.");
			//Getting aut url.
			driver.get(OpenStreetMapLocatorPage.staging_url_qa1);

			WebElement addtxt=onPage.addressSearch;

			wc.until(ExpectedConditions.textToBePresentInElementValue(addtxt, "Arlington Heights  IL 60006"));

			addtxt.click();

			addtxt.clear();

			addtxt.sendKeys("48158");

			onPage.buttonSearch.click();

			Thread.sleep(3000);

			if(driver.findElement(By.xpath("//h3")).getText().contains("locations found nearby"))
			{
				System.out.println("Pass: Test QA6");
				testqa6.log(LogStatus.PASS, "Location found under provided zip code.");
			}
			else
			{
				System.out.println("Fails :Test QA5");
				testqa6.log(LogStatus.FAIL, "Location is not found under provided zip code.");
			}


		} catch (Exception e) {
			e.printStackTrace();
			testqa6.log(LogStatus.FAIL, "Exception Occured. : "+e.getMessage()+ " " +testqa6.addScreenCapture(captureScreenMethod(dest)));
		}
	}
	@AfterMethod
	public void getResult(ITestResult result)
	{
		extent.endTest(testqa6);
		testqa6.log(LogStatus.INFO, "TC QA6 executed succesfully.");
		driver.quit();


	}
}