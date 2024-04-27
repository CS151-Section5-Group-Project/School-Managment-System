package Main_System;

import java.util.Scanner;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

public class Administrator extends User {
	public enum Level {
		I,II
	}
	Level i;
	public Administrator() {
		super();
		i = null;
	}
	public Administrator(String firstName, String lastName, String userName, String password, int id) {
		super(firstName, lastName, userName, password, id);
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
	
	public static void addStudentAccount(String firstName, String lastName, String userName, String password, int id, LocalDate enrollmentDate) {
		Student student = new Student(firstName, lastName, userName, password, id, enrollmentDate);
		Database.addAccount(student);
	}
	
	public static void addTeacherAccount(String firstName, String lastName, String userName, String password, int id) {
		Teacher teacher = new Teacher(firstName, lastName, userName, password, id);
		Database.addAccount(teacher);
	}
	
	public static void addAdminAccount(String firstName, String lastName, String userName, String password, int id) {
		Administrator administrator = new Administrator(firstName, lastName, userName, password, id);
		Database.addAccount(administrator);
	}
	
	public static void addCourse(String name, Teacher teacher, String term, Classroom classroom, LocalDate startTime, LocalDate endTime, ArrayList<DayOfWeek> day) {
		Course course = new Course(name, teacher, term, classroom, startTime, endTime, day);
		Database.addCourse(course);
	}
	
	public static void addCourse(String name, Teacher teacher, String term) {
		Course course = new Course(name, teacher, term);
		Database.addCourse(course);
	}
	
	public void setLevel(Level i) {
		this.i = i;
	}
	
	public String toString() {
		return super.toString() + "\nLevel: " + i;
	}
	
	/*
	 * Leo: Allows administrator to create posts and 
	 * depending on their type (Announcement or Message)
	 * will either add the post to every user in the Accounts class
	 * or add the post to a specific user
	 */
	public void createPost() {	
		Scanner scan = new Scanner(System.in);
		System.out.println("SUBJECT:");
		String subject = scan.nextLine();
        System.out.println("MESSAGE:");
        String message = scan.nextLine();
        System.out.println("(Announcement or Message) TYPE:");
        String type = scan.nextLine();
		Post a = new Post(subject, message, type);
		
		if (type.equals("Announcement")) {
			for(User account: Database.users.values()) {
				account.getInbox().getReceived().add(a);
			}
			System.out.println("Post Created:\n" + a.toString() + "\n");
		} else if (type.equals("Message")){
			System.out.println("(username) TO:");
			String user = scan.nextLine();
			if (Database.users.containsKey(user)) {
				Database.users.get(user).getInbox().getReceived().add(a);
				System.out.println("Post Created:\n" + a.toString() + "\n");
			}else {
				System.out.println("User does not exist.");
			}
		}else {
			System.out.println("Invalid type");
		}
	}
}
