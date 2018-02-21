package com.koitt.board.dao;

import java.util.List;

import com.koitt.board.model.Board;
import com.koitt.board.model.BoardException;

public interface BoardDao {
	
	// 글 추가
	public void insert(Board board) throws BoardException;
	
	// 글 번호를 이용하여 글 하나 불러오기
	public Board select(String no) throws BoardException;
	
	// 전체 글 불러오기
	public List<Board> selectAll() throws BoardException;
	
	// 게시글 개수 가져오기
	public int boardCount() throws BoardException;
	
	// 글 수정하기
	public void update(Board board) throws BoardException;
	
	// 글 삭제하기
	public void delete(String no) throws BoardException;
}
