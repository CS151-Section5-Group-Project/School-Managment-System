package Main_System;

import java.time.LocalDate;

public class Assignment {
	private Student owner;
	private Course course;
	private String name;
	private String description;
	private int totalScore;
	private int gradedScore;
	private boolean graded;
	private LocalDate createdAt;
	
	// default constructor
	public Assignment() {
		owner = null;
		course = null;
		name = "";
		description = "";
		totalScore = 0;
		gradedScore = 0;
		graded = false;
		createdAt = LocalDate.now();
	}
	
	// constructor for ungraded assignments
	public Assignment(Student owner, Course course, String name, String description, int totalScore) {
		this.owner = owner;
		this.course = null;
		this.name = name;
		this.description = description;
		this.totalScore = totalScore;
		this.gradedScore = 0;
		graded = false;
		createdAt = LocalDate.now();
	}
	
	// constructor for graded assignments
	public Assignment(Student owner, Course course, String name, String description, int totalScore, int gradedScore) {
		this.name = name;
		this.course = course;
		this.totalScore = totalScore;
		this.gradedScore = 0;
		graded = true;
		createdAt = LocalDate.now();
	}
	
	public int getTotalScore() {
		return totalScore;
	}
	
	public int getGradedScore() {
		return gradedScore;
	}
	
	public String getName() {
		return name;
	}
	
	public Student getOwner() {
		return owner;
	}
	
	public void gradeAssignment(int gradedScore) {
		this.gradedScore = gradedScore;
		graded = true;
	}
	
	public double getPercentScore() {
		return ((double)gradedScore/totalScore) * 100;
	}
	
	@Override
	public String toString() {
		String result = "Assignment Owner: " + owner.getFullName() +
				"\nCourse: " + course.getName() + 
				"\nTeacher: " + course.getTeacher().getFullName() + 
				"\nAssignment Name: " + name + 
				"\nAssignment Description: " + description + 
				"\nAssignment Total Score: " + totalScore +
				"\nAssignment Graded Score: ";
		
		if (graded == false) {
			result += "Ungraded";
		} else {
			result += gradedScore;
		}
		
		result += "\nAssignment created at: " + createdAt.toString();
		
		return result;
	}
}
