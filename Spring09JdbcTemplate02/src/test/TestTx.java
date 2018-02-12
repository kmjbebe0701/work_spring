package test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import model.Job;
import service.TxService;

public class TestTx {
	
	public static void main(String[] args) {
		// 1. 스프링 설정 파일 불러오기
		ApplicationContext context = new GenericXmlApplicationContext("/config/config.xml");
		
		// 2. 애플리케이션 컨텍스트를 이용하여 빈 객체 생성
		TxService service = context.getBean(TxService.class);
		
		// 3. service 객체를 이용하여 Job 객체 내용을 데이터베이스에 저장
		service.save(new Job("AAA", "title", 200, 900));
		
		
	}
}
