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

	//Staging Project URL.
	//	public static  String stagingURL[]={"http://zeus.dev.where2getit.com/yong_test/backbone.QA1.html",
	//			"http://zeus.dev.where2getit.com/yong_test/backbone.QA2.html",
	//			"http://zeus.dev.where2getit.com/yong_test/backbone.QA3.html"};
	public static  String zeusUrl[]={"http://zeus.dev.where2getit.com/yong_test/backbone.QA1.html"};
	
	public static String stagingURL[]={"http://hosted.where2getit.com/yong/backbone.QA1.html",
			"https://hosted.where2getit.com/yong/backbone.QA2.html",
	"http://hosted.where2getit.com/yong/backbone.QA3.html"};


	//Address Search Input TextBox
	@FindBy(name="addressline")
	public 
	WebElement addressSearch;

	//Search Address Drop Down box.
	@FindBy(id="search_country")
	public 
	WebElement searchCountryOption;

	//Search Address Drop Down box.
	@FindBy(name="searchradius")
	public 
	WebElement chooseRadius;

	//Pop Up Close button.
	@FindBy(className="closeBtn")
	public WebElement closeButton;

	//Button Search
	@FindBy(className="button-search")
	public WebElement buttonSearch;

	//Modal Message Box
	@FindBy(xpath="//*[@class='modal']/p")
	public WebElement modalmsg;







}
