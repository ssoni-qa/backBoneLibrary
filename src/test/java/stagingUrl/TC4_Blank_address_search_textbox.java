package stagingUrl;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import static org.testng.Assert.assertEquals;

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
	ExtentTest test4 ;
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
				"Verification Step: An error message 'Please enter address.' should be displayed.");

		for (String stagingURL:OpenStreetMapLocatorPage.stagingURL) {

			try {

				test4 = extent.startTest("Blank address/search textbox.",
						"To verify whether a proper error message is displayed or not, for blank address/search text box.");

				test4.log(LogStatus.INFO, "Staging URL :"+stagingURL);

				test4.log(LogStatus.INFO,
						"An error message 'Please enter address.' should be displayed. Message can be customized by Client.");

				System.out.println("Staging URL :"+stagingURL);

				//Initializing WebElemrnt from Page Factory.
				OpenStreetMapLocatorPage onPage = PageFactory.initElements(driver, OpenStreetMapLocatorPage.class);

				//Getting aut url.
				driver.get(stagingURL);


				try {
					wc.until(ExpectedConditions.elementToBeClickable(By.linkText("Close")));
					onPage.closeButton.click();
			
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}

				Thread.sleep(2000);
				onPage.buttonSearch.click();

				wc.until(ExpectedConditions.elementToBeClickable(onPage.closeButton));

				//Test Case
				System.out.println("TC 4.1 :An error message 'Please enter address.' should be displayed.");
				test4.log(LogStatus.INFO, "TC 4.1 :An error message 'Please enter address.' should be displayed.");

				try {
					AssertJUnit.assertEquals(driver.findElement(By.xpath("//*[@class='modal']/p")).getText(), "Please enter an address.");
					System.out.println("Pass.");
					test4.log(LogStatus.PASS, "Pass.");
				} catch (AssertionError e) {
					e.printStackTrace();
					System.out.println("Fail.");
					test4.log(LogStatus.FAIL, "Fail." +e.getMessage()
					+ test4.addScreenCapture(captureScreenMethod(dest)));

				}
			extent.endTest(test4);

			} catch (Exception e) {

				e.printStackTrace();

				test4.log(LogStatus.FAIL, "Exception Occured. : " + e.getMessage() + " "
						+ test4.addScreenCapture(captureScreenMethod(dest)));
			} 
		}
	}
	@AfterMethod
	public void getResult(ITestResult result)
	{
		test4.log(LogStatus.INFO, "Test Case Exceuted.");

		System.out.println("------------------------------------------------------------------------------------");

		driver.quit();

	}
}
