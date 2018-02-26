package com.koitt.dao;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.koitt.vo.User;

public class UserDaoTest {
	public static void main(String[] args) throws SQLException {
		ApplicationContext context = new GenericXmlApplicationContext("/com/koitt/config/applicationContext.xml");
		
		UserDao dao = context.getBean("userDao", UserDao.class);
		User user = new User();
		user.setId("curling");
		user.setName("김영미");
		user.setPassword("1234");
		
		// 생성한 사용자를 데이터베이스에 저장
		dao.add(user);
		
		System.out.println(user.getId() + "등록성공");
		
		// 저장한 사용자를 id 값으로 불러오기
		User user2 = dao.get(user.getId());
		
		// 불러온 사용자를 눈으로 정확히 입력이 됬는지 확인
		System.out.println(user2.getName());
		System.out.println(user2.getPassword());
		
		System.out.println(user2.getId() + "조회성공");
	}

}
