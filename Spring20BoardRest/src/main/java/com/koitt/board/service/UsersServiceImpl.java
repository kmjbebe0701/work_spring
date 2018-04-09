package com.koitt.board.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.koitt.board.dao.AuthorityDao;
import com.koitt.board.dao.UsersDao;
import com.koitt.board.model.Authority;
import com.koitt.board.model.AuthorityId;
import com.koitt.board.model.Board;
import com.koitt.board.model.Users;
import com.koitt.board.model.UsersException;

@Service
@Transactional
public class UsersServiceImpl implements UsersService {
	
	@Autowired
	private UsersDao usersDao;
	
	@Autowired
	private AuthorityDao authorityDao;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public List<Users> list() throws UsersException {
		return usersDao.selectAll();
	}

	@Override
	public Users detail(Integer no) throws UsersException {
		return usersDao.select(no);
	}

	@Override
	public void add(Users users) throws UsersException {
		// 입력받은 비밀번호를 암호화
		String encode = passwordEncoder.encode(users.getPassword());
		users.setPassword(encode);
		
		// 가입하려는 사용자의 권한을 입력 (일반사용자 권한: 20, "USER")
		Authority auth = new Authority(AuthorityId.USER.getAuthorityId(), AuthorityId.USER.name());
		
		// Set 컬렉션을 이용하여 users 객체에 권한을 담기
		Set<Authority> auths = new HashSet<>();
		auths.add(auth);
		users.setAuthorities(auths);
		
		// users 테이블에 사용자 정보 입력
		usersDao.insert(users);
		
		// users_authority 테이블에 사용자 권한 정보 입력
		usersDao.insertAuthority(users);
	}

	@Override
	public String remove(Integer no, String password) throws UsersException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String modify(Users users) throws UsersException {
		// 수정하기 전에 기존에 저장되어 있던 첨부파일 이름을 가져온다.
		Users item = usersDao.select(users.getNo());
		String filename = item.getAttachment();
		
		// 입력받은 비밀번호를 암호화
		users.setPassword(passwordEncoder.encode(users.getPassword()));
		
		// 암호화까지 마친 users 객체를 데이터베이스로 전달
		usersDao.update(users);

		// 기존에 저장되어 있던 첨부파일명을 컨트롤러로 전달
		return filename;
	}

	@Override
	public Users detailByEmail(String email) throws UsersException {
		return usersDao.selectByEmail(email);
	}

	@Override
	public Authority getAuthority(Integer id) throws UsersException {
		return authorityDao.select(id);
	}

	@Override
	public UserDetails getPrincipal() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		Object principal = auth.getPrincipal();
		if (principal instanceof UserDetails) {
			return (UserDetails) principal;
		}
		
		return null;
	}

	/*
	 * 아래와 같이 코드를 작성하면 스프링에서 로그아웃 처리를 한다.
	 */
	@Override
	public void logout(HttpServletRequest req, HttpServletResponse resp) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(req, resp, auth);
		}
	}

	@Override
	public boolean isPasswordMatched(String oldPassword) throws UsersException {
		// 현재 로그인한 사용자의 암호화된 비밀번호를 가져온다.
		String email = this.getPrincipal().getUsername();
		Users users = usersDao.selectByEmail(email);
		
		// 입력한 비밀번호와 기존 비밀번호를 비교하여 일치하면 true, 아니면 false 리턴
		return passwordEncoder.matches(oldPassword, users.getPassword());
	}

}








