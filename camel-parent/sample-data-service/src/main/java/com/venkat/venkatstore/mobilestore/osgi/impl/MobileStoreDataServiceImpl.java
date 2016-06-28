/**
 * 
 */
package com.venkat.venkatstore.mobilestore.osgi.impl;

import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;

import com.venkat.venkatstore.mobilestore.osgi.service.MobileStoreDataService;

/**
 * @author Venkat
 *
 */
public class MobileStoreDataServiceImpl implements MobileStoreDataService{

	@EndpointInject(uri="direct:getStoreDetails")
	private ProducerTemplate getStoreDetailsTemplate;
	@Override
	public Object getMobileStoreDetails(/*StoreDetailsDataRequest*/ Object dataRequest) {
		return getStoreDetailsTemplate.requestBody("direct:getStoreDetails", dataRequest, String.class);
	}
	

}
