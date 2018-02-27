package com.koitt.junit;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertThat;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:com/koitt/junit/config.xml")
public class JUnitTestUsingSet {
	@Autowired
	private ApplicationContext context;
	
	static ApplicationContext contextObject = null;
	
	static Set<ApplicationContext> testObject = new HashSet<>();
	
	
	@Test public void test1() {
		/*
		 * 컬렉션의 사이즈가 0 초과인 것은 적어도 애플리케이션 컨텍스트 객체가
		 * 하나 이상 존재하는 것이므로 그때부터 객체를 비교해서 같은것이 있는지 조사를 하는 것이다.
		 */
		if (testObject.size() > 0) {
			assertThat(testObject, hasItem(this.context));
		}
		
		testObject.add(this.context);
	}
	@Test public void test2() {
		if (testObject.size() > 0) {
			assertThat(testObject, hasItem(this.context));
		}
		
		testObject.add(this.context);
	}
	@Test public void test3() {
		if (testObject.size() > 0) {
			assertThat(testObject, hasItem(this.context));
		}
		
		testObject.add(this.context);
	}
}
