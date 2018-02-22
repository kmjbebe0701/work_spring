package com.koitt.book.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koitt.book.model.Book;
import com.koitt.book.model.BookException;

@Repository
public class BookDaoImpl implements BookDao{
	
	private static final String MAPPER_NS = Book.class.getName();
	
	@Autowired
	private SqlSession session;
	
	public BookDaoImpl() {}

	@Override
	public Book select(String isbn) throws BookException {
		Book book = null;
		try {
			book = session.selectOne(MAPPER_NS + ".select", isbn);
			
		}catch(Exception e){
			System.out.println(e.getMessage());
			throw new BookException(e.getMessage());
		}
		
		return book;
	}

	@Override
	public List<Book> selectAll() throws BookException {
		List<Book> list = null;
		try {
			list = session.selectList(MAPPER_NS + ".select-all");
			
		}catch(Exception e){
			System.out.println(e.getMessage());
			throw new BookException(e.getMessage());
		}
		
		
		return list;
	}

	@Override
	public int bookCount() throws BookException {
		Integer result = null;
		try {
			result = session.selectOne(MAPPER_NS + ".select-cnt");
			
			
		}catch(Exception e){
			System.out.println(e.getMessage());
			throw new BookException(e.getMessage());
		}
		return result;
	}

	@Override
	public void insert(Book book) throws BookException {
		try {
			session.insert(MAPPER_NS + ".insert", book);
			
		}catch(Exception e){
			System.out.println(e.getMessage());
			throw new BookException(e.getMessage());
		}
		
	}

	@Override
	public void delete(String isbn) throws BookException {
		try {
			session.delete(MAPPER_NS + ".delete", isbn);
			
		}catch(Exception e){
			System.out.println(e.getMessage());
			throw new BookException(e.getMessage());
		}
		
	}

	@Override
	public void update(Book book) throws BookException {
		try {
			session.update(MAPPER_NS + ".update", book);
			
		}catch(Exception e){
			System.out.println(e.getMessage());
			throw new BookException(e.getMessage());
		}
		
	}

}
