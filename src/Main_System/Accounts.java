package Main_System;

import java.util.HashMap;

public class Accounts extends Administrator{
	
	public static HashMap<String, User> users = new HashMap<String, User>();
	
	public static void addStudentAccount(String last, String first, String user, 
			String pass, int id, double gpa, String year, Boolean gradStatus) {
		Student u = new Student(last, first, user, pass, id, gpa, year, gradStatus);
		users.put(user, u);
	}
	public static void addTeacherAccount(String last, String first, String user, String pass, int id, String courses) {
		Teacher u = new Teacher(last, first, user, pass, id, courses);
		users.put(user, u);
	}
	public static void addAdminAccount(String last, String first, String user, String pass, int id, Level l) {
		Administrator u = new Administrator(last, first, user, pass, id, l);
		users.put(user, u);
	}
	
	/*
	 * Leo: Initializes accounts in the users HashMap
	 */
	public static void initializeAccounts() {
		addStudentAccount("Truong", "Leo", "leo.truong", "leopass123", 174, 3.1, "2025", false);
		addTeacherAccount("Teacher", "Mr", "teacher.user", "teachpass", 82, "MATH");
		addAdminAccount("Luong", "Brian", "flame", "123", 1, Administrator.Level.I);
	}
}
