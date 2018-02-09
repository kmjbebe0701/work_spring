package test;

public class DotDotDot {
	
	public void print(Object... args) {
		for (int i = 0; i < args.length; i++) {
			System.out.print(args[i]);
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		DotDotDot dot = new DotDotDot();
		
		dot.print("A");
		dot.print("1","2");
		dot.print(1, 2, 3, 4, 5);
		
		
	}
}
