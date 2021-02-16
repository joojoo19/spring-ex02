package org.zerock.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageDTO;
import org.zerock.service.BoardService;
import org.zerock.service.FileUpService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/board/*")
@AllArgsConstructor
@Log4j
public class BoardController {
	private BoardService service;
	private FileUpService fileUpSvc;
	
/*	@RequestMapping(value = "/list", method = RequestMethod.GET)*/
	@GetMapping("/list")
	public void list(Model model, @ModelAttribute("cri") Criteria cri) {
	List<BoardVO> list = service.getList(cri);
		
		int total = service.getTotal(cri);
		PageDTO dto = new PageDTO(cri, total);
		
		model.addAttribute("list", list);
		model.addAttribute("pageMaker", dto);
	}
	
	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttr, MultipartFile file) {
//		BoardVO board = new BoardVO();
//		board.getTitle(request.getParameter("title"));
//		board.getContent(request.getParameter("content"));
//		board.getWriter(request.getParameter("writer"));
		
		board.setFilename("");
		
		service.register(board);
		if(file != null) {
			board.setFilename(board.getBno() + "_" + file.getOriginalFilename());
			service.modify(board);
			fileUpSvc.write(file, board.getFilename());
		}

		rttr.addFlashAttribute("result", board.getBno());
		rttr.addFlashAttribute("message", board.getBno() + "번 글이 등록 되었습니다");
		
		
		return "redirect:/board/list";
	}
	
	@GetMapping("/register")
	public void register(@ModelAttribute("cri") Criteria cri) {
		
	}
	
	@GetMapping({"/read", "/modify"})
	public void get(Long bno, Model model, @ModelAttribute("cri") Criteria cri) {
		log.info("*************get method bno : " + bno + ", amount : " + cri.getAmount()+", pageNo : "+ cri.getPageNo());
		BoardVO vo = service.get(bno);
		model.addAttribute("board", vo);
	//	model.addAttribute("cri", cri);
	}
	
	@PostMapping("/remove") 
	public String remove(Long bno, RedirectAttributes rttr, Criteria cri) {
		if (service.remove(bno)) {
			rttr.addFlashAttribute("result", "success");
			rttr.addFlashAttribute("message", bno + "번 글이 삭제되었습니다.");
		}
		
		rttr.addAttribute("pageNo", cri.getPageNo());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		
		return "redirect:/board/list";
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO board, RedirectAttributes rttr, Criteria cri) {
		if (service.modify(board)) {
			rttr.addFlashAttribute("result", "success");
			rttr.addFlashAttribute("message", board.getBno() + "번 글이 수정되었습니다.");
		}
		log.info(cri);
		rttr.addAttribute("pageNo", cri.getPageNo());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		return "redirect:/board/list";
	}
	
//	@GetMapping("/modify")
//	public void modify(Long bno, Model model) {
//		BoardVO vo = service.get(bno);
//		model.addAttribute("board", vo);
//	}
}
