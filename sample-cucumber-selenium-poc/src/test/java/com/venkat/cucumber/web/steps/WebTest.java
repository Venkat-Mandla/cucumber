/**
 * 
 */
package com.venkat.cucumber.web.steps;

import java.util.concurrent.TimeUnit;

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

public class WebTest {
	private Logger LOG=LoggerFactory.getLogger(this.getClass());
	private WebDriver driver ;
	
	@Before
	public void setUp(){
		System.setProperty("webdriver.chrome.driver", "src/test/resources/lib/chromedriver.exe");
		driver=new ChromeDriver();
		//driver.get("http://github.com/venkat-mandla/camel");
		driver.get("https://www.google.co.in");
	}

	@Given("^User entered valid url as \"([^\"]*)\"$")
	public void user_entered_valid_url_as(String webUrl) throws Throwable {
		driver.get(webUrl);
		LOG.info("Entered URL: "+webUrl);
	}

	@When("^pressed enter button$")
	public void pressed_enter_button() throws Throwable {
	}
	@Then("^render home page$")
	public void render_home_page() throws Throwable {
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
	}
	
	@After
	public void tearDown(){
		driver.quit();
	}

}
