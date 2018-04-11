package selTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.ArrayList;

class GoogleSearchTest {

	static boolean FirefoxFans;
	private WebDriver webDriver;
	private boolean driverReady = false;
	final String googleUrl = "http://www.google.com";

	private List<String> googleSearch(String searchKeyword)
	{
		List<String> searchResult;
		
		WebElement queryTxt = webDriver.findElement(By.name("q"));
		
		queryTxt.sendKeys(searchKeyword);
		queryTxt.submit();
		
		WebElement myDynamicElement = (new WebDriverWait(webDriver, 10))
	              .until(ExpectedConditions.presenceOfElementLocated(By.id("resultStats")));

	    List<WebElement> findElements = webDriver.findElements(By.xpath("//*[@id='rso']//h3/a"));
	    searchResult = new ArrayList<String>(findElements.size());
		
	    for (WebElement urlResult : findElements)
	    {
	    	searchResult.add(urlResult.getAttribute("href").toString());
	    }
	    
	    return searchResult;
	    
	}
	@BeforeAll
	static void initAll()
	{
		FirefoxFans = true;		// Test on Firefox
		//FirefoxFans = false;	// Test on Safari for now
	}
	@BeforeEach
	void init()
	{
		if (FirefoxFans)
		{
			webDriver = new FirefoxDrv().getDriver();
		}
		else
		{
			webDriver = new SafariDrv().getDriver();
		}
		webDriver.get(googleUrl);
		driverReady = true;
	}
	@Test
	@DisplayName("Organization Web Page Test")
	void orgPageTest() {
		final String expectedUrl = "https://www.seleniumhq.org/";
		if (driverReady)
		{
			List<String> resultList = googleSearch("Selenium");
			assertTrue(resultList.stream().anyMatch(str -> str.trim().equals(expectedUrl)));
		}
		else
		{
			fail("Failed to connect to browser");
		}
	}
	@Test
	@DisplayName("Wiki Page Test")
	void wikiPageTest() {
		final String expectedUrl = "https://en.wikipedia.org/wiki/Selenium";
		if (driverReady)
		{
			List<String> resultList = googleSearch("Selenium");
			assertTrue(resultList.stream().anyMatch(str -> str.trim().equals(expectedUrl)));
		}
		else
		{
			fail("Failed to connect to browser");
		}
	}
	@AfterEach
	void tearDown()
	{
		driverReady = false;
		webDriver.close();
		if (!FirefoxFans)
		{
			webDriver.quit();
		}
	}
	
	

}
