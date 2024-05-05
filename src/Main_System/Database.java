package Main_System;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public final class Database {
	private static HashMap<String, User> users = new HashMap<String, User>();
	private static HashMap<String, Classroom> classrooms = new HashMap<String, Classroom>();
	static ArrayList<Course> courses = new ArrayList<Course>();
	public static Scanner scanner = new Scanner(System.in);

	public static void initialize() {
		Administrator.addAdminAccount("Main", "Admin", "admin1", "myPassword");
	}
	
	public static void initialize2() {
		Student student1 = new Student("Andy", "Test", "Andy.Test", "password", LocalDate.of(2021, 8, 26));
		new Student("Leo", "Test", "Leo.Test", "password", LocalDate.of(2022, 8, 27));
		new Student("Brian", "Test", "Brian.Test", "password", LocalDate.of(2023, 8, 28));
		new Student("The", "Student", "student", "study", LocalDate.of(2003, 8, 15));
		
		Teacher teacher1 = new Teacher("Daniel", "Smith", "Daniel.Smith", "password");
		Teacher teacher2 = new Teacher("Richard", "Johnson", "Richard.Johnson", "password");
		Teacher teacher3 = new Teacher("Best", "Teacher", "Best.Teacher", "password");
		Teacher teacher4 = new Teacher("Mr", "Teacher", "teacher", "teach");
		
		new Administrator("Admin", "1", "Admin.1", "password");
		new Administrator("Admin", "2", "Admin.2", "password");
		new Administrator("Admin", "3", "Admin.3", "password");
		new Administrator("The", "Admin", "admin", "admin");
		
		Classroom classroom1 = new Classroom("BBC100", 30);
		Classroom classroom2 = new Classroom("BBC101", 30);
		Classroom classroom3 = new Classroom("SCI100", 30);
		Classroom classroom4 = new Classroom("SCI101", 30);
		
		ArrayList<DayOfWeek> MW = new ArrayList<DayOfWeek>();
		MW.add(DayOfWeek.MONDAY);
		MW.add(DayOfWeek.WEDNESDAY);
		
		ArrayList<DayOfWeek> TTh = new ArrayList<DayOfWeek>();
		TTh.add(DayOfWeek.TUESDAY);
		TTh.add(DayOfWeek.THURSDAY);
		
		ArrayList<DayOfWeek> F = new ArrayList<DayOfWeek>();
		F.add(DayOfWeek.FRIDAY);
		
		ArrayList<DayOfWeek> MTWThF = new ArrayList<DayOfWeek>();
		MTWThF.add(DayOfWeek.MONDAY);
		MTWThF.add(DayOfWeek.TUESDAY);
		MTWThF.add(DayOfWeek.WEDNESDAY);
		MTWThF.add(DayOfWeek.THURSDAY);
		MTWThF.add(DayOfWeek.FRIDAY);
		
		ArrayList<DayOfWeek> W = new ArrayList<DayOfWeek>();
		W.add(DayOfWeek.WEDNESDAY);
		
		Course course1 = new Course("CS123", teacher1, "SP2024", classroom1, LocalTime.of(18, 0), LocalTime.of(19, 15), MW);
		Course course2 = new Course("CS123LAB", teacher2, "FA2024", classroom2, LocalTime.of(19, 30), LocalTime.of(20, 45), F);
		new Course("CMPE131", teacher3, "SUM2024", classroom3, LocalTime.of(21, 0), LocalTime.of(22, 15), TTh);
		new Course("CS133", teacher4, "WIN2024", classroom4, LocalTime.of(12, 0), LocalTime.of(13, 15), MTWThF);
		new Course("CMPE133", teacher4, "SP2022", classroom1, LocalTime.of(14, 30), LocalTime.of(15, 45), W);
		
		course1.addStudent(student1);
		
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
	
	public static boolean containCourse(Course course) {
		for (Course courseDatabase: courses) {
			if (courseDatabase.toString().equals(course.toString())) {
				return true;
			}
		}
		
		return false;
	}
	
	public static boolean containUser(String username) {
		if (users.containsKey(username)) {
			return true;
		}
		
		System.out.println("User not found in database.");
		return false;
	}
	
	public static User getUser(String username) {
		if (containUser(username)) { // user found
			return users.get(username);
		}
		
		System.out.println("Cannot retreive user.");
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
	
	public static boolean addUser(User user) {
		if (containUser(user.getUserName())) { // user found
			System.out.println("User already added.");
			return false;
		}
		
		System.out.println("Account added: \n" + user.toString() + "\n");
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
		
		System.out.println("Course added: \n" + course.toString() + "\n");
		courses.add(course);
		return true;
	}
	
	public static boolean addClassroom(Classroom classroom) {
		if (containClassroom(classroom.getName())) { // classroom found
			System.out.println("Classroom already added.");
			return false;
		}
		
		System.out.println("Classroom added: \n" + classroom.toString() + "\n");
		classrooms.put(classroom.getName(), classroom);
		return true;
	}
}
