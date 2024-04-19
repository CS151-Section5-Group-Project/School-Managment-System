package Main_System;

public class Main {
	public static void main(String[] args) {
		System.out.println("Directory:\n1. Administrator\n2. Teacher\n3. Student");
		Scanner scan = new Scanner(System.in);
		int role = scan.nextInt();
		if (role == 1) {
			System.out.println("Administrator Login:\nUsername:");
		} else if (role == 2) {
			System.out.println("Teacher Login:\nUsername:");
		} else if (role == 3) {
			System.out.println("Student Login:\nUsername:");
		} else {
			System.out.println("Not a valid option");
		}
	}
}
