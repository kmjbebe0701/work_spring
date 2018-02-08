package bean;

public class PersonImpl implements Person{

	// 1-1.필드만들기
	private String name;
	
	// 1-2. 기본생성자만들기
	public PersonImpl() {
	}
	
	// 1-3.필드 초기화 생성자 만들기 
	public PersonImpl(String name) {
		this.name = name;
	}
	
	//1-4.name필드 getter
	
	@Override
	public String getName() {
		
		return this.name;
	}
	


}
