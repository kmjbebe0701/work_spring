package di;

import java.util.ArrayList;
import java.util.List;

//Ora회사에 UserDao 인터페이스를 전달해서 Ora회사에서 각 메소드를 구현한것
public class OraUserDao implements UserDao{
	
	List<User> list = new ArrayList<>();

	@Override
	public void insert(User user) {
		list.add(user);
		
	}

	@Override
	public User select(int id) {
		for(User user : list) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}

}
