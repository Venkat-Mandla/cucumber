/**
 * 
 */
package com.venkat.camel.pollenrich;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Venkat
 *
 */
public class ControllBusTest extends CamelTestSupport{

	private static final Logger LOG=LoggerFactory.getLogger(ControllBusTest.class);
	@Override
	protected RouteBuilder createRouteBuilder() throws Exception {
		return new RouteBuilder() {
			
			@Override
			public void configure() throws Exception {
	
				from("file:src/test/resources/input/vr?noop=true")
					.noAutoStartup()
					.routeId("fileComponentTestRoute")
					//.onCompletion().to("controlbus:route?routeId=fileComponentTestRoute&action=stop").end()
					.log("###########File name: ${header.CamelFileNameOnly}");
					
			}
		};
	}
	
	
	@Test
	public void testControllBus() throws Exception{
		LOG.info("starting file listner");
		template.sendBody("controlbus:route?routeId=fileComponentTestRoute&action=start", null);
		Thread.sleep(2000);
		template.sendBody("controlbus:route?routeId=fileComponentTestRoute&action=stop", null);
	}
	
	
	

}
