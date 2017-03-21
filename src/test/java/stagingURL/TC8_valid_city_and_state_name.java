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

public class TC8_valid_city_and_state_name extends BrowserStackTestNGTest
{
	ExtentTest test8 ;
	@BeforeMethod
	public void handleTestMethodName(Method method)
	{
		testName = method.getName(); 
	}
	@Test
	public void valid_postal_code() throws InterruptedException, IOException
	{

		System.out.println("Test Objective :To verify whether the appropriate error message is displayed or not, if we give invalid postal code message.");

		System.out.println("Verification : The search results should be displayed only from that area"
				+ " (city and state/postalcdoe) or it should display a proper error message "
				+ "if the store is not there in that city.");
		for (String stagingURL:OpenStreetMapLocatorPage.stagingURL) {

			try {
				test8=extent.startTest("Valid City and state/province name.","To verify whether the correct search results are shown or not, when valid city and state name is given into the search textbox.");
				test8.log(LogStatus.INFO, "The search results should be displayed only from that area (city and state/postalcdoe) or it should display a proper error message if the store is not there in that city.");
				
				System.out.println("Staging URL :"+stagingURL);
				test8.log(LogStatus.INFO, stagingURL);
				
				//Initializing WebElemrnt from Page Factory.
				OpenStreetMapLocatorPage onPage=PageFactory.initElements(driver, OpenStreetMapLocatorPage.class);

				//Getting aut url.
				driver.get(stagingURL);

				WebElement addtxt=onPage.addressSearch;

				wc.until(ExpectedConditions.textToBePresentInElementValue(addtxt, "Arlington Heights  IL 60006"));

				addtxt.click();

				addtxt.clear();

				addtxt.sendKeys("Anaheim, CA");
				onPage.buttonSearch.click();

				Thread.sleep(5000);
				//Test Cases

				System.out.println("TC 8.1 :The search results should be displayed only from that area"
						+ " (city and state/postalcdoe).");
				test8.log(LogStatus.INFO, "TC 8.1 :The search results should be displayed only from that area"
						+ " (city and state/postalcdoe).");

				try {
					assertEquals(driver.findElement(By.xpath("//li/div[2]/div")).getText(), "Anaheim, CA 92807");				System.out.println("Pass.");
					test8.log(LogStatus.PASS, "Pass.");

				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Fail.");
					test8.log(LogStatus.FAIL, "Fail." +e.getMessage()
					+ test8.addScreenCapture(captureScreenMethod(dest)));	
				}


			} catch (Exception e) {
				e.printStackTrace();
				test8.log(LogStatus.FAIL, "Exception Occured. : "+e.getMessage()+ " " +test8.addScreenCapture(captureScreenMethod(dest)));
			}
		}
	}
	@AfterMethod
	public void getResult(ITestResult result)
	{
		extent.endTest(test8);
		test8.log(LogStatus.INFO, "TC 8 executed succesfully.");
		driver.quit();


	}
}
