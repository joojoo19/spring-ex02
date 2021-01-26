package org.zerock.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.Rest1;

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
	
	@RequestMapping("/ex5") 
	public String method5(@RequestBody String a) {
		log.info("----- method5 " + a +"-----");
		
		
		return "method5";
	}
	
	@RequestMapping("/ex6") 
	public String method6(@RequestBody Rest1 body) {
		log.info("----- method6 body : " + body +"-----");
		
		
		return "method6";
	}
}
