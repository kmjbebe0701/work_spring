package com.koitt.book.service;

import java.util.List;

import com.koitt.book.model.Book;
import com.koitt.book.model.BookException;

public interface BookService {
	
	// 책 하나 불러오기
	public Book detail(String isbn) throws BookException;
	
	// 책 목록
	public List<Book> list() throws BookException;
	
	// 책 개수 가져오기
	public int count() throws BookException;
	
	// 책 추가
	public void add(Book book) throws BookException;
	
	// 책 삭제
	public void remove(String isbn) throws BookException;
	
	// 책 수정
	public void modify(Book book) throws BookException;
	

}
