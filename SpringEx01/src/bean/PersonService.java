package bean;

public class PersonService {
	//2-1.필드만들기
	private Person person;
	
	
	// 2-2. person 객체의 getName() 호출하여 그 값을 출력하는 메소드 만들기

	/*
	 * 생성자 (autowire의 constructor를 이용하기 위해)
	 * 생성자 중 파라미터 타입이 같은 생성자를 찾는다
	 */
	public PersonService() {
	
	}
	
	public PersonService(Person person) {
		this.person = person;
	}

	/*
	 * person필드에 대한 setter (autowire의 byName을 이용하기 위해)
	 * byName : setter중 이름이 같은 것을 찾는다
	 * byType : setter중 파라미터 타입이 같은걸 찾는다
	 */

	public void setPerson(Person person) {
		this.person = person;
	}
	
	
	public void name() {
		System.out.println(person.getName());
	}
	

	
	
	
	

}
