package com.koitt.board.model;

/*
 * 작성한 이유
 * 권한 번호를 코드상에 직접 입력(하드코딩) 할 경우
 * 관리하기가 힘들어 지기 때문에 번호 대신 enum 타입의 항목명을 사용
 * 
 * authority 테이블의 내용이 변경되면 이 enum도 변경해줘야 한다.
 */
public enum AuthorityId {
	ADMIN(10),
	USER(20);
	
	private Integer id;
	
	private AuthorityId(Integer id) {
		this.id = id;
	}
	
	public Integer getAuthorityId() {
		return this.id;
	}
}
