package com.poc.base;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.poc.pages.HomePage;
import com.poc.utils.Utils;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver = null;
	public Utils util = new Utils();
	public String propFile = "resources/data.properties";

	/*
	 * @author:Navakanth Description: To launch browser & Maximize browser window
	 */
	public WebDriver driverInit(String browser) {
		driver = null;
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			util.log(browser + " is launched");
		} else if (browser.equalsIgnoreCase("msedge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			util.log(browser + " is launched");
		} else if (browser.equalsIgnoreCase("safari")) {
			driver = new SafariDriver();
			util.log(browser + " is launched");
		} else {
			util.log("Please enter correct browser in config sheet");
		}

		driver.manage().window().maximize();
		util.log("Browser Window is Maximized");
		return driver;

	}

	/*
	 * @author:Navakanth Description: To navigate to a specific url
	 */
	public HomePage navigateTo(String url) {
		driver.get(url);
		util.log("Navigated to : " + url);
		return new HomePage(driver);
	}

	/*
	 * @author:Navakanth Description: To get page title
	 */
	public String getPageTitle() {
		return driver.getTitle();
	}

	/*
	 * @author:Navakanth Description: To click on a specific web element
	 */
	public void clickOn(WebElement ele) {
		ele.click();

		util.log("Clicked on " + ele);

	}

	/*
	 * @author:Navakanth Description: To input text in a web element
	 */
	public void enterIn(WebElement ele, String text) {
		ele.clear();
		ele.sendKeys(text);
		util.log("Entered " + text + " in " + ele);
	}

	/*
	 * @author:Navakanth Description: To scroll web page based on pixel
	 */
	public void scrollDown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("window.scrollBy(0,80)");
	}

	/*
	 * @author:Navakanth Description: To scroll web page to a specific element
	 */
	public void scrollToELe(WebElement ele) {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].scrollIntoView(true)", ele);
	}

	/*
	 * @author:Navakanth Description: To wait for a specific element
	 */

	public void waitForEle(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(ele));

		util.log("Waiting for Ele" + ele);

	}

}
