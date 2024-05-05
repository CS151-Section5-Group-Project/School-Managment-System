package Main_System;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map.Entry;

public class Student extends User {
	/*
	 * 
	 * Variables
	 * 
	 */
	
	private LocalDate enrollmentDate; // the date the student have applied
	private HashMap<Course, Double> courseHistory; // all courses taken (including repeats) with displayed GPA (for that course)
	private boolean graduationStatus;
	
	/*
	 * 
	 * Constructors
	 * 
	 */
	
	public Student() {
		super();
		enrollmentDate = null;
		courseHistory = new HashMap<Course, Double>();
		graduationStatus = false;
		
		Database.addUser(this);
	}
	
	public Student(String firstName, String lastName, String userName, String password, LocalDate enrollmentDate) {
		super(firstName, lastName, userName, password);
		this.enrollmentDate = enrollmentDate;
		this.courseHistory = new HashMap<Course, Double>();
		this.graduationStatus = false;
		
		Database.addUser(this);
	}
	
	@Override
	public void onLogin() throws CloneNotSupportedException {
		while (true) {
			System.out.println("\nEnter one of the following commands:\n"
					+ "\n	\"1\"	View inbox"
					+ "\n	\"2\"	View user information"
					+ "\n	\"3\"	Create post"
					+ "\n	\"4\"	View all assignments"
					+ "\n	\"5\"	View an assignment"
					+ "\n	\"6\"	View GPA"
					+ "\n	\"7\"	View graduation status"
					+ "\n	\"8\"	View number of units taken"
					+ "\n	\"9\"	View courses taken"
					+ "\n	\"10\"	View schedule" // possibly an optional feature
					+ "\n	\"11\"	Add course"
					+ "\n	\"12\"	Drop course"
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
		    	     
//	    	  case "4": {
//	    		  if (Course.getAssignments().isEmpty()) {
//	    			  System.out.println("No assignments to display.\n");
//	    			  break;
//		    		 }
//	    		  System.out.println("Assignments: " + Course.getAssignments().toString());
//	    		  break;
//	    	  }
	    	  
//	    	  case "5": {
//	    		  if (Course.getAssignments().isEmpty()) {
//	    			  System.out.println("No assignments to display.\n");
//	    			  break;
//	    		  }
//	    		  System.out.println("Name of Assignment:");
//	    		  String assign = Database.scanner.nextLine();
//			      System.out.println();
//			      Course.getAllAssignmentOfName(assign);
//			      break;
//	    	  }
	    	  case "6": {
	    		 System.out.println(getGPA());
	    	     break; 
	    	  }    
	    	  case "7": {
	    		 getGraduationStatus();
	    	     break; 
	    	  }
	    	     
	    	     
	    	  case "8": {
	    		 System.out.println(getUnit());
	    	     break;
	    	  }
	    	     
	    	  case "9": {
	    		 if (Database.getCourses().isEmpty()) {
	    			 System.out.println("No courses to display.\n");
	    			 break;
	    		 }
	    		 
	    		 System.out.println("Courses: " + courseHistory.toString());
	    	     break;
	    	  }
	    	  
//	    	  case "10": {
//	    		 for (Entry<Course, Double> courses : courseHistory.entrySet()) {
//	    			 System.out.println("Classrooms: " + courseHistory.toString());
//	    			 break;
//	    		 }
//	    	  }
	    	  
//	    	  case "11": {
//	    		 addCourse(); 
//	    	     break;
//	    	  }
	    	     
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
		
		System.out.println("Logging out of " + getUserName() + "...");
	}
	
	/*
	 * 
	 * Getter Methods
	 * 
	 */
	
	public String getClassOf() {
		return "Class of " + enrollmentDate.getYear();
	}
	
	public double getGPA() {
		int totalScore = 0;
		
		for (Entry<Course, Double> entry: courseHistory.entrySet()) {
			totalScore += Math.ceil(entry.getValue());
		}
		
		return (double)totalScore/courseHistory.size();
	}
	
	public double getUnit() {
		int totalUnit = 0;
		
		for (Entry<Course, Double> entry: courseHistory.entrySet()) {
			totalUnit += entry.getKey().getUnit();
		}
		
		return totalUnit;
	}
	
	/*
	 * Leo: Checks the graduation status of the student. 
	 * If gradStatus is true, the student will see that 
	 * they've applied for graduation, otherwise it's pending.
	 */
	public void getGraduationStatus() {
		System.out.print("GRADUATION STATUS: ");
		
		if(graduationStatus && getUnit() == 120) {
			System.out.println("Applied for Graduation");
			return;
		}
		
		System.out.println("Need to Finish Pending Work");
	}
	
	/*
	 * 
	 * Other Methods
	 * 
	 */
	
	public boolean addCourse(Course course) {
		if (courseHistory.containsKey(course)) {
			System.out.println("Course already enrolled");
			return false;
		}
		
		courseHistory.put(course, 4.0);
		return true;
	}
	
	public void dropCourse(Course course) {
		if (!courseHistory.containsKey(course)) {
			System.out.println("Course not enrolled.");
			return;
		}
		
		courseHistory.remove(course);
	}
	
	public String toString() {
		return super.toString() + 
				"\nGPA: " + getGPA() + 
				"\nAll classes taken: " + courseHistory.toString() +
				"\nEnrolled in: " + enrollmentDate.toString() +
				"\nClass: " + getClassOf();
	}
	
	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Student)) {
			return false;
		}
		
		Student student = (Student) object;
		
		if (student.toString().equals(this.toString())) {
			return true;
		}
		
		return false;
	}
}
