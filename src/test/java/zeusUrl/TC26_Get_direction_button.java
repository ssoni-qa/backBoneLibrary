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

public class TC26_Get_direction_button extends BrowserStackTestNGTest
{
	ExtentTest test26 ;

	@BeforeMethod
	public void handleTestMethodName(Method method)
	{
		testName = method.getName(); 

	}
	@Test
	public void get_Direction_Button() throws InterruptedException, IOException
	{
		System.out.println("Test Objective : Functionality of 'Get Directions' button - Inside Locator.");

		System.out.println("Verification Step :1. Directions page is returned and includes:"
				+ " Start and End address points, "
				+ "turn by turn steps along with mileage for each step and total distance at end."
				+ " Map should show highlighted routed corresponding to directions.");
		for (String zeusUrl:OpenStreetMapLocatorPage.zeusUrl) {
			try {

				//
				test26 = extent.startTest("Test Objective : Functionality of 'Get Directions' button - Inside Locator.");

				test26.log(LogStatus.INFO,
						"Verification Step :1. Directions page is returned and includes:"
								+ " Start and End address points, "
								+ "turn by turn steps along with mileage for each step and total distance at end."
								+ " Map should show highlighted routed corresponding to directions.");

				test26.log(LogStatus.INFO, "Zeus Url :"+zeusUrl);
				System.out.println("Zeus URL :"+zeusUrl);

				//Initializing WebElemrnt from Page Factory.
				OpenStreetMapLocatorPage onPage = PageFactory.initElements(driver, OpenStreetMapLocatorPage.class);

				//Getting aut url.
				driver.get(zeusUrl);

				WebElement addtxt = onPage.addressSearch;

				wc.until(ExpectedConditions.textToBePresentInElementValue(addtxt, "Arlington Heights  IL 60006"));

				driver.findElement(By.linkText("Yong_test")).click();

				//Click on Go button
				wc.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@value='Go']")));
				driver.findElement(By.xpath("//*[@value='Go']")).click();

				//wait

				wc.until(ExpectedConditions.textToBePresentInElement(By.xpath("//h3"), "Directions"));

				//Validating Start and EndPoint
                System.out.println("TC 26.1: Check for valid Start and End address points.");
                test26.log(LogStatus.INFO, "TC 26.1: Check for valid Start and End address points.");
				if(driver.findElement(By.xpath("//*[@class='maneuver_start']")).getText().contains("From (A): Arlington Heights IL 60006")
						&& driver.findElement(By.xpath("//*[@class='maneuver_end']")).getText().contains("To (B): Arlington Heights IL 60005"))
				{

					System.out.println("Pass.");
					test26.log(LogStatus.PASS, "Pass.");

				}
				else
				{
					System.out.println("Fail.");
					test26.log(LogStatus.FAIL, "Fail."
							+ test26.addScreenCapture(captureScreenMethod(dest)));
				}

				// Milege for each step and total distance.
				System.out.println("TC 26.2: Check for valid Milege for each step and total distance.");
                test26.log(LogStatus.INFO, "TC 26.2: Check for valid Milege for each step and total distance.");
				if(driver.findElement(By.xpath("//*[@class='poi maneuver']/p[2]")).getText()
						.contains("Distance: 0.8 miles. Drive time: 2 minutes"))
				{
					System.out.println("Pass");
					test26.log(LogStatus.PASS, "Pass.");

				}
				else
				{
					System.out.println("Fail.");
					test26.log(LogStatus.FAIL, "Fail."
							+ test26.addScreenCapture(captureScreenMethod(dest)));
				}

				//Valid Test step turn.
				System.out.println("TC 26.3: Check for Valid Test step turn.");
                test26.log(LogStatus.INFO, "TC 26.3: Check for Valid Test step turn.");
				if(driver.findElement(By.xpath("//*[@class='content-list maneuver-result']/li[1]")).getText()
						.contains("1 Start out on W Euclid Ave (Going East) 0.07 miles"))
					if(driver.findElement(By.xpath("//*[@class='content-list maneuver-result']/li[2]")).getText()
							.contains("2 Turn SLIGHT RIGHT onto Northwest Hwy/US-14 (Going Southeast) 0.64 miles"))
						if(driver.findElement(By.xpath("//*[@class='content-list maneuver-result']/li[3]")).getText()
								.contains("3 Turn RIGHT onto Evergreen Ave (Going South) 0.06 miles"))
						{
							System.out.println("Pass.");
							test26.log(LogStatus.PASS, "Pass.");

						}
						else
						{
							System.out.println("Fail.");
							test26.log(LogStatus.FAIL, "Fail."
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
		test26.log(LogStatus.INFO, "TC 26 executed succesfully.");

		System.out.println("------------------------------------------------------------------------------------");

		driver.quit();

	}

}
