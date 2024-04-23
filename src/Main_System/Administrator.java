package Main_System;

import java.util.Scanner;

public class Administrator extends User {
	public enum Level {
		I,II
	}
	Level i;
	public Administrator() {
		super();
		i = null;
	}
	public Administrator(String last, String first, String user, String pass, int id, Level i) {
		super(last, first, user, pass, id);
		this.i = i;
	}
	public String levelNames() {
		switch(i) {
		case I:
			return "Moderator";
		case II:
			return "Administrator";
		default:
			return " ";
		}
	}
	public void setLevel(Level i) {
		this.i = i;
	}
	public String toString() {
		return super.toString() + "\nLevel: " + i;
	}
	
	public void createPost() {	
		Scanner scan = new Scanner(System.in);
		System.out.println("SUBJECT:");
		String subject = scan.nextLine();
        System.out.println("MESSAGE:");
        String message = scan.nextLine();
        System.out.println("TYPE:");
        String type = scan.nextLine();
		Post a = new Post(subject, message, type);
		getInbox().add(a);
		System.out.println("Post Created:\n" + a.toString() + "\n");
	}
}
