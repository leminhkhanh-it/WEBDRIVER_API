package selenium_api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_DropdownList {

	WebDriver driver;
	WebDriverWait waitExplicit;
	JavascriptExecutor javaExecutor;

	@BeforeClass
	public void beforeClass() {

		driver = new FirefoxDriver();
		waitExplicit = new WebDriverWait(driver, 30);
		javaExecutor = (JavascriptExecutor) driver;
		

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Dropdown_List() {

		driver.get("https://daominhdam.github.io/basic-form/index.html");
		
		Select select = new Select(driver.findElement(By.xpath("//select[@id='job1']")));

		Assert.assertFalse(select.isMultiple());
		select.selectByVisibleText("Automation Tester");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Automation Tester");
		select.selectByValue("manual");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Manual Tester");
		select.selectByIndex(3);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Mobile Tester");
		Assert.assertEquals(select.getOptions().size(), 5);
	}

	@Test
	public void TC_02_JqueryUI_Dropdown_List() throws Exception {

		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");

		selectItemInCutomDropdow("//div[@class='demo']", "//span[@id='number-button']/span[contains(@class,'ui-selectmenu-icon')]", "//ul[@id='number-menu']//li", "19");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='19']")).isDisplayed());
		Thread.sleep(3000);

		selectItemInCutomDropdow("//div[@class='demo']", "//span[@id='number-button']/span[contains(@class,'ui-selectmenu-icon')]", "//ul[@id='number-menu']//li", "5");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='5']")).isDisplayed());
		Thread.sleep(3000);

		selectItemInCutomDropdow("//div[@class='demo']", "//span[@id='number-button']/span[contains(@class,'ui-selectmenu-icon')]", "//ul[@id='number-menu']//li", "2");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='2']")).isDisplayed());
		Thread.sleep(3000);

	}

	@Test
	public void TC_03_KendoUI_Dropdown_List() throws Exception {

		driver.get("https://demos.telerik.com/kendo-ui/dropdownlist/index");

		selectItemInCutomDropdow("//div[@id='cap-view']", "//span[@aria-owns='color_listbox']", "//ul[@id=\"color_listbox\"]/li", "Orange");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@aria-owns='color_listbox']//span[@class='k-input' and text()='Orange']")).isDisplayed());
		Thread.sleep(3000);

		selectItemInCutomDropdow("//div[@id='cap-view']", "//span[@aria-owns='color_listbox']", "//ul[@id=\"color_listbox\"]/li", "Black");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@aria-owns='color_listbox']//span[@class='k-input' and text()='Black']")).isDisplayed());
		Thread.sleep(3000);

		selectItemInCutomDropdow("//div[@id='cap-view']", "//span[@aria-owns='color_listbox']", "//ul[@id=\"color_listbox\"]/li", "Grey");
		Assert.assertTrue(driver.findElement(By.xpath("//span[@aria-owns='color_listbox']//span[@class='k-input' and text()='Grey']")).isDisplayed());
		Thread.sleep(3000);

	}

	@Test
	public void TC_04_Angular_Dropdown_List() throws Exception {

		driver.get("https://material.angular.io/components/select/examples");

		selectItemInCutomDropdow("//div[@class='docs-example-viewer-title-spacer' and text()='Select with reset option']", "//mat-select[@placeholder='State']", "//div[@id='cdk-overlay-0']//mat-option", "Washington");
		Assert.assertTrue(driver.findElement(By.xpath("//mat-select[@placeholder='State']//span[text()='Washington']")).isDisplayed());
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		Thread.sleep(3000);

		selectItemInCutomDropdow("//div[@class='docs-example-viewer-title-spacer' and text()='Select with reset option']", "//mat-select[@placeholder='State']", "//div[@id='cdk-overlay-0']//span", "Oregon");
		Assert.assertTrue(driver.findElement(By.xpath("//mat-select[@placeholder='State']//span[text()='Oregon']")).isDisplayed());
		Thread.sleep(3000);

		selectItemInCutomDropdow("//div[@class='docs-example-viewer-title-spacer' and text()='Select with reset option']", "//mat-select[@placeholder='State']", "//div[@id='cdk-overlay-0']//span", "Louisiana");
		Assert.assertTrue(driver.findElement(By.xpath("//mat-select[@placeholder='State']//span[text()='Louisiana']")).isDisplayed());
		Thread.sleep(3000);

	}

	@Test
	public void TC_05_Vue_Dropdown_List() throws Exception {

		driver.get("https://mikerodham.github.io/vue-dropdowns/");

		selectItemInCutomDropdow("//div[@class='btn-group']", "//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//li", "Second Option");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='btn-group']//li[contains(text(),'Second Option')]")).isDisplayed());
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		Thread.sleep(3000);

		selectItemInCutomDropdow("//div[@class='btn-group']", "//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//li", "First Option");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='btn-group']//li[contains(text(),'First Option')]")).isDisplayed());
		Thread.sleep(3000);

		selectItemInCutomDropdow("//div[@class='btn-group']", "//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//li", "Third Option");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='btn-group']//li[contains(text(),'Third Option')]")).isDisplayed());
		Thread.sleep(3000);

	}

	/*@Test
	public void TC_06_Editable_Dropdown_List() throws Exception {

		driver.get("http://indrimuska.github.io/jquery-editable-select/");

		selectItemInCutomDropdow("//div[@id='basic-place']", "//div[@id='default-place']//input", "//div[@id='default-place']//li", "Volkswagen");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='default-place']//li[@class='es-visible' and text()='Volkswagen']")).isDisplayed());
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@id='default-place']//input")).clear();

		selectItemInCutomDropdow("//div[@id='basic-place']", "//div[@id='default-place']//input", "//div[@id='default-place']//li", "Jeep");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='default-place']//li[@class='es-visible' and text()='Jeep']")).isDisplayed());
		Thread.sleep(3000);

		selectItemInCutomDropdow("//div[@id='basic-place']", "//div[@id='default-place']//input", "//div[@id='default-place']//li", "Citroen");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='default-place']//li[@class='es-visible' and text()='Citroen']")).isDisplayed());
		Thread.sleep(3000);

	}*/

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void selectItemInCutomDropdow(String scrollXpath, String parentXpath, String childXpath, String selecItem) throws Exception {

		// Scroll Parent Element
		WebElement scrollElement = driver.findElement(By.xpath(scrollXpath));
		javaExecutor.executeScript("arguments[0].scrollIntoView(true);", scrollElement);
		// Thread.sleep(2000);

		// Click Dropdown
		WebElement element = driver.findElement(By.xpath(parentXpath));
		element.click();
		// Thread.sleep(2000);

		List<WebElement> childList = driver.findElements(By.xpath(childXpath));
		waitExplicit.until(ExpectedConditions.visibilityOfAllElements(childList));

		for (WebElement child : childList) {
			String textItem = child.getText();
			System.out.println("Text in Dropdown: " + textItem);

			if (textItem.equals(selecItem)) {
				javaExecutor.executeScript("arguments[0].scrollIntoView(true);", child);
				Thread.sleep(2000);
				child.click();
				break;
			}
		}
	}
}
