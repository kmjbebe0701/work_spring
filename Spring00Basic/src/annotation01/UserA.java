package annotation01;

public class UserA {
	
	@ObjectId
	private String ssn;
	private String name;
	
	public UserA(String ssn, String name) {
		this.ssn = ssn;
		this.name = name;
	}

}
