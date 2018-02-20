package com.koitt.board.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.koitt.board.model.Board;
import com.koitt.board.model.BoardException;
import com.koitt.board.model.FileException;
import com.koitt.board.service.BoardService;
import com.koitt.board.service.FileService;

@Controller
public class BoardWebController {

	@Autowired
	private BoardService boardService;

	@Autowired
	private FileService fileService;

	/*
	 * HTTP Method GET 방식으로 /board-list.do를 클라이언트가 요청하면 아래 메소드 호출
	 */
	@RequestMapping("/board-list.do")
	public String list(Model model) {
		List<Board> list = null;

		try {
			// service를 이용하여 글 목록 가져오기
			list = boardService.list();

		} catch (BoardException e) {
			// 예외가 발생하면 error키의 값을 이용하여 JSP에 표시
			model.addAttribute("error", "server");
		}

		// list키에 list 객체를 추가
		model.addAttribute("list", list);

		/*
		 * ViewResolver에 의해서 /WEB-INF/views/board-list.jsp 페이지로 포워딩 하게 된다.
		 */
		return "board-list";
	}

	// 글 상세 화면
	@RequestMapping(value = "/board-detail.do", method = RequestMethod.GET)
	public String detail(Model model, HttpServletRequest request,
			@RequestParam(value = "no", required = true) String no) {
		Board board = null;
		String filename = null;
		String imgPath = null;

		try {
			board = boardService.detail(no);

			// URL Encoding 된 파일명을 다시 Decoding해서 포워딩
			filename = board.getAttachment();
			if (filename != null && !filename.trim().isEmpty()) {
				filename = URLDecoder.decode(filename, "UTF-8");
			}
			
			imgPath = fileService.getimgPath(request, filename);

		} catch (BoardException e) {
			System.out.println(e.getMessage());
			model.addAttribute("error", "server");
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
			model.addAttribute("error", "encoding");
		}

		model.addAttribute("board", board);
		model.addAttribute("filename", filename);
		if (imgPath != null && !imgPath.trim().isEmpty()) {

			model.addAttribute("imgPath", imgPath);
		}

		return "board-detail";
	}

	// 글 작성 화면
	@RequestMapping(value = "/board-add.do", method = RequestMethod.GET)
	public String add() {
		return "board-add";
	}

	// 글 추가 후, 글 목록 화면으로 이동
	@RequestMapping(value = "/board-add.do", method = RequestMethod.POST)
	public String add(HttpServletRequest request, Integer userNo, String title, String content,
			@RequestParam("attachment") MultipartFile attachment) {

		Board board = new Board();
		board.setUserNo(userNo);
		board.setTitle(title);
		board.setContent(content);

		try {
			fileService.add(request, attachment, board);
			boardService.add(board);

		} catch (BoardException e) {
			request.setAttribute("error", "server");
		} catch (FileException e) {
			request.setAttribute("error", "file");
		}

		// redirect: 뒤에 입력한 주소로 이동
		return "redirect:board-list.do";
	}

	// 글 삭제 확인 화면
	@RequestMapping(value = "/board-remove.do", method = RequestMethod.GET)
	public String removeConfirm(Model model, @RequestParam(value = "no", required = true) String no) {

		model.addAttribute("no", no);

		return "board-remove-confirm";
	}

	// 글 삭제 후, 글 목록 화면으로 이동
	@RequestMapping(value = "/board-remove.do", method = RequestMethod.POST)
	public String remove(Model model, String no, HttpServletRequest request) {
		try {
			String toDeleteFilename = boardService.remove(no);
			fileService.remove(request, toDeleteFilename);

		} catch (BoardException e) {
			model.addAttribute("error", "server");
		} catch (FileException e) {
			model.addAttribute("error", "file");
		}

		return "redirect:board-list.do";
	}

	// 글 수정하기 화면
	@RequestMapping(value = "/board-modify.do", method = RequestMethod.GET)
	public String modify(Model model, @RequestParam(value = "no", required = true) String no) {
		Board board = null;

		try {
			/*
			 * 수정하고자 하는 글의 정보를 가져와서 글 수정하기 화면에 출력하기 위해 아래와 같이 호출
			 */
			board = boardService.detail(no);

		} catch (BoardException e) {
			model.addAttribute("error", "server");
		}

		model.addAttribute("board", board);

		return "board-modify";
	}

	// 글 수정한 후, 글 목록 화면으로 이동
	@RequestMapping(value = "/board-modify.do", method = RequestMethod.POST)
	public String modify(HttpServletRequest request, Integer no, String title, String content,
			@RequestParam("attachment") MultipartFile attachment) {

		Board board = new Board();
		board.setNo(no);
		board.setTitle(title);
		board.setContent(content);

		try {
			// 새롭게 수정할 파일을 서버에 저장
			fileService.add(request, attachment, board);

			// 기존 파일명을 가져온다
			String toDeleteFilename = boardService.modify(board);

			// 기존에 있던 파일을 삭제
			fileService.remove(request, toDeleteFilename);

		} catch (BoardException e) {
			System.out.println(e.getMessage());
			request.setAttribute("error", "server");
		} catch (FileException e) {
			System.out.println(e.getMessage());
			request.setAttribute("error", "file");
		}

		return "redirect:board-list.do";
	}

	/*
	 * 다운로드 링크를 화면에서 클릭하면 아래와 같이 서버에 GET 방식으로 요청한다. download.do?filename=파일명
	 * 
	 * 아래 RequestMapping 애노테이션 뜻은 아래와 같다. 요청 URL은 /download.do 요청 HTTP Method는 GET
	 * 요청한 쿼리문자열의 변수명이 filename일 경우 아래 메소드를 실행 (params)
	 */
	@RequestMapping(value = "/download.do", method = RequestMethod.GET, params = "filename")
	public void download(HttpServletRequest request, HttpServletResponse response, String filename) {

		try {
			fileService.download(request, response, filename);
		} catch (FileException e) {
			System.out.println(e.getMessage());
		}
	}
}
