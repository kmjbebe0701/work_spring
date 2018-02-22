package com.koitt.book.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.koitt.book.model.FileException;
import com.koitt.book.model.Users;
import com.koitt.book.model.UsersException;
import com.koitt.book.service.FileService;
import com.koitt.book.service.UsersService;

@Controller
public class UsersWedController {

	@Autowired
	private UsersService usersService;

	@Autowired
	private FileService fileService;

	@RequestMapping("/users-list.do")
	public String list(Model model, HttpServletRequest req) {
		List<Users> list = null;

		try {
			list = usersService.list();
		} catch (UsersException e) {
			model.addAttribute("error", "server");
		}
		
		model.addAttribute("list", list);
		model.addAttribute("uploadPath", fileService.getUploadPath(req));

		return "admin/users-list";

	}
	
	@RequestMapping(value="/join.do", method=RequestMethod.GET)
	public String join() {
		
		return "join";
	}

	@RequestMapping(value="/join.do", method=RequestMethod.POST)
	public String join(HttpServletRequest request, String email, String password, String name,
			@RequestParam("attachment") MultipartFile attachment) {
		
		Users users = new Users();
		users.setEmail(email);
		users.setName(name);
		users.setPassword(password);
		
		try {
			String filename = fileService.add(request, attachment);
			
			users.setAttachment(filename);
			
			usersService.add(users);
			
			String encodedName = URLEncoder.encode(users.getName(), "UTF-8");
			
			return "redirect:join-confirm.do?name=" + encodedName;
		} catch (UsersException e) {
			System.out.println(e.getMessage());
			request.setAttribute("error", "server");
		} catch (FileException e) {
			System.out.println(e.getMessage());
			request.setAttribute("error", "file");
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
			request.setAttribute("error", "encoding");
		}
		
		return "redirect:index.html";
	}
	
	@RequestMapping(value="/join-confirm.do", method=RequestMethod.GET)
	public String joinConfirm(Model model, String name) {
		model.addAttribute("name", name);
		return "join-confirm";
	}
}
