package com.koitt.book.service;

import java.util.List;

import com.koitt.book.model.Users;
import com.koitt.book.model.UsersException;



/*
 * 기본적으로 구현할 네가지
 * C: Create
 * R: Read
 * U: Update
 * D: Delete
 */
public interface UsersService {
	
	public List<Users> list() throws UsersException;
	
	public Users detail (Integer no) throws UsersException;
	
	public void add(Users users) throws UsersException;
	
	public String remove (Integer no, String password) throws UsersException;
	
	public String modify(Users users) throws UsersException;

}
