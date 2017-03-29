package stagingUrl;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import browserStackTestNG.BrowserStackTestNGTest;
import junit.framework.AssertionFailedError;
import page.OpenStreetMapLocatorPage;

public class TC30_Send_Location_details_to_email_address extends BrowserStackTestNGTest
{
	ExtentTest test26 ;

	@BeforeMethod
	public void handleTestMethodName(Method method)
	{
		testName = method.getName(); 

	}
	@Test
	public void send_location_details_to_email_address() throws InterruptedException, IOException ,Exception
	{
		System.out.println("Test Objective : To Verify the functionality of the send details to email.");

		System.out.println("Verification Step :1. Location details email litbox should open properly as "
				+ "overlay to the locator."
				+ " 2. Collection fields should all validate for proper email (@ and . ) "
				+ "3. Confirm submission goes through and you can properly close the litbox window after "
				+ "submission message recieved on browser. "
				+ "4. Verify email is delivered. Default information is kept to a mimium of location name, "
				+ "address, phone, additional details must be specified by client. "
				+ "From will be client account name and 'locator' unless specified by client. "
				+ "Additional desired location details must be specified by client..");

		for (String stagingURL:OpenStreetMapLocatorPage.stagingURL) {
			try {

				//
				test26 = extent.startTest("Test Objective : To Verify the functionality of the send details to email.");

				test26.log(LogStatus.INFO,
						"Verification Step :1. Location details email litbox should open properly as "
								+ "overlay to the locator."
								+ " 2. Collection fields should all validate for proper email (@ and . ) "
								+ "3. Confirm submission goes through and you can properly close the litbox window after "
								+ "submission message recieved on browser. "
								+ "4. Verify email is delivered. Default information is kept to a mimium of location name, "
								+ "address, phone, additional details must be specified by client. "
								+ "From will be client account name and 'locator' unless specified by client. "
								+ "Additional desired location details must be specified by client..");

				test26.log(LogStatus.INFO, "Staging Url :"+stagingURL);

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
                
				wc.until(ExpectedConditions.elementToBeClickable(By.linkText("Cracker Barrel")));


				//Click on Email Link
				driver.findElement(By.linkText("Email")).click();
				//TC
				System.out.println("1-Location details email lit box should open properly as overlay to the locator.");
				test26.log(LogStatus.INFO, 
						"1-Location details email lit box should open properly as overlay to the locator.");
				try {
					AssertJUnit.assertEquals(driver.findElement(By.cssSelector("div.modal")).getText(), "Close");
					test26.log(LogStatus.PASS, "Pass");
					System.out.println("Pass");
				} catch (AssertionError e) {
					System.out.println("FAIL :"+e.getMessage());
					test26.log(LogStatus.FAIL, "Fail : " + e.getMessage() + " "
							+ test26.addScreenCapture(captureScreenMethod(dest)));				}
				//Wait until Email pop Up windop appear
				driver.switchTo().frame(0);
				wc.until(ExpectedConditions.elementToBeClickable(By.name("useremail")));
				driver.findElement(By.name("send_message")).click();
				//TC
				System.out.println("2.1-Check for blank address validation.");
				test26.log(LogStatus.INFO, 
						"2.1-Check for blank address validation.");
				try {
					AssertJUnit.assertEquals("EMAIL ADDRESS IS REQUIRED", driver.findElement(By.cssSelector("font")).getText());
					test26.log(LogStatus.PASS, "Pass.");
					System.out.println("Pass");
				} catch (AssertionError e) {
					System.out.println("Fail :"+e.getMessage());
					test26.log(LogStatus.FAIL, "Fail :" + e.getMessage() + " "
							+ test26.addScreenCapture(captureScreenMethod(dest)));
				}
				driver.findElement(By.name("useremail")).click();
				driver.findElement(By.name("useremail")).clear();
				driver.findElement(By.name("useremail")).sendKeys("ssss");
				driver.findElement(By.name("send_message")).click();
				//TC
				System.out.println("2.2-Check for Invalid Email Address.");
				test26.log(LogStatus.INFO, 
						"2.2-Check for Invalid Email Address.");
				try {
					AssertJUnit.assertEquals("INVALID EMAIL ADDRESS", driver.findElement(By.cssSelector("font")).getText());
					test26.log(LogStatus.PASS, "Pass.");
					System.out.println("Pass");


				} catch (AssertionError e) {
					System.out.println("Fail :"+e.getMessage());
					test26.log(LogStatus.FAIL, "Fail :" + e.getMessage() + " "
							+ test26.addScreenCapture(captureScreenMethod(dest)));
				}
				driver.findElement(By.name("useremail")).click();
				driver.findElement(By.name("useremail")).clear();
				driver.findElement(By.name("useremail")).sendKeys("ssoni@where2getit.com");
				driver.findElement(By.name("usercomments")).clear();
				driver.findElement(By.name("usercomments")).sendKeys("test auton");
				driver.findElement(By.name("send_message")).click();
				//TC
				System.out.println("3-Email send confirmation message box.");
				test26.log(LogStatus.INFO, 
						"3.1-Email send confirmation message box.");
				try {
					AssertJUnit.assertEquals(driver.findElement(By.cssSelector("div.divmargin.confirmation")).getText(),
							"Confirmation");
					test26.log(LogStatus.PASS, "Pass.");
					System.out.println("Pass");

				} catch (AssertionError e) {
					System.out.println("Fail :"+e.getMessage());
					test26.log(LogStatus.FAIL, "Fail :" + e.getMessage() + " "
							+ test26.addScreenCapture(captureScreenMethod(dest)));
				}
				driver.switchTo().defaultContent();
				//TC
				System.out.println("3.2-Properly close the litbox window after submission message recieved on browser.");
				test26.log(LogStatus.INFO, 
						"3.2-Properly close the litbox window after submission message recieved on browser.");
				wc.until(ExpectedConditions.elementToBeClickable(By.linkText("Close")));
				driver.findElement(By.linkText("Close")).click();
				try {
					AssertJUnit.assertTrue(isElementPresent(By.cssSelector("canvas.ol-unselectable")));
					test26.log(LogStatus.PASS, "Pass.");
					System.out.println("Pass");
				} catch (AssertionError e) {
					System.out.println("Fail :"+e.getMessage());
					test26.log(LogStatus.FAIL, "Fail :" + e.getMessage() + " "
							+ test26.addScreenCapture(captureScreenMethod(dest)));				}
			} catch ( Exception e) {
				System.out.println("Excpetion Occured.");
				try {
					test26.log(LogStatus.FAIL, "Exception Occured. : " + e.getMessage() + " "
							+ test26.addScreenCapture(captureScreenMethod(dest)));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		extent.endTest(test26);
	}
	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	@AfterMethod
	public void getResult()
	{
		test26.log(LogStatus.INFO, "TC 30 executed succesfully.");
		System.out.println("------------------------------------------------------------------------------------");
		driver.quit();
	}

}
