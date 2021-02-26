package org.zerock.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zerock.domain.admin.AdminMemberVO;

@Controller
@RequestMapping("/aj")
public class AJAXController {

	@RequestMapping("/t1")
	@ResponseBody
	public String t1() {
		return "<span style='background-color: red' >hello </span>";
	}
	
	@RequestMapping("/t2")
	@ResponseBody
	public AdminMemberVO t2() {
		AdminMemberVO vo = new AdminMemberVO();
		
		vo.setEmail("h@gmail.com");
		vo.setName("world");
		
		
		return vo ;
	}
	
	@RequestMapping("/t3")
	@ResponseBody
	public List<AdminMemberVO> t3() {
		List<AdminMemberVO> list = new ArrayList<>();
		AdminMemberVO vo = new AdminMemberVO();
		
		vo.setEmail("h@gmail.com");
		vo.setName("world");
		
		list.add(vo);
		list.add(vo);
		
		
		return list ;
	}
}
