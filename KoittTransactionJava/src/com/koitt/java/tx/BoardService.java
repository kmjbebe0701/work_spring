package com.koitt.java.tx;

import java.sql.Connection;

public class BoardService {

	private BoardDao dao = new BoardDao();

	/*
	 * 트련잭션 처리 ㅣ각
	 * 
	 * 
	 * 롤백(Rollback): SQL문 실행 전 상태로 되돌리는 것
	 * 트랜젝션(Transaction): 여러개의 SQL문을 하나의 작업단위로 묶은것
	 * 트랜젠션처리를 한다: 여러개의 SQL문을 하나로 묶어 작업하다 문제가 발생됟다면 롤백을 호출하여 이전상태로 되돌리도록 처리하는것을 트랜젝션 처리라고한다.
	 */
	public void add(Board board) {
		Connection conn = null;

		try {
			conn = DBUtil.getInstance().getConnection();
			/*
			 * 자동 Commit을 막기 위해 false설정
			 * Commit: 테이블에 최종적으로 SQL문 실행 결과를 반영 한 것
			 * false로 지정했기 때문에 우리가 직접 커밋을 호출해야 한다.
			 */
			conn.setAutoCommit(false);
			Integer no = dao.getBoardNo(conn);
			board.setNo(no);
			dao.insert(conn, board);
			
			//마지막으로 SQL 실행한 결과를 반영
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			if (conn != null) {
				try {
					conn.setAutoCommit(true);
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
	}
}
