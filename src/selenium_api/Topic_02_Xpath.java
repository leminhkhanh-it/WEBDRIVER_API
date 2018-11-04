package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Xpath {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		// Chrome
		// System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
		// driver = new ChromeDriver();

		// Firefox
		driver = new FirefoxDriver();
		driver.get("http://live.guru99.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Verify_URL_and_title() {
		String homePageTittle = driver.getTitle();
		Assert.assertEquals(homePageTittle, "Home page");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//*[@title='Create an Account']")).click();
		driver.navigate().back();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/login/");
		driver.navigate().forward();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/create/");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
