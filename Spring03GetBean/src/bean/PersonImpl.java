package bean;

public class PersonImpl implements Person{

	private String greeting = "안녕하세요?";
	private String name = "현빈";
	private int age = 36;
	
	//생성자 오버로딩
	public PersonImpl() {
		System.out.println("기본 생성자 호출...");
	}
	
	public PersonImpl(String greeting) {
		this.greeting = greeting;
	}
	
	public PersonImpl(int age) {
		super();
		this.age = age;
	}
	
	public PersonImpl(String greeting, String name, int age) {
		super();
		this.greeting = greeting;
		this.name = name;
		this.age = age;
	}

	//메소드 오버 라이딩(인터페이스 상속)
	@Override
	public void sayHello() {
		System.out.println(this.name + "(" + age + "): " + this.greeting);
		
	}

}
