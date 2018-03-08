package com.koitt.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;


import freemarker.template.Configuration;
import freemarker.template.Template;

@Service
public class MailServiceImpl implements MailService{
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private Configuration freeMarkerConfiguration;

	@Override
	public void sendEmail(String email) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			
			//Date date = new SimpleDateFormat("yyyy/MM/dd");
			
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				
				
				// FreeMarker 템플릿에 전달할 정보를 Map에 담기
				Map<String, Object> model = new HashMap<String, Object>();
				
				// 이메일 메시지 작성
				Template template = freeMarkerConfiguration.getTemplate("fm-mail-template.html");
				String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
				
				// 이메일 메시지 작성 Helper 객체 생성
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
				helper.setFrom("kmj.tt.71@gmail.com");		// 보내는 사람 이메일 주소
				helper.setTo(email);					// 받는 사람 이메일 주소
				helper.setSubject("board 정보입니다.");		// 메일 제목
				helper.setText(text, true);						// 메일내용, true는 HTML mail
				
				// 받는 사람 이메일 값과 메일 내용 테스트 위해 콘솔에 출력
				System.out.println(email);
				System.out.println(text);
			
				// 첨부파일
				helper.addAttachment("board.csv", new FileSystemResource("C:/sample/prac02/board.csv"));
			}
		};		// 익명클래스
		
		try {
			System.err.println("메일 보내는 중 ...");
			mailSender.send(preparator);
			System.err.println("메일 보내기 완료!!");
		}catch (MailException e) {
			System.out.println(e.getMessage());
		}
		
	}

}
