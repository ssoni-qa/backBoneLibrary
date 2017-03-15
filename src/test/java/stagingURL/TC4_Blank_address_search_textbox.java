package stagingURL;

import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import browserStackTestNG.BrowserStackTestNGTest;
import page.OpenStreetMapLocatorPage;

public class TC4_Blank_address_search_textbox extends BrowserStackTestNGTest
{
	ExtentTest testqa4 ;
	@BeforeMethod
	public void handleTestMethodName(Method method)
	{
		testName = method.getName(); 
	}
	@Test
	public void blank_address_search_textbox() throws InterruptedException, IOException
	{
		System.out.println(
				"Test Objective: To verify whether a proper error message is displayed or not, for blank address/search text box.");

		System.out.println(
				"Verification Step: An error message 'Please enter address.' should be displayed. Message can be customized by Client.");

		for (String stagingURL:OpenStreetMapLocatorPage.stagingURL) {

			try {

				testqa4 = extent.startTest("Blank address/search textbox.",
						"To verify whether a proper error message is displayed or not, for blank address/search text box.");

				testqa4.log(LogStatus.INFO, "Staging URL :"+stagingURL);

				testqa4.log(LogStatus.INFO,
						"An error message 'Please enter address.' should be displayed. Message can be customized by Client.");

				System.out.println("Staging URL :"+stagingURL);

				//Initializing WebElemrnt from Page Factory.
				OpenStreetMapLocatorPage onPage = PageFactory.initElements(driver, OpenStreetMapLocatorPage.class);

				//Getting aut url.
				driver.get(stagingURL);

				WebElement addtxt = onPage.addressSearch;

				wc.until(ExpectedConditions.textToBePresentInElementValue(addtxt, "Arlington Heights  IL 60006"));

				addtxt.click();

				addtxt.clear();

				onPage.buttonSearch.click();

				wc.until(ExpectedConditions.elementToBeClickable(onPage.closeButton));

				if (driver.findElement(By.xpath("//*[@class='modal']/p")).getText()

						.equals("Please enter an address.")) {

					System.out.println("Pass: Test QA4");

					testqa4.log(LogStatus.PASS, "An error message is showned.");

				} else {

					System.out.println("Fails :Test QA4");

					testqa4.log(LogStatus.FAIL, "An error message is not showned.");

					testqa4.log(LogStatus.FAIL, "Test Case Fails:. : " 
							+ testqa4.addScreenCapture(captureScreenMethod(dest)));

				}

				extent.endTest(testqa4);

			} catch (Exception e) {

				e.printStackTrace();

				testqa4.log(LogStatus.FAIL, "Exception Occured. : " + e.getMessage() + " "
						+ testqa4.addScreenCapture(captureScreenMethod(dest)));
			} 
		}
	}
	@AfterMethod
	public void getResult(ITestResult result)
	{
		testqa4.log(LogStatus.INFO, "TC QA4 executed succesfully.");

		System.out.println("------------------------------------------------------------------------------------");

		driver.quit();

	}
}
