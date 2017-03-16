package stagingURL;

import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
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

public class TC23_Zoom_out extends BrowserStackTestNGTest
{
	ExtentTest test23 ;

	@BeforeMethod
	public void handleTestMethodName(Method method)
	{
		testName = method.getName(); 

	}
	@Test
	public void TC23_Zoom_out() throws InterruptedException, IOException
	{
		System.out.println("Test Objective : Zoom out by clicking on the zoom in button (minus sign).");

		System.out.println("Verification Step :To verify the functionality of zoom out button.");
		for (String stagingURL:OpenStreetMapLocatorPage.stagingURL) {
			try {

				//
				test23 = extent.startTest("Zoom out by clicking on the zoom in button (minus sign).",
						"To verify the functionality of zoom out button.");

				test23.log(LogStatus.INFO,
						"Verification step :The map should zoom out smoothly and it should stop zooming out "
								+ "after certain limit (once the zoom level indicator reaches the highest).");

				test23.log(LogStatus.INFO, "Staging Url :"+stagingURL);

				System.out.println("Staging URL :"+stagingURL);

				//Initializing WebElemrnt from Page Factory.
				OpenStreetMapLocatorPage onPage = PageFactory.initElements(driver, OpenStreetMapLocatorPage.class);

				//Getting aut url.
				driver.get(stagingURL);

				WebElement addtxt = onPage.addressSearch;

				wc.until(ExpectedConditions.textToBePresentInElementValue(addtxt, "Arlington Heights  IL 60006"));


				WebElement zoomSlider=driver.findElement(By.xpath("//*[@class='ol-zoomslider-thumb ol-unselectable']"));


				while(!zoomSlider.getCssValue("top").contains("145px"))
				{
					driver.findElement(By.id("OpenLayers_Control_PanZoom_zoomout_innerImage")).click();
				}
				System.out.println("TC 22 Pass : Zoom levele indicator reaches the highest.");
				test23.log(LogStatus.PASS, "Zoom levele indicator reaches the highest.");
				extent.endTest(test23);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

				test23.log(LogStatus.FAIL, "Exception Occured. : " + e.getMessage() + " "
						+ test23.addScreenCapture(captureScreenMethod(dest)));
			}
		}

	}

	@AfterMethod
	public void getResult(ITestResult result)
	{
		test23.log(LogStatus.INFO, "TC 23 executed succesfully.");

		System.out.println("------------------------------------------------------------------------------------");

		driver.quit();

	}

}
