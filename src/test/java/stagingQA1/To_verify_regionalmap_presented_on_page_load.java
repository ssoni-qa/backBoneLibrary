package stagingQA1;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import browserStackTestNG.BrowserStackTestNGTest;
import page.OpenStreetMapLocatorPage;

public class To_verify_regionalmap_presented_on_page_load extends BrowserStackTestNGTest
{

	@Test
	public void verify_page_load_on_browser() throws InterruptedException
	{
		try {
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
			}
			else
			{
				System.out.println("Fail:GEOIP detected Map");
			}
			System.out.println("TC 2 executed succesfully.");
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			driver.quit();
		}
		driver.quit();
	}

}
