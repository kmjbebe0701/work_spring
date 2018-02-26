package com.koitt.book.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
public class IndexWebController {
	
	
	@RequestMapping(value= {"/", "/index.do"}, method=RequestMethod.GET)
	public String index(Model model) {
		
		return "index";
	}

}
