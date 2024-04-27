package Main_System;

import java.util.ArrayList;

public class Teacher extends User{
	private ArrayList<Course> courses = new ArrayList<Course>(); // courses being taught
	
	public Teacher() {
		super();
	}
	
	public Teacher(String firstName, String lastName, String userName, String password, int id) {
		super(firstName, lastName, userName, password, id);
	}
	
	public String toString() {
		return super.toString() + 
				"\nCourses being taught: " + courses;
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
}
