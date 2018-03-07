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

import com.koitt.model.Users;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Service
public class MailServiceImpl implements MailService{
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private Configuration freeMarkerConfiguration;

	@Override
	public void sendEmail(Users user) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			
			private Random random = new Random();
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				// 100001~999999 범위에서 난수 생성
				String authCode = Integer.toString(this.random.nextInt(899999) + 100001);
				
				// FreeMarker 템플릿에 전달할 정보를 Map에 담기
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("authCode", authCode);
				
				// 이메일 메시지 작성
				Template template = freeMarkerConfiguration.getTemplate("fm-mail-template.html");
				String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
				
				// 이메일 메시지 작성 Helper 객체 생성
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
				helper.setFrom("a@gmail.com");		// 보내는 사람 이메일 주소
				helper.setTo(user.getEmail());					// 받는 사람 이메일 주소
				helper.setSubject("[KOITT] 이메일 인증 코드");		// 메일 제목
				helper.setText(text, true);						// 메일내용, true는 HTML mail
				
				// 받는 사람 이메일 값과 메일 내용 테스트 위해 콘솔에 출력
				System.out.println(user.getEmail());
				System.out.println(text);
				
				/* 
				 * 내장 리소스 
				 * (FreeMarker 템플릿(HTML)에서 cid:image.jpg로 사용하면
				 * img/lights.jpg 경로에 해당하는 이미지를 사용하는 것이다) 
				 */
				helper.addInline("image.jpg", new FileSystemResource("img/lights.jpg"));
				
				
				// 첨부파일
				helper.addAttachment("document.csv", new FileSystemResource("C:/sample/ex01/report.csv"));
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
