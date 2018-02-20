package com.koitt.book.dao;

import java.util.List;

import com.koitt.book.model.Book;
import com.koitt.book.model.BookException;


public interface BookDao {

	// 책 하나 불러오기
	public Book select(String isbn) throws BookException;
	
	// 책 목록
	public List<Book> selectAll() throws BookException;
	
	// 책 개수 가져오기
	public int bookCount() throws BookException;
	
	// 책 추가
	public void insert(Book book) throws BookException;
	
	// 책 삭제
	public void delete(String isbn) throws BookException;
	
	// 책 수정
	public void update(Book book) throws BookException;
	
}
