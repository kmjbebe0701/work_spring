package com.koitt.kakao.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/kakao-pay") // 페이 관련 URL 앞에는 항상 kakao-pay를 사용
public class KakaoPayWebController {

	// 결제 준비 화면
	@RequestMapping(value = "/ready.do", method = RequestMethod.GET)
	public String ready() {
		return "kakao-pay/ready";
	}

	// 결제 준비 수행
	@RequestMapping(value = "/ready.do", method = RequestMethod.POST)
	public String ready(@RequestParam Map<String, String> order, HttpSession session)
			throws JsonParseException, JsonMappingException, IOException {

		/*
		 * 서버에 요청할 주소 및 Body 정보 항목에 대한 정보는 아래 링크 참조
		 * https://developers.kakao.com/docs/restapi/kakaopay-api
		 */
		RestTemplate rt = new RestTemplate(); // Spring4.0부터 사용가능, REST API호출을 위한 클래스

		// Map에 정보를 담는다. MultValueMAp 타입은 RestTemplate을 이용할 때 사용하는 Map 타입
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("cid", "TC0ONETIME"); // 가맹점 코드 번호 (테스트용)
		params.add("partner_order_id", order.get("partner_order_id")); // 가맹점 주문 번호
		params.add("partner_user_id", order.get("partner_user_id")); // 가맹점 회원 ID
		params.add("item_name", order.get("item_name")); // 상품명
		params.add("quantity", order.get("quantity")); // 상품 수량
		params.add("total_amount", order.get("total_amount")); // 상품 총액
		params.add("tax_free_amount", order.get("tax_free_amount")); // 상품 비과세 금액

		// 결제 승인 성공시 이동할 우리 페이지 주소
		params.add("approval_url", "http://localhost:8080/KakaoApi/kakao-pay/approve.do");

		// 결제 취소시 이동할 우리 페이지 주소
		params.add("cancel_url", "http://localhost:8080/KakaoApi/kakao-pay/cancel.do");

		// 결제 실패시 이동할 우리 페이지 주소
		params.add("fail_url", "http://localhost:8080/KakaoApi/kakao-pay/fail.do");

		/*
		 * 우리 페이지 주소를 보내주는 이유는 결제 준비 단계에서 우리 페이지에서 카카오 페이지로 넘어갔기 때문에 카카오페이지에서 결제 처리 후 다시
		 * 우리 페이지로 넘어오려면 카카오에게 우리 서버 주소를 알려줘야 다시 복귀할 수 있기 때문
		 */

		// 서버로 요청할 Header
		HttpHeaders headers = new HttpHeaders();

		// 본인의 카카오 관리자 키를 헤더에 저장
		headers.add("Authorization", "KakaoAK " + "a841ef298ef56c54c7bd02da44eb489d");

		// 우리가 응답받을 문서 형태 지정: JSON
		headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);

		// 우리가 요청하는 문서 형태 지정: Form Urlencoded
		headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

		// 위에서 작성한 헤더정보와 Body정보를 담는 HttpEntity 객체를 생성
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(params,
				headers);

		// RestTemplate 객체를 이용하여 HttpEntity 객체를 카카오 서버로 보낸다. (POST 요청하기)
		String response = rt.postForObject("https://kapi.kakao.com/v1/payment/ready", request, String.class);
		System.out.println(response);

		/*
		 * JSON String을 Map 형태로 변환 JavaScript 객체를 Java에서 사용하기 위해 Java 객체로 변환하는 과정을 거쳐야
		 * 한다
		 */
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> resulMap = mapper.readValue(response, new TypeReference<Map<String, Object>>() {
		});

		/*
		 * 결제 승인 페이지에서 사용할 정보를 Session 객체에 담기 다른 영향을 끼치지 않기 위해, 로그인 중에만 세션이 실행되기 때문에 로그
		 * 아웃시 안전한 정보보호를 위해 (결제 고유번호, 가맹점 주문번호, 가맹점 회원 ID) Session 객체를 사용하는 이유는 사용자마다
		 * 고유의 Session 객체를 가지고 있어서 다른 사용자의 결제 과정에 영향을 끼치지 않는다.
		 */
		session.setAttribute("tid", resulMap.get("tid"));
		session.setAttribute("partner_order_id", order.get("partner_order_id"));
		session.setAttribute("partner_user_id", order.get("partner_user_id"));
		return "redirect:" + resulMap.get("next_redirect_pc_url"); // 임시로 이동

	}

	// 결제 승인 화면
	@RequestMapping(value = "/approve.do", method = RequestMethod.GET)
	public String approve(@RequestParam(value = "pg_token", required = true) String token, HttpSession session,
			Model model) throws JsonParseException, JsonMappingException, IOException {

		RestTemplate rt = new RestTemplate(); // Spring4.0부터 사용가능, REST API호출을 위한 클래스

		// Map에 정보를 담는다. MultValueMAp 타입은 RestTemplate을 이용할 때 사용하는 Map 타입
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("cid", "TC0ONETIME"); 								// 가맹점 코드 번호 (테스트용)
		params.add("tid", (String) session.getAttribute("tid"));		//결제 고유번호
		params.add("partner_order_id", (String) session.getAttribute("partner_order_id"));	//주문번호
		params.add("partner_user_id", (String) session.getAttribute("partner_user_id"));	//회원 ID
		params.add("pg_token", token);					//쿼리문자열로 전달받은 token값

		// 서버로 요청할 Header
		HttpHeaders headers = new HttpHeaders();

		// 본인의 카카오 관리자 키를 헤더에 저장
		headers.add("Authorization", "KakaoAK " + "a841ef298ef56c54c7bd02da44eb489d");

		// 우리가 응답받을 문서 형태 지정: JSON
		headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);

		// 우리가 요청하는 문서 형태 지정: Form Urlencoded
		headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");

		// 위에서 작성한 헤더정보와 Body정보를 담는 HttpEntity 객체를 생성
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(params,
				headers);

		// RestTemplate 객체를 이용하여 HttpEntity 객체를 카카오 서버로 보낸다. (POST 요청하기)
		String response = rt.postForObject("https://kapi.kakao.com/v1/payment/approve", 
				request, String.class);
		System.out.println(response);
		
		//세션에 저장했던 내용 삭제
		session.removeAttribute("tid");						
		session.removeAttribute("partner_order_id");
		session.removeAttribute("partner_user_id");

		/*
		 * JSON String을 Map 형태로 변환 JavaScript 객체를 Java에서 사용하기 위해 Java 객체로 변환하는 과정을 거쳐야
		 * 한다
		 */
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> resulMap = mapper.readValue(response, 
				new TypeReference<Map<String, Object>> (){});
		
		//approve.do 페이지에 정보를 포워딩
		model.addAttribute("result", resulMap);
		
		return "kakao-pay/approve";

	}

}
