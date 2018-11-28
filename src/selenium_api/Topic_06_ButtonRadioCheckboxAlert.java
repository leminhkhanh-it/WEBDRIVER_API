package selenium_api;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_ButtonRadioCheckboxAlert {
	WebDriver driver;
	JavascriptExecutor javaExecotor;

	@BeforeClass
	public void beforeClass() {

		// Firefox
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		javaExecotor = (JavascriptExecutor) driver;
	}

	// @Test
	public void TC_01_ClickButtonByJavascrip() {

		driver.get("http://live.guru99.com/");

		// Click Button My Account
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		AssertJUnit.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/login/");

		// Click Button CREATE AN ACCOUNT By Javascripe
		WebElement accountElementButton = driver.findElement(By.xpath("//a[@title='Create an Account']"));
		Commons.clickElementByJavascrip(driver, accountElementButton);
		AssertJUnit.assertEquals(driver.getCurrentUrl(), "http://live.guru99.com/index.php/customer/account/create/");
	}

	// @Test
	public void TC_02_CheckboxHandle() {

		driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
		WebElement dualZoneCheckbox = driver.findElement(By.xpath("//label[contains(text(),'Dual-zone air conditioning')]/preceding-sibling::input"));
		Commons.clickElementByJavascrip(driver, dualZoneCheckbox);
		Assert.assertTrue(dualZoneCheckbox.isSelected());
		Commons.clickElementByJavascrip(driver, dualZoneCheckbox);
		Assert.assertFalse(dualZoneCheckbox.isSelected());
	}

	// @Test
	public void TC_03_RadioButtonHandle() throws Exception {

		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
		WebElement petrolRadioButton = driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input"));
		Commons.clickElementByJavascrip(driver, petrolRadioButton);
		Thread.sleep(3000);
		if (!petrolRadioButton.isSelected()) {
			Commons.clickElementByJavascrip(driver, petrolRadioButton);
		}
	}

	@Test
	public void TC_04_Alert() throws Exception {

		driver.get("https://daominhdam.github.io/basic-form/index.html");

		WebElement result = driver.findElement(By.xpath("//p[@id='result']"));

		javaExecotor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//div[@class='example']")));
		// Click for JS Alert
		Commons.clickElementByJavascrip(driver, driver.findElement(By.xpath("//button[text()='Click for JS Alert']")));
		Thread.sleep(3000);
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		alert.accept();
		Assert.assertEquals(result.getText(), "You clicked an alert successfully");

		// Click for JS Confirm
		Commons.clickElementByJavascrip(driver, driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")));
		Thread.sleep(3000);
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		alert.dismiss();
		Assert.assertEquals(result.getText(), "You clicked: Cancel");

		// Click for JS Prompt
		Commons.clickElementByJavascrip(driver, driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")));
		Thread.sleep(3000);
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		alert.sendKeys("Le Minh Khanh");
		alert.accept();
		Assert.assertEquals(result.getText(), "You entered: Le Minh Khanh");
		
		
	}
	
	@Test
	public void TC_05_Authentication_Alert() throws Exception {
       driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
		/*alert.sendKeys("admin");
		alert.sendKeys("keyTAB");
		alert.sendKeys("admin");
		alert.accept();*/
		String checkText = "Congratulations! You must have the proper credentials.";
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='example']//p")).getText().trim(), checkText);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
