package Main_System;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Scanner;
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
	}
	
	public Student(String firstName, String lastName, String userName, String password, int id, LocalDate enrollmentDate) {
		super(firstName, lastName, userName, password, id);
		this.enrollmentDate = enrollmentDate;
		this.courseHistory = new HashMap<Course, Double>();
		this.graduationStatus = false;
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
	
	public void addCourse(Course course) {
		courseHistory.put(course, 4.0);
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
	
	/*
	 * Leo: Allows student to create posts of type 
	 * "Message" which would allow them send a message
	 * to any user within the database.
	 */
	public void createPost() {
		Scanner scan = new Scanner(System.in);
		System.out.println("SUBJECT:");
		String subject = scan.nextLine();
        System.out.println("MESSAGE:");
        String message = scan.nextLine();
        Post msg = new Post(subject, message, "Message");
        
        System.out.println("(username) TO:");
		String user = scan.nextLine();
		if (Accounts.users.containsKey(user)) {
			Accounts.users.get(user).getInbox().getReceived().add(msg);
			System.out.println("Post Created:\n" + msg.toString() + "\n");
		}else {
			System.out.println("User does not exist.");
		}
	}
}
