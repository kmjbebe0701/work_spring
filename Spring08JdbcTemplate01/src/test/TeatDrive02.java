package test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import model.Department;
import model.Employee;

public class TeatDrive02 {

	public static void main(String[] args) {
		
		ApplicationContext context = new GenericXmlApplicationContext("/config/config.xml");
		JdbcTemplate temlate = context.getBean(JdbcTemplate.class);
		
		// 1. 
		String sql = "SELECT * FROM emp WHERE empno = ?";
		Employee emp = temlate.queryForObject(sql, new MyRowMapper(), 7839);
		System.out.println(emp);
		System.out.println();
		
		// 2.
		sql ="SELECT * FROM emp WHERE deptno = ?";
		List<Employee> empList = temlate.query(sql, new MyRowMapper(), 10);
		for(Employee item : empList) {
			System.out.println(item);
		}
		System.out.println();
			
		
		// 3. 
		sql = "SELECT * FROM emp WHERE empno = ?";
		emp = temlate.queryForObject(sql, new BeanPropertyRowMapper<Employee>(Employee.class), 7839);
		
		System.out.println(emp);
		System.out.println();
		
		
		// 4. 
		sql = "SELECT * FROM emp WHERE deptno = ?";
		empList = temlate.query(sql, new BeanPropertyRowMapper<Employee>(Employee.class), 10);
		for(Employee item : empList) {
			System.out.println(item);
		}
		System.out.println();
		
		// 5. 테이블 2개를 조인(join)한 결과를 객체에 어떻게 담아야 하는지에 대한 예제
		StringBuilder sql02 = new StringBuilder();
		sql02.append("SELECT * FROM dept d, emp e WHERE d.deptno = e.deptno ");
		sql02.append("AND d.deptno = ? ");
		
		/*
		 * ResultSetExtractor제네릭에 들어갈 타입은 join 할 테이블 중 부모 엔티티에 해당하는 클래스 타입을 넣는다.
		 */
		//익명 클래스: 65-95라인까지
		ResultSetExtractor<Department> extractor = new ResultSetExtractor<Department>() {

			@Override
			public Department extractData(ResultSet rs) throws SQLException, DataAccessException {
				Department dept = null;
				
				if(rs.next()) {
					dept = new Department();
					dept.setDeptno(rs.getInt("deptno"));
					dept.setDname(rs.getString("dname"));
					dept.setLoc(rs.getString("loc"));
					
					do {
						Employee emp = new Employee();
						emp.setComm(rs.getInt("comm"));
						emp.setDeptno(rs.getInt("deptno"));
						emp.setEmpno(rs.getInt("empno"));
						emp.setEname(rs.getString("ename"));
						emp.setHireDate(rs.getDate("hiredate"));
						emp.setJob(rs.getString("job"));
						emp.setMgr(rs.getInt("mgr"));
						emp.setSal(rs.getInt("sal"));
						dept.getEmployeeList().add(emp);
						
					}while (rs.next());
					
				}
				
				
				
				return dept;
			}
		};
		
		Department dept = temlate.query(sql02.toString(), extractor, 10);
		System.out.println(dept);
		System.out.println();
		
		
	}

}

/*
 * RowMapper: SQL문 실행 결과를 VO객체와 연결시켜 VO 객체에 결과를 담는 것을 도와주는 인터페이스
 */
class MyRowMapper implements RowMapper<Employee> {

	@Override
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		// 1. VO 객체 생성
		Employee emp = new Employee();
		
		// 2. 생성한 객체에 ResultSet을 이용하여 SQL문 결과를 저장한다
		emp.setComm(rs.getInt("comm"));
		emp.setDeptno(rs.getInt("deptno"));
		emp.setEmpno(rs.getInt("empno"));
		emp.setEname(rs.getString("ename"));
		emp.setHireDate(rs.getDate("hiredate"));
		emp.setJob(rs.getString("job"));
		emp.setMgr(rs.getInt("mgr"));
		emp.setSal(rs.getInt("sal"));
		
		// 3. 저장한 객체를 리턴한다
		return emp;
	}
	
}


