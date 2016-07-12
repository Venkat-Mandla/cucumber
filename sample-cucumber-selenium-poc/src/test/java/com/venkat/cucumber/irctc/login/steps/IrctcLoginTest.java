/**
 * 
 */
package com.venkat.cucumber.irctc.login.steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gargoylesoftware.htmlunit.BrowserVersion;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * @author Venkat
 *
 */

public class IrctcLoginTest {
	private Logger LOG = LoggerFactory.getLogger(this.getClass());
	private WebDriver driver;

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/lib/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://github.com/venkat-mandla/camel");
	}

	@Given("^User entered valid userName or phoneNumber as \"([^\"]*)\" and password as \"([^\"]*)\"$")
	public void user_entered_valid_userName_or_phoneNumber_as_and_password_as(String userName, String password)
			throws Throwable {
		driver.get("https://www.irctc.co.in/eticketing/loginHome.jsf");
		driver.findElement(By.id("usernameId")).sendKeys(userName);
		driver.findElement(By.name("j_password")).sendKeys(password);

		driver.findElement(By.name("j_password")).sendKeys(password);

		HtmlUnitDriver unitdriver = new HtmlUnitDriver(BrowserVersion.CHROME);

		unitdriver.get("https://www.irctc.co.in/eticketing/loginHome.jsf");

		WebElement cImageElement = unitdriver.findElement(By.id("cimage"));

		System.out.println("UserName: " + cImageElement);

	}

	@When("^click on loginbutton \"([^\"]*)\" button$")
	public void click_on_loginbutton_button(String loginButton) throws Throwable {
		driver.findElement(By.id(loginButton)).click();
	}

	@Given("^User entered valid irctc url as \"([^\"]*)\"$")
	public void user_entered_valid_irctc_url_as(String arg1) throws Throwable {
		driver.get(arg1);
	}

	@Then("^render home page$")
	public void render_home_page() throws Throwable {
	}

	// usernameId
	// j_password
	// j_captcha
	// cimage

	// loginbutton

	@After
	public void tearDown() {
		LOG.info("closing the browser");
		driver.quit();
	}

}
