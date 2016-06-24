/**
 * 
 */
package com.venkat.spring.controller;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Venkat
 *
 */

@RestController
public class LoginController {
	
	@Autowired
	private ProducerTemplate template;
	
	public LoginController(){
		System.out.println("initilized...");
	}

	@RequestMapping("/login")
	public String login(){
		//From here we can invoke the camel route by template
		return "success";
	}
}
