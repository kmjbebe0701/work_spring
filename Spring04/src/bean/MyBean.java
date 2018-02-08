package bean;

public class MyBean {
	//필드
	private String greeting;

	//생성자
	public MyBean(String greeting) {
		this.greeting = greeting;
	}
	
	public void sayHello() {
		System.out.println(this.greeting + "~");
	}

	public String getGreeting() {
		return greeting;
	}

	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}
	
	
}
