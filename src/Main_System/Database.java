package Main_System;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public final class Database {
	public static HashMap<String, User> users;
	public static ArrayList<Course> courses;
	
	public Database() {
		users = new HashMap<String, User>();
		courses = new ArrayList<Course>();
	}
	
	public static void addAccount(User user) {
		users.put(user.getUserName(), user);
	}
	
	public static void addCourse(Course course) {
		courses.add(course);
	}
}
