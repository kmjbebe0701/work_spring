package pattern;

import java.util.Date;

public class BuilderPatternTest {
	public static void main(String[] args) {
		StringBuilder builder01 = new StringBuilder();
		
		builder01
			.append("안녕하세요 ")
			.append("반갑습니다.")
			.append("오랜만이네요");
		/*
		 * 해당 방식으로 builder를 작성하면 중간에 다른 코드를 작성 할 수 없기 때문에
		 * 모아 보기 편하다 (스프링 설정 파일에 사용하면 코드 보기 편함)
		 */

		
		System.out.println(builder01);
		
		UserBuilder builder02 = new UserBuilder()
										.SetAge(10)
										.SetDob(new Date())
										.SetEmail("aaa@gmail.com")
										.SetName("에이");
		
		System.out.println(builder02);
		
		
	}
	
}
