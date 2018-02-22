package com.koitt.board.controller;

import com.koitt.board.model.Authority;
import com.koitt.board.model.UsersException;

public interface AuthorityDao {
	
	public Authority select(Integer id) throws UsersException;

}
