/**
 * 
 */
package com.venkat.camel.rest.service;

import org.apache.camel.spring.Main;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Venkat
 *
 */
public class Application {
	
	private Main main;
	
	public static void main(String[] args) throws Exception {
		Application app=new Application();
		app.boot();
		System.out.println("Application ends");
	}

	private void boot() throws Exception {
		main=new Main();
		
		main.setApplicationContext(new ClassPathXmlApplicationContext("classpath:META-INF/spring/bundle-context-routes.xml"));
		main.run();
		System.out.println(main.getCamelContexts());
		
	}

}
