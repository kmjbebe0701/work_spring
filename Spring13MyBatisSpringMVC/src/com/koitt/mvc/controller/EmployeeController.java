package com.koitt.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.koitt.mvc.dao.EmployeeDao;
import com.koitt.mvc.model.Employee;

/*
 * Controller 클래스에 해당하는 클래스에 @Controller 애노테이션을 붙인다.
 * 스프링에서 해당 클래스가 Controller 역할을 한다는 것을 알리기 위해
 */
@Controller	
public class EmployeeController {
	
	/*
	 * @Repository로 만든 빈 객체를 dao 필드에 저장
	 */
	@Autowired
	private EmployeeDao dao;
	
	/*
	 * @RequestMapping : 괄호안의 값에 해당하는 경로로 접속을 하면 아래의 메소드가 호출된다.
	 * @RequestParam : URL의 쿼리문자열
	 * value : 쿼리문자열의 변수명
	 * defaultValue : value에 해당하는 변수에 값이 없을 경우 기본으로 들어갈 값
	 * required : value에 해당하는 변수가 필수인지 boolean값으로 설정
	 */
	@RequestMapping("/")
	public String getEmployee(
			@RequestParam(value="id", defaultValue="7839", required=false) int empno,
			Model model) {
		Employee emp = dao.getEmployee(empno);
		
		// Servlet에서 req.setAttribute("emp", emp); 와 같은 역할
		model.addAttribute("emp", emp);
		
		return "employee";
	}
}
