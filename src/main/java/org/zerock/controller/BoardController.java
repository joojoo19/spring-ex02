package org.zerock.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/board/*")
@AllArgsConstructor
@Log4j
public class BoardController {
	private BoardService service;
	
/*	@RequestMapping(value = "/list", method = RequestMethod.GET)*/
	@GetMapping("/list")
	public void list(Model model) {
		log.info("************************** list ***************************");
		List<BoardVO> list = service.getList();
		model.addAttribute("list", list);
	}
	
	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttr) {
//		BoardVO board = new BoardVO();
//		board.getTitle(request.getParameter("title"));
//		board.getContent(request.getParameter("content"));
//		board.getWriter(request.getParameter("writer"));
		
		service.register(board);
		rttr.addFlashAttribute("result", board.getBno());
		
		
		return "redirect:/board/list";
	}
	
	@GetMapping("/read")
	public void get() {
		
	}
	
	@PostMapping("/remove") 
	public void remove() {
		
	}
	
	@PostMapping("/modify")
	public void modify() {
		
	}
}
