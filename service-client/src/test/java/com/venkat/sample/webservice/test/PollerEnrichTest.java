/**
 * 
 */
package com.venkat.sample.webservice.test;

import org.apache.camel.Exchange;
import org.apache.camel.PollingConsumer;
import org.apache.camel.component.file.FileComponent;
import org.apache.camel.component.file.FileEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Venkat
 *
 */
public class PollerEnrichTest extends CamelTestSupport{

	private static final Logger LOG=LoggerFactory.getLogger(PollerEnrichTest.class);
	/*
	@Override
	protected RouteBuilder createRouteBuilder() throws Exception {
		return new RouteBuilder() {
			
			@Override
			public void configure() throws Exception {
	
				from("file:H://venkat")
					.noAutoStartup()
					.routeId("fileComponentTestRoute")
					//.onCompletion().to("controlbus:route?routeId=fileComponentTestRoute&action=stop").end()
					.log("before delay file name: ${header.fileName}")
					.delay(2000)
					.log("After delay file name: ${header.fileName}");
					
			}
		};
	}*/
	
	@Test
	public void testJpaEndpoint() throws Exception{
		FileComponent fileComponent=new FileComponent(context());
		FileEndpoint fileEndpoint=(FileEndpoint) fileComponent.createEndpoint("file:H://vr");
		PollingConsumer pollingConsumer=fileEndpoint.createPollingConsumer();
		pollingConsumer.start();

		Exchange exchange=pollingConsumer.receive();
		if(null != exchange){
			int batchCount=exchange.getProperty("CamelBatchSize", Integer.class);
			System.out.println(exchange);
			LOG.info("File poll: 1+"+exchange.getProperty("CamelBatchIndex"));
			while(--batchCount>0){
				exchange=pollingConsumer.receive();
				LOG.info("File poll: 1+"+exchange.getProperty("CamelBatchIndex"));
				if(LOG.isDebugEnabled()){
					LOG.debug("exchage: "+exchange);
				}
			}
		}else{
			LOG.info("No data found for poll"); 
		}
		//template.sendBody("controlbus:route?routeId=fileComponentTestRoute&action=start", null);
	}
	
	
	

}
