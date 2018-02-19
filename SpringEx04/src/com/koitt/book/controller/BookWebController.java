package com.koitt.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import com.koitt.book.model.Book;
import com.koitt.book.model.BookException;
import com.koitt.book.service.BookService;

@Controller
public class BookWebController {
	
	@Autowired
	private BookService service;

	@RequestMapping("/book-list.do")
	public String list(Model model) {
		List<Book> list = null;
		
		try {
			list=service.list();
		}catch(BookException e){
			model.addAttribute("error", "server");
		}
		model.addAttribute("list",list);
		
		
		
		return "book-list";
	}
	@RequestMapping(value="/book-detail.do", method=RequestMethod.GET)
	public String detail(Model model, @RequestParam(value="isbn", required=true) String isbn) {
		Book book = null;
		
		try {
			book = service.detail(isbn);
		}catch (BookException e){
			model.addAttribute("error", "server");
		}
		model.addAttribute("book", book);
		
		return "book-detail";
	}
	
	@RequestMapping(value="/book-add.do", method=RequestMethod.GET)
	public String add() {
		
		return "book-add";
	}
	
	@RequestMapping(value="/book-add.do", method=RequestMethod.POST)
	public String add(Model model, Book book) {
		
		try {
			service.add(book);
		}catch (BookException e){
			model.addAttribute("error", "server");
		}
		
		return "redirect:book-list.do";
	}
	
	@RequestMapping(value="/book-remove.do", method=RequestMethod.GET)
	public String removeConfirm(Model model, @RequestParam(value="isbn", required=true) String isbn) {
		
		model.addAttribute("isbn", isbn);
		
		return "book-remove-confirm";
	}
	
	@RequestMapping(value="/book-remove.do", method=RequestMethod.POST)
	public String remove(Model model, @RequestParam(value="isbn", required=true) String isbn) {
		
		try {
			service.remove(isbn);
		}catch (BookException e){
			model.addAttribute("error", "server");
		}
		
		return "redirect:book-list.do";
	}
	
	@RequestMapping(value="/book-modify.do", method=RequestMethod.GET)
	public String modify(Model model, @RequestParam(value="isbn", required=true) String isbn) {
		Book book = null;
		
		try {
			book = service.detail(isbn);
			
		} catch (BookException e){
			model.addAttribute("error", "server");
		}
		model.addAttribute("book", book);
		
		return "book-modify";
	}
	
	@RequestMapping(value="/book-modify.do", method=RequestMethod.POST)
	public String modify(Model model, Book book) {
		
		try {
			service.modify(book);
		}catch (BookException e){
			model.addAttribute("error", "server");
		}
	
		
		return "redirect:book-list.do";
	}
}
