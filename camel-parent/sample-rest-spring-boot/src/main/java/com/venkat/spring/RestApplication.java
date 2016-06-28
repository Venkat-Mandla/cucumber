/**
 * 
 */
package com.venkat.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Venkat
 *
 */
@SpringBootApplication
//@ImportResource({"classpath:META-INF/spring/bundle-context-${componentName}-routes.xml"})
//@ImportResource({"classpath:META-INF/spring/bundle-context-rest-routes.xml"})
public class RestApplication{
	
	public static void main(String[] args) {
		new SpringApplication(RestApplication.class,"classpath:META-INF/spring/bundle-context-rest-routes.xml").run();
	}

}
