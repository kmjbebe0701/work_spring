package com.koitt.book.dao;

import com.koitt.book.model.Authority;
import com.koitt.book.model.UsersException;

public interface AuthorityDao {
	
	public Authority select(Integer id) throws UsersException;

}
