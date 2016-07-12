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
@CucumberOptions(features={"src/test/resources/irctc-features"},
				tags="~@UC",
				glue={"com.venkat.cucumber.irctc.login.steps"})
public class IrctcLoginRunner {

}
