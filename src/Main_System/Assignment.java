package Main_System;

import java.time.LocalDate;

public class Assignment {
	private Student owner;
	private String name;
	private int totalScore;
	private int gradedScore;
	private boolean graded;
	private LocalDate createdAt;
	
	// default constructor
	public Assignment() {
		owner = null;
		name = "";
		totalScore = 0;
		gradedScore = 0;
		graded = false;
		createdAt = LocalDate.now();
	}
	
	// constructor for ungraded assignments
	public Assignment(Student owner, String name, int totalScore) {
		this.owner = owner;
		this.name = name;
		this.totalScore = totalScore;
		this.gradedScore = 0;
		graded = false;
		createdAt = LocalDate.now();
	}
	
	// constructor for graded assignments
	public Assignment(Student owner, String name, int totalScore, int gradedScore) {
		this.name = name;
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
				"\nAssignment Name: " + name + 
				"\nAssignment Total Score: " + totalScore +
				"\nAssignment Graded Score: ";
		
		if (graded == false) {
			result += "Ungraded";
		} else {
			result += gradedScore;
		}
		
		return result;
	}
}
