package Main_System;

import java.util.Scanner;

public class Student extends User {
	private double GPA;
	private String yearClass;
	private Boolean gradStatus;
	public Student() {
		super();
		GPA = 0.0;
		yearClass = " ";
		gradStatus = false;
	}
	public Student(String last, String first, String user, String pass, int id, double Gpa, String Class, Boolean gradStatus) {
		super(last, first, user, pass, id);
		GPA = Gpa;
		yearClass = Class;
		this.gradStatus = gradStatus;
	}
	public double getGPA() {
		return GPA;
	}
	public String getYearClass() {
		return yearClass;
	}
	public Boolean getGradStatus() {
		return gradStatus;
	}
	public void setGradStatus(Boolean gradStatus) {
		this.gradStatus = gradStatus;
	}
	public void setGPA(double gpa) {
		GPA = gpa;
	}
	public void setYearClass(String year) {
		yearClass = year;
	}
	public String toString() {
		return super.toString() + "\nGPA: " + GPA + "\nClass: " + yearClass;
	}
	
	/*
	 * Leo: Allows student to create posts of type 
	 * "Message" which would allow them send a message
	 * to any user within the database.
	 */
	public void createPost() {
		Scanner scan = new Scanner(System.in);
		System.out.println("SUBJECT:");
		String subject = scan.nextLine();
        System.out.println("MESSAGE:");
        String message = scan.nextLine();
        Post msg = new Post(subject, message, "Message");
        
        System.out.println("(username) TO:");
		String user = scan.nextLine();
		if (Accounts.users.containsKey(user)) {
			Accounts.users.get(user).getInbox().add(msg);
			System.out.println("Post Created:\n" + msg.toString() + "\n");
		}else {
			System.out.println("User does not exist.");
		}
	}
	
	/*
	 * Leo: Checks the graduation status of the student. 
	 * If gradStatus is true, the student will see that 
	 * they've applied for graduation, otherwise it's pending.
	 */
	public void checkGradStatus() {
		System.out.print("GRADUATION STATUS: ");
		if(gradStatus) {
			System.out.println("Applied for Graduation");
		}else {
			System.out.println("Need to Finish Pending Work");
		}
	}
}
