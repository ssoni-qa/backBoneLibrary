package stagingUrl;

import org.testng.annotations.Test;
import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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


import java.util.List;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import java.util.ArrayList;

import java.util.HashSet;

import java.util.Iterator;

import java.util.Set;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.Keys;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebDriver.Navigation;

import org.openqa.selenium.WebDriver.Options;

import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.firefox.FirefoxProfile;

import org.openqa.selenium.support.ui.*;

import com.google.common.base.Function;

import com.google.common.base.Predicate;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

public class TC40_Header_Footer_links extends BrowserStackTestNGTest
{
	ExtentTest test40 ;
	private int invalidLinksCount;

	@BeforeMethod
	public void handleTestMethodName(Method method)
	{
		testName = method.getName(); 

	}
	@Test
	public void eader_Footer_links() throws InterruptedException, IOException 
	{
		System.out.println("Test Objective : To verify Header Footer links all lead to a page with no 404 errors.");

		System.out.println("Verification Step :1. Confirm all header/footer links working correctly..");

		for (String stagingURL:OpenStreetMapLocatorPage.stagingURL) {

			try {
				
				
				test40 = extent.startTest("Test Objective : To verify Header Footer links all lead to a page with no 404 errors.");

				test40.log(LogStatus.INFO,
						"Verification Step :1. Confirm all header/footer links working correctly..");

				test40.log(LogStatus.INFO, "Staging Url :"+stagingURL);
				
				System.out.println("Project URL :"+stagingURL);
				
				//Initializing WebElement from Page Factory.
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



				try {
					List<WebElement> links=driver.findElements(By.tagName("a"));


					for(int i=0;i<links.size();i++)
					{

						WebElement ele= links.get(i);

						String url=ele.getAttribute("href");
						verifyLinkActive(url);
					}

				} catch (Exception e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
					System.out.println("Excpetion Occured.");
					test40.log(LogStatus.FAIL, "Exception Occured. : " + e.getMessage() + " "
							+ test40.addScreenCapture(captureScreenMethod(dest)));
				}
			} catch (Exception e) {
				
				System.out.println("Exception Occured.");
				test40.log(LogStatus.FAIL, "Exception Occured. : " + e.getClass() +e.getMessage()+ " "
						+ test40.addScreenCapture(captureScreenMethod(dest)));
			}
		}

	}

	public  void verifyLinkActive(String linkUrl) throws IOException
	{
		
		try 
		{
			URL url = new URL(linkUrl);

			HttpURLConnection httpURLConnect=(HttpURLConnection)url.openConnection();

			httpURLConnect.setConnectTimeout(3000);

			httpURLConnect.connect();

			if(httpURLConnect.getResponseCode()==200)
			{
				System.out.println(linkUrl+" - "+httpURLConnect.getResponseMessage());
				test40.log(LogStatus.INFO, linkUrl+" - "+httpURLConnect.getResponseMessage());
				System.out.println("Pass");
			}
			if(httpURLConnect.getResponseCode()==HttpURLConnection.HTTP_NOT_FOUND)  
			{
				System.out.println(linkUrl+" - "+httpURLConnect.getResponseMessage() + " - "+ HttpURLConnection.HTTP_NOT_FOUND);
				test40.log(LogStatus.INFO, linkUrl+" - "+httpURLConnect.getResponseMessage());
			}
		} catch (Exception e) {
		}
	} 
	@AfterMethod
	public void getResult()
	{
		extent.endTest(test40);
		test40.log(LogStatus.INFO, "TC 40 executed succesfully.");
		System.out.println("------------------------------------------------------------------------------------");
		driver.quit();
	}

}
