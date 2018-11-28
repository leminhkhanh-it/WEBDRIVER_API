package selenium_api;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Cexercise {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		// Chrome
		/*
		 * System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
		 * driver = new ChromeDriver();
		 */

		// Firefox
		driver = new FirefoxDriver();
		// driver.get("https://google.com");

	}

	@Test
	public void TC_01_Check_Element_IsDisplay() {
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Check element Email / Age:Under 18 / Education
		WebElement Email = driver.findElement(By.id("mail"));
		
		Assert.assertTrue(Email.isDisplayed());
		
		if (Email.isDisplayed()) {
			System.out.println("Email element is displayed");
			Email.sendKeys(randomText() + "@gmail.com");
		}

		WebElement Age_under18 = driver.findElement(By.id("under_18"));
		
		Assert.assertTrue(Age_under18.isDisplayed());
		
		if (Age_under18.isDisplayed()) {
			System.out.println("Age:under_18 element is displayed");
			Age_under18.click();
		}

		WebElement Education = driver.findElement(By.id("edu"));
		
		Assert.assertTrue(Education.isDisplayed());
		
		if (Education.isDisplayed()) {
			System.out.println("Education element is displayed");
			Education.sendKeys(randomText() + randomText());
		}
	}

	@Test
	public void TC_02_Check_Element_IsEnable() {
		driver.get("https://daominhdam.github.io/basic-form/index.html");

		// Check element Enable

		CheckElementisEnable(driver, "//*[@id='mail']", "Email:");

		CheckElementisEnable(driver, "//*[@id='under_18']", "Age:under_18");

		CheckElementisEnable(driver, "//*[@id='edu']", "Education");

		CheckElementisEnable(driver, "//*[@id='job1']", "Job Role 01");

		CheckElementisEnable(driver, "//*[@id='development']", "Interests Development");

		CheckElementisEnable(driver, "//*[@id='slider-1']", "Slider 01");

		CheckElementisEnable(driver, "//*[@id='button-enabled']", "Button Enable");

		// Check Element is Disable

		CheckElementisEnable(driver, "//*[@id='password']", "Password");
		
		CheckElementisEnable(driver, "//*[@id='radio-disabled']", "Age (Radiobutton is disabled");
		
		CheckElementisEnable(driver, "//*[@id='bio']", "Biography");
		
		CheckElementisEnable(driver, "//*[@id='job2']", "Job Role 02");
		
		CheckElementisEnable(driver, "//*[@id='check-disbaled']", "Interests (Checkbox is disabled)");
		
		CheckElementisEnable(driver, "//*[@id='slider-2']", "Slider 02");
		
		CheckElementisEnable(driver, "//*[@id='button-disabled']", "Button Disabled");
	}
	
	@Test
	public void TC_03_Check_Element_IsSelected() {
		
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		
		WebElement Age_under_18 = driver.findElement(By.id("under_18"));
		//Age_under_18.click();
		if(Age_under_18.isSelected()) {
			Assert.assertTrue(Age_under_18.isSelected());
		}else {
			Age_under_18.click();
		}
		
		WebElement Development = driver.findElement(By.xpath("//*[@id='development']"));
		Development.click();
		
		if(Development.isSelected()) {
			
		}else {
			Development.click();
		}
		
	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

	private Boolean CheckElementisEnable(WebDriver driver, String locatorCheck, String elementName) {
		WebElement locator = driver.findElement(By.xpath(locatorCheck));
		if (locator.isEnabled()) {
			System.out.println(elementName + "Element is enable");
			return locator.isEnabled();
		} else {
			System.out.println(elementName + "Element is not disabled");
			return locator.isDisplayed();
		}
		// return locator.isEnabled();
	}
	
	

	public String randomText() {
		Random num = new Random();
		String first_mail_name = "automationtest" + num.nextInt();
		return first_mail_name;
	}

}
