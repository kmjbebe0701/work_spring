package com.koitt.test;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.koitt.dao.BoardDao01;
import com.koitt.dao.BoardDao02;
import com.koitt.model.Board;

public class TestDrive {
	
	public static void main(String[] args) {
		// 1. 스프링 설정 파일 불러오기
		ApplicationContext context = new GenericXmlApplicationContext("/com/koitt/config/config.xml");
		
		// 방법 1
		BoardDao01 dao01 = context.getBean(BoardDao01.class);
		Board board01 = dao01.getBoard(1);
		System.out.println(board01);
		
		// 방법 2
		BoardDao02 dao02 = context.getBean(BoardDao02.class);
		Board board02 = dao02.getboard(1);
		System.out.println(board02);
	}
	
	
}
