package com.koitt.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koitt.board.model.Board;
import com.koitt.board.model.BoardException;

@Repository
public class BoardDaoImpl implements BoardDao {
	
	private static final String MAPPER_NS = Board.class.getName();
	
	@Autowired
	private SqlSession session;
	
	public BoardDaoImpl() {}

	@Override
	public void insert(Board board) throws BoardException {
		try {			
			session.insert(MAPPER_NS + ".insert-board", board);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new BoardException(e.getMessage());
		}
	}

	@Override
	public Board select(String no) throws BoardException {
		Board board = null;
		
		try {
			board = session.selectOne(MAPPER_NS + ".select-board", no);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new BoardException(e.getMessage());
		}
		
		return board;
	}

	@Override
	public List<Board> selectAll() throws BoardException {
		List<Board> list = null;
		
		try {
			list = session.selectList(MAPPER_NS + ".select-all-board");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new BoardException(e.getMessage());
		}
		
		return list;
	}

	@Override
	public int boardCount() throws BoardException {
		Integer result = null;
		
		try {
			result = session.selectOne(MAPPER_NS + ".select-count-board");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new BoardException(e.getMessage());
		}
		
		return result;
	}

	@Override
	public void update(Board board) throws BoardException {
		try {
			session.update(MAPPER_NS + ".update-board", board);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new BoardException(e.getMessage());
		}
	}

	@Override
	public void delete(String no) throws BoardException {
		try {
			session.delete(MAPPER_NS + ".delete-board", no);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new BoardException(e.getMessage());
		}
	}

}








