/**
 * 
 */
package com.venkat.spring.config;

import org.apache.camel.component.restlet.RestletComponent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Venkat
 *
 */
@Configuration
@PropertySource("applicaton.properties")
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
