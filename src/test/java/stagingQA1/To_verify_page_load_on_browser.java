package stagingQA1;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import browserStackTestNG.BrowserStackTestNGTest;
import page.OpenStreetMapLocatorPage;

public class To_verify_page_load_on_browser extends BrowserStackTestNGTest
{
    
	@Test
	public void verify_page_load_on_browser() throws InterruptedException
	{
		try {
			//Initializing WebElemrnt from Page Factory.
			OpenStreetMapLocatorPage onPage=PageFactory.initElements(driver, OpenStreetMapLocatorPage.class);
			System.out.println("Test Objective: To verify page loads on browser.");
			System.out.println("Verification 1: 1. The page should load, "
					+ "if GeoIP detection on, should attemp to determine location, "
					+ "if geoIP off, then request address information.");
			//Getting aut url.
			driver.get(OpenStreetMapLocatorPage.staging_url_qa1);
			WebElement addtxt=onPage.addressSearch;
			wc.until(ExpectedConditions.textToBePresentInElementValue(addtxt, "Arlington Heights  IL 60006"));
			//Verify Page Load on browser
			if(driver.getTitle().equals("Problem loading page"))
			{
				System.out.println("Fail: Error in loading Page.");
			}else {
				System.out.println("Pass:Page loaded succesfully.");
			}
			if(!onPage.addressSearch.getAttribute("value").equals(""))
			{
				System.out.println("Pass: GeoIP is on , location is detected.");
			}
			else
			{
				System.out.println("Fail:GeoIP is on , location is detected.");
			}
			System.out.println("TC 1 executed succesfully.");
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			driver.quit();
		}
		driver.quit();
	}

}
