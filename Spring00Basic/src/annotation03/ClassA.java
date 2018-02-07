package annotation03;

public class ClassA {
	
	@Call(seq = 2)
	public void second() {
		System.out.println("second 호출...");
	}
	
	@Call(seq = 1)
	public void first() {
		System.out.println("first 호출...");
	}
	
	@Call(seq = 3)
	public void third() {
		System.out.println("third 호출...");
	}

}
