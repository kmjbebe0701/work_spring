package com.koitt.board.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.koitt.board.model.Board;
import com.koitt.board.model.FileException;

public interface FileService {
	
	// 파일 추가
	public void add(HttpServletRequest request, MultipartFile attachment, Board board) throws FileException;
	
	// 파일 삭제
	public void remove(HttpServletRequest request, String filename) throws FileException;
}
