package annotation02;

public class TestDrive {

	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		User jeong = new User("jeong", 33, "seoul");
		User kim = new User("kim", 15, null);
		User park = new User("park", 101, "busan");
		
		Validator validator = new Validator();
		validator.validate(jeong);
		validator.validate(kim);
		validator.validate(park);
		
		
	}

}
