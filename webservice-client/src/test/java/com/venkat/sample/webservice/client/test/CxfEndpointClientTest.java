/**
 * 
 */
package com.venkat.sample.webservice.client.test;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Venkat
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/spring/bundle-context-test-routes.xml" })
public class CxfEndpointClientTest{
	
	private static final Logger LOGGER=LoggerFactory.getLogger(CxfEndpointClientTest.class);
	@Autowired
	private CamelContext camelContext;

	@Produce
	private ProducerTemplate template;
	
	@EndpointInject(uri="mock:results")
	private MockEndpoint mockEndpoint;
	
	
	@Before
	public void setUp() throws Exception {
		camelContext.addRoutes(createRouteBuilder());
		template=camelContext.createProducerTemplate();
	}
	
	protected RouteBuilder createRouteBuilder() throws Exception {
		
		return new RouteBuilder() {
			@Override
			public void configure() throws Exception {
				from("direct:start").to("direct:inputQueue").to("mock:results");
			}
		};
	}

	@Test
	public void testCxfEndpoint() throws Exception {
		LOGGER.info("started...");
		mockEndpoint.setExpectedMessageCount(1);
		addAdwiseToSkipEndpoint();
		template.requestBody("direct:start","dummy");
		mockEndpoint.assertIsSatisfied();
	}
	

	private void addAdwiseToSkipEndpoint() throws Exception {
		camelContext.getRouteDefinition("mobileStoreClient").adviceWith(camelContext, new AdviceWithRouteBuilder() {
		    @Override
		    public void configure() throws Exception {
		    	
		    	 interceptSendToEndpoint("cxf:bean:mobileStoreClient")
                 .skipSendToOriginalEndpoint()
                 .log("################skipping service call and here we can set mock response")
                 .setBody().mvel("new com.venkat.venkatstore.types.mobilestore.BuyNewMobielResponse()");
                
		    	 
		    	
		    }
		});
	}

}
