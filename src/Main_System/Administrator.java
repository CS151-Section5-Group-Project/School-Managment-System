package Main_System;

import java.util.Scanner;
import java.util.Map.Entry;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

public class Administrator extends User {
//	public enum Level {
//		I,II
//	}
//	Level i;
	public Administrator() {
		super();
		//i = null;
	}
	
	public Administrator(String firstName, String lastName, String userName, String password) {
		super(firstName, lastName, userName, password);
		//this.i = i;
	}
	
	@Override
	public void onLogin() {
		// TODO Auto-generated method stub
	}
	
//	public String levelNames() {
//		switch(i) {
//		case I:
//			return "Moderator";
//		case II:
//			return "Administrator";
//		default:
//			return " ";
//		}
//	}
	
	public static void addStudentAccount(String firstName, String lastName, String userName, String password, LocalDate enrollmentDate) {
		Student student = new Student(firstName, lastName, userName, password, enrollmentDate);
		Database.addAccount(student);
	}
	
	public static void addTeacherAccount(String firstName, String lastName, String userName, String password) {
		Teacher teacher = new Teacher(firstName, lastName, userName, password);
		Database.addAccount(teacher);
	}
	
	public static void addAdminAccount(String firstName, String lastName, String userName, String password) {
		Administrator administrator = new Administrator(firstName, lastName, userName, password);
		Database.addAccount(administrator);
	}
	
	public static void addCourse(String name, Teacher teacher, String term, Classroom classroom, LocalDate startTime, LocalDate endTime, ArrayList<DayOfWeek> day) {
		Course course = new Course(name, teacher, term, classroom, startTime, endTime, day);
		Database.addCourse(course);
	}
	
	public static void addCourse(String name, Teacher teacher, String term) {
		Course course = new Course(name, teacher, term);
		Database.addCourse(course);
	}
	
	public Post sendAnnouncement(String subject, String message) throws CloneNotSupportedException {
		Post post = new Post(this, subject, message, "announcement");
		
		for (Entry<String, User> entry: Database.getUsers().entrySet()) {
			Inbox.sendPost(entry.getValue(), post.clone());
		}
		
		return post;
	}
	
//	public void setLevel(Level i) {
//		this.i = i;
//	}
	
//	public String toString() {
//		return super.toString() + "\nLevel: " + i;
//	}
	
	/*
	 * Leo: Allows administrator to create posts and 
	 * depending on their type (Announcement or Message)
	 * will either add the post to every user in the Accounts class
	 * or add the post to a specific user
	 */
	public void createPost() throws CloneNotSupportedException {
		String receiverUsername = "";
		User receiverObjectFound = null;
		String subject = "";
		String message = "";
		String postType = "";
		
		int state = 0;
		
		Scanner scanner = null;
		
		try {
		    scanner = new Scanner(System.in);
		    
		    while (true) {
		    	if (state == 0) { // listening for type of post
					System.out.print("Enter type (message or announcement): ");
			        postType = scanner.nextLine();
			        System.out.println("\n");
			        
			        if (postType != "message" && postType != "announcement") {
			        	System.out.println("The following choice is not valid. Try again.");
			        	continue;
			        }
			        
			        state++;
				}
		    	
				if (state == 1) { // listening for receiver (by Username)
					System.out.print("Enter username to send to: ");
					receiverUsername = scanner.nextLine();
					System.out.println("\n");
					
					if (!Database.getUsers().containsKey(receiverUsername)) { // check if user exist
						System.out.println("User does not exist.");
						continue;
					}
					
					receiverObjectFound = Database.getUsers().get(receiverUsername);
					
					state++;
				}
				
				if (state == 2) { // listening for subject
					System.out.print("Enter subject: ");
					subject = scanner.nextLine();
					System.out.println("\n");
					
					state++;
				}
				
				if (state == 3) { // listening for message
					System.out.print("Enter message:");
			        message = scanner.nextLine();
			        System.out.println("\n");
			        
			        if (postType == "message") {
			        	Post post = sendPost(receiverObjectFound, subject, message);
						
						System.out.println("Message sent: \n" + post);
			        	break;
			        }
			        
			        if (postType == "announcement") {
			        	Post post = sendAnnouncement(subject, message);
						
						System.out.println("Message sent: \n" + post);
			        	break;
			        }
				}
			}
		} finally {
		    if(scanner != null)
		        scanner.close();
		}
	}
	public void removeUser(String user) {
		Scanner scan = new Scanner(System.in);
    		if (Accounts.users.containsKey(user)) {
    			System.out.println("Are you sure you want to delete this person?\n1. Yes\n2. No");
    			int confirm = scan.nextInt();
    			if (confirm == 1) {
    				Accounts.users.remove(user);
    				System.out.println("Removal Completed");
    			} else {
    				System.out.println("Removal Cancelled");
    			}
    		}
	}
}
