/**
 * 
 */
package com.venkat.sample.webservice.test;

import java.util.concurrent.atomic.AtomicBoolean;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.model.DataFormatDefinition;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.venkat.venkatstore.types.mobilestore.Address;

/**
 * @author Venkat
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/META-INF/spring/bundle-context-test-routes.xml" })
public class SampleTest extends CamelTestSupport {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(SampleTest.class);
	private static final AtomicBoolean INIT = new AtomicBoolean();
	@Autowired
	private CamelContext camelContext;

	@EndpointInject(uri="mock:results")
	private MockEndpoint mockEndpoint;
	
	@Override
	public void setUp() throws Exception {
		doPostSetup();
		template=camelContext.createProducerTemplate();
	}
	
	
	@Override
	protected void doPostSetup() throws Exception {
			super.doPostSetup();
			boolean first = INIT.compareAndSet(false, true);
			if(first){
				camelContext.addRoutes(createRouteBuilder());
			}
	}

	@Override
	public boolean isCreateCamelContextPerClass() {
		return true;
	}
	protected RouteBuilder createRouteBuilder() throws Exception {
		
		return new RouteBuilder() {
			private DataFormatDefinition jaxb= camelContext.getDataFormats().get("jaxbReqRes");
			@Override
			public void configure() throws Exception {
				from("direct:marshal").marshal(jaxb).log("Jaxb Response: ${body}").to("mock:results");
				from("direct:startSampleRoute").to("direct:test").to("mock:results");
			}
		};
	}
	@Test
	public void test() throws InterruptedException {
		LOGGER.info("started..");
		mockEndpoint.setExpectedMessageCount(1);
		template.sendBody("direct:startSampleRoute", "mock data");
		mockEndpoint.assertIsSatisfied();
		LOGGER.info("ended..");
	}

	@Test
	public void marshal() throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(Address.class);

		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		Address add = new Address();

		add.setState("AP");
		add.setCity("Kadapa");
		add.setLine1("venkat");
		
		template.sendBody("direct:marshal",add);
		marshaller.marshal(add, System.out);

	}

}
