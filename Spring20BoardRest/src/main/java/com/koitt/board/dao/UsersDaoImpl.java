package com.koitt.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koitt.board.model.Users;
import com.koitt.board.model.UsersException;

@Repository
public class UsersDaoImpl implements UsersDao {
	
	private static final String MAPPER_NS = Users.class.getName();
	
	@Autowired
	private SqlSession session;
	
	public UsersDaoImpl() {}

	@Override
	public List<Users> selectAll() throws UsersException {
		List<Users> list = null;
		
		try {
			list = session.selectList(MAPPER_NS + ".select-all-users"); 
			
		} catch (Exception e) {
			throw new UsersException(e.getMessage());
		}
		
		return list;
	}

	@Override
	public Users select(Integer no) throws UsersException {
		Users users = null;
		
		try {
			users = session.selectOne(MAPPER_NS + ".select-users", no);
			
		} catch (Exception e) {
			throw new UsersException(e.getMessage());
		}
		
		return users;
	}

	@Override
	public void insert(Users users) throws UsersException {
		try {
			session.insert(MAPPER_NS + ".insert-users", users);
			
		} catch (Exception e) {
			throw new UsersException(e.getMessage());
		}
	}

	@Override
	public void delete(Integer no) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUserTypes(Integer no) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Users users) throws UsersException {
		try {
			session.update(MAPPER_NS + ".update-users", users);
			
		} catch (Exception e) {
			throw new UsersException(e.getMessage());
		}
	}

	@Override
	public Users selectByEmail(String email) throws UsersException {
		Users users = null;
		
		try {
			users = session.selectOne(MAPPER_NS + ".select-users-by-email", email);
			
		} catch (Exception e) {
			throw new UsersException(e.getMessage());
		}
		
		return users;
	}

	@Override
	public void insertAuthority(Users users) throws UsersException {
		try {
			session.insert(MAPPER_NS + ".insert-authority", users);
			
		} catch (Exception e) {
			throw new UsersException(e.getMessage());
		}
	}

	@Override
	public Integer selectLastInsertId() throws UsersException {
		Integer lastInsertId = null;
		try {
			lastInsertId = session.selectOne(MAPPER_NS + ".select-last-insert-id");
			
		} catch (Exception e) {
			throw new UsersException(e.getMessage());
		}
		
		return lastInsertId;
	}

	@Override
	public void deleteAll() throws UsersException {
		try {
			session.delete(MAPPER_NS + ".delete-all-users");
			
		} catch (Exception e) {
			throw new UsersException(e.getMessage());
		}
	}

	@Override
	public Integer getCount() throws UsersException {
		Integer count = null;
		
		try {
			count = session.selectOne(MAPPER_NS + ".count-users");
			
		} catch (Exception e) {
			throw new UsersException(e.getMessage());
		}
		
		return count;
	}

	@Override
	public void deleteAllUsersAuthority() throws UsersException {
		try {
			session.delete(MAPPER_NS + ".delete-all-users-authority");
			
		} catch (Exception e) {
			throw new UsersException(e.getMessage());
		}
	}

	@Override
	public Integer getCountUsersAuthority() throws UsersException {
		Integer count = null;
		
		try {
			count = session.selectOne(MAPPER_NS + ".count-users-authority");
			
		} catch (Exception e) {
			throw new UsersException(e.getMessage());
		}
		
		return count;
	}
}







