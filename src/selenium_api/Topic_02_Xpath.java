package selenium_api;

import java.util.Random;
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

	String First_Name = "";
	String Last_Name = "";
	String Email_Address = "";
	String Pass_Word = "";

	@BeforeClass
	public void beforeClass() {
		// Chrome
		/*
		 * System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
		 * driver = new ChromeDriver();
		 */

		// Firefox

		driver = new FirefoxDriver();
		driver.get("http://live.guru99.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test
	public void TC_01_Verify_Url_and_Title() {

		System.out.println("test case 01");
		String homePageTittle = driver.getTitle();
		Assert.assertEquals(homePageTittle, "Home page");

		// Click Button My Account
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		// Click Button Create an Account
		driver.findElement(By.xpath("//*[@title='Create an Account']")).click();

		driver.navigate().back();

		Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/login/");
		driver.navigate().forward();

		Assert.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/create/");
		driver.navigate().to("http://live.guru99.com");
	}

	@Test
	public void TC_02_Login_Empty() {
		// System.out.println("test case 02");

		// Click Button My Account
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		// Click Button Login
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		String verify_user_error_message = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']"))
				.getText();

		String verify_pass_error_message = driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']"))
				.getText();

		// System.out.println(verify_pass_error_message);

		Assert.assertEquals(verify_user_error_message, "This is a required field.");
		Assert.assertEquals(verify_pass_error_message, "This is a required field.");
	}

	@Test
	public void TC_03_Login_With_Email_Invalid() {

		driver.navigate().to("http://live.guru99.com");
		// Click Button My Account
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		// Input email
		Email_Address = "123434234@12312.123123";
		driver.findElement(By.xpath("//*[@id='email']")).sendKeys(Email_Address);

		// Click Button Login
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		String verify_user_error_message = driver.findElement(By.xpath("//div[@id='advice-validate-email-email']"))
				.getText();

		Assert.assertEquals(verify_user_error_message,
				"Please enter a valid email address. For example johndoe@domain.com.");
	}

	@Test
	public void TC_04_Login_With_Less_6_Charactor_PassWord() {
		driver.navigate().to("http://live.guru99.com");
		// Click Button My Account
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		// Input email
		Email_Address = "automation@gmail.com";
		Pass_Word = "123";
		driver.findElement(By.xpath("//*[@id='email']")).sendKeys(Email_Address);
		driver.findElement(By.xpath("//*[@id='pass']")).sendKeys(Pass_Word);

		// Click Button Login
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		String verify_pass_error_message = driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']"))
				.getText();
		
		System.out.println(verify_pass_error_message);

		Assert.assertEquals(verify_pass_error_message,
				"Please enter 6 or more characters without leading or trailing spaces.");
	}

	@Test
	public void TC_05_Login_With_PassWord_Invalid() {
		driver.get("http://live.guru99.com");
		// Click Button My Account
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		// Input email
		Email_Address = "automation@gmail.com";
		Pass_Word = "12312312";
		driver.findElement(By.xpath("//*[@id='email']")).sendKeys(Email_Address);
		driver.findElement(By.xpath("//*[@id='pass']")).sendKeys(Pass_Word);

		// Click Button Login
		driver.findElement(By.xpath("//button[@id='send2']")).click();

		String verify_pass_error_message = driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText();

		// System.out.println("Check: pass error" + verify_pass_error_message);

		Assert.assertEquals(verify_pass_error_message, "Invalid login or password.");
	}

	@Test
	public void TC_06_Create_Account() {

		First_Name = "Automation";
		Last_Name = "Online_07";
		Email_Address = "automation" + test_random() + "@gmail.com";
		Pass_Word = "121212";

		driver.get("http://live.guru99.com");

		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		driver.findElement(By.xpath("//*[@title='Create an Account']")).click();

		// Input data Create Account
		driver.findElement(By.id("firstname")).sendKeys(First_Name);

		driver.findElement(By.id("middlename")).sendKeys("");

		driver.findElement(By.name("lastname")).sendKeys(Last_Name);

		driver.findElement(By.id("email_address")).sendKeys(Email_Address);

		driver.findElement(By.id("password")).sendKeys(Pass_Word);

		driver.findElement(By.id("confirmation")).sendKeys(Pass_Word);

		driver.findElement(By.xpath("//form[@id='form-validate']//button[@title='Register']")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(),
				"Thank you for registering with Main Website Store.");

		driver.findElement(By.xpath("//div[@class='skip-links']//span[contains(text(),'Account')]")).click();
		
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();

		String TextTemp = driver.findElement(By.xpath("//div[@class='page-title']/h2")).getText();
		
		
		System.out.println(TextTemp);
		
		Assert.assertEquals(TextTemp, "THIS IS DEMO SITE FOR   ");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int test_random() {
		Random rd = new Random();

		return rd.nextInt(999999);

	}

}
