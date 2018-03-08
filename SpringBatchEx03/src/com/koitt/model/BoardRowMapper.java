package com.koitt.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class BoardRowMapper implements RowMapper<Board>{

	@Override
	public Board mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Board item = new Board();
		
		item.setNo(rs.getInt("no"));
		item.setTitle(rs.getString("title"));
		item.setContent(rs.getString("content"));
		item.setUserNo(rs.getInt("user_no"));
		item.setRegdate(rs.getDate("regdate"));
		item.setAttachment(rs.getString("attachment"));
		
		return item;
	}

}
