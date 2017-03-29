package stagingUrl;

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
		for (String stagingURL:OpenStreetMapLocatorPage.stagingURL) {
			try {

				//
				test26 = extent.startTest("Test Objective : Functionality of 'Get Directions' button - Inside Locator.");

				test26.log(LogStatus.INFO,
						"Verification Step :1. Directions page is returned and includes:"
								+ " Start and End address points, "
								+ "turn by turn steps along with mileage for each step and total distance at end."
								+ " Map should show highlighted routed corresponding to directions.");

				test26.log(LogStatus.INFO, "Staging Url :"+stagingURL);
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

				onPage.addressSearch.sendKeys("test location");

				Thread.sleep(2000);
				onPage.buttonSearch.click();

				wc.until(ExpectedConditions.elementToBeClickable(By.linkText("Cracker Barrel")));

				driver.findElement(By.linkText("Cracker Barrel")).click();

				//Click on Go button
				wc.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@value='Go']")));


				driver.findElement(By.xpath("//*[@value='Go']")).click();

				//wait

				wc.until(ExpectedConditions.textToBePresentInElement(By.xpath("//h3"), "Directions"));

				//Validating Start and EndPoint
				System.out.println("TC 26.1: Check for valid Start and End address points.");
				test26.log(LogStatus.INFO, "TC 26.1: Check for valid Start and End address points.");
				if(driver.findElement(By.xpath("//*[@class='maneuver_start']")).getText().contains("From (A): test, test MD 20670")
						&& driver.findElement(By.xpath("//*[@class='maneuver_end']")).getText().contains("To (B): 45315 Abell Houselane, California MD 20619"))
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
						.contains("Distance: 6.3 miles. Drive time: 10 minutes"))
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
						.contains("1 Start out on Saufley Rd (Going South) 0.62 miles") && 
						driver.findElement(By.xpath("//*[@class='content-list maneuver-result']/li[2]")).getText()
						.contains("2 Turn RIGHT onto Cedar Point Rd (Going Southwest) 1.65 miles")
						&& driver.findElement(By.xpath("//*[@class='content-list maneuver-result']/li[3]")).getText()
						.contains("3 Turn RIGHT onto MD-235 N (Going Northwest) 3.85 miles")
						&& driver.findElement(By.xpath("//*[@class='content-list maneuver-result']/li[4]")).getText()
						.contains("4 Turn RIGHT onto Shady Mile Dr (Going North) 0.08 miles")
						&& driver.findElement(By.xpath("//*[@class='content-list maneuver-result']/li[5]")).getText()
						.contains("5 Turn LEFT onto Abell House Ln (Going West) 0.14 miles"))
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
