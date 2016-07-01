/**
 * 
 */
package com.venkat.spring.routes;

import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

/**
 * @author Venkat
 *
 */
@Component("demoRouteBuilder")
public class DemoRouteBuilder extends RouteBuilder{


	@Override
	public void configure() throws Exception {
		
		restConfiguration().component("restlet").host("localhost").port("9191").bindingMode(RestBindingMode.auto);
		
		rest("/camelRest").produces("applicatoin/json")
		.get("getStoreDetails").to("direct:process")
		.post("persistStoreDetails").to("direct:persistStoreDetails");
		
		from("direct:process").setBody().simple("mock data").setExchangePattern(ExchangePattern.InOnly).log("invoked rest service").to("activemq:inputQueue");
		
	}

}
