/**
 * 
 */
package com.venkat.spring.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.activemq.camel.component.ActiveMQConfiguration;
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
	
	@Bean(name="activemq")
	public ActiveMQComponent getActiveMQComponent(){
		
		ActiveMQConfiguration activeMQConfiguration = new ActiveMQConfiguration();
		ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
		activeMQConnectionFactory.setUserName("venkat");
		activeMQConnectionFactory.setPassword("venkat");
		activeMQConnectionFactory.setBrokerURL("tcp://127.0.0.1:61616");
		activeMQConfiguration.setConnectionFactory(activeMQConnectionFactory);
		return new ActiveMQComponent(activeMQConfiguration);
	}
}
