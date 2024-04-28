package Main_System;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public final class Database {
	private static HashMap<String, User> users = new HashMap<String, User>();
	private static HashMap<String, Classroom> classrooms = new HashMap<String, Classroom>();
	private static ArrayList<Course> courses = new ArrayList<Course>();
	public static Scanner scanner = new Scanner(System.in);

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
	
	public static HashMap<String, Classroom> getClassrooms() {
		return classrooms;
	}
	
	public static boolean containUser(String username) {
		if (users.containsKey(username)) {
			return true;
		}
		
		return false;
	}
	
	public static User getUser(String username) {
		if (containUser(username)) { // user found
			return users.get(username);
		}
		
		return null;
	}
	
	public static boolean containClassroom(String classroomName) {
		if (classrooms.containsKey(classroomName)) {
			return true;
		}
		
		return false;
	}
	
	public static Classroom getClassroom(String classroomName) {
		if (containClassroom(classroomName)) { // classroom found
			return classrooms.get(classroomName);
		}
		
		return null;
	}
	
	public static boolean addAccount(User user) {
		if (containUser(user.getUserName())) { // user found
			System.out.println("User already added.");
			return false;
		}
		
		users.put(user.getUserName(), user);
		return true;
	}
	
	public static boolean addCourse(Course course) {
		for (Course courseFromDatabase: Database.getCourses()) {
			if (course.equals(courseFromDatabase)) { // course found
				System.out.println("Courses already exist.");
				return false;
			}
		}
		
		courses.add(course);
		return true;
	}
	
	public static boolean addClassroom(Classroom classroom) {
		if (containClassroom(classroom.getName())) { // classroom found
			System.out.println("Classroom already added.");
			return false;
		}
		
		classrooms.put(classroom.getName(), classroom);
		return true;
	}
}
