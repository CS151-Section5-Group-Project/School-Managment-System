package Main_System;

import java.util.*;

public class Main {
	public static void main(String[] args) {
		Accounts.addAdminAccount("Luong", "Brian", "flame4547", "123", 1, Administrator.Level.I);
		Accounts.addTeacherAccount("Luong", "Brian", "flame4547", "123", 1, "Math");
		Accounts.addStudentAccount("Luong", "Brian", "flame4547", "123", 1, 3.5, "Sophomore");
		System.out.println("Directory:\n1. Administrator\n2. Teacher\n3. Student");
		Scanner scan = new Scanner(System.in);
		int role = scan.nextInt();
		if (role == 1) {
			Iterator<Administrator> iterator = Accounts.admins.iterator();
			System.out.println("Administrator Login:\nUsername:");
			String user = scan.next();
			while (iterator.hasNext()) {
				Administrator admin = iterator.next();
				if (admin.getUserName().equals(user)) {
					System.out.println("\nPassword:");
					break;
				} else {
					System.out.println("\nAccount does not exist");
					break;
				}
			}
			String pass = scan.next();
			while (iterator.hasNext()) {
				Administrator admin = iterator.next();
				if (admin.getPassword().equals(pass)) {
					System.out.println("\nLogin Complete");
					break;
				} else {
					System.out.println("\nWrong Password");
				}	
			}
		} else if (role == 2) {
			Iterator<Teacher> iterator = Accounts.teachers.iterator();
			System.out.println("Teacher Login:\nUsername:");
			String user = scan.next();
			while (iterator.hasNext()) {
				Teacher teacher = iterator.next();
				if (teacher.getUserName().equals(user)) {
					System.out.println("\nPassword:");
					break;
				} else {
					System.out.println("\nAccount does not exist");
					break;
				}
			}
			String pass = scan.next();
			while (iterator.hasNext()) {
				Teacher teacher = iterator.next();
				if (teacher.getPassword().equals(pass)) {
					System.out.println("\nLogin Complete");
					break;
				} else {
					System.out.println("\nWrong Password");
				}	
			}
		} else if (role == 3) {
			Iterator<Student> iterator = Accounts.students.iterator();
			System.out.println("Student Login:\nUsername:");
			String user = scan.next();
			while (iterator.hasNext()) {
				Student student = iterator.next();
				if (student.getUserName().equals(user)) {
					System.out.println("\nPassword:");
					break;
				} else {
					System.out.println("\nAccount does not exist");
					break;
				}
			}
			String pass = scan.next();
			while (iterator.hasNext()) {
				Student student = iterator.next();
				if (student.getPassword().equals(pass)) {
					System.out.println("\nLogin Complete");
					break;
				} else {
					System.out.println("\nWrong Password");
				}	
			}
		} else {
			System.out.println("Not a valid option");
		}
	}
}
