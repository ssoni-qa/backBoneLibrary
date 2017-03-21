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

public class TC5_Special_characters_in_the_search_textbox extends BrowserStackTestNGTest
{
	ExtentTest test5 ;
	@BeforeMethod
	public void handleTestMethodName(Method method)
	{
		testName = method.getName(); 
	}
	@Test
	public void special_characters_in_the_search_textbox() throws InterruptedException, IOException
	{
		System.out.println(
				"Test Objective :To verify whether the appropriate error message is displayed or not, if we give only special characters.");

		System.out.println(
				"Verification Step:An error message 'Your search must not include !#$%^*().' should be displayed.");

		for (String stagingURL:OpenStreetMapLocatorPage.stagingURL) {

			try {

				test5 = extent.startTest("Special characters in the search textbox.",
						"To verify whether the appropriate error message is displayed or not, if we give only special characters.");

				test5.log(LogStatus.INFO,
						"An error message 'Your search must not include '!#$%^*().' should be displayed.");

				test5.log(LogStatus.INFO, "Staging URL: "+stagingURL);

				System.out.println("Staging URL :"+stagingURL);

				//Initializing WebElemrnt from Page Factory.
				OpenStreetMapLocatorPage onPage = PageFactory.initElements(driver, OpenStreetMapLocatorPage.class);

				//Getting aut url.
				driver.get(stagingURL);

				WebElement addtxt = onPage.addressSearch;

				wc.until(ExpectedConditions.textToBePresentInElementValue(addtxt, "Arlington Heights  IL 60006"));

				addtxt.click();

				addtxt.clear();

				addtxt.sendKeys("!$%^*()");

				onPage.buttonSearch.click();

				wc.until(ExpectedConditions.elementToBeClickable(onPage.closeButton));

				//Test Case

				System.out.println("TC 5.1 :An error message 'Your search must not include '!#$%^*().' should be displayed.");
				test5.log(LogStatus.INFO, "TC 5.1 :An error message 'Your search must not include '!#$%^*().' should be displayed.");
				
				try {
					assertEquals(driver.findElement(By.xpath("//*[@class='modal']/p")).getText(), "Your search must not include !$%^*()");
					System.out.println("Pass.");
					test5.log(LogStatus.PASS, "Pass.");
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Fail.");
					test5.log(LogStatus.FAIL, "Fail." +e.getMessage()
					+ test5.addScreenCapture(captureScreenMethod(dest)));
				}
				

				extent.endTest(test5);

			} catch (Exception e) {

				e.printStackTrace();

				test5.log(LogStatus.FAIL, "Exception Occured. : " + e.getMessage() + " "
						+ test5.addScreenCapture(captureScreenMethod(dest)));
			} 
		}
	}
	@AfterMethod
	public void getResult(ITestResult result)
	{
		test5.log(LogStatus.INFO, "TC 5 exceuted.");

		System.out.println("------------------------------------------------------------------------------------");

		driver.quit();


	}
}
