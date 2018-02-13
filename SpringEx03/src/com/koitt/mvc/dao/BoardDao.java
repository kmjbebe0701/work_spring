package com.koitt.mvc.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koitt.mvc.model.Board;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSession session;
	
	public Board getBoard(int no) {
		Board board = session.selectOne("com.koitt.mvc.model.Board.select2", no);
		
		return board;
	}

}
