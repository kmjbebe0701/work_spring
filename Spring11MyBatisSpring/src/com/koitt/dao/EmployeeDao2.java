package com.koitt.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koitt.model.Employee;

@Repository
public class EmployeeDao2 {
	
	@Autowired
	private SqlSession session;
	
	public Employee getEmployee(int empno) {
		Employee emp = session.selectOne("com.koitt.model.Employee.select", empno);
		//문제 발생(싱글 턴 방식이라 객체가 하나만 생성되기 때문에 객체를 닫으면 다음 호출시 selectOne이 동작하지 않기 때문에 객체를 닫지 않는다.)
		//session.close();			
		return emp;
	}
	
	
	
}
