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

public class QA8_valid_city_and_state_name extends BrowserStackTestNGTest
{
	ExtentTest testqa8 ;
	@BeforeMethod
	public void handleTestMethodName(Method method)
	{
		testName = method.getName(); 
	}
	@Test
	public void valid_postal_code() throws InterruptedException, IOException
	{
		try {
			testqa8=extent.startTest("Valid City and state/province name.","To verify whether the correct search results are shown or not, when valid city and state name is given into the search textbox.");
			testqa8.log(LogStatus.INFO, "The search results should be displayed only from that area (city and state/postalcdoe) or it should display a proper error message if the store is not there in that city.");
			//Initializing WebElemrnt from Page Factory.
			OpenStreetMapLocatorPage onPage=PageFactory.initElements(driver, OpenStreetMapLocatorPage.class);

			System.out.println("Test Objective :To verify whether the appropriate error message is displayed or not, if we give invalid postal code message.");

			System.out.println("Verification : The search results should be displayed only from that area"
					+ " (city and state/postalcdoe) or it should display a proper error message "
					+ "if the store is not there in that city.");
			//Getting aut url.
			driver.get(OpenStreetMapLocatorPage.staging_url_qa1);

			WebElement addtxt=onPage.addressSearch;

			wc.until(ExpectedConditions.textToBePresentInElementValue(addtxt, "Arlington Heights  IL 60006"));

			addtxt.click();

			addtxt.clear();

			addtxt.sendKeys("JONESBORO");
			onPage.buttonSearch.click();

			wc.until(ExpectedConditions.textToBePresentInElement(By.xpath("//h3"), "Please select your location"));


			if(driver.findElement(By.xpath("//*[@class='content-list address-suggestions']/li[1]")).getText().contains("Jonesboro"))
			{
				System.out.println("Pass: Test QA8");
				testqa8.log(LogStatus.PASS, "Search result is displayed for searched area.");
			}
			else
			{
				System.out.println("Fails :Test QA8");
				testqa8.log(LogStatus.FAIL, "Search result is not  displayed for searched area.");
			}


		} catch (Exception e) {
			e.printStackTrace();
			testqa8.log(LogStatus.FAIL, "Exception Occured. : "+e.getMessage()+ " " +testqa8.addScreenCapture(captureScreenMethod(dest)));
		}
	}
	@AfterMethod
	public void getResult(ITestResult result)
	{
		extent.endTest(testqa8);
		testqa8.log(LogStatus.INFO, "TC QA8 executed succesfully.");
		driver.quit();


	}
}
