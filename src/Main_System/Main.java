package Main_System;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
    	Accounts.initializeAccounts();
        User currentUser;
        
        try(Scanner scan = new Scanner(System.in)){
        	// Leo: Outer while loop to continue the simulation until program terminates
        	while(true) {
        		// Leo: Inner while loop to simulate the login process; exits loop once "logged in"
	            while(true) {
	                System.out.println("Username:");
	                String user = scan.next();
	                System.out.println("Password:");
	                String pass = scan.next();
	                if (Accounts.users.containsKey(user) && Accounts.users.get(user).getPassword().equals(pass)) {
	                    System.out.println("Login successful");
	                    currentUser = Accounts.users.get(user);
	                    break;
	                }else {
	                    System.out.println("Invalid username or password\n");
	                }
	            }
	            
	            // Leo: Inner while loop to simulate user choices; exits when user "logs out" or program terminates
	            while(true) {
		            if(currentUser instanceof Main_System.Administrator) {
		            	Administrator currentAdmin = (Administrator) currentUser;
		                System.out.println("\nDirectory:\n1. Admin Information \n2. Create Announcement \n"
		                		+ "3. Remove Teacher or Student \n4. View Inbox \n5. Logout \n6. Quit");
		                int command = scan.nextInt();
		                if (command == 1) {
		                	System.out.println(currentUser.toString());
		                } else if (command == 2) {
		                	currentAdmin.createPost();
		                } else if (command == 3) {
		                	System.out.println("Person being removed: ");
		                	String name = scan.next();
		                	currentAdmin.removeUser(name);
		                } else if (command == 4) {
		                	currentAdmin.viewInbox();
		                } else if (command == 5) {
		                	break;
		                } else {
		                	System.exit(0);
		                }
		            }else if (currentUser instanceof Main_System.Teacher) {
		            	Teacher currentTeacher = (Teacher) currentUser;
		                System.out.println("\nDirectory:\n1. Teacher Information \n2. View Inbox \n"
		                		+ "3. Change Student GPA\n4. Logout \n5. Quit");
		                int command = scan.nextInt();
		                if (command == 1) {
		                	System.out.println(currentTeacher.toString());
		                } else if (command == 2) {
		                	currentTeacher.viewInbox();
		                } else if (command == 3) {
		            		System.out.println("Student Username:");
		            		String student = scan.next();
		            		System.out.println("New GPA:");
		            		double gpa = scan.nextDouble();
		            		currentTeacher.changeStudentGPA(student, gpa);
		                } else if (command == 4){
		                	break;
		                }else {
		                	System.exit(0);
		                }
		            }else {
		            	Student currentStudent = (Student) currentUser;
		                System.out.println("\nDirectory:\n1. Student Information \n2. View Inbox \n"
		                		+ "3. Check Graduation Status \n4. Logout \n5. Quit");
		                int command = scan.nextInt();
		                if (command == 1) {
		                	System.out.println(currentStudent.toString());
		                } else if (command == 2) {
		                	currentStudent.viewInbox();
		                } else if (command == 3) {
		                	currentStudent.checkGradStatus();
		                } else if (command == 4) {
		                	break;
		                }else {
		                	System.exit(0);
		                }
		            }
	            }
        	}
        }
    }
}
