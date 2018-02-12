package com.koitt.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.koitt.dao.EmployeeDao;
import com.koitt.dao.EmployeeDao2;
import com.koitt.model.Employee;

public class TestDrive {
	
	public static void main(String[] args) {
		// 1. 스프링 설정 파일 불러오기
		ApplicationContext context = new GenericXmlApplicationContext("/com/koitt/config/config.xml");
		
		// 방법 1
		EmployeeDao dao01 = context.getBean(EmployeeDao.class);
		Employee emp01 = dao01.getEmployee(7698);
		System.out.println(emp01);
		
		// 방법 2
		EmployeeDao2 dao02 = context.getBean(EmployeeDao2.class);
		Employee emp02 = dao02.getEmployee(7698);
		System.out.println(emp02);
		
	}
	
	
}
