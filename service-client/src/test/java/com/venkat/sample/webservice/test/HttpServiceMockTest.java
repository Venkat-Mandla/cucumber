/**
 * 
 */
package com.venkat.sample.webservice.test;

import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Venkat
 *
 */
public class HttpServiceMockTest extends CamelSpringTestSupport{
	
	private static final Logger LOGGER=LoggerFactory.getLogger(HttpServiceMockTest.class);
	
	@EndpointInject(uri="mock:results")
	private MockEndpoint mockEndpoint;
	@Override
	protected AbstractApplicationContext createApplicationContext() {
		return new ClassPathXmlApplicationContext("/META-INF/spring/bundle-context-test-routes.xml");
	}
	
	//I was getting below error so I have override the teamDown()
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
				from("direct:startRestClient").to("direct:restClient").to("mock:results");
			}
		};
	}
	@Test
	public void httpSuccessScenario() throws Exception{
		LOGGER.info("started ...");
		 context.getRouteDefinition("RestClient").adviceWith(context, new RouteBuilder() {
		        @Override
		        public void configure() throws Exception {
		            interceptSendToEndpoint("http://localhost:8080/demoService")
		                    .skipSendToOriginalEndpoint()
		                    .log("################skipping http call")
		                    .setBody().simple("Http Mock endpoint resonse");
		        }
		    });
		template.sendBody("direct:startRestClient", "Dummy http request");
		mockEndpoint.setExpectedMessageCount(1);
		mockEndpoint.assertIsSatisfied();
		Exchange exchange=mockEndpoint.getExchanges().get(0);
		assertEquals("Hello Camel", exchange.getIn().getBody());
		LOGGER.info("ended ...");
	}

}
