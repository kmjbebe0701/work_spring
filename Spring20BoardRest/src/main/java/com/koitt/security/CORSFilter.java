package com.koitt.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component("corsFilter")
public class CORSFilter implements Filter {
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, 
			FilterChain chain) throws IOException, ServletException {
		System.out.println("CORSFilter 동작 ... ");
		HttpServletResponse resp = (HttpServletResponse) response;
		/*
		 * 아래의 Header 설정 값들은 다른 클라이언트가 서버로 요청할 때
		 * 허용하는 값들을 설정하는 값들이다.
		 */
		
		// 허용할 클라이언트의 IP주소를 작성, * 값은 모든 클라이언트의 접속을 허용한다는 뜻
		resp.setHeader("Access-Control-Allow-Origin", "*");
		
		// Credential은 ID와 비밀번호 값을 Base64 인코딩한 값을 의미, 사용할 것이기 때문에 true
		resp.setHeader("Access-Control-Allow-Credentials", "true");
		
		// HTTP Method들 중 허용할 HTTP Method를 작성
		resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
		
		// 1시간까지 요청이 유효한 것으로 설정
		resp.setHeader("Access-Control-Max-Age", "3600");
		
		// 허용하는 헤더의 목록을 작성
		resp.setHeader("Access-Control-Allow-Headers", 
				"X-Requested-With, Content-Type, Authorization, "
				+ "Origin, Accept, Access-Control-Request-Method, "
				+ "Access-Control-Request-Headers");
		chain.doFilter(request, resp);
	}

	@Override
	public void destroy() {}
}
