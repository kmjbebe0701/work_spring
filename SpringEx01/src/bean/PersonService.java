package bean;

public class PersonService {
	//2-1.필드만들기
	private Person person;
	
	
	// 2-2. person 객체의 getName() 호출하여 그 값을 출력하는 메소드 만들기

	public PersonService() {
	
	}
	
	public PersonService(Person person) {
		this.person = person;
	}


	public void setPerson(Person person) {
		this.person = person;
	}
	
	
	public void name() {
		System.out.println(person.getName());
	}
	

	
	
	
	

}
