package stagingQA1;

import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
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

public class QA2_To_verify_regionalmap_presented_on_page_load extends BrowserStackTestNGTest
{
	ExtentTest test2 ;
	@BeforeMethod
    public void handleTestMethodName(Method method)
    {
         testName = method.getName(); 
    }
	@Test
	public void verify_regionalmap_presented_on_page_load() throws InterruptedException, IOException
	{
		try {
			test2=extent.startTest("GEOIP detected Map","To verify if the regional map is presented on page load.");
			test2.log(LogStatus.INFO, "1. The map should reflect your current geographical location or "
					+ "the geographical location of your IP address "
					+ "( please note due to the VPN connection, "
					+ "your IP address might not match your current office location )");
			//Initializing WebElemrnt from Page Factory.
			OpenStreetMapLocatorPage onPage=PageFactory.initElements(driver, OpenStreetMapLocatorPage.class);
		
		    System.out.println("Test Objective: To verify if the regional map is presented on page load");
		
			System.out.println("Verification 1: 1. The map should reflect your current geographical location "
					+ "or the geographical location of your IP address "
					+ "( please note due to the VPN connection, "
					+ "your IP address might not match your current location )");
			//Getting aut url.
			driver.get(OpenStreetMapLocatorPage.staging_url_qa1);
			
			WebElement addtxt=onPage.addressSearch;
			
			wc.until(ExpectedConditions.textToBePresentInElementValue(addtxt, "Arlington Heights  IL 60006"));

			Select countrySelect= new Select(onPage.searchCountryOption);
			WebElement selectedCountry=countrySelect.getFirstSelectedOption();
			
			if(!onPage.addressSearch.getAttribute("value").equals("Arlington Heights  IL 60006") 
					&& selectedCountry.getText().equals("United States Of America"))
			{
				System.out.println("Pass: GEOIP detected Map.");
				test2.log(LogStatus.PASS, "GEOIP detected Map.");
			}
			else
			{
				System.out.println("Fail:GEOIP detected Map");
				test2.log(LogStatus.FAIL, "GEOIP does not detected Map : "+test2.addScreenCapture(captureScreenMethod(dest)));
			}
			System.out.println("TC 2 executed succesfully."+testName);
		} catch (Exception e) {
			e.printStackTrace();
			test2.log(LogStatus.FAIL, "Exception Occured. : "+e.getMessage()+ " " +test2.addScreenCapture(captureScreenMethod(dest)));
		}
	}
	@AfterMethod
	public void getResult(ITestResult result)
	{
		extent.endTest(test2);
		test2.log(LogStatus.INFO, "TC 1 executed succesfully.");
		driver.quit();
		

	}
}
