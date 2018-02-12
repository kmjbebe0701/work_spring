package com.koitt.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koitt.model.Board;

@Repository
public class BoardDao02 {
	
	@Autowired
	private SqlSession session;
	
	public Board getboard(int no) {
		Board board = session.selectOne("com.koitt.model.Board.select2", no);
		
		return board;
	}

}
