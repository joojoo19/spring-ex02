package org.zerock.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j;

@Log4j
@RestController
@RequestMapping("/rest3")
public class RestControllerEx3 {
	
	@RequestMapping("/ex1")
	public String method1(String name) {
		log.info("----- method1 name : " + name + "-----");
		
		return "str";
	}
	
	@RequestMapping("/ex2/{val}") // path variable
	public String method2(@PathVariable("val") String value) {
		log.info("----- method2 value : " + value +"-----");
		
		
		return "method2";
	}
	
	@RequestMapping("/ex3/{val}")
	public String method3(@PathVariable String val) {
		log.info("----- method3 value : " + val +"-----");
		
		
		return val;
	}
	
	@RequestMapping("/ex4/{val}/other/{age}") 
	public String method4(@PathVariable String val, @PathVariable int age) {
		log.info("----- method3 val : " + val+", age : " + age +"-----");
		
		
		return val+age;
	}
}