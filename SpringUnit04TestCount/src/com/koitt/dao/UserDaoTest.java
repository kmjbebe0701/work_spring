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
		User user1 = new User("curling", "김영미", "1234");
		User user2 = new User("speed", "이승훈", "5678");

		
		// 생성한 사용자를 데이터베이스에 저장
		dao.add(user1);
		dao.add(user2);
	
		// 생성된 데이터가 2개인지 확인
		assertThat(dao.getCount(), is(2));
		
		// 첫번째 User의 id로 get()을 실행하면 첫번째 User의 값을 가진 객체를 돌려주는지 확인
		User userget1 = dao.get(user1.getId());
		assertThat(userget1.getName(), is(user1.getName()));
		assertThat(userget1.getPassword(), is(user1.getPassword()));
		
		/*
		 * 두번째 User에 대해서도 같은 방법으로 검증한다
		 */
		User userget2 = dao.get(user2.getId());
		assertThat(userget2.getName(), is(user2.getName()));
		assertThat(userget2.getPassword(), is(user2.getPassword()));
		
		/*
		 * assertThat
		 * 첫번째파라미터: 비교할 원본
		 * 두번째파라미터: 비교 대상
		 * 원본과 대상이 같다면 넘어가고 같지 않다면 테스트 실패로 간주
		 * (아래 assertThat 모두 테스트 성공해야 addAndGet 테스트는 성공한 것으로 JUnit에서 출력)
		 */
	
	}
	
	@Test
	public void count() throws SQLException {
		ApplicationContext context = 
				new GenericXmlApplicationContext("/com/koitt/config/applicationContext.xml");
		
		UserDao dao = context.getBean("userDao",UserDao.class);
		
		// 동일한 테스트 결과를 얻기 위해 테이블 내용을 비워준다.
		dao.deleteAll();
		// 실제 비워졌는지 getCount 메소드를 이용하여 확인
		assertThat(dao.getCount(), is(0));
		
		// User 객체 하나씩 데이터베이스에 입력하면서 getCount 메소드가 제대로 동작하는지 검증
		User user1 = new User("curling", "김영미", "1234");
		User user2 = new User("speed", "이승훈", "5678");
		User user3 = new User("skeleton", "윤성빈", "3344");
		
		dao.add(user1);
		assertThat(dao.getCount(), is(1));
		
		dao.add(user2);
		assertThat(dao.getCount(), is(2));
		
		dao.add(user3);
		assertThat(dao.getCount(), is(3));
		
	}
	/*
	 *  주의할 점: 두개의 테스트가 어떤 순서로 실행될지는 알 수 없다.
	 *  모든 테스트는 실행 순서에 상관없이 독립적으로 항상 동일한 결과를 낼 수 있도록 한다.
	 */
}
