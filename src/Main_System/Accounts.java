package Main_System;

import java.time.LocalDate;
import java.util.HashMap;

public class Accounts extends Administrator{
	public static void addStudentAccount(String firstName, String lastName, String userName, String password, int id, LocalDate enrollmentDate) {
		Student student = new Student(firstName, lastName, userName, password, id, enrollmentDate);
		//users.put(userName, student);
		
		Database.addAccount(student);
	}
	
	public static void addTeacherAccount(String firstName, String lastName, String userName, String password, int id) {
		Teacher teacher = new Teacher(firstName, lastName, userName, password, id);
		//users.put(userName, teacher);
		
		Database.addAccount(teacher);
	}
	
	public static void addAdminAccount(String firstName, String lastName, String userName, String password, int id) {
		Administrator administrator = new Administrator(firstName, lastName, userName, password, id);
		//users.put(userName, administrator);
		
		Database.addAccount(administrator);
	}
	
	/*
	 * Leo: Initializes accounts in the users HashMap
	 */
	public static void initializeAccounts() {
		addStudentAccount("Leo", "Truong", "leo.truong", "leopass123", 174, LocalDate.of(2021, 8, 25));
		addTeacherAccount("Mr", "Teacher", "teacher.user", "teachpass", 82);
		addAdminAccount("Brian", "Luong", "flame", "123", 1);
		
//		addStudentAccount("Truong", "Leo", "leo.truong", "leopass123", 174, 3.1, "2025", false);
//		addTeacherAccount("Teacher", "Mr", "teacher.user", "teachpass", 82, "MATH");
//		addAdminAccount("Luong", "Brian", "flame", "123", 1, Administrator.Level.I);
	}
}
