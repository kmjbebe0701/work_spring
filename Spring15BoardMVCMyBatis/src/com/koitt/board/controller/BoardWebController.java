package com.koitt.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.koitt.board.model.Board;
import com.koitt.board.model.BoardException;
import com.koitt.board.service.BoardService;

@Controller
public class BoardWebController {
	
	@Autowired
	private BoardService service;
	
	// HTTP Method GET 방식으로 /board-list.do를 클라이언트가 요청하면 아래 메소드 호출
	@RequestMapping("/board-list.do")
	public String list(Model model) {
		List<Board> list = null;
		
		try {
			//service를 이용하여 글 목록 가져오기
			list = service.list();
		}catch(BoardException e) {
			//예외가 발생하면 error키의 값을 이용하여 JSP에 표시
			model.addAttribute("error", "server");
		}
		
		// list 키에 list 객체를 추가
		model.addAttribute("list", list);
		
		// ViewResolver 에 의해 /WEB-INF/views/board-list.jsp페이지로 포워딩 하게 된다.
		return "board-list";
	}
	
	// 글 상세 화면
	@RequestMapping(value="/board-detail.do", method=RequestMethod.GET)
	public String detail(Model model, @RequestParam(value="no", required=true) String no) {
		Board board = null;
		
		try {
			board = service.detail(no);
		}catch (BoardException e){
			model.addAttribute("error", "server");
		}
		model.addAttribute("board", board);
		
		return "board-datail";
		
	}
	
	// 글 작성 화면
	@RequestMapping(value="/board-add.do", method=RequestMethod.GET)
	public String add() {		
		return "board-add";
	}
	
	// 글 추가 후, 글 목록 화면으로 이동
	@RequestMapping(value="/board-add.do", method=RequestMethod.POST)
	public String add(Model model, Board board) {
		try {
			service.add(board);
		}catch (BoardException e){
			model.addAttribute("error", "server");
		}
		
		// redirect: 뒤에 입력한 주소로 이동
		return "redirect:board-list.do";
	}
	
	// 글 삭제 확인
	@RequestMapping(value="/board-remove.do", method=RequestMethod.GET)
	public String removeConfirm(Model model, @RequestParam(value="no", required=true) String no ) {
		
		model.addAttribute("no", no);
		
		return "board-remove-confirm";
	}
	
	// 글 삭제 후, 글 목록 화면으로 이동
	@RequestMapping(value="/board-remove.do", method=RequestMethod.POST)
	public String remove(Model model, String no ) {
		try {
			service.remove(no);
		}catch (BoardException e){
			model.addAttribute("error", "server");
		}
		
		return "redirect:board-list.do";
	}
	
	// 글 수정하기 화면
	@RequestMapping(value="/board-modify.do", method=RequestMethod.GET)
	public String modify(Model model, @RequestParam(value="no", required=true)String no ) {
		Board board = null;
		try {
			// 수정 하고자 하는 글의 정보를 가져와서 글 수정하기 화면에 출력하기 위해 아래와 같이 호출
			board = service.detail(no);
		}catch (BoardException e){
			model.addAttribute("error", "server");
		}
		model.addAttribute("board", board);
		
		return "board-modify";
	}
	
	// 글 수정 후, 글 목록으로 이동
	@RequestMapping(value="/board-modify.do", method=RequestMethod.POST)
	public String modify(Model model, Board board) {
		try {
			service.modify(board);
		}catch (BoardException e){
			model.addAttribute("error", "server");
		}
		
		return "redirect:board-list.do";
	}
	
}
