package com.koitt.book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koitt.book.dao.UsersDao;
import com.koitt.book.model.Users;
import com.koitt.book.model.UsersException;

@Service
public class UsersServiceImpl implements UsersService{
	
	@Autowired
	private UsersDao dao;

	@Override
	public List<Users> list() throws UsersException {
		
		return dao.selectAll();
	}

	@Override
	public Users detail(Integer no) throws UsersException {
		
		return dao.select(no);
	}

	@Override
	public void add(Users users) throws UsersException {
		dao.insert(users);
		
	}

	@Override
	public String remove(Integer no, String password) throws UsersException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String modify(Users users) throws UsersException {
		// TODO Auto-generated method stub
		return null;
	}

}
