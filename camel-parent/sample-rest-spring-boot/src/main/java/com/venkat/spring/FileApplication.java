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
//@ImportResource({"classpath:META-INF/spring/bundle-context-file-routes.xml"})
public class FileApplication{
	
	public static void main(String[] args) {
		new SpringApplication(FileApplication.class,"classpath:META-INF/spring/bundle-context-file-routes.xml").run();
	}

}
