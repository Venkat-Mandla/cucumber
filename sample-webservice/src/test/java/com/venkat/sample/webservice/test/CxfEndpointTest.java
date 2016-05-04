/**
 * 
 */
package com.venkat.sample.webservice.test;

import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.model.DataFormatDefinition;
import org.apache.camel.model.ToDefinition;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.venkat.venkatstore.types.mobilestore.Address;
import com.venkat.venkatstore.types.mobilestore.BuyNewMobielRequest;

/**
 * @author Venkat
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/spring/bundle-context-test-routes.xml" })
public class CxfEndpointTest{
	
	private static final Logger LOGGER=LoggerFactory.getLogger(CxfEndpointTest.class);
	private static final AtomicBoolean INIT = new AtomicBoolean();
	@Autowired
	private CamelContext camelContext;

	@Produce
	private ProducerTemplate template;
	
	@EndpointInject(uri="mock:results")
	private MockEndpoint mockEndpoint;
	
	
	@Before
	public void setUp() throws Exception {
		doPostSetup();
		template=camelContext.createProducerTemplate();
	}
	
	
	protected void doPostSetup() throws Exception {
			boolean first = INIT.compareAndSet(false, true);
			if(first){
				camelContext.addRoutes(createRouteBuilder());
			}
	}
	
	protected RouteBuilder createRouteBuilder() throws Exception {
		
		return new RouteBuilder() {
			private DataFormatDefinition jaxb= camelContext.getDataFormats().get("jaxbReqRes");
			@Override
			public void configure() throws Exception {
				from("direct:start")
				.marshal(jaxb).convertBodyTo(String.class)
				.log("Jaxb marshal Response: ${body}")
				.setHeader("operationName").simple("buyNewMobile")
				.to("direct:startCxf")
				.to("mock:results");
			}
		};
	}

	@Test
	public void testCxfEndpoint() throws Exception {
		LOGGER.info("started...");
		BuyNewMobielRequest request = getRequestMock();
		mockEndpoint.setExpectedMessageCount(1);
		addAdwiseToSkipEndpoint();
		template.requestBody("direct:start",request);
		mockEndpoint.assertIsSatisfied();
		mockEndpoint.reset();
	}
	
	@Test
	public void testCxfEndpoinScenario2() throws Exception {
		LOGGER.info("started...");
		BuyNewMobielRequest request = getRequestMock();
		mockEndpoint.setExpectedMessageCount(1);
		addAdwiseToRemoveEndpoint();
		template.requestBody("direct:start",request);
		mockEndpoint.assertIsSatisfied();
		mockEndpoint.reset();
	}

	private void addAdwiseToSkipEndpoint() throws Exception {
		camelContext.getRouteDefinition("StoreServiceRoute").adviceWith(camelContext, new AdviceWithRouteBuilder() {
		    @Override
		    public void configure() throws Exception {
		    	
		    	 interceptSendToEndpoint("bean:messageService?method=getMobileStoreDetails")
                 .skipSendToOriginalEndpoint()
                 .log("################skipping db call and here we can set mock response");
		    }
		});
	}

	private void addAdwiseToRemoveEndpoint() throws Exception {
		camelContext.getRouteDefinition("StoreServiceRoute").adviceWith(camelContext, new AdviceWithRouteBuilder() {
		    @Override
		    public void configure() throws Exception {
		      weaveByType(ToDefinition.class).selectIndex(1).remove();
		    }
		});
	}

	private BuyNewMobielRequest getRequestMock() {
		Address add = new Address();
		add.setState("AP");
		add.setCity("Kadapa");
		add.setLine1("venkat");
		BuyNewMobielRequest request=new BuyNewMobielRequest();
		request.setAddress(add);
		return request;
	}

}
