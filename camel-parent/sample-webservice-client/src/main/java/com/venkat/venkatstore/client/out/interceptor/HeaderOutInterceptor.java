/**
 * 
 */
package com.venkat.venkatstore.client.out.interceptor;

import java.util.List;

import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.SoapHeader;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;

import com.venkat.venkatstore.types.mobilestore.MobileStoreHeader;

/**
 * @author Venkateswarlu.Mandla
 *
 */
public class HeaderOutInterceptor extends AbstractSoapInterceptor {

	

	
	public HeaderOutInterceptor(String phase) {
		super(phase);
	}
	
	public HeaderOutInterceptor() {
		super(Phase.WRITE);
	}

	@Override
	public void handleMessage(SoapMessage soapMessage) throws Fault {
		List<Header> headers = soapMessage.getHeaders();
		if(headers.isEmpty()){
			headers.add(getHeader());
		}
	}

	private Header getHeader() {
		MobileStoreHeader mobileStoreHeader = new MobileStoreHeader();
		mobileStoreHeader.setClientId("11110000");
		mobileStoreHeader.setClientName("CGI");
		Header header=new SoapHeader(new QName("http://www.venkat.com/venkatstore/types/MobileStore/"), mobileStoreHeader);
		return header;
	}

	

	
	
}
