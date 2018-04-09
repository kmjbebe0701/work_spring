package com.koitt.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.koitt.board.model.FileException;
import com.koitt.board.service.FileService;

@Controller
public class CommonWebController {
	
	@Autowired
	private FileService fileService;

	/*
	 * 다운로드 링크를 화면에서 클릭하면 아래와 같이 서버에 GET 방식으로 요청한다.
	 * download.do?filename=파일명
	 * 
	 * 아래 RequestMapping 애노테이션 뜻은 아래와 같다.
	 * 요청 URL은 /download.do
	 * 요청 HTTP Method는 GET
	 * 요청한 쿼리문자열의 변수명이 filename일 경우 아래 메소드를 실행 (params)
	 */
	@RequestMapping(value="/download.do", method=RequestMethod.GET, params="filename")
	public void download(HttpServletRequest request, HttpServletResponse response, 
			String filename) {
		
		try {
			fileService.download(request, response, filename);
			
		} catch (FileException e) {
			System.out.println(e.getMessage());
		}
	}
}
