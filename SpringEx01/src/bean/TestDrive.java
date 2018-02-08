package bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TestDrive {

	public static void main(String[] args) {
		// 1. 스프링설정파일 불러오기
		ApplicationContext context = new GenericXmlApplicationContext("./config/config.xml");
		// 2. ApplicationContext객체 이용하여 PersonService 객체만들기
		 PersonService service = context.getBean("person1", PersonService.class);
		// 3. 만든 객체로 2-2 에서 구현한 메소드 호출하기
		service.name();
		
			
	}
	
	/*
	 * config.xml 설정파일 만들고
	 * xml방식으로 autowire설정하기
	 */

}
