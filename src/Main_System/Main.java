package Main_System;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        HashMap<String, User> users = new HashMap<String, User>();
        users.put("leo.truong", new Student("Truong", "Leo", "leo.truong", 
                "leopass123", 174, 3.1, "2025"));
        users.put("teacher.user", new Teacher("Teacher", "Mr", "teacher.user",
                "teachpass", 82, "MATH"));
        
        Administrator admin = new Administrator("Luong", "Brian", "flame", "123", 1, Administrator.Level.I);
        
        users.put("flame", admin);
        
        User currentUser;
        
        try(Scanner scan = new Scanner(System.in)){
        	while(true) {
	            while(true) {
	                System.out.println("Username:");
	                String user = scan.next();
	                System.out.println("Password:");
	                String pass = scan.next();
	                if (users.containsKey(user) && users.get(user).getPassword().equals(pass)) {
	                    System.out.println("Login successful");
	                    currentUser = users.get(user);
	                    break;
	                }else {
	                    System.out.println("Invalid username or password\n");
	                }
	            }
	            
	            while(true) {
		            if(currentUser instanceof Main_System.Administrator) {
		            	Administrator currentAdmin = (Administrator) currentUser;
		                System.out.println("\nDirectory:\n1. Admin Information \n2. Create Announcement \n"
		                		+ "3. View Inbox \n4. Logout \n5. Quit");
		                int command = scan.nextInt();
		                if (command == 1) {
		                	System.out.println(currentUser.toString());
		                } else if (command == 2) {
		                	currentAdmin.createPost();
		                }else if (command == 3) {
		                	currentAdmin.viewInbox();
		                } else if (command == 4) {
		                	break;
		                }else {
		                	System.exit(0);
		                }
		            }else if (currentUser instanceof Main_System.Teacher) {
		                System.out.println("Directory:\n1. Teacher Information \n2. View Inbox \n3. Change Student GPA\n4. Quit");
		                int command = scan.nextInt();
		                if (command == 1) {
		                	System.out.println(currentUser.toString());
		                } else if (command == 2) {
		                	currentUser.viewInbox();
		                } else if (command == 3) {
		            		System.out.println("Student Username:");
		            		String student = scan.next();
		            		System.out.println("New GPA:");
		            		double gpa = scan.nextDouble();
		            		if (users.containsKey(student)) {
		            			Student currentStudent = (Student) users.get(student);
		            			currentStudent.setGPA(gpa);
		            			System.out.println("Change Complete");
		            			System.exit(0);
		            		} else {
		            			System.out.println("Student Not in Database");
		            			System.exit(0);
		            		}
		                } else {
		                	System.exit(0);
		                }
		            }else {
		                System.out.println("Directory:\n1. Student Information \n2. View Inbox \n3. Quit");
		                int command = scan.nextInt();
		                if (command == 1) {
		                	System.out.println(currentUser.toString());
		                } else if (command == 2) {
		                	currentUser.viewInbox();
		                } else {
		                	System.exit(0);
		                }
		            }
	            }
        	}
        }
    }
}
