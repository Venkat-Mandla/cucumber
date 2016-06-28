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
//@ImportResource({"classpath:META-INF/spring/bundle-context-rest-routes"})
public class Application {
	
	public static void main(String[] args) {
		try {
			new SpringApplication(Class.forName(args[0])).run(args);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
