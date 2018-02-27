package com.koitt.junit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class SringTest {
	
	@Test public void subStringTest() {
		String object = "한글 부분 스트링 테스트";
		
		// 첫번째 파라미터는 유동적인 값, 두번째 파라미터는 고정적인 값
		assertThat(object.substring(0, 2), is("한글"));
		
	}

	@Test public void lengthTest() {
		String object = "동해물과 백두산이 마르고 닳도록 ...";
		assertThat(object.length(), is(21));
	}
}
