package com.koitt.java.tx;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BoardDao {

	/*
	 * 최근 글 번호를 가져온 뒤
	 * 글 번호 1 증가 시키고
	 * 테이블에 글 번호만 INSERT 한다.
	 */
	public Integer getBoardNo(Connection conn) throws SQLException {

		Integer no = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// 최근 글 번호 가져오기
		String sql = "SELECT no FROM board02 ORDER BY no DESC LIMIT 1";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			no = rs.getInt("no") + 1;
		}
		else {
			no = 1;
		}

		// 가져온 글 번호를 테이블에 저장
		sql = "INSERT INTO board02 (no, title, content, writer, regdate) ";
		sql += "VALUES (?, NULL, NULL, NULL, CURDATE())";

		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, no);
		pstmt.executeUpdate();

		DBUtil.getInstance().close(rs);
		DBUtil.getInstance().close(pstmt);

		return no;
	}

	/*
	 * getBoardNo 메소드를 통해 얻은 번호를 이용하여
	 * 그 번호에 해당하는 글에 데이터를 테이블에 입력한다.
	 */
	/*public void insert(Connection conn, Board board) throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "UPDATE board02 SET title = ?, content = ?, writer = ? WHERE no = ?";

		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, board.getTitle());
		pstmt.setString(2, board.getContent());
		pstmt.setString(3, board.getWriter());
		pstmt.setInt(4, board.getNo());
		pstmt.executeUpdate();

		DBUtil.getInstance().close(pstmt);
	}*/
	
	public void insert(Connection conn, Board board) throws SQLException {
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO board02 (no, title, content, writer, regdate) ";
		sql += "VALUES (?, ?, ?, ?, CURDATE())";

		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, board.getNo());
		pstmt.setString(2, board.getTitle());
		pstmt.setString(3, board.getContent());
		pstmt.setString(4, board.getWriter());
		
		pstmt.executeUpdate();

		DBUtil.getInstance().close(pstmt);
	}
	
}