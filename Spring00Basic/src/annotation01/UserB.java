package annotation01;


public class UserB {
	
	@ObjectId
	private String id;
	private String lastname;
	
	public UserB(String id, String lastname) {
		this.id = id;
		this.lastname = lastname;
	}
	
	

}
