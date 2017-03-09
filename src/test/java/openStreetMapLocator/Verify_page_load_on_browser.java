package openStreetMapLocator;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import browserStackTestNG.BrowserStackTestNGTest;
import page.OpenStreetMapLocatorPage;

public class Verify_page_load_on_browser extends BrowserStackTestNGTest
{

	@Test
	public void verify_page_load_on_browser()
	{
		System.out.println("Verification Step: The page should load ,"
				+ "if GeoIP detection on ,should attempt to determine location,if off ,then request address information.");
		//Initializing WebElemrnt from Page Factory.
		OpenStreetMapLocatorPage onPage=PageFactory.initElements(driver, OpenStreetMapLocatorPage.class);
		//Getting aut url.
		driver.get(OpenStreetMapLocatorPage.osmURL);
		wc.until(ExpectedConditions.elementToBeClickable(By.className("closeBtn")));
				onPage.closeButton.click();
		
        

	}

}
