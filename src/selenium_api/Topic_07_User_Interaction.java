package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_User_Interaction {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		
		// Firefox
		driver = new FirefoxDriver();
		driver.get("https://google.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_CheckInvinonment() {
		String homePageTittle = driver.getTitle();
		Assert.assertEquals(homePageTittle, "Google");
	}

	@AfterClass
	public void afterClass() {
	}

}
