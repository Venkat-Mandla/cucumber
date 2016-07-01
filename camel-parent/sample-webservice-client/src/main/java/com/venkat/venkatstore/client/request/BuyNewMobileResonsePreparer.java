/**
 * 
 */
package com.venkat.venkatstore.client.request;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.venkat.venkatstore.types.mobilestore.BuyNewMobielResponse;

/**
 * @author Venkat
 *
 */
public class BuyNewMobileResonsePreparer implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		
		exchange.getOut().setBody(exchange.getIn().getBody(BuyNewMobielResponse.class));
	}

	
}
