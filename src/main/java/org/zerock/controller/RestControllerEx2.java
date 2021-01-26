package org.zerock.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.Rest1;
import org.zerock.domain.Rest2;

import lombok.extern.log4j.Log4j;

@Log4j
@RestController
@RequestMapping("/rest2")
public class RestControllerEx2 {
	
	@RequestMapping("/ex1")
	public String method1() {
		return "hello";
	}
	
	@RequestMapping("/ex2")
	public Rest1 method2() {
		log.info("----- method2 -----");
		Rest1 r = new Rest1();
		r.setName("hong");
		r.setAge(10);
		return r;
	}
	
	@RequestMapping("/ex3")
	public String method3() {
		log.info("----- method3 -----");
		Rest1 r = new Rest1();
		r.setName("hong");
		r.setAge(10);
		
//		String res = "이름 : " + r.getName() + ", " + "나이 : " + r.getAge(); -> json으로 표기
		String res = "{name : \""+r.getName() + "\", age : \"" + r.getAge() + "\"}";
		
		return res;
	}
	
	@RequestMapping("/ex4")
	public Rest1 method4() {
		log.info("----- method4 -----");
		Rest1 r = new Rest1();
		r.setName("hong");
		r.setAge(10);
		return r;
	}
	
	@RequestMapping("/ex5")
	public Rest2 method5() {
		log.info("----- method4 -----");
		Rest2 r2 = new Rest2();
		r2.setAddress("incheon");
		
		Rest1 r1 = new Rest1();
		r1.setName("hong");
		r1.setAge(25);
		r1.setVote(true);
		
		r2.setRest1(r1);
		
		return r2;
	}
	
	@RequestMapping("/ex6")
	public String[] method6() {
		log.info("----- method6 -----");
		String[] arr = {"java", "spring", "oracle"};

		return arr;
	}
	
	@RequestMapping("/ex7")
	public List<String> method7() {
		log.info("----- method7 -----");
		List<String> list = new ArrayList<>();
		list.add("hello");
		list.add("world");
		list.add("java");

		return list;
	}
	
	@RequestMapping("/ex8")
	public Map<String, String> method8() {
		log.info("----- method8 -----");
		Map<String, String> map = new HashMap<>();
	map.put("html", "css");
	map.put("java", "hello");
	map.put("spring", "json");

		return map;
	}
	
	@RequestMapping("/ex9")
	public List<Rest1> method9() {
		log.info("----- method9 -----");
		List<Rest1> list = new ArrayList<>();
		
		Rest1 r1 = new Rest1();
		r1.setName("hong");
		r1.setAge(25);
		r1.setVote(true);
		
		list.add(r1);
		
		Rest1 r2 = new Rest1();
		r2.setName("lee");
		r2.setAge(17);
		r2.setVote(false);
		
		list.add(r2);

		return list;
	}
	// 특정 status code로 응답할 때
	@RequestMapping("/ex10")
	public ResponseEntity<String> method10() {
		return ResponseEntity.status(200).body("hello");
	}
	
	@RequestMapping("/ex11")
	public ResponseEntity<String> method11(int num) {
		if(num > 0) {	
		return ResponseEntity.status(200).body("hello");
		} else {
			return ResponseEntity.status(404).body("");
		}
	}
}
