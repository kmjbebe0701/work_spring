package test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;


public class TestDrive01 {
	
	public static void main(String[] args) {
		
		// 1. 스프링 설정 파일 불러오기
		ApplicationContext context = new GenericXmlApplicationContext("/config/config.xml");
		
		// 1-2. JDBC Template 객체를 이용하여 SQL query문 실행
		// 클래스의 타입을 이용해서 빈 객체를 가져온 경우
		JdbcTemplate template = context.getBean(JdbcTemplate.class);
		
		// SQL문 만들기: emp 테이블의 행의 개수를 가져오는 SQL문
		String sql = "SELECT COUNT(*) FROM emp";
		//int count = template.queryForIn(sql);			//Spring 3버전에서 사용하던 방식
		//SQL 실행: 파라미터 첫번째는 sql문, 두번째는 결과의 타입
		int count = template.queryForObject(sql, Integer.class);		//SQL 실행: 파라미터 첫번째는 sql문, 두번째는 결과의 타입
		System.out.println("1: " + count);
		
		
		// 2-1. DataSource 객체를 이용하여 SQL query실행
		DataSource dataSource = context.getBean(DataSource.class);
		try {
			Connection conn = dataSource.getConnection();
			
			//Connection의 정체를 알아보기 위해 출력
			System.out.println("2: " + conn.getClass().getName());
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				System.out.println("3: " + rs.getInt(1));
			}
			
			rs.close();
			stmt.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// 3. 
		sql = "SELECT COUNT(*) FROM emp WHERE deptno = ?";
		count = template.queryForObject(sql, Integer.class, 20);
		System.out.println("4: " + count);
		
		
		// 4. (마지막 숫자입력시 String타입으로도 입력가능)
		sql = "SELECT ename FROM emp WHERE empno = ?";
		String name = template.queryForObject(sql, String.class, 7900);
		System.out.println("5: " + name);
		
		// 5. 
		sql = "SELECT hiredate FROM emp WHERE empno = ? ";
		Date hiredate = template.queryForObject(sql, Date.class, 7900);
		System.out.println("6: " + hiredate);
		
		// 6. 
		sql = "SELECT * FROM emp WHERE empno = ? ";
		Map<String, Object> emp = template.queryForMap(sql, 7900);
		for(String key: emp.keySet()) {
			System.out.println(key + ": " + emp.get(key));
		}
		System.out.println();
		
		// 7-1. SQL문 작성
		sql = "SELECT * FROM emp WHERE deptno = ? ";
		
		/*
		 *  7-2. 여러 행을 출력하는 SQL문이기 때문에 queryForList 메소드를 사용한다
		 *  첫번째 파라미터: SQL문
		 *  두번째 파라미터: 물음표에 해당하는 값
		 */
		List<Map<String, Object>> empList = template.queryForList(sql, 20);
		
		/*
		 *  7-3. Map은 각 행을 의미, 각 행을 모두 담고 있어야 하기 때문에 Map타입의 List로 사용
		 *  첫번째 for문은 List에서 각 행 하나를 끄집어 낸 것이다
		 */
		for(Map<String, Object> item : empList) {
			
			// 7-4. 두번째 for문은 6번 예제와 같이 key값을 사용하여 각 행의 Map에서 value값을 가져온다
			for(String key: item.keySet()) {
				System.out.print(key + ": " + item.get(key) + ", ");
			}
			System.out.println();
		}
		System.out.println();
	
		
		// 8. 
		sql = "SELECT sal FROM emp WHERE deptno = ? ";
		List<Integer> salary = template.queryForList(sql, Integer.class, 20);
		for(int item: salary) {
			System.out.println(item);
		}
		
	}
}
