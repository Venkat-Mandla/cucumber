/**
 * 
 */
package com.venkat.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author Venkat
 *
 */
@SpringBootApplication
@ImportResource({"classpath:META-INF/spring/bundle-context-routes.xml"/*,"classpath:META-INF/spring/bundle-context-routes-1.xml"*/})
public class Application {
	
	public static void main(String[] args) {
		new SpringApplication(Application.class).run(args);
	}

}
