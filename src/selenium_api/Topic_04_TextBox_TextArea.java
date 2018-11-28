package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_TextBox_TextArea{
	
	WebDriver driver;
	By userNameId = By.xpath("//input[@name='uid']");
	By userPasswordId = By.xpath("//input[@name='password']");

	// element edit
	By byCustomerID = By.xpath("//td[contains(text(),'Customer ID')]/following-sibling::td");
	By byCustomerName = By.xpath("//td[contains(text(),'Customer Name')]/following-sibling::td//input");
	By byGender = By.xpath("//td[contains(text(),'Gender')]/following-sibling::td//input");
	By byBirthday = By.xpath("//td[contains(text(),'Birthdate')]/following-sibling::td//input");
	By byAddress = By.xpath("//td[contains(text(),'Address')]/following-sibling::td//textarea");
	By byCity = By.xpath("//td[contains(text(),'City')]/following-sibling::td//input");
	By byState = By.xpath("//td[contains(text(),'State')]/following-sibling::td//input");
	By byPin = By.xpath("//td[contains(text(),'PIN')]/following-sibling::td//input");
	By byMobieNo = By.xpath("//td[contains(text(),'Mobile Number')]/following-sibling::td//input");
	By byEmail = By.xpath("//td[contains(text(),'E-mail')]/following-sibling::td//input");

	@BeforeClass
	public void beforeClass() {

		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Textbox_Textarea() {

		driver.get("http://demo.guru99.com/v4/");

		WebElement userNameIdElement = driver.findElement(userNameId);
		userNameIdElement.sendKeys("mngr161493");

		WebElement passIdElement = driver.findElement(userPasswordId);
		passIdElement.sendKeys("harErAh");

		driver.findElement(By.name("btnLogin")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//marquee")).getText(),
				"Welcome To Manager's Page of Guru99 Bank");

		
		// Create New Customer
		clickLink(driver, "//a[text()='New Customer']");

		sendKeyTextbox(driver, "//input[@name='name']", "Selenium Online");

		WebElement gender = driver.findElement(By.xpath("//input[@value='m']"));
		if (gender.isSelected()) {
			Assert.assertTrue(gender.isSelected());
		} else {
			gender.click();
		}

		sendKeyTextbox(driver, "//input[@id='dob']", "2000-10-01");

		sendKeyTextbox(driver, "//textarea[@name='addr']", "123 Address");

		sendKeyTextbox(driver, "//input[@name='city']", "Ho Chi Minh");

		sendKeyTextbox(driver, "//input[@name='state']", "Thu Duc");

		sendKeyTextbox(driver, "//input[@name='pinno']", "123456");

		sendKeyTextbox(driver, "//input[@name='telephoneno']", "0123456987");

		sendKeyTextbox(driver, "//input[@name='emailid']", "testest0707@gmail.com");

		sendKeyTextbox(driver, "//input[@name='password']", "123123");

		clickLink(driver, "//input[@value='Submit']");

		// Check ID
		String customerId = getInfo(driver, byCustomerID);
		System.out.println(customerId);

		// Verify Customer Registered Successfully!!!

		verifyInfo(driver, "//td[contains(text(),'Customer Name')]/following-sibling::td", "Selenium Online");

		verifyInfo(driver, "//td[contains(text(),'Gender')]/following-sibling::td", "male");

		verifyInfo(driver, "//td[contains(text(),'Birthdate')]/following-sibling::td", "2000-10-01");

		verifyInfo(driver, "//td[contains(text(),'Address')]/following-sibling::td", "123 Address");

		verifyInfo(driver, "//td[contains(text(),'City')]/following-sibling::td", "Ho Chi Minh");

		verifyInfo(driver, "//td[contains(text(),'State')]/following-sibling::td", "Thu Duc");

		verifyInfo(driver, "//td[contains(text(),'Pin')]/following-sibling::td", "123456");

		verifyInfo(driver, "//td[contains(text(),'Mobile No.')]/following-sibling::td", "0123456987");

		verifyInfo(driver, "//td[contains(text(),'Email')]/following-sibling::td", "testest0707@gmail.com");

		// Edit Customer
		clickLink(driver, "//a[text()='Edit Customer']");

		sendKeyTextbox(driver, "//input[@name='cusid']", customerId);

		clickLink(driver, "//input[@name='AccSubmit']");

		// Verify Customer Name & Address Form Edit
		Assert.assertEquals(driver.findElement(byCustomerName).getAttribute("value"), "Selenium Online");

		Assert.assertEquals(getInfo(driver, byAddress), "123 Address");

		// Input Text Edit
		checkIsEnable_And_InputText(driver, byAddress, "Edit 123 Addeess");
		checkIsEnable_And_InputText(driver, byCity, "Edit Ho Chi Minh");
		checkIsEnable_And_InputText(driver, byState, "Edit Thu Duc");
		checkIsEnable_And_InputText(driver, byPin, "999999");
		checkIsEnable_And_InputText(driver, byMobieNo, "987654321");
		checkIsEnable_And_InputText(driver, byEmail, "edittestest0707@gmail.com");

		// Submit Edit
		clickLink(driver, "//input[@name='sub']");

		// Verify Customer details updated Successfully!!!
		verifyInfo(driver, "//td[contains(text(),'Address')]/following-sibling::td", "Edit 123 Addeess");
		verifyInfo(driver, "//td[contains(text(),'City')]/following-sibling::td", "Edit Ho Chi Minh");
		verifyInfo(driver, "//td[contains(text(),'State')]/following-sibling::td", "Edit Thu Duc");
		verifyInfo(driver, "//td[contains(text(),'Pin')]/following-sibling::td", "999999");
		verifyInfo(driver, "//td[contains(text(),'Mobile No.')]/following-sibling::td", "987654321");
		verifyInfo(driver, "//td[contains(text(),'Email')]/following-sibling::td", "edittestest0707@gmail.com");

	}


	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	// Method
	public void clickLink(WebDriver driver, String xpath) {
		driver.findElement(By.xpath(xpath)).click();
	}

	public void sendKeyTextbox(WebDriver driver, String xpath, String senskey) {
		driver.findElement(By.xpath(xpath)).sendKeys(senskey);
	}

	public String getInfo(WebDriver driver, By byName) {
		return driver.findElement(byName).getText();
	}

	public void checkIsEnable_And_InputText(WebDriver driver, By byName, String textInput) {
		WebElement isCheck = driver.findElement(byName);
		if (isCheck.isEnabled()) {
			isCheck.clear();
			isCheck.sendKeys(textInput);
		}
	}

	public void verifyInfo(WebDriver driver, String xpath, String textVerify) {
		Assert.assertEquals(driver.findElement(By.xpath(xpath)).getText(), textVerify);
	}

	/*
	 * public String randomText() { Random num = new Random(); String
	 * first_mail_name = "automationtest" + num.nextInt(); return first_mail_name; }
	 */

}
