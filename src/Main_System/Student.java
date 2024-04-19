package Main_System;

public class Student extends User {
	private double GPA;
	private String yearClass;
	public Student() {
		super();
		GPA = 0.0;
		yearClass = " ";
	}
	public Student(String last, String first, String user, String pass, int id, double Gpa, String Class) {
		super(last, first, user, pass, id);
		GPA = Gpa;
		yearClass = Class;
	}
	public double getGPA() {
		return GPA;
	}
	public String getYearClass() {
		return yearClass;
	}
	public void setGPA(double gpa) {
		GPA = gpa;
	}
	public void setYearClass(String year) {
		yearClass = year;
	}
	public String toString() {
		return super.toString() + "\nGPA: " + GPA + "\nClass: " + yearClass;
	}
}
