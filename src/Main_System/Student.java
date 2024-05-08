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
					+ "\n	\"10\"	Add course"
					+ "\n	\"11\"	Drop course"
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
	    		 System.out.println(toString());
		    	 break;
	    	  }
		    	     
	    	  case "3": {
	    		 createPost();
	    	     break; 
	    	  }
		    	     
	    	  case "4": {
	    		  viewAllAssignments();
			  break;
	    	  }
	    	  
	    	  case "5": {
	    		  viewAnAssignment();
	    		  break;
	    	  }
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
	    		 } else {
	    			 System.out.println(displayCourses());
		    	     break;
	    		 }
	    	  }
    	  
	    	  case "10": {
	    		  addCourse();
	    		  break;
	    	  }
	    	  
	    	  case "11": {
	    		  dropCourse();
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
		
		System.out.println("Logged out of " + getUserName() + "...");
	}
	
	/*
	 * 
	 * Getter Methods
	 * 
	 */
	
	public HashMap<Course, Double> getCourseHistory() {
		return courseHistory;
	}
	
	public String displayCourses() {
		String result = "";
	
		for (Entry<Course, Double> entry: courseHistory.entrySet()) {
			result += entry.getKey().toString() + "\n" + 
					"GPA in this course: " + entry.getValue().toString() + "\n\n";
		}
		
		return result;
	}
	
	public void viewAllAssignments() {
		if (courseHistory.isEmpty()) {
			System.out.println("Not enrolled in any classes");
			return;
		} 
		
		for (Entry<Course, Double> entry: courseHistory.entrySet()) {
			Course course = entry.getKey();
			
			for (Assignment assignment: course.getAssignments().get(this)) {
				System.out.println(assignment.toString());
			}
		}
	}
	
	public void viewAnAssignment() {
		String assignmentName = "";
		Assignment assignmentObject = null;
		String courseName = " ";
		Course courseObject = null;

		int state = 0;
		Database.scanner.nextLine();
		
		if (courseHistory.isEmpty()) {
			System.out.println("Not enrolled in any classes");
			return;
		} 
		
		while (true) {
			if (state == 0) { // listening for course name
				System.out.println("Enter \"q\" to exit.");
				System.out.println("Enter Course Name: ");
				courseName = InputHandler.promptLine();
				
				if (courseName.equals("q")) {
					break;
				}
				
				for (Entry<Course, Double> entry: courseHistory.entrySet()) {
					if (courseName.equals(entry.getKey().getName())) {
						courseObject = entry.getKey();
						break;
					}
				}
				
				if (courseObject == null) {
					System.out.println("Not in course");
					return;
				}
				
				state++;
			}
			
			if (state == 1) { // listening for assignment name
				System.out.println("Enter \"q\" to go back.");
				System.out.print("Enter assignment name: ");
				assignmentName = InputHandler.promptLine();
				System.out.println("");
		        
		        if (assignmentName.equals("q")) {
		        	state--;
				courseObject = null;
				continue;
				}
		        
		        assignmentObject = courseObject.getAssignmentFromStudent(this, assignmentName);
		        
		        if (assignmentObject == null) {
		        	continue;
		        }
		        
		        System.out.println(assignmentObject.toString());
		        break;
			}
		}
	}
	
	public void addCourse() {
		String courseName = "";
		Database.scanner.nextLine();
		
		while (true) {
			System.out.println("Enter \"q\" to exit.");
			System.out.print("Enter course name: ");
			courseName = InputHandler.promptLine();
			System.out.println("");
			boolean classFound = false;
			boolean classFull = false;
			
			if (courseName.equals("q")) {
				break;
			}
				
				
			for (Course i : Database.courses) {
				if (courseName.equals(i.getName())) {
					if (i.isFull()) {
						classFull = true;
						continue;
					} else {
						if (addCourse(i) == false) {
							System.out.println("Course already enrolled");
							return;
						} else {
							addCourse(i);
							classFound = true;
							break;
						}
					}
				}
   			 }
				
			if (classFound) {
				System.out.println("Course has been added");
				break;
			} else if (classFull){
				System.out.println("Course is full");
				continue;
			} else { 
				System.out.println("Course is not found");
				continue;
			}
		}
		System.out.println("Exiting addCourse method...");
	}
	
	public void dropCourse() {
		String courseName = "";
		Database.scanner.nextLine();
		boolean courseFound = false;
		
		if (courseHistory.isEmpty()) {
			System.out.println("No courses found");
			return;
		}
		
		while (true) {
			System.out.println("Enter \"q\" to exit.");
			System.out.print("Enter course name: ");
			courseName = InputHandler.promptLine();
			System.out.println("");
			
			if (courseName.equals("q")) {
				break;
			}
				
			for (Entry<Course, Double> entry: courseHistory.entrySet()) {
				if (courseName.equals(entry.getKey().getName())) {
					courseFound = true;
					dropCourse(entry.getKey());
					System.out.println("Course Dropped");
					break;
				} 
			}
			
			if (courseFound == false) {
				System.out.println("Student not in course");
				return;
			}
		}
		System.out.println("Exiting dropCourse method...");
	}
	
	public String getClassOf() {
		return "Class of " + enrollmentDate.getYear();
	}
	
	public double getGPA() {
		int totalScore = 0;
		
		for (Course course: courseHistory.keySet()) {
			// Checks if the course has at least one assignment
			if (course.getAssignments().get(this).size() >= 1) {
				double coursePercentage = 0.0;
				int totalPoints = 0;
				int totalGradedPoints = 0;
				for (Assignment assignment: course.getAssignments().get(this)) {
					totalPoints += assignment.getTotalScore();
					totalGradedPoints += assignment.getGradedScore();
				}
				
				coursePercentage = (double) totalGradedPoints / totalPoints * 100;
				courseHistory.replace(course, GradeSystem.percentageToGPA(coursePercentage));
			}
		}
		
		for (Entry<Course, Double> entry: courseHistory.entrySet()) {
			totalScore += entry.getValue() * entry.getKey().getUnit();
		}
		
		// Unrounded GPA
		double GPA = (double)totalScore/getUnit();
		
		// Rounds to the 1st decimal place
		double roundedGPA = Math.round(GPA * Math.pow(10, 1)) / Math.pow(10, 1);
		
		return roundedGPA;
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
			return false;
		}
		
		courseHistory.put(course, 4.0);
		course.getTeacher().getCourses().add(course);
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
				"\nAll classes taken: " + displayCourses() +
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
