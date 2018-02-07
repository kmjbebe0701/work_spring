package di;

public interface UserDao {
	
	//사용자 데이터 베이스에 저장
	void insert(User user);
	
	//데이터 베이스로부터 id 값을 이용해 사용자 불러오기
	User select(int id);

}
