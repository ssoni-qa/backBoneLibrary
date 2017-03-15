package stagingURL;

import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
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

public class TC14_Moving_the_map_by_dragging extends BrowserStackTestNGTest
{
	ExtentTest test14 ;

	@BeforeMethod
	public void handleTestMethodName(Method method)
	{
		testName = method.getName(); 

	}
	@Test
	public void moving_the_map_by_dragging() throws InterruptedException, IOException
	{
		System.out.println("Test Objective : To verify the Map is moving around by dragging it or not,Search On and OFF.");

		System.out.println("Verification Step :1. The map should be moved freely in the direction"
				+ " it is being dragged. 2. No error is thrown, if new search on pan on search active,"
				+ " new results, if new search on pan 'off' then same results. ");

		try {
			for (String stagingURL:OpenStreetMapLocatorPage.stagingURL) {
				//
				test14 = extent.startTest("Moving the map by dragging.",
						"To verify the Map is moving around by dragging it or not.");

				test14.log(LogStatus.INFO,
						"1. The map should be moved freely in the direction it is being dragged. "
						+ "2. No error is thrown, If new search on pan on search active, new results,"
						+ " if new search on pan 'off' then same results.");

				test14.log(LogStatus.INFO, "Staging Url :"+stagingURL);
				


				System.out.println("Staging URL :"+stagingURL);

				//Initializing WebElemrnt from Page Factory.
				OpenStreetMapLocatorPage onPage = PageFactory.initElements(driver, OpenStreetMapLocatorPage.class);


				//Getting aut url.
				driver.get(stagingURL);

				WebElement addtxt = onPage.addressSearch;

				wc.until(ExpectedConditions.textToBePresentInElementValue(addtxt, "Arlington Heights  IL 60006"));

				//finding canvas object.

				Thread.sleep(3000);

				WebElement map = driver.findElement(By.xpath("//canvas"));

				Actions actionBuilder = new Actions(driver);

				Action drawOnCanvas = actionBuilder.contextClick(map)
						.moveToElement(map, 8, 8)
						.clickAndHold(map)
						.moveByOffset(120, 120)
						.moveByOffset(60, 70)
						.moveByOffset(140, 140)
						.release(map).build();

				drawOnCanvas.perform();

				Thread.sleep(3000);

				if(stagingURL.equals("http://zeus.dev.where2getit.com/yong_test/backbone.QA3.html"))
				{
					try {
						test14.log(LogStatus.INFO, "New Search on Map Pan.");

						Thread.sleep(3000);
						System.out.println(driver.findElement(By.xpath("//a[contains(text(), 'test 2')]")).getText());
						System.out.println("Pass: TC 14 and TC 15");
						test14.log(LogStatus.PASS, "PAN is ON , New result.");


					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println("Fail : TC 14 and TC 15");

						test14.log(LogStatus.FAIL, "PAN is ON , New result is not showing");

						test14.log(LogStatus.FAIL, "PAN is ON , New result is not showing" 
								+ test14.addScreenCapture(captureScreenMethod(dest)));
					}
				}
				else{
					test14.log(LogStatus.INFO, "No Search On , Map Feature.");

					if (driver.findElement(By.xpath("//h3")).getText().contains("locations found")) {

						System.out.println("Pass: TC 14 and TC 16");
						
						test14.log(LogStatus.PASS, "PAN is OFF , Same Result is showned.");

					} 
					else
					{
						System.out.println("Fail : TC 14 and TC 16");

						test14.log(LogStatus.FAIL, "PAN is OFF , Same Result is showned." 
								+ test14.addScreenCapture(captureScreenMethod(dest)));
					}

				}

			}

			extent.endTest(test14);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			test14.log(LogStatus.FAIL, "Exception Occured. : " + e.getMessage() + " "
					+ test14.addScreenCapture(captureScreenMethod(dest)));
		}


	}

	@AfterMethod
	public void getResult(ITestResult result)
	{
		test14.log(LogStatus.INFO, "TC 14,15,16 executed succesfully.");

		System.out.println("------------------------------------------------------------------------------------");

		//driver.quit();

	}

}
