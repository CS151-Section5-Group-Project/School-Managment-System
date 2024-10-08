package Main_System;

import java.util.ArrayList;
import java.util.Map.Entry;

public class Teacher extends User{
	private ArrayList<Course> courses; // courses being taught
	
	public Teacher() {
		super();
		courses = new ArrayList<Course>();
		
		Database.addUser(this);
	}
	
	public Teacher(String firstName, String lastName, String userName, String password) {
		super(firstName, lastName, userName, password);
		courses = new ArrayList<Course>();
		
		Database.addUser(this);
	}
	
	@Override
	public void onLogin() throws CloneNotSupportedException {
		while (true) {
			System.out.println("\nEnter one of the following commands:\n"
					+ "\n	\"1\"	View inbox"
					+ "\n	\"2\"	View user information"
					+ "\n	\"3\"	Create post"
					+ "\n	\"4\"	View a student's grade"
					+ "\n	\"5\"	View a student's assignment"
					+ "\n	\"6\"	View average course grade"
					+ "\n	\"7\"	View all assignment of a course"
					+ "\n	\"8\"	View all courses taught by this account"
					+ "\n	\"9\"	Make an assignment"
					+ "\n	\"10\"	Grade an assignment"
					+ "\n	\"11\"	Delete an assignment"
					+ "\n	\"12\"	Drop student from course"
					+ "\n	\"q\"	Logout\n");
	    	
	    	System.out.print("Enter Command: ");
	    	String input = Database.scanner.next();
	    	System.out.println();
	    	
	    	switch(input) {
	    	  case "1": {
	    		  viewInbox();
	    	      break;
	    	  }
	    	     
	    	  case "2": {
	    		  System.out.println(this.toString());
		    	  break;
	    	  }
		    	     
	    	  case "3": {
	    		  createPost();
	    	      break; 
	    	  }
		    	     
	    	  case "4": {
	    		  viewStudentGrade();
	    	      break; 
	    	  }
	    	  
	    	  case "5": {
	    		  viewStudentAssignment();
	    	      break;
	    	  }
		    	     
	    	  case "6": {
	    		 viewAverageCourseGrade();
	    	     break; 
	    	  }
	    	    
	    	  case "7": {
	    		 viewAllAssignment();
	    	     break; 
	    	  }
	    	       
	    	  case "8": {
	    		 System.out.println("Courses currently teaching: " + courses.toString());
	    	     break;
	    	  }
	    	     
	    	  case "9": {
	    		  createAssignment();
	    		  break;
	    	  }
	    	  
	    	  case "10": {
	    		 gradeStudentAssignment();
	    	     break;
	    	  }
	    	  
	    	  case "11": {
	    		 deleteAssignment();
	    	     break;
	    	  }
	    	  
	    	  case "12": {
	    		 removeStudent();
	    		 break;
	    	  }
	    	     
	    	  case "q": { // Exit
	    		 System.out.println("Logging out...");
	    	     break;
	    	  }
	    		 
	    	     
	    	  default: {
	    		 System.out.println("Not a valid command."); 
	    	  }
	    	}
	    	
	    	if (input.equals("q")) {
	    		break;
	    	}
		}
		
		System.out.println("Fully exit out of teacher mode.");
	}
	
	public ArrayList<Course> getCourses() {
		return courses;
	}
	
	public Student promptFindStudent(Course course) {
		String studentUsername = "";
		
		while (true) { // listening for student
			System.out.println("Enter \"q\" to go back.");
			System.out.print("Enter username of student: ");
			studentUsername = InputHandler.promptLine();
			System.out.println("");
			
			if (studentUsername.equals("q")) {
				break;
			}
			
			Student studentObject = course.getStudent(studentUsername);
			
			if (studentObject == null) { // check if student belong to course
				continue;
			}
			
			if (!course.containStudent(studentUsername)) {
				System.out.println("This student is no in this course.");
				break;
			}
			
			return studentObject;
		}
		
		return null;
	}
	
	public Course promptFindCourse() {
		String courseChoice = "";
		
		while (true) { // listening for course name
			System.out.println("Enter \"q\" to exit.");
			System.out.print("Enter a number corresponding to the following courses: \n");
			
			for (int i = 0; i < courses.size(); i++) {
				System.out.println("	\"" + i + "\"\n" + courses.get(i).toString() + "\n");
			}
			
			courseChoice = InputHandler.promptLine();
			System.out.println("");
			
			if (courseChoice.equals("q")) {
				break;
			}
			
			int choice = 0;
			
			try {
				choice = Integer.parseInt(courseChoice);
			} catch (NumberFormatException e) {
				System.out.println("Invalid number");
				continue;
			}
			
			if (choice < 0 || choice >= courses.size()) {
				System.out.println("Invalid number");
				continue;
			}
			
			return courses.get(choice);
		}
		
		return null;
	}
	
