package com.koitt.board.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.koitt.board.model.Users;
import com.koitt.board.model.UsersException;
import com.koitt.board.service.CustomUserDetailsService;
import com.koitt.board.service.FileService;
import com.koitt.board.service.UsersService;

@RestController
@RequestMapping("/rest") // 아래 클래스에 선언된 RequestMapping value 값에 /rest를 공통으로 붙인다.
public class UserRestController {

	@Autowired
	private UsersService usersService;
	
	@Autowired
	private FileService fileService;

	// produces: 서버가 응답으로 보낼 미디어 타입을 지정 (아래는 응답을 JSON으로 응답할 경우)
	@RequestMapping(value="/user/login", method=RequestMethod.POST,
			produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Map<String, Object>> login(@RequestBody Users users) {

		System.out.println(users);

		// 아이디 존재 유무와 비밀번호 일치 여부 확인
		try {
			boolean isMatched = usersService.isPasswordMatched(
					users.getEmail(), users.getPassword());

			if (isMatched) {				
				// Base64 인코딩 전 평문
				String plainCredentials = users.getEmail() + ":" + users.getPassword();

				// 평문을 Base64로 인코딩
				String base64Credentials = new String(Base64.encodeBase64(
						plainCredentials.getBytes()));

				System.out.println("인코딩 한 값: " + base64Credentials);
				
				// email 값을 이용하여 해당 사용자에 대한 정보를 DB로부터 가져오기
				users = usersService.detailByEmail(users.getEmail());
				
				Map<String, Object> resultMap = new HashMap<>();
				resultMap.put("credentials", base64Credentials);
				resultMap.put("usersNo", users.getNo());

				return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}

		} catch (UsersException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 사용자 생성
	@RequestMapping(value="/user", method=RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> newUser(HttpServletRequest req,
			String email, String password, String name,
			@RequestParam("attachment") MultipartFile attachment) {

		// 클라이언트로부터 전달받은 값으로 객체 생성
		Users users = new Users(null, email, password, name, null);

		try {
			// 프로필 사진 저장
			String filename = fileService.add(req, attachment);

			// 프로필 사진 파일명을 users VO 객체에 담기
			users.setAttachment(filename);

			// 데이터베이스에 사용자 추가
			usersService.add(users);

			// 정상적으로 생성되었다는 응답코드를 클라이언트로 전달
			return new ResponseEntity<>(HttpStatus.CREATED);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			
			// 서버 오류가 발생했다는 응답코드를 클라이언트로 전달
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// 사용자 한 명의 데이터 가져오기
	@RequestMapping(value="/user/{no}", method=RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> detail(@PathVariable("no") Integer no,
			HttpServletRequest req) {
		
		Users users = null;
		
		try {
			if (no != null) {
				users = usersService.detail(no);
				
				if (users != null) {
					// 서버에 저장된 프로필 사진 경로 가져오기
					String filename = users.getAttachment();
					String imgPath = fileService.getImgPath(req, filename);
					
					Map<String, Object> resultMap = new HashMap<>();
					resultMap.put("users", users);
					resultMap.put("src", imgPath);
					
					return new ResponseEntity<>(resultMap, HttpStatus.OK);
				}
				else {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
			}
			else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// 사용자 수정
	@RequestMapping(value="/user/modify", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Map<String, Object>> modifyUser(HttpServletRequest req,
			String oldPassword, String newPassword, String name,
			@RequestHeader("Authorization") String authorization,
			@RequestParam("attachment") MultipartFile attachment) {

		Users users = null;
		try {
			// "Basic email:password" 에서 "email:pssword" 부분만 떼어낸다. @PathVariable("no") Integer no,
	        String base64Credentials = authorization.split(" ")[1];
	        
	        // 떼어낸 "email:password" 부분은 base64 인코딩 된 부분이므로 디코딩하여 복원 
	        String plainCredentials = new String(Base64.decodeBase64(base64Credentials));
	        
	        // 복원한 내용을 ":" 기준으로 나누어서 password 값을 뽑아내어 사용한다.
	        String email = plainCredentials.split(":")[0];
	        
	        
	        boolean isMatched = usersService.isPasswordMatched(email, oldPassword);
			
			/*
			 // 기존 비밀번호가 일치하는지 확인하기
			 boolean isMatched = usersService.isPasswordMatched(oldPassword);
			 */
	        
			// 비밀번호가 일치하면 사용자 정보를 변경한다.
			if (isMatched) {
				

				// 현재 로그인한 사용자 정보를 email 값을 이용하여 가져오기
				Users oldUsers = usersService.detailByEmail(email);

				// 새로 변경할 프로필 사진을 서버에 저장한다.
				String filename = fileService.add(req, attachment);

				// 데이터베이스에 저장할 users 객체 생성
				users = new Users(oldUsers.getNo(), email, newPassword, name, filename);


				// 수정할 정보를 데이터베이스에 전달
				String toDeleteFile = usersService.modify(users);

				// 기존 프로필 사진 삭제
				fileService.remove(req, toDeleteFile);
				
			}
			// 기존 비밀번호가 일치하지 않은 경우에는 오류메시지를 수정페이지에 출력한다.
			else {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}

			// Base64 인코딩 전 평문
			String plainCredentials2 = users.getEmail() + ":" + newPassword;

			// 평문을 Base64로 인코딩
			String base64Credentials2 = new String(Base64.encodeBase64(plainCredentials2.getBytes()));

			System.out.println("인코딩 한 값: " + base64Credentials2);
			
			Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("credentials", base64Credentials2);
		

			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);

		} catch (Exception e) {
			System.out.println(e.getMessage());

			// 서버 오류가 발생했다는 응답코드를 클라이언트로 전달
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// 사용자 탈퇴
	@RequestMapping(value="/user/{no}", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Map<String, Object>> removeUser(HttpServletRequest req,
			@PathVariable("no") String no, @RequestHeader("Authorization") String authorization) {

		Users users = null;
		try {
			// "Basic email:password" 에서 "email:pssword" 부분만 떼어낸다. @PathVariable("no") Integer no,
			String base64Credentials = authorization.split(" ")[1];

			// 떼어낸 "email:password" 부분은 base64 인코딩 된 부분이므로 디코딩하여 복원 
			String plainCredentials = new String(Base64.decodeBase64(base64Credentials));

			// 복원한 내용을 ":" 기준으로 나누어서 password 값을 뽑아내어 사용한다.
			String email = plainCredentials.split(":")[0];

			
			// 현재 로그인한 사용자 정보를 email 값을 이용하여 가져오기
			Users oldUsers = usersService.detailByEmail(email);

			String fakeName = oldUsers.getNo().toString();
			String fakeEmail = "emailNull";
			String password = "1234";

			// 데이터베이스에 저장할 users 객체 생성
			users = new Users(oldUsers.getNo(), fakeEmail, password, fakeName, null);

			

			// 수정할 정보를 데이터베이스에 전달
			String toDeleteFile = usersService.modify(users);

			// 기존 프로필 사진 삭제
			fileService.remove(req, toDeleteFile);

			// Base64 인코딩 전 평문
			String plainCredentials2 = users.getEmail() + ":" + password;

			// 평문을 Base64로 인코딩
			String base64Credentials2 = new String(Base64.encodeBase64(plainCredentials2.getBytes()));

			System.out.println("인코딩 한 값: " + base64Credentials2);
			Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("credentials", base64Credentials2);
			resultMap.put("usersNo", users.getNo());

			return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);

		} catch (Exception e) {
			System.out.println(e.getMessage());

			// 서버 오류가 발생했다는 응답코드를 클라이언트로 전달
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}







