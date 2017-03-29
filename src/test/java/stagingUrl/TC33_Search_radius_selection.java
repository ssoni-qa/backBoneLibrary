package stagingUrl;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import browserStackTestNG.BrowserStackTestNGTest;
import page.OpenStreetMapLocatorPage;

public class TC33_Search_radius_selection extends BrowserStackTestNGTest
{
	ExtentTest test33 ;

	@BeforeMethod
	public void handleTestMethodName(Method method)
	{
		testName = method.getName(); 

	}
	@Test
	public void search_radius_selection() throws InterruptedException, IOException ,Exception
	{
		try {
			System.out.println("Test Objective : To verify whether search results are expanding until at least "
					+ "one results is found based on radius selected. Client can set radius defaults.");

			System.out.println("Verification Step :1. Verify that the results return are within the said desired"
					+ " mileage. "
					+ "Conduct search again for increased radius and decreased radius, confirm mileage distance changes accordingly. "
					+ "Additional desired location details must be specified by client.");

			for (String stagingURL:OpenStreetMapLocatorPage.stagingURL) {

				//
				test33 = extent.startTest("Test Objective : To verify whether search results are expanding until at least "
						+ "one results is found based on radius selected. Client can set radius defaults.");

				test33.log(LogStatus.INFO,
						"Verification Step :1. Verify that the results return are within the said desired"
								+ " mileage. "
								+ "Conduct search again for increased radius and decreased radius, confirm mileage distance changes accordingly. "
								+ "Additional desired location details must be specified by client.");

				test33.log(LogStatus.INFO, "Staging Url :"+stagingURL);

				System.out.println("Staging URL :"+stagingURL);

				//Initializing WebElemrnt from Page Factory.
				OpenStreetMapLocatorPage onPage = PageFactory.initElements(driver, OpenStreetMapLocatorPage.class);

				//Open Locator in the browser.
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


				//TC
				if(stagingURL.contains("http://hosted.where2getit.com/yong/backbone.QA1.html") 
						|| stagingURL.equals("http://hosted.where2getit.com/yong/backbone.QA3.html") )
				{
					wc.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[4]")));
					//TC
					System.out.println("1 - Verify that the results return are within the said desired mileage.");
					test33.log(LogStatus.INFO, 
							"1 - Verify that the results return are within the said desired mileage.");
					try {
						AssertJUnit.assertEquals(driver.findElement(By.xpath("//span[4]")).getText(), "Drive it - Distance: 4.45 miles");
						test33.log(LogStatus.PASS, "Pass");
						System.out.println("Pass");
					} catch (AssertionError e) {
						System.out.println("FAIL :"+e.getMessage());
						test33.log(LogStatus.FAIL, "Fail : " + e.getMessage() + " "
								+ test33.addScreenCapture(captureScreenMethod(dest)));	
					}
					new Select(driver.findElement(By.name("searchradius"))).selectByVisibleText("15 miles");
					Thread.sleep(2000);
					onPage.buttonSearch.click();
					wc.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[4]")));
					System.out.println("2 - Increased radius or decreased radius, confirm mileage distance changes accordingly.");
					test33.log(LogStatus.INFO, 
							"2 - Increased radius or decreased radius, confirm mileage distance changes accordingly.");
					try {
						AssertJUnit.assertEquals(driver.findElement(By.xpath("//span[4]")).getText(), "Drive it - Distance: 4.45 miles");
						test33.log(LogStatus.PASS, "Pass");
						System.out.println("Pass");
					} catch (AssertionError e) {
						System.out.println("FAIL :"+e.getMessage());
						test33.log(LogStatus.FAIL, "Fail : " + e.getMessage() + " "
								+ test33.addScreenCapture(captureScreenMethod(dest)));
					}
				}
				else
				{
					wc.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[4]")));
					//TC
					System.out.println("1 - Verify that the results return are within the said desired mileage.");
					test33.log(LogStatus.INFO, 
							"1 - Verify that the results return are within the said desired mileage.");
					try {
						AssertJUnit.assertEquals(driver.findElement(By.xpath("//span[4]")).getText(), "Drive it - Distance: 7.16 km");
						test33.log(LogStatus.PASS, "Pass");
						System.out.println("Pass");
					} catch (AssertionError e) {
						System.out.println("FAIL :"+e.getMessage());
						test33.log(LogStatus.FAIL, "Fail : " + e.getMessage() + " "
								+ test33.addScreenCapture(captureScreenMethod(dest)));	
					}
					new Select(driver.findElement(By.name("searchradius"))).selectByVisibleText("15");
					Thread.sleep(2000);
					onPage.buttonSearch.click();
					wc.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[4]")));
					System.out.println("2 - Increased radius or decreased radius, confirm mileage distance changes accordingly.");
					test33.log(LogStatus.INFO, 
							"2 - Increased radius or decreased radius, confirm mileage distance changes accordingly.");
					try {
						AssertJUnit.assertEquals(driver.findElement(By.xpath("//span[4]")).getText(), "Drive it - Distance: 7.16 km");
						test33.log(LogStatus.PASS, "Pass");
						System.out.println("Pass");
					} catch (AssertionError e) {
						System.out.println("FAIL :"+e.getMessage());
						test33.log(LogStatus.FAIL, "Fail : " + e.getMessage() + " "
								+ test33.addScreenCapture(captureScreenMethod(dest)));
					}
				}

				extent.endTest(test33);
			}
		} catch ( Exception e) {
			e.printStackTrace();
			System.out.println("Excpetion Occured." );
			test33.log(LogStatus.FAIL, "Exception Occured. : " + e.getMessage() + " "
					+ test33.addScreenCapture(captureScreenMethod(dest)));
		}
		extent.endTest(test33);

	}


	@AfterMethod
	public void getResult()
	{
		test33.log(LogStatus.INFO, "TC 30 executed succesfully.");
		System.out.println("------------------------------------------------------------------------------------");
		driver.quit();
	}

}
