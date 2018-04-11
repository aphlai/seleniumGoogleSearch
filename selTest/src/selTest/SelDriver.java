package selTest;

import org.openqa.selenium.WebDriver;

public abstract class SelDriver {
	protected WebDriver driver;
	public String urlVisit;
	
	public WebDriver getDriver()
	{
		return driver;
	}
}

