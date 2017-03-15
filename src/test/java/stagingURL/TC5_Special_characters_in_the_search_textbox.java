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

public class TC5_Special_characters_in_the_search_textbox extends BrowserStackTestNGTest
{
	ExtentTest testqa5 ;
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

				testqa5 = extent.startTest("Special characters in the search textbox.",
						"To verify whether the appropriate error message is displayed or not, if we give only special characters.");

				testqa5.log(LogStatus.INFO,
						"An error message 'Your search must not include '!#$%^*().' should be displayed.");

				testqa5.log(LogStatus.INFO, "Staging URL: "+stagingURL);

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

				if (driver.findElement(By.xpath("//*[@class='modal']/p")).getText()
						.equals("Your search must not include !$%^*()")) 
				{
					System.out.println("Pass: Test QA5");

					testqa5.log(LogStatus.PASS, "An error message is showned.");

				} else {

					System.out.println("Fails :Test QA5");

					testqa5.log(LogStatus.FAIL, "An error message is not showned.");

					testqa5.log(LogStatus.FAIL, "Test Case Fails:. : " 
							+ testqa5.addScreenCapture(captureScreenMethod(dest)));
				}

				extent.endTest(testqa5);

			} catch (Exception e) {

				e.printStackTrace();

				testqa5.log(LogStatus.FAIL, "Exception Occured. : " + e.getMessage() + " "
						+ testqa5.addScreenCapture(captureScreenMethod(dest)));
			} 
		}
	}
	@AfterMethod
	public void getResult(ITestResult result)
	{
		testqa5.log(LogStatus.INFO, "TC QA5 executed succesfully.");

		System.out.println("------------------------------------------------------------------------------------");

		driver.quit();


	}
}
