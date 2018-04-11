package selTest;

import org.apache.commons.lang3.SystemUtils;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxDrv extends SelDriver {
	public FirefoxDrv()
	{
		//TODO: geckodriver for windows not tested
		String geckoPath = "";
		if (SystemUtils.IS_OS_WINDOWS)
		{
			geckoPath = "lib/geckodriver.exe";
		}
		else
		{
			geckoPath = "lib/geckodriver";
		}
		System.setProperty("webdriver.gecko.driver", geckoPath);
		driver = new FirefoxDriver();
	}
}
