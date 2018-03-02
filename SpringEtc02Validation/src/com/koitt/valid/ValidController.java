package com.koitt.valid;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ValidController {

	@RequestMapping(value = "/customer.do", method = RequestMethod.GET)
	public String customer(@Valid Customer customer, Errors err) {

		// 만약 유효범위를 벗어날 경우 hasError() 메소드의 리턴값은 true가 된다
		if (err.hasErrors()) {
			System.out.println("유효성 검증 오류 발생");

			/*
			 * 유효범위에서 벗어난 경우가 한가지 이상이 되기 때문에 Error 객체에서 유효범위를 벗어나 발생한 오류들을 모두 가지고 있다
			 * 아래 코드는 그 오류들을 하나씩 끄집어 내어 오류 정보를 콘솔에 표기한다
			 */
			for (ObjectError object : err.getAllErrors()) {
				System.out.println(object.getObjectName() + ":" + object.getDefaultMessage());
			}
			//오류가 발생했으니 오류페이지 (error.jsp)로 포워딩(이동)
			return "error";
		}
		System.out.println(customer);
		return "customer";		//유효범위를 벗어나지 않았다면 customer.jsp 페이지로 포워딩
		
	}

}
