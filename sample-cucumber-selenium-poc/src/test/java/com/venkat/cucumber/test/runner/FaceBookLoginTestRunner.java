/**
 * 
 */
package com.venkat.cucumber.test.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/**
 * @author Venkat
 *
 */

@RunWith(Cucumber.class)
@CucumberOptions(features={"src/test/resources/facebook-features"},
				tags="~@IUP",
				glue={"com.venkat.cucumber.facebook.login.steps"})
public class FaceBookLoginTestRunner {

}
