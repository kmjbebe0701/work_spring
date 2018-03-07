package com.koitt.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UserRowMapper implements RowMapper<User>{

	/*
	 * mapRow 메소드는 한 아이템(행)에 대한 데이터를 VO와 연결시키는 역할
	 * 
	 * ResultSet에서 Cursor를 옮기기 위해 rs.next() 메소드를 호출 하는 것은
	 * mapRow 메소드 호출 이전에 스프링에서 알아서 호출한다.
	 */
	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		User item = new User();
		
		item.setId(rs.getInt("id"));
		item.setUsername(rs.getString("username"));
		item.setPassword(rs.getString("password"));
		item.setAge(rs.getInt("age"));
		
		return item;
	}

}
