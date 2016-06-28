/**
 * 
 */
package com.venkat.spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Venkat
 *
 */

@RestController
public class LoginController {
	
	
	public LoginController(){
		System.out.println("initilized...");
	}

	@RequestMapping("/login")
	public String login(){
		//From here we can invoke the camel route by template
		return "success";
	}
}
