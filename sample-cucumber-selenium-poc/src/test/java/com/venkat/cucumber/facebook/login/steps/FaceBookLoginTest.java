/**
 * 
 */
package com.venkat.cucumber.facebook.login.steps;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * @author Venkat
 *
 */

public class FaceBookLoginTest {
	private Logger LOG=LoggerFactory.getLogger(this.getClass());
	private WebDriver driver ;
	
	@Before
	public void setUp(){
		System.setProperty("webdriver.chrome.driver", "src/test/resources/lib/chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("http://github.com/venkat-mandla/camel");
	}

	@Given("^User entered valid userName or phoneNumber as \"([^\"]*)\" and password as \"([^\"]*)\"$")
	public void user_entered_valid_userName_or_phoneNumber_as_and_password_as(String userName, String password) throws Throwable {
		driver.get("https://www.facebook.com/");
		driver.findElement(By.id("email")).sendKeys(userName);
		driver.findElement(By.id("pass")).sendKeys(password);
		
		System.out.println("UserName: " + userName);
	}

	@When("^click on loginbutton \"([^\"]*)\" button$")
	public void click_on_loginbutton_button(String loginButton) throws Throwable {
		driver.findElement(By.id(loginButton)).click();
	}

	@Then("^render home page$")
	public void render_home_page() throws Throwable {
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
	}
	
	@Given("^User entered valid userName as \"([^\"]*)\" and password as \\$$")
	public void user_entered_valid_userName_as_and_password_as_$(String arg1) throws Throwable {
		System.out.println(arg1);
	}
	@After
	public void tearDown(){
		LOG.info("closing the browser");
		driver.quit();
	}

}