	public void gradeStudentAssignment() {
		Course courseObject = null;
		Student studentToGrade = null;
		Assignment assignmentObject = null;
		String assignmentName = "";
		int gradedScore = 0;
		
		int state = 0;
		Database.scanner.nextLine();
		
		while (true) {
			if (state == 0) { // listening for course name
				courseObject = promptFindCourse();
				
				if (courseObject == null) { // no selection is made
					break;
				}
				
				state++;
			}
			if (state == 1) { // listening for student 
				studentToGrade = promptFindStudent(courseObject);
				
				if (studentToGrade == null) {
					state--;
				}else {
					state++;
				}
			}
			if (state == 2) { //listening for student's assignment
				System.out.println("Enter \"q\" to exit.");
				System.out.print("Enter the name of the assignment: ");
				assignmentName = InputHandler.promptLine();
				System.out.println("");
				
				
				if (assignmentName.equals("q")) {
					state--;
					continue;
				}
				
				if (courseObject.getAssignmentFromStudent(studentToGrade, assignmentName) == null) {
					System.out.println("The name given is not found. ");
					continue;
				}
				
				assignmentObject = courseObject.getAssignmentFromStudent(studentToGrade, assignmentName);
				state++;
			}
			if (state == 3) { // listening for graded score
				System.out.println("Enter \"-1\" to exit.");
				System.out.print("Enter the total score of the assignment: ");
				gradedScore = InputHandler.promptNumber();
				System.out.println("");
				
				if (gradedScore == -1) {
					state--;
					continue;
				}
				gradeAssignment(assignmentObject, gradedScore);
				System.out.println("Graded Assignment: " + assignmentName);
				break;
			}
			System.out.println("Exiting gradeStudentAssignment method...");
		}
		
		
	}
	
	public void createAssignment() {
		//String courseChoice = "";
		Course courseObject = null;
		String assignmentName = "";
		String assignmentDescription = "";
		int assignmentScore = 0;

		int state = 0;
		Database.scanner.nextLine();
		
		while (true) {
			if (state == 0) { // listening for course name
				courseObject = promptFindCourse();
				
				if (courseObject == null) { // no selection is made
					break;
				}
				
				state++;
			}
			
			if (state == 1) { // listening for assignemnt name
				System.out.println("Enter \"q\" to exit.");
				System.out.print("Enter the name of the assignment: ");
				assignmentName = InputHandler.promptLine();
				System.out.println("");
				
				if (assignmentName.equals("q")) {
					state--;
					continue;
				}
				
				if (courseObject.findAssignmentPosition(assignmentName).size() != 0) {
					System.out.println("Assignment already been made");
					continue;
				}
				
				state++;
			}
			
			if (state == 2) { // listening for description name
				System.out.println("Enter \"q\" to exit.");
				System.out.print("Enter the description of the assignment: ");
				assignmentDescription = InputHandler.promptLine();
				System.out.println("");
				
				if (assignmentDescription.equals("q")) {
					state--;
					continue;
				}
				
				state++;
			}
			
			if (state == 3) { // listening for assignment score
				System.out.println("Enter \"-1\" to exit.");
				System.out.print("Enter the total score of the assignment: ");
				assignmentScore = InputHandler.promptNumber();
				System.out.println("");
				
				if (assignmentScore == -1) {
					state--;
					continue;
				}
				
				for (Entry<Student, ArrayList<Assignment>> entry: courseObject.getAssignments().entrySet()) {
					new Assignment(entry.getKey(), courseObject, assignmentName, assignmentDescription, assignmentScore);
				}
				
				break;
			}
		}
		
		System.out.println("Exiting createAssignment method...");
	}
	
	public void deleteAssignment() {
		Course courseObject = null;
		String assignmentName = "";
		
		int state = 0;
		Database.scanner.nextLine();
		
		while (true) {
			if (state == 0) { // listening for course name
				courseObject = promptFindCourse();
				
				if (courseObject == null) { // no selection is made
					break;
				}
				
				state++;
			}
			
			if (state == 1) { // listening for assignment name
				System.out.println("Enter \"q\" to exit.");
				System.out.print("Enter the name of the assignment: ");
				assignmentName = InputHandler.promptLine();
				System.out.println("");
				
				if (assignmentName.equals("q")) {
					state--;
					continue;
				}
				
				removeAssignment(courseObject, assignmentName);
				System.out.println("Deleted Assignment: " + assignmentName);
				break;
			}
		}
		System.out.println("Exiting deleteAssignment method...");
	}
	
