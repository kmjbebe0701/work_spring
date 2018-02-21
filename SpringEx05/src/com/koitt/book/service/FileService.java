package com.koitt.book.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.koitt.book.model.Book;
import com.koitt.book.model.FileException;

public interface FileService {
	
	// 파일 추가
	public void add(HttpServletRequest request, MultipartFile attachment, Book book) throws FileException;
	
	// 파일 다운로드
	public void download(HttpServletRequest request, HttpServletResponse response, String filename)throws FileException;
	
	// 파일 삭제
	public void remove(HttpServletRequest request, String filename) throws FileException;
	
	//파일 저장 경로 가져오기
	public String getimgPath(HttpServletRequest request, String filename) ;
}
