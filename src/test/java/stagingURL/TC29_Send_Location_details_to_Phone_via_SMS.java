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

public class TC29_Send_Location_details_to_Phone_via_SMS extends BrowserStackTestNGTest
{
	ExtentTest test26 ;

	@BeforeMethod
	public void handleTestMethodName(Method method)
	{
		testName = method.getName(); 

	}
	@Test
	public void send_location_details_to_phone_via_sms() throws InterruptedException, IOException
	{
		System.out.println("Test Objective : Send location details to Phone via SMS.");

		System.out.println("Verification Step :To Verify the functionality of the send details to phone via sms.");

		for (String stagingURL:OpenStreetMapLocatorPage.stagingURL) {
			try {

				//
				test26 = extent.startTest("Test Objective : Send location details to Phone via SMS.");

				test26.log(LogStatus.INFO,
						"Verification Step :1. Location details SMS litbox should open properly as overlay"
						+ " to the locator. 2. Collection fields should all validate for proper phone address "
						+ "and if necessary by client design include country pulldown "
						+ "(if outside North America). "
						+ "3. Confirm submission goes through and you can properly close the litbox window"
						+ " after submission message recieved on browser. "
						+ "4. Verify SMS text is recieved to phone. "
						+ "Default information is kept to a mimium of "
						+ "location name, address, phone, additional details must be specified by client.");

				test26.log(LogStatus.INFO, "Staging Url :"+stagingURL);

				System.out.println("Staging URL :"+stagingURL);

				//Initializing WebElemrnt from Page Factory.
				OpenStreetMapLocatorPage onPage = PageFactory.initElements(driver, OpenStreetMapLocatorPage.class);

				//Open Locator in the browser.
				driver.get(stagingURL);

				WebElement addtxt = onPage.addressSearch;

				wc.until(ExpectedConditions.textToBePresentInElementValue(addtxt, "Arlington Heights  IL 60006"));

				driver.findElement(By.linkText("Yong_test")).click();

				//Click on Go button
				wc.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@value='Go']")));
				driver.findElement(By.xpath("//*[@value='Go']")).click();

				//wait

				wc.until(ExpectedConditions.textToBePresentInElement(By.xpath("//h3"), "Directions"));

				//Validating Start and EndPoint

				if(driver.findElement(By.xpath("//*[@class='maneuver_start']")).getText().contains("From (A): Arlington Heights IL 60006")
						&& driver.findElement(By.xpath("//*[@class='maneuver_end']")).getText().contains("To (B): Arlington Heights IL 60005"))
				{

					System.out.println("Pass: Valid Start and End  Point.");
					test26.log(LogStatus.PASS, "Valid Start and End  Point located.");

				}
				else
				{
					System.out.println("Fail : Invalid Start and End Point is found.");
					test26.log(LogStatus.FAIL, "Invalid Start and End Point is found. "
							+ test26.addScreenCapture(captureScreenMethod(dest)));
				}

				// Milege for each step and total distance.
				if(driver.findElement(By.xpath("//*[@class='poi maneuver']/p[2]")).getText()
						.contains("Distance: 0.8 miles. Drive time: 2 minutes"))
				{
					System.out.println("Pass: Valid total Drive time and Distance.");
					test26.log(LogStatus.PASS, "Valid total Drive time and Distance.");

				}
				else
				{
					System.out.println("Fail : Invalid total Drive time and Distance.");
					test26.log(LogStatus.FAIL, "Invalid total Drive time and Distance."
							+ test26.addScreenCapture(captureScreenMethod(dest)));
				}

				//Valid Test step turn.
				if(driver.findElement(By.xpath("//*[@class='content-list maneuver-result']/li[1]")).getText()
						.contains("1 Start out on W Euclid Ave (Going East) 0.07 miles"))
					if(driver.findElement(By.xpath("//*[@class='content-list maneuver-result']/li[2]")).getText()
							.contains("2 Turn SLIGHT RIGHT onto Northwest Hwy/US-14 (Going Southeast) 0.64 miles"))
						if(driver.findElement(By.xpath("//*[@class='content-list maneuver-result']/li[3]")).getText()
								.contains("3 Turn RIGHT onto Evergreen Ave (Going South) 0.06 miles"))
						{
							System.out.println("Pass: Valid Step direction.");
							test26.log(LogStatus.PASS, "Valid Step direction.");

						}
						else
						{
							System.out.println("Fail : Invalid Valid Step direction.");
							test26.log(LogStatus.FAIL, "Invalid Valid Step direction."
									+ test26.addScreenCapture(captureScreenMethod(dest)));
						}

				extent.endTest(test26);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				test26.log(LogStatus.FAIL, "Exception Occured. : " + e.getMessage() + " "
						+ test26.addScreenCapture(captureScreenMethod(dest)));
			}
		}

	}

	@AfterMethod
	public void getResult(ITestResult result)
	{
		test26.log(LogStatus.INFO, "TC 25 executed succesfully.");

		System.out.println("------------------------------------------------------------------------------------");

		driver.quit();

	}

}
