package Main_System;

import java.util.ArrayList;

public class Teacher extends User{
	private ArrayList<Course> courses; // courses being taught
	
	public Teacher() {
		super();
		courses = new ArrayList<Course>();
	}
	
	public Teacher(String firstName, String lastName, String userName, String password) {
		super(firstName, lastName, userName, password);
		courses = new ArrayList<Course>();
	}
	
	@Override
	public void onLogin() {
		try {
		    while (true) {
		    	System.out.println("\nEnter one of the following commands:\n"
						+ "\n	\"1\"	Do command 1"
						+ "\n	\"2\"	Do command 2"
						+ "\n	\"3\"	Do command 3"
						+ "\n	\"4\"	Do command 4"
						+ "\n	\"q\"	Logout\n");
		    	
		    	System.out.print("Enter Command: ");
		    	String input = Database.scanner.next();
		    	System.out.println();
		    	
		    	switch(input) {
		    	  case "1":
		    	     System.out.println("Running command 1");
		    	     break;
		    	     
		    	  case "2":
		    	     System.out.println("Running command 2");
		    	     break;
			    	     
		    	  case "3":
		    	     System.out.println("Running command 3");
		    	     break;
			    	     
		    	  case "4":
		    	     System.out.println("Running command 4");
		    	     break;
		    	  
		    	  case "q": // Exit
		    		 System.out.println("Logging out...");
		    	     break;
		    	     
		    	  default: 
		    	     System.out.println("Not a valid command.");
		    	}
		    	
		    	if (input.equals("q")) {
		    		break;
		    	}
			}
		} finally {
		    System.out.println("Fully exit out of teacher mode.");
		}
	}
	
	public Post sendAnnouncement(Course course, String subject, String message) throws CloneNotSupportedException {
		Post post = new Post(this, subject, message, "announcement");
		
		course.sendAnnouncement(post);
		
		return post;
	}
	
	public String toString() {
		return super.toString() + 
				"\nCourses being taught: " + courses.toString();
	}
	
	public void gradeAssignment(Course course, Student student, String string, int score) {
		course.gradeAssignment(course.getAssignmentFromStudent(student, string), score);
	}
	
	public void gradeAssignment(Assignment assignment, int score) {
		assignment.gradeAssignment(score);
	}
	
	public void addAssignment(Course course, String assignmentName, int totalScore) {
		course.addAssignment(assignmentName, totalScore);;
	}
	
	public void removeAssignment(Course course, String assignmentName) {
		course.removeAssignment(assignmentName);
	}
	
	public void createPost() throws CloneNotSupportedException {
		String receiverUsername = "";
		User receiverObjectFound = null;
		String subject = "";
		String message = "";
		String postType = "";
		String courseName = "";
		Course courseObjectFound = null;
		
		int state = 0;
		
		try {
		    while (true) {
		    	if (state == 0) { // listening for type of post
					System.out.print("Enter type (message or announcement): ");
			        postType = Database.scanner.nextLine();
			        System.out.println("\n");
			        
			        if (postType == "message") {
			        	state = 1;
			        	continue;
			        }
			        
			        if (postType == "announcement") {
			        	state = 2;
			        	continue;
			        }
			        
			        System.out.println("The following choice is not valid. Try again.");
				}
		    	
				if (state == 1) { // listening for receiver (by username)
					System.out.print("Enter username to send to: ");
					receiverUsername = Database.scanner.nextLine();
					System.out.println("\n");
					
					if (!Database.getUsers().containsKey(receiverUsername)) { // check if user exist
						System.out.println("User does not exist.");
						continue;
					}
					
					receiverObjectFound = Database.getUsers().get(receiverUsername);
					
					state++;
				}
				
				if (state == 2) { // listening for course (by name)
					System.out.print("Enter course to send to: ");
					courseName = Database.scanner.nextLine();
					System.out.println("\n");
					
					for (Course courseObject: Database.getCourses()) {
						if (courseObject.getName() == courseName) {
							if (courseObject.getTeacher() == this) {
								courseObjectFound = courseObject;
								state++;
								break;
							}
						}
					}
					
					
					
					if (courseObjectFound == null) { // check if user exist
						System.out.println("This teacher is not teaching the specified course.");
						continue;
					}
					
					state++;
				}
				
				if (state == 3) { // listening for subject
					System.out.print("Enter subject: ");
					subject = Database.scanner.nextLine();
					System.out.println();
					
					state++;
				}
				
				if (state == 4) { // listening for message
					System.out.print("Enter message:");
			        message = Database.scanner.nextLine();
			        System.out.println();
			        
			        if (postType == "message") {
			        	Post post = sendPost(receiverObjectFound, subject, message);
						
						System.out.println("Message sent: \n" + post);
			        	break;
			        }
			        
			        if (postType == "announcement") {
			        	Post post = sendAnnouncement(courseObjectFound, subject, message);
						
						System.out.println("Message sent: \n" + post);
			        	break;
			        }
				}
			}
		} finally {
		    System.out.println("Exiting creating post mode");
		}
	}
	
	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Teacher)) {
			return false;
		}
		
		Teacher teacher = (Teacher) object;
		
		if (teacher.toString().equals(this.toString())) {
			return true;
		}
		
		return false;
	}
}
