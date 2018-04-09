package com.koitt.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.koitt.board.model.Board;

public class TestDrive {
	
	public static void main(String[] args) {
		String result = Board.class.getName();
		System.out.println(result);
		
		// 비밀번호 암호화 테스트
		String password = "1234";
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encoding = encoder.encode(password);
		System.out.println("암호화 이전 비밀번호 값: " + password);
		System.out.println("암호화한 비밀번호 값: " + encoding);
		
		/*
		 *  matches 첫번째 파라미터는 입력한 비밀번호
		 *  두번째 파라미터는 암호화된 패스워드
		 */
		boolean isMatched = encoder.matches("1234", encoding);
		if (isMatched) {
			System.out.println("비밀번호가 일치합니다.");
		}
		else {
			System.out.println("비밀번호가 일치하지 않습니다.");
		}
	}
}








