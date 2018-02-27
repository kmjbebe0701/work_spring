package com.koitt.JUnit;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

/* 
 * 매번 새로운 객체를 만드는지 테스트하는 ㅔ테스츠
 * JUnitTest 테스트 크래스를 개선
 */
public class JUnitTestUsingSet {
	
	static Set<JUnitTestUsingSet> testObject = new HashSet<>();
	
	@Test public void test1() {
		/*
		 *  not hasItem: 첫번째 파라미터 컬렉션에 해당 객체가 존재하는지 확인
		 *  이 메소드 호출 부분을 통과하면 같은 객체가 컬렉션에 존재하지 않는 것이다.
		 */
		assertThat(testObject, not(hasItem(this)));
		
		// 존재하지 않는다면 현재 JUnitTestUsingSet 객체를 컬렉션에 저장
		testObject.add(this);
	}
	@Test public void test2() {
		assertThat(testObject, not(hasItem(this)));
		testObject.add(this);
	}
	@Test public void test3() {
		assertThat(testObject, not(hasItem(this)));
		testObject.add(this);
	}
}
