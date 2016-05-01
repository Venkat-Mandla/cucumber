/**
 * 
 */
package com.venkat.sample.webservice.test;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Venkat
 *
 *///@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"/META-INF/spring/bundle-context-test-routes.xml"})
public class DemoTest extends CamelSpringTestSupport{
	private static final Logger LOGGER=LoggerFactory.getLogger(DemoTest.class);
	
	@EndpointInject(uri="mock:results")
	private MockEndpoint mockEndpoint;
	
	
	@Override
	protected AbstractApplicationContext createApplicationContext() {
		return new ClassPathXmlApplicationContext("/META-INF/spring/bundle-context-test-routes.xml");
	}
	
	//It was giving below error so I have override the teamDown()
	//CamelSpringTestSupport.tearDown:118 » IncompatibleClassChange
	@Override
	public void tearDown() throws Exception {
		//super.tearDown();
	}
	
	@Override
	protected RouteBuilder createRouteBuilder() throws Exception {
		return new RouteBuilder() {
			@Override
			public void configure() throws Exception {
				from("direct:start").to("direct:demoStart").to("mock:results");
			}
		};
	}

	@Test
	public void testSuccessScenario() throws Exception{
		LOGGER.info("started ...");
		template.sendBody("direct:start", "dummy data");
		mockEndpoint.setExpectedMessageCount(1);
		mockEndpoint.assertIsSatisfied();
		Exchange exchange=mockEndpoint.getExchanges().get(0);
		assertEquals("Hello Camel", exchange.getIn().getBody());
		LOGGER.info("ended ...");
	}
}