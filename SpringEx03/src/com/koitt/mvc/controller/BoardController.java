package com.koitt.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.koitt.mvc.dao.BoardDao;
import com.koitt.mvc.model.Board;

@Controller
public class BoardController {

	@Autowired
	private BoardDao dao;

	@RequestMapping("/")
	public String getBoard (@RequestParam(value="id", defaultValue="1", required=false) int no, Model model)
	{
		Board board = dao.getBoard(no);

		
		model.addAttribute("board", board);

		return "board";
	}

}
