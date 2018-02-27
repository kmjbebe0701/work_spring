package com.koitt.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.koitt.vo.User;

public class UserDaoTest {
	
	@Test		//JUnit 에게 테스트용 메소드임을 알려준다
	public void addAndGet() throws SQLException {	//JUnit 테스트 메소드는 반드시 public 으로 선언
		ApplicationContext context = new GenericXmlApplicationContext("/com/koitt/config/applicationContext.xml");
		
		UserDao dao = context.getBean("userDao", UserDao.class);
		
		// 전체 삭제
		dao.deleteAll();
		// 삭제 확인 위해 getCount로 확인
		assertThat(dao.getCount(), is(0));
		User user = new User();
		user.setId("curling");
		user.setName("김영미");
		user.setPassword("1234");
		
		// 생성한 사용자를 데이터베이스에 저장
		dao.add(user);
	
		// 생성된 데이터가 1개인지 확인
		assertThat(dao.getCount(), is(1));
		
		// 저장한 사용자를 id 값으로 불러오기
		User user2 = dao.get(user.getId());
		
		/*
		 * assertThat
		 * 첫번째파라미터: 비교할 원본
		 * 두번째파라미터: 비교 대상
		 * 원본과 대상이 같다면 넘어가고 같지 않다면 테스트 실패로 간주
		 * (아래 assertThat 모두 테스트 성공해야 addAndGet 테스트는 성공한 것으로 JUnit에서 출력)
		 */
		assertThat(user2.getName(), is(user.getName()));
		assertThat(user2.getPassword(), is(user.getPassword()));
		
		
	}
}
