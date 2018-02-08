package bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TestDrive {
	
	public static void main(String[] args) {
		// 1. 스프링 설정 파일 불러오기
		ApplicationContext context = new GenericXmlApplicationContext("./config/config.xml");
		
		// 2. GreetingService 객체 생성
		GreetingService service = context.getBean("greeting", GreetingService.class);
		
		// 3. 일반 메소드 호출
		service.sayHello();
		
	}
}
