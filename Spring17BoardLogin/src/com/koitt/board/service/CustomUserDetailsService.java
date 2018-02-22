package com.koitt.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.koitt.board.model.Users;
import com.koitt.board.model.UsersException;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UsersService service;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		try {
			Users users = service.detailByEmail(email);
			if (users == null) {
				throw new UsernameNotFoundException("해당 사용자를 찾지 못했습니다.");
			}
			
			/*
			 * username
			 * password
			 * enabled
			 * accountNonExpired
			 * credentialsNonExpired,
			 * accountNonLocked
			 * authorities
			 */
			return new User(users.getEmail(), users.getPassword(), true, true, true, true, null);
			
		} catch (UsersException e) {
			System.out.println(e.getMessage());
		}
		
		
		return null;
	}
	
	private List<GrantedAuthority> getGrantedAuthorities (Users users) {
		return null;
	}

}
