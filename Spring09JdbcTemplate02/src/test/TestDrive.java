package test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import dao.TxDao;
import model.Job;

public class TestDrive {
	
	public static void main(String[] args) {
		// 1. 스프링 설정 파일 불러오기
		ApplicationContext context = new GenericXmlApplicationContext("/config/config.xml");
		
		// 2. TxDao 빈 객체 가져오기
		TxDao dao = context.getBean(TxDao.class);
		
		Job job = new Job("A01", "개발자", 10000, 200);
		
		dao.insert(job);
		
	}
}
