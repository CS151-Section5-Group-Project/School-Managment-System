package Main_System;

import java.util.ArrayList;

public class Accounts extends Administrator{

	public static ArrayList<Student> students = new ArrayList<Student>();
	public static ArrayList<Teacher> teachers = new ArrayList<Teacher>();
	public static ArrayList<Administrator> admins = new ArrayList<Administrator>();
	
	public static void addStudentAccount(String last, String first, String user, String pass, int id, double gpa, String year) {
		for (int i = 0; i < 100; i++) {
			Student u = new Student(last, first, user, pass, id, gpa, year);
			students.add(u);
		}
	}
	public static void addTeacherAccount(String last, String first, String user, String pass, int id, String courses) {
		for (int i = 0; i < 100; i++) {
			Teacher u = new Teacher(last, first, user, pass, id, courses);
			teachers.add(u);
		}
	}
	public static void addAdminAccount(String last, String first, String user, String pass, int id, Level l) {
		for (int i = 0; i < 100; i++) {
			Administrator u = new Administrator(last, first, user, pass, id, l);
			admins.add(u);
		}
	}
}
