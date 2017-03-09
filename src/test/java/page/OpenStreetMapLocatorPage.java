package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import browserStackTestNG.BrowserStackTestNGTest;

public class OpenStreetMapLocatorPage extends BrowserStackTestNGTest
{
	
	public OpenStreetMapLocatorPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public static String staging_url_qa1="http://zeus.dev.where2getit.com/yong_test/backbone.QA1.html";
	@FindBy(name="addressline")
	public 
	WebElement addressLine;
	
	@FindBy(className="closeBtn")
	public WebElement closeButton;

	
	
	
	
	

}
