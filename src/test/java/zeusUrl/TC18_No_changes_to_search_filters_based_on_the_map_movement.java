package zeusUrl;

import org.testng.annotations.Test;
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

public class TC18_No_changes_to_search_filters_based_on_the_map_movement extends BrowserStackTestNGTest
{
	ExtentTest test18 ;

	@BeforeMethod
	public void handleTestMethodName(Method method)
	{
		testName = method.getName(); 

	}
	@Test
	public void no_changes_to_search_filters_based_on_the_map_movement() throws InterruptedException, IOException
	{
		System.out.println("Test Objective : No changes to search filters based on the map movement.");

		System.out.println("Verification Step :1. To verify the postal/ city state/province search address- "
				+ "country and selected criteria do not change based on the map interactions.");
		for (String stagingURL:OpenStreetMapLocatorPage.zeusUrl) {
			try {

				//
				test18 = extent.startTest("No changes to search filters based on the map movement.",
						"To verify the Map is moving around by dragging it or not.");

				test18.log(LogStatus.INFO,
						"To verify the postal/ city state/province search address- country "
								+ "and selected criteria do not change based on the map interactions.");

				test18.log(LogStatus.INFO, "Staging Url :"+stagingURL);

				System.out.println("Staging URL :"+stagingURL);

				//Initializing WebElemrnt from Page Factory.
				OpenStreetMapLocatorPage onPage = PageFactory.initElements(driver, OpenStreetMapLocatorPage.class);

				//Getting aut url.
				driver.get(stagingURL);

				WebElement addtxt = onPage.addressSearch;

				wc.until(ExpectedConditions.textToBePresentInElementValue(addtxt, "Arlington Heights  IL 60006"));

				//Getting initial search criteria data.

				Select countrySelect = new Select(onPage.searchCountryOption);
				WebElement selectedCountry = countrySelect.getFirstSelectedOption();

				Select radiusSelect = new Select(onPage.chooseRadius);
				radiusSelect.selectByIndex(1);
				WebElement selectedRadius= radiusSelect.getFirstSelectedOption();

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

				//Test Cases.
				System.out.println("TC 18.1 :Check results if changed based on new search on Map Pan, should still reflect the select criteria of Client Design.");
				test18.log(LogStatus.INFO, "TC 18.1 :Check results if changed based on new search on Map Pan, should still reflect the select criteria of Client Design.");
				if (addtxt.getAttribute("value").contains("Arlington Heights  IL 60006") 

						&& selectedCountry.getText().contains("United States Of America")

						&& selectedRadius.getText().contains("15")) {

					System.out.println("Pass");

					test18.log(LogStatus.PASS, "Pass.");

				} 
				else
				{
					System.out.println("Fail.");

					test18.log(LogStatus.FAIL, "Fail" 
							+ test18.addScreenCapture(captureScreenMethod(dest)));
				}
				extent.endTest(test18);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

				test18.log(LogStatus.FAIL, "Exception Occured. : " + e.getMessage() + " "
						+ test18.addScreenCapture(captureScreenMethod(dest)));
			}
		}

	}

	@AfterMethod
	public void getResult(ITestResult result)
	{
		test18.log(LogStatus.INFO, "TC 18 executed succesfully.");

		System.out.println("------------------------------------------------------------------------------------");

		driver.quit();

	}

}
