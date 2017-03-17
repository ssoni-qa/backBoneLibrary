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

public class TC30_Send_Location_details_to_email_address extends BrowserStackTestNGTest
{
	ExtentTest test26 ;

	@BeforeMethod
	public void handleTestMethodName(Method method)
	{
		testName = method.getName(); 

	}
	@Test
	public void send_location_details_to_email_address() throws InterruptedException, IOException ,Exception
	{
		System.out.println("Test Objective : To Verify the functionality of the send details to email.");

		System.out.println("Verification Step :1. Location details email litbox should open properly as "
				+ "overlay to the locator."
				+ " 2. Collection fields should all validate for proper email (@ and . ) "
				+ "3. Confirm submission goes through and you can properly close the litbox window after "
				+ "submission message recieved on browser. "
				+ "4. Verify email is delivered. Default information is kept to a mimium of location name, "
				+ "address, phone, additional details must be specified by client. "
				+ "From will be client account name and 'locator' unless specified by client. "
				+ "Additional desired location details must be specified by client..");

		for (String stagingURL:OpenStreetMapLocatorPage.stagingURL) {
			try {

				//
				test26 = extent.startTest("Test Objective : To Verify the functionality of the send details to email.");

				test26.log(LogStatus.INFO,
						"Verification Step :1. Location details email litbox should open properly as "
								+ "overlay to the locator."
								+ " 2. Collection fields should all validate for proper email (@ and . ) "
								+ "3. Confirm submission goes through and you can properly close the litbox window after "
								+ "submission message recieved on browser. "
								+ "4. Verify email is delivered. Default information is kept to a mimium of location name, "
								+ "address, phone, additional details must be specified by client. "
								+ "From will be client account name and 'locator' unless specified by client. "
								+ "Additional desired location details must be specified by client..");

				test26.log(LogStatus.INFO, "Staging Url :"+stagingURL);

				System.out.println("Staging URL :"+stagingURL);

				//Initializing WebElemrnt from Page Factory.
				OpenStreetMapLocatorPage onPage = PageFactory.initElements(driver, OpenStreetMapLocatorPage.class);

				//Open Locator in the browser.
				driver.get(stagingURL);

				WebElement addtxt = onPage.addressSearch;

				wc.until(ExpectedConditions.textToBePresentInElementValue(addtxt, "Arlington Heights  IL 60006"));

				try {
					//Click on Email Link
					driver.findElement(By.linkText("Email")).click();
					//Wait until Email pop Up windop appear
					driver.switchTo().frame(0);
					wc.until(ExpectedConditions.elementToBeClickable(By.name("useremail")));
					driver.findElement(By.name("send_message")).click();
					s_assert.assertEquals("EMAIL ADDRESS IS REQUIRED", driver.findElement(By.cssSelector("font")).getText());
					driver.findElement(By.name("useremail")).click();
					driver.findElement(By.name("useremail")).clear();
					driver.findElement(By.name("useremail")).sendKeys("ssss");
					driver.findElement(By.name("send_message")).click();
					s_assert.assertEquals("INVALID EMAIL ADDRESS", driver.findElement(By.cssSelector("font")).getText());
					driver.findElement(By.name("useremail")).click();
					driver.findElement(By.name("useremail")).clear();
					driver.findElement(By.name("useremail")).sendKeys("ss@gmail.com");
					driver.findElement(By.name("usercomments")).clear();
					driver.findElement(By.name("usercomments")).sendKeys("test auton");
					driver.findElement(By.name("send_message")).click();
					s_assert.assertEquals("Your results have been sent to your e-mail address.\n\n Thanks!", driver.findElement(By.xpath("//div[2]")).getText());

					driver.switchTo().defaultContent();
					wc.until(ExpectedConditions.elementToBeClickable(By.linkText("Close")));
					driver.findElement(By.linkText("Close")).click();
					extent.endTest(test26);
				} catch (AssertionError a) {
					// TODO Auto-generated catch block
					a.printStackTrace();
					System.out.println("Assertion Catch Block");
				}


			} catch ( Exception e) {
				System.out.println("Excpetion Catch Block");

				test26.log(LogStatus.FAIL, "Exception Occured. : " + e.getMessage() + " "
						+ test26.addScreenCapture(captureScreenMethod(dest)));
			}

		}
		s_assert.assertAll();

	}

	@AfterMethod
	public void getResult()
	{
		test26.log(LogStatus.INFO, "TC 25 executed succesfully.");
		System.out.println("------------------------------------------------------------------------------------");
		driver.quit();

	}

}
