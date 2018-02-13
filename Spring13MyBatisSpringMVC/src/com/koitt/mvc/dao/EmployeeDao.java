package com.koitt.mvc.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koitt.mvc.model.Employee;

@Repository
public class EmployeeDao {
	
	@Autowired
	private SqlSession session;
	
	public Employee getEmployee(int empno) {
		return session.selectOne("com.koitt.mvc.model.Employee.select", empno);
	}
}
