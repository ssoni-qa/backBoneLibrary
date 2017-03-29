package stagingUrl;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

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

public class TC25_Result_Panel_Click_on_Bubble extends BrowserStackTestNGTest
{
	ExtentTest test25 ;

	@BeforeMethod
	public void handleTestMethodName(Method method)
	{
		testName = method.getName(); 

	}
	@Test
	public void result_Panel_Click_on_Bubble() throws InterruptedException, IOException
	{
		System.out.println("Test Objective : Results Panel Click on Bubble.");

		System.out.println("Verification Step :To verify whether the location bubble is coming or not, "
				+ "when we click on the map pins or in the Results panel.");
		for (String stagingURL:OpenStreetMapLocatorPage.stagingURL) {
			try {

				//
				test25 = extent.startTest("Test Objective : Results Panel Click on Bubble.",
						"Verification Step :To verify whether the location bubble is coming or not, "
								+ "when we click on the map pins or in the Results panel.");

				test25.log(LogStatus.INFO,
						"Verification step :1. The location details bubble should appear ."
								+ " The location details in the bubble should be the same as it is shown in "
								+ "the result panel. "
								+ "Can include name, address,phone, contact info, hours, distance, criteria and any other"
								+ " designed variable requested by client..");

				test25.log(LogStatus.INFO, "Staging Url :"+stagingURL);

				System.out.println("Staging URL :"+stagingURL);

				//Initializing WebElemrnt from Page Factory.
				OpenStreetMapLocatorPage onPage = PageFactory.initElements(driver, OpenStreetMapLocatorPage.class);

				//Getting aut url.
				driver.get(stagingURL);

				WebElement addtxt = onPage.addressSearch;

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

				Thread.sleep(5000);

				System.out.println("TC 25.1 :Check The location details in the bubble should be the same as it is shown in the result panel.");
				test25.log(LogStatus.INFO, "TC 25.1 :Check The location details in the bubble should be the same as it is shown in the result panel.");
				String rightPanel = driver.findElement(By.xpath("//li/div[2]/div")).getText();
				String resultBubble = driver.findElement(By.xpath("//div[2]/div/div/div/div/div/div/ul/li[3]")).getText();
				
				if (rightPanel.contains(resultBubble))
				{
					System.out.println("Pass.");
					test25.log(LogStatus.PASS, "Pass.");
				}
				else
				{
					System.out.println("Fail.");
					test25.log(LogStatus.FAIL, "Fail" +  " "
							+ test25.addScreenCapture(captureScreenMethod(dest)));
				}

				
				extent.endTest(test25);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

				test25.log(LogStatus.FAIL, "Exception Occured. : " + e.getMessage() + " "
						+ test25.addScreenCapture(captureScreenMethod(dest)));
			}
		}

	}

	@AfterMethod
	public void getResult(ITestResult result)
	{
		test25.log(LogStatus.INFO, "TC 25 executed succesfully.");

		System.out.println("------------------------------------------------------------------------------------");

		driver.quit();

	}

}
