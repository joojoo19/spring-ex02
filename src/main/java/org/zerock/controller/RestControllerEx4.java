package org.zerock.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.Rest1;

import lombok.extern.log4j.Log4j;

@Log4j
@RestController
@RequestMapping("/rest4")
public class RestControllerEx4 {

	@RequestMapping(path = "/ex1", produces = MediaType.TEXT_PLAIN_VALUE)
	public String method1() {
		log.info("----- method1 -----");
		return "hello";
	}
	
	@RequestMapping(path = "/ex2", produces = MediaType.APPLICATION_JSON_VALUE)
	public String method2() {
		log.info("----- method2 -----");
		return "{}";
	}
	                                           // 순서에 따라 응답을 받음
	@RequestMapping(path = "/ex3", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public Rest1 method3() {
		log.info("----- method3 -----");
		Rest1 r = new Rest1();
		r.setName("hong");
		r.setAge(30);
		r.setObj(null);
		r.setVote(true);
		
		return r;
	}
	
	@RequestMapping(path = "/ex4", produces = MediaType.TEXT_PLAIN_VALUE)
	public String method4() {
		log.info("----- method4 -----");
		return "hello world";
	}
	
	@RequestMapping(path = "/ex5", produces = "text/plain;charset=UTF-8")
	public String metho5() {
		log.info("----- method5 -----");
		return "자바자바";
	}
}