	public void viewAllAssignment() {
		Course courseObject = null;

		int state = 0;
		Database.scanner.nextLine();
		
		while (true) {
			if (state == 0) { // listening for course name
				courseObject = promptFindCourse();
				
				if (courseObject == null) { // no selection is made
					break;
				}
				
				System.out.println("Showing all assignments: \n" + courseObject.getAssignments().values().toString());
				break;
			}
		}
		
		System.out.println("Exiting viewAllAssignment method...");
	}
	
	public void viewAverageCourseGrade() {
		Course courseObject = null;

		int state = 0;
		Database.scanner.nextLine();
		
		while (true) {
			if (state == 0) { // listening for course name
				courseObject = promptFindCourse();
				
				if (courseObject == null) { // no selection is made
					break;
				}
				
				System.out.println("Course average score: " + courseObject.getCourseAverageScore());
				break;
			}
		}
		
		System.out.println("Exiting viewAverageCourseGrade method...");
	}
	
	public void viewStudentAssignment() {
		Course courseObject = null;
		String studentUsername = "";
		Student studentObject = null;
		String assignmentName = "";
		Assignment assignmentObject = null;

		int state = 0;
		Database.scanner.nextLine();
		
		while (true) {
			if (state == 0) { // listening for course name
				courseObject = promptFindCourse();
				
				if (courseObject == null) { // no selection is made
					break;
				}
				
				state++;
			}
			
			if (state == 1) { // listening for student
				System.out.println("Enter \"q\" to go back.");
				System.out.print("Enter username of student: ");
				studentUsername = InputHandler.promptLine();
				System.out.println("");
				
				if (studentUsername.equals("q")) {
					state--;
					continue;
				}
				
				studentObject = courseObject.getStudent(studentUsername);
				
				if (studentObject == null) { // check if student belong to course
					continue;
				}
				
				state++;
			}
			
			if (state == 2) { // listening for assignment
				System.out.println("Enter \"q\" to go back.");
				System.out.print("Enter name of assignment: ");
				assignmentName = InputHandler.promptLine();
				System.out.println("");
				
				if (assignmentName.equals("q")) {
					state--;
					continue;
				}
				
				assignmentObject = courseObject.getAssignmentFromStudent(studentObject, assignmentName);
				
				if (assignmentObject == null) {
					continue;
				}
				
				System.out.println("Assignment information: \n" + assignmentObject.toString());
				break;
			}
		}
		
		System.out.println("Exiting getStudentGrade method...");
	}
	
	public void viewStudentGrade() {
		Course courseObject = null;
		String studentUsername = "";
		Student studentObject = null;

		int state = 0;
		Database.scanner.nextLine();
		
		while (true) {
			if (state == 0) { // listening for course name
				boolean hasStudent = false;
				
				for (Course course: courses) {
					if (course.getAssignments().size() > 0) {
						hasStudent = true;
					}
				}
				
				if (hasStudent == false) {
					System.out.println("None of enrolled class have any students enrolled.");
					break;
				}
				
				courseObject = promptFindCourse();
				
				if (courseObject == null) {
					break;
				}
				
				state++;
			}
			
			if (state == 1) { // listening for student
				System.out.println("Enter \"q\" to go back.");
				System.out.print("Enter username of student: ");
				studentUsername = InputHandler.promptLine();
				System.out.println("");
				
				if (studentUsername.equals("q")) {
					state--;
					continue;
				}
				
				studentObject = courseObject.getStudent(studentUsername);
				
				if (studentObject == null) {
					continue;
				}
				
				System.out.println("Current grade: " + courseObject.getStudentGrade(studentObject));
				break;
			}
		}
		
		System.out.println("Exiting getStudentGrade method...");
	}
	
