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
import com.koitt.board.model.Users;
import com.koitt.board.model.UsersException;

@Service
@Transactional
public class UsersServiceImpl implements UsersService{
	
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
		// 비밀번호 암호화
		String encode = passwordEncoder.encode(users.getPassword());
		users.setPassword(encode);
		
		// 가입하려는 사용자의 권한을 입력 (일반 사용자 권한: 20, "USER")
		Authority auth = new Authority(AuthorityId.USER.getAuthorityId(), AuthorityId.USER.name());
		
		// Set 컬렉션을 이용하여 users 객체에 권한을 담기
		Set<Authority> auths = new HashSet<>();
		auths.add(auth);
		users.setAuthorities(auths);
		
		// users 테이블에 사용자 정보 입력
		usersDao.insert(users);
		
		// 방금 등록한 users의 사용자 번호를 가져온다
		Integer no = usersDao.selextLastInsertId();
		
		// 가져온 사용자의 번호를 users 객체에 담는다
		users.setNo(no);
		
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Users detailByEmail(String email) throws UsersException {
		
		return usersDao.selectByEmail(email);
	}

	@Override
	public Authority getAuthoriy(Integer id) throws UsersException {
		
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
	 * 아래와 같이 코드를 작성하면 스프링에서 로그아웃처리한다.
	 */
	@Override
	public void logout(HttpServletRequest req, HttpServletResponse resp) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(auth != null) {
			new SecurityContextLogoutHandler().logout(req, resp, auth);
		}
		
	}

}
