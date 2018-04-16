package com.koitt.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.koitt.board.model.Board;
import com.koitt.board.model.BoardException;
import com.koitt.board.model.FileException;
import com.koitt.board.service.BoardService;
import com.koitt.board.service.FileService;
import com.koitt.board.service.UsersService;

@RestController
@RequestMapping("/rest")
public class BoardRestController {

	@Autowired
	private BoardService boardService;

	@Autowired
	private FileService fileService;

	@Autowired
	private UsersService usersService;

	@RequestMapping(value="/board", method=RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> list() {
		List<Board> list = null;

		try {
			// service를 이용하여 글 목록 가져오기
			list = boardService.list();

			if (list != null && list.size() > 0) {
				Map<String, Object> resultMap = new HashMap<>();
				resultMap.put("boardList", list);

				return new ResponseEntity<>(resultMap, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

		} catch (BoardException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// 글 상세 화면
	@RequestMapping(value="/board/{no}", method=RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> detail(HttpServletRequest request, 
			@PathVariable("no") String no) {

		Board board = null;
		String filename = null;
		String downloadPath = null;
		String imgPath = null;

		try {

			if (no != null) {
				board = boardService.detail(no);

				if (board != null) {
					filename = board.getAttachment();
					downloadPath = fileService.getDownloadPath(request, filename);
					imgPath = fileService.getImgPath(request, filename);

					Map<String, Object> resultMap = new HashMap<>();
					resultMap.put("item", board);
					resultMap.put("src", downloadPath);
					resultMap.put("imgPath", imgPath);

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

	// 글 추가
	@RequestMapping(value="/board", method=RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> add(HttpServletRequest request,
			Integer userNo,
			String title,
			String content,
			@RequestParam("attachment") MultipartFile attachment) {

		Board board = new Board();
		board.setUserNo(userNo);
		board.setTitle(title);
		board.setContent(content);

		try {
			// 파일 서비스로부터 전달받은 파일명을 VO 객체에 담는다.
			String filename = fileService.add(request, attachment);
			board.setAttachment(filename);

			// 모든 내용을 저장한 VO 객체를 데이터베이스로 전달
			boardService.add(board);

			return new ResponseEntity<>(HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*
	 *  글 수정
	 *  https://blog.outsider.ne.kr/1001 참고
	 *  1. multipart/form-data를 이용하여 수정할 때에는 POST로 전송해야 한다.
	 *  2. 만약 PUT을 이용해서 전송하고 싶다면,
	 *  	input type file에 대해서는 따로 POST로 전송하고,
	 *  	나머지 일반 정보는 PUT으로 따로 전송하면 된다. 
	 */
	@RequestMapping(value="/board/{no}", method=RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> modify(HttpServletRequest request,
			@PathVariable("no") Integer no,
			String title,
			String content,
			@RequestParam("attachment") MultipartFile attachment,
			String password,
			String email) {

		try {
			// 작성자의 비밀번호와 입력한 비밀번호 비교
			boolean isMatched = usersService.isPasswordMatched(email, password);
			if (!isMatched) {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}

			Board board = new Board();
			board.setNo(no);
			board.setTitle(title);
			board.setContent(content);

			// 새롭게 수정할 파일을 서버에 저장
			String filename = fileService.add(request, attachment);
			board.setAttachment(filename);

			// 기존 파일명을 가져온다.
			String toDeleteFilename = boardService.modify(board);

			// 기존에 있던 파일을 삭제
			fileService.remove(request, toDeleteFilename);

			return new ResponseEntity<>(HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*
	 *  글 삭제
	 *  @RequestHeader("Authorization")을 사용하면
	 *  "Basic email:password" 정보를 가져올 수 있다.
	 */
	@RequestMapping(value="/board/{no}", method=RequestMethod.DELETE)
	public ResponseEntity<Map<String, Object>> remove(
			@PathVariable("no") String no,
			@RequestHeader("Authorization") String authorization,
			HttpServletRequest request) {
		
		// "Basic email:password" 에서 "email:password" 부분만 떼어낸다.
		String base64Credentials = authorization.split(" ")[1];
		
		// 떼어낸 "email:password" 부분은 base64 인코딩 된 부분이므로 디코딩하여 복원한다.
		String plainCredentials = new String(Base64.decodeBase64(base64Credentials));
		
		// 복원한 내용을 ":" 기준으로 나누어서 password 값을 뽑아내어 사용한다.
		String email = plainCredentials.split(":")[0];
		String password = plainCredentials.split(":")[1];
		
		try {
			// 현재 글의 작성자(이메일) 값을 가져온다.
			Board item = boardService.detail(no);
			String writerEmail = item.getUsers().getEmail();
			
			// 작성자의 이메일과 로그인한 사용자의 이메일을 비교한다.
			boolean isEmailMatched = email.equals(writerEmail);

			// 작성자의 비밀번호와 로그인한 사용자의 비밀번호를 비교한다.
			boolean isMatched = usersService.isPasswordMatched(writerEmail, password);
			if (!(isEmailMatched && isMatched)) {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}

			if (no != null) {
				String toDeleteFilename = boardService.remove(no);
				fileService.remove(request, toDeleteFilename);
				
				return new ResponseEntity<>(HttpStatus.OK);
			}
			else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
















