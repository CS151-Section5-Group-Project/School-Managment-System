package Main_System;

public class Teacher extends User{
	private String Courses;
	public Teacher() {
		super();
		Courses = " ";
	}
	public Teacher(String last, String first, String user, String pass, int id, String courses) {
		super(last, first, user, pass, id);
		Courses = courses;
	}
	public String getCourses() {
		return Courses;
	}
	public void setCourses(String courses) {
		Courses = courses;
	}
	public String toString() {
		return super.toString() + "\nCourses: " + Courses;
	}
}
