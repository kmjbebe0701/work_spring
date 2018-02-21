package com.koitt.book.controller;

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

import com.koitt.book.model.Book;
import com.koitt.book.model.BookException;
import com.koitt.book.model.FileException;
import com.koitt.book.service.BookService;
import com.koitt.book.service.FileService;

@Controller
public class BookWebController {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private FileService fileService;

	@RequestMapping("/book-list.do")
	public String list(Model model) {
		List<Book> list = null;
		
		try {
			list=bookService.list();
		}catch(BookException e){
			model.addAttribute("error", "server");
		}
		model.addAttribute("list",list);
		
		
		
		return "book-list";
	}
	@RequestMapping(value="/book-detail.do", method=RequestMethod.GET)
	public String detail(Model model, HttpServletRequest request, 
			@RequestParam(value="isbn", required=true) String isbn) {
		Book book = null;
		String filename = null;
		String imgPath = null;
		try {
			book = bookService.detail(isbn);
			
			filename = book.getAttachment();
			if (filename != null && !filename.trim().isEmpty()) {
				filename = URLDecoder.decode(filename,"UTF-8");
			}
			
			imgPath = fileService.getimgPath(request, filename);
			
		}catch (BookException e){
			System.out.println(e.getMessage());
			model.addAttribute("error", "server");
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
			model.addAttribute("error", "incoding");
		}
		
		model.addAttribute("book", book);
		model.addAttribute("filename", filename);
		if (imgPath != null && !imgPath.trim().isEmpty()) {

			model.addAttribute("imgPath", imgPath);
		}
		
		return "book-detail";
	}
	
	@RequestMapping(value="/book-add.do", method=RequestMethod.GET)
	public String add() {
		
		return "book-add";
	}
	
	@RequestMapping(value="/book-add.do", method=RequestMethod.POST)
	public String add(String title, String author, String publisher, Integer price, 
			String description,	@RequestParam("attachment") MultipartFile attachment, HttpServletRequest request) {
		
		Book book = new Book();
		book.setAuthor(author);
		book.setDescription(description);
		book.setPrice(price);
		book.setPublisher(publisher);
		book.setTitle(title);
		
		try {
			fileService.add(request, attachment, book);
			bookService.add(book);
			
		}catch (BookException e){
			System.out.println(e.getMessage());
			request.setAttribute("error", "server");
		} catch (FileException e) {
			System.out.println(e.getMessage());
			request.setAttribute("error", "file");
		}
		
		return "redirect:book-list.do";
	}
	
	@RequestMapping(value="/book-remove.do", method=RequestMethod.GET)
	public String removeConfirm(Model model, @RequestParam(value="isbn", required=true) String isbn) {
		
		model.addAttribute("isbn", isbn);
		
		return "book-remove-confirm";
	}
	
	@RequestMapping(value="/book-remove.do", method=RequestMethod.POST)
	public String remove(Model model, String isbn, HttpServletRequest request) {
		try {
			String toDeleteFilename = bookService.remove(isbn);
			fileService.remove(request, toDeleteFilename);

		} catch (BookException e) {
			model.addAttribute("error", "server");
		} catch (FileException e) {
			model.addAttribute("error", "file");
		}

		
		return "redirect:book-list.do";
	}
	
	@RequestMapping(value="/book-modify.do", method=RequestMethod.GET)
	public String modify(Model model, @RequestParam(value="isbn", required=true) String isbn) {
		Book book = null;
		
		try {
			book = bookService.detail(isbn);
			
		} catch (BookException e){
			model.addAttribute("error", "server");
		}
		model.addAttribute("book", book);
		
		return "book-modify";
	}
	
	@RequestMapping(value="/book-modify.do", method=RequestMethod.POST)
	public String modify(HttpServletRequest request, Integer isbn, String title, String author, String publisher, Integer price, 
			String description, @RequestParam("attachment") MultipartFile attachment) {
		
		
		Book book = new Book();
		book.setIsbn(isbn);
		book.setAuthor(author);
		book.setDescription(description);
		book.setPrice(price);
		book.setPublisher(publisher);
		book.setTitle(title);


		try {
			// 새롭게 수정할 파일을 서버에 저장
			fileService.add(request, attachment, book);

			// 기존 파일명을 가져온다
			String toDeleteFilename = bookService.modify(book);

			// 기존에 있던 파일을 삭제
			fileService.remove(request, toDeleteFilename);

		} catch (BookException e) {
			System.out.println(e.getMessage());
			request.setAttribute("error", "server");
		} catch (FileException e) {
			System.out.println(e.getMessage());
			request.setAttribute("error", "file");
		}
		
		return "redirect:book-list.do";
	}
	
	@RequestMapping(value = "/download.do", method = RequestMethod.GET, params = "filename")
	public void download(HttpServletRequest request, HttpServletResponse response, String filename) {

		try {
			fileService.download(request, response, filename);
		} catch (FileException e) {
			System.out.println(e.getMessage());
		}
	}
}
