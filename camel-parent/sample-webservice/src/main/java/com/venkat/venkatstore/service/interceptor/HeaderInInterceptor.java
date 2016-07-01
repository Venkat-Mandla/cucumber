/**
 * 
 */
package com.venkat.venkatstore.service.interceptor;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Venkateswarlu.Mandla
 *
 */
public class HeaderInInterceptor extends AbstractPhaseInterceptor<Message> {

	private Logger LOG=LoggerFactory.getLogger(HeaderInInterceptor.class);

	public HeaderInInterceptor(String phase) {
		super(phase);
	}
	
	public HeaderInInterceptor() {
		super(Phase.RECEIVE);
	}
	

	@Override
	public void handleMessage(Message message) throws Fault {
		LOG.info("message received: "+message);
	}

	

	

	
	
	
}
