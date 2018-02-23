package com.koitt.board.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.koitt.board.model.FileException;
import com.koitt.board.model.Users;
import com.koitt.board.model.UsersException;
import com.koitt.board.service.FileService;
import com.koitt.board.service.UsersService;

@Controller
public class UsersWebController {

	@Autowired
	private UsersService usersService;
	
	@Autowired
	private FileService fileService;

	// 사용자 목록
	@RequestMapping(value = "/admin/users-list.do", method = RequestMethod.GET)
	public String list(Model model, HttpServletRequest req) {
		List<Users> list = null;

		try {
			list = usersService.list();
		} catch (UsersException e) {
			System.out.println(e.getMessage());
			model.addAttribute("error", "server");
		}
		
		model.addAttribute("list", list);
		model.addAttribute("uploadPath", fileService.getUploadPath(req));

		return "admin/users-list";
	}
	
	// 사용자 추가 (가입하기 화면)
	@RequestMapping(value="/join.do", method=RequestMethod.GET)
	public String join() {
		return "join";
	}
	
	// 사용자 추가 (가입하기 화면에서 전달받은 값으로 사용자 생성)
	@RequestMapping(value="/join.do", method=RequestMethod.POST)
	public String join(HttpServletRequest req,
			String email,
			String password,
			String name,
			@RequestParam("attachment") MultipartFile attachment) {
		
	
		
		// 클라이언트로부터 전달받은 값으로 객체 생성
		Users users = new Users(null, email, password, name, null);
		
		try {
			// 프로필 사진 저장
			String filename = fileService.add(req, attachment);
			
			// 프로필 사진 파일명을 users VO 객체에 담기
			users.setAttachment(filename);
			
			// 데이터베이스에 사용자 추가
			usersService.add(users);
			String encodedName = URLEncoder.encode(users.getName(), "UTF-8");
			
			// 가입 환영 페이지로 이동
			return "redirect:join-confirm.do?name=" + encodedName;
			
		} catch (FileException e) {
			System.out.println(e.getMessage());
			req.setAttribute("error", "file");
		} catch (UsersException e) {
			System.out.println(e.getMessage());
			req.setAttribute("error", "server");
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
			req.setAttribute("error", "encoding");
		}
		
		// 가입 시 문제가 발생하면 index.html로 이동
		return "redirect:index.do";
	}
	
	// 가입 확인 페이지
	@RequestMapping(value="/join-confirm.do", method=RequestMethod.GET)
	public String joinConfirm(Model model, String name) {
		model.addAttribute("name", name);
		return "join-confirm";
	}
	
	// 로그인 페이지
	@RequestMapping(value="/login.do", method=RequestMethod.GET)
	public String login() {
		
		return "login";
		
	}
	
	//접근 제한 페이지
	@RequestMapping(value="/access-denied.do", method=RequestMethod.GET)
	public String accessDenied(Model model) {
		
		model.addAttribute("email", usersService.getPrincipal().getUsername());
		
		return "access-denied";
	}
	
	// 로그아웃
	@RequestMapping(value="/logout.do", method=RequestMethod.GET)
	public String logout(HttpServletRequest req, HttpServletResponse resp) {
		// 서비스의 로그아웃메소드 호출
		usersService.logout(req, resp);
		
		// 로그아웃 한 뒤 로그인 페이지로 이동 후 로그아웃 메시지 출력을 위해 쿼리문자열 사용
		return "redirect:/login.do?logout=true";
	}
}