package stagingQA1;

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

public class QA14_Moving_the_map_by_dragging extends BrowserStackTestNGTest
{
	ExtentTest test1 ;

	@BeforeMethod
	public void handleTestMethodName(Method method)
	{
		testName = method.getName(); 

	}
	@Test
	public void moving_the_map_by_dragging() throws InterruptedException, IOException
	{
  
		for (String myurl:OpenStreetMapLocatorPage.url) {
			//
			test1 = extent.startTest("Moving the map by dragging.",
					"To verify the Map is moving around by dragging it or not.");
			test1.log(LogStatus.INFO,
					"1. The page should load, if GeoIP detection on, " + "should attemp to determine location, "
							+ "if geoIP off, then request address information."
							+ "2. By default the cursor position should be in the search text box.");
			//Initializing WebElemrnt from Page Factory.
			OpenStreetMapLocatorPage onPage = PageFactory.initElements(driver, OpenStreetMapLocatorPage.class);
			System.out.println("Test Objective:1. The map should be moved freely in the direction it is being dragged. "
					+ "2. No error is thrown, if new search on pan on search active, new results, "
					+ "if new search on pan 'off' then same results.");
			//Getting aut url.
			driver.get(myurl);
			WebElement addtxt = onPage.addressSearch;
			wc.until(ExpectedConditions.textToBePresentInElementValue(addtxt, "Arlington Heights  IL 60006"));
			//finding canvas object.
			Thread.sleep(3000);
			WebElement map = driver.findElement(By.xpath("//canvas"));
			Actions actionBuilder = new Actions(driver);
			Action drawOnCanvas = actionBuilder.contextClick(map).moveToElement(map, 8, 8).clickAndHold(map)
					.moveByOffset(120, 120).moveByOffset(60, 70).moveByOffset(-140, -140).release(map).build();
			drawOnCanvas.perform();
			Thread.sleep(3000);
			if (driver.findElement(By.xpath("//h3")).getText().contains("locations found nearby")) {
				System.out.println("Pass: ");
			} 
		}


	}

	@AfterMethod
	public void getResult(ITestResult result)
	{
		extent.endTest(test1);
		test1.log(LogStatus.INFO, "TC 1 executed succesfully.");
		//driver.quit();

	}

}
