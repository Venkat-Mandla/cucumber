/**
 * 
 */
package com.venkat.spring.config;

import org.apache.camel.component.restlet.RestletComponent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Venkat
 *
 */
@Configuration
public class SpringApplicationConfig {

/*	@Bean
	public CamelContext getCamelContext(){
		return new DefaultCamelContext();
	}*/
	@Bean
	public RestletComponent getRestComponent(){
		return new RestletComponent();
	}
}
