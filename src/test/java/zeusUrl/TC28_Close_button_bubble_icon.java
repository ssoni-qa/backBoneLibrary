package zeusUrl;

import org.testng.annotations.Test;
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

public class TC28_Close_button_bubble_icon extends BrowserStackTestNGTest
{
	ExtentTest test26 ;

	@BeforeMethod
	public void handleTestMethodName(Method method)
	{
		testName = method.getName(); 

	}
	@Test
	public void close_button_of_the_details_bubble_icon() throws InterruptedException, IOException
	{
		System.out.println("Test Objective : Close button of the details bubble/icon.");

		System.out.println("Verification Step :To verify whether the address details bubble is is closing "
				+ "or not when close button is clicked.");
		for (String stagingURL:OpenStreetMapLocatorPage.zeusUrl) {
			try {

				//
				test26 = extent.startTest("Test Objective : Close button of the details bubble/icon.");

				test26.log(LogStatus.INFO,
						"Verification Step :To verify whether the address details bubble is is closing "
								+ "or not when close button is clicked.");

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
