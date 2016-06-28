/**
 * 
 */
package com.venkat.spring.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

/**
 * @author Venkat
 *
 */
//@Component("demoRouteBuilder")
public class DemoRouteBuilder extends RouteBuilder{


	@Override
	public void configure() throws Exception {
		
		restConfiguration().component("restlet").host("localhost").port("8181").bindingMode(RestBindingMode.auto);
		
		rest("/camelRest").produces("applicatoin/json")
		.get("getStoreDetails").to("direct:process")
		.post("persistStoreDetails").to("direct:persistStoreDetails");
		
		from("direct:process").log("invoked rest service").to("direct-vm:getStoreDetails");
		
	}

}
