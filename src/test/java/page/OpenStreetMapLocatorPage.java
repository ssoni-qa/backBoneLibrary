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

	//Project URL.
	public static String staging_url_qa1="http://zeus.dev.where2getit.com/yong_test/backbone.QA1.html";
	
	//Address Search Input TextBox
	@FindBy(name="addressline")
	public 
	WebElement addressSearch;
	
	//Search Address Drop Down box.
	@FindBy(id="search_country")
	public 
	WebElement searchCountryOption;
	
	//Pop Up Close button.
	@FindBy(className="closeBtn")
	public WebElement closeButton;








}
