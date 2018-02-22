package com.koitt.board.controller;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.koitt.board.model.Authority;
import com.koitt.board.model.UsersException;

@Repository
public class AuthorityDaoImpl implements AuthorityDao{
	
	private static final String MAPPER_NS = Authority.class.getName();
	
	private SqlSession session;
	
	public AuthorityDaoImpl() {}

	@Override
	public Authority select(Integer id) throws UsersException {
		
		Authority authority = null;
		
		try {
			authority = session.selectOne(MAPPER_NS + ".select-authority", id);
			
		}catch (Exception e) {
			throw new UsersException(e.getMessage());
		}
		return authority;
	}
	
}
