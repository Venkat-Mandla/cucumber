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
@CucumberOptions(features={"src/test/resources/web-features"},glue={"com.venkat.cucumber.web.steps"})
public class WebFeatureTest {

}