	public void removeStudent() {
		Course courseObject = null;
		Student studentObject = null;
		String studentUsername = "";
		
		int state = 0;
		Database.scanner.nextLine();
		
		while (true) {
			if (state == 0) { // listening for course name
				courseObject = promptFindCourse();
				
				if (courseObject == null) { // no selection is made
					break;
				}
				
				state++;
			}
			
			if (state == 1) { // listening for student
				System.out.println("Enter \"q\" to go back.");
				System.out.print("Enter username of student: ");
				studentUsername = InputHandler.promptLine();
				System.out.println("");
				
				if (studentUsername.equals("q")) {
					state--;
					continue;
				}
				
				studentObject = courseObject.getStudent(studentUsername);
				
				if (studentObject == null) { // check if student belong to course
					continue;
				}
				
				dropStudent(courseObject, studentObject);
				// Removes course from student's course history
				studentObject.dropCourse(courseObject);
				System.out.println(studentObject + " was dropped from course: " + courseObject.getName());
				break;
			}
		}
		
		System.out.println("Exiting removeStudent method...");
	}
	
	public boolean containCourse(String courseName) {
		for (Course course: courses) {
			if (course.getName().equals(courseName)) {
				return true;
			}
		}
		
		return false;
	}
	
	public Post sendAnnouncement(Course course, String subject, String message) throws CloneNotSupportedException {
		Post post = new Post(this, subject, message, "announcement");
		
		course.sendAnnouncement(post);
		
		return post;
	}
	
	public String toString() {
		String result = super.toString() + "\nCourses being taught: ";
		
		for (Course course: courses) {
			result += course.getName() + ", ";
		}
		
		return result;
	}
	
	public void gradeAssignment(Assignment assignment, int score) {
		assignment.gradeAssignment(score);
	}
	
	public void removeAssignment(Course course, String assignmentName) {
		course.removeAssignment(assignmentName);
	}
	
	public void dropStudent(Course course, Student student) {
		course.dropStudent(student);
	}
	
	public void createPost() throws CloneNotSupportedException {
		String receiverUsername = "";
		User receiverObjectFound = null;
		String subject = "";
		String message = "";
		String postType = "";
		Course courseObjectFound = null;
		
		int state = 0;
		Database.scanner.nextLine();
		
		while (true) {
	    	if (state == 0) { // listening for type of post
	    		System.out.println("Enter \"q\" to exit.");
				System.out.print("Enter type (message or announcement): ");
				
				postType = InputHandler.promptLine();
		        System.out.println("");
		        
		        if (postType.equals("q")) {
					break;
				}
		        
		        if (!postType.equals("message") && !postType.equals("announcement")) {
		        	System.out.println("The following choice is not valid. Try again.");
		        	continue;
		        }
		        
		        if (postType.equals("message")) {
		        	state = 1;
		        	continue;
		        }
		        
		        if (postType.equals("announcement")) {
		        	state = 2;
		        	continue;
		        }
		        
			}
	    	
			if (state == 1) { // listening for receiver (by username)
				System.out.println("Enter \"q\" to exit.");
				System.out.print("Enter username to send to: ");
				receiverUsername = Database.scanner.nextLine();
				System.out.println();
				
				if (receiverUsername.equals("q")) {
					state--;
					continue;
				}
				
				if (!Database.getUsers().containsKey(receiverUsername)) { // check if user exist
					System.out.println("User does not exist.");
					continue;
				}
				
				receiverObjectFound = Database.getUsers().get(receiverUsername);
				
				state += 2;
			}
			
			if (state == 2) { // listening for course (by name)
				courseObjectFound = promptFindCourse();
				
				if (courseObjectFound == null) { // check if user exist
					System.out.println("This teacher is not teaching the specified course.");
					continue;
				}
				
				state++;
			}
			
			if (state == 3) { // listening for subject
				System.out.println("Enter \"q\" to go back.");
				System.out.print("Enter subject: ");
				subject = Database.scanner.nextLine();
				System.out.println();
				
				if (subject.equals("q")) {
					if (postType.equals("announcement")) {
			        	state--;
			        	continue;
			        }
					
					state = 1;
					continue;
				}
				
				state++;
			}
			
			if (state == 4) { // listening for message
				System.out.println("Enter \"q\" to go back.");
				System.out.print("Enter message:");
		        
				message = Database.scanner.nextLine();
		        System.out.println();
		        
		        
		        
		        if (postType.equals("message")) {
		        	Post post = sendPost(receiverObjectFound, subject, message);
					
					System.out.println("Message sent: \n" + post);
		        	break;
		        }
		        
		        if (postType.equals("announcement")) {
		        	Post post = sendAnnouncement(courseObjectFound, subject, message);
					
					System.out.println("Message sent: \n" + post);
		        	break;
		        }
			}
		}
		
		System.out.println("Exiting createPost method...");
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
