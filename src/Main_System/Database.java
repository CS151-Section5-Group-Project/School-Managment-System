package Main_System;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public final class Database {
	private static HashMap<String, User> users = new HashMap<String, User>();
	private static ArrayList<Course> courses = new ArrayList<Course>();

	public static void initialize() {
		Administrator.addAdminAccount("Main", "Admin", "admin1", "myPassword");
	}
	
	public static void initialize2() {
		Administrator.addStudentAccount("Leo", "Truong", "leo.truong", "leopass123", LocalDate.of(2021, 8, 25));
		Administrator.addTeacherAccount("Mr", "Teacher", "teacher", "pass");
		Administrator.addAdminAccount("Brian", "Luong", "flame", "123");
	}
	
	public static ArrayList<Course> getCourses() {
		return courses;
	}
	
	public static HashMap<String, User> getUsers() {
		return users;
	}
	
	public static void addAccount(User user) {
		users.put(user.getUserName(), user);
	}
	
	public static void addCourse(Course course) {
		courses.add(course);
	}
}
