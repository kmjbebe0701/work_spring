package com.koitt.java.tx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
	// 싱글턴 패턴 만들기
	
	// 1. 객체를 저장할 필드 선언
	private static DBUtil instance;
	
	// 2. 외부에서 객체를 생성하지 못하도록 private 기본 생성자 만들기
	private DBUtil() {}
	
	// 3. 외부에서 DBUtil 객체를 사용하기 위한 메소드 (캡슐화)
	public static DBUtil getInstance() {
		if (instance == null) {
			/*
			 *  최초로 DBUtil을 사용할 경우 객체가 만들어지지 않았기 때문에
			 *  새로운 객체 생성
			 *  이후에는 필드에 저장된 DBUtil 객체를 계속사용
			 *  이 프로그램에서 DBUtil은 오로지 하나의 객체만 존재
			 */
			instance = new DBUtil();
		}
		return instance;
	}
	// End of 싱글턴 패턴 만들기
	
	// 데이터베이스 커넥션 객체 가져오는 메소드
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		String url = "jdbc:mysql://localhost:3306";
		String dbName = "koitt";
		
		// 1. 드라이버 로드
		Class.forName("com.mysql.jdbc.Driver");
		
		// 2. 데이터베이스 연결
		Connection conn = DriverManager.getConnection(url + "/" + dbName, "root", "koitt");
		
		return conn;
	}
	
	// Connection 객체 연결 해제
	public void close(Connection conn) throws SQLException {
		if (conn != null) {
			conn.close();
		}
	}
	
	// Statement(PreparedStatement 포함) 객체 연결 해제
	public void close(Statement stmt) throws SQLException {
		if (stmt != null) {
			stmt.close();
		}
	}
	
	// ResultSet 객체 연결 해제
	public void close(ResultSet rs) throws SQLException {
		if (rs != null) {
			rs.close();
		}
	}
	
	// Rollback 메소드
	public void rollback(Connection conn) throws SQLException {
		if (conn != null) {
			conn.rollback();
		}
	}
}





