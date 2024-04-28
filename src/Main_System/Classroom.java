package Main_System;

import java.util.ArrayList;

public class Classroom {
	private String name; // name of classroom
	private int capacity; // size of classroom
	private ArrayList<Course> courses = new ArrayList<Course>();
	
	public Classroom() {
		name = ""; // Name of classroom
		capacity = 0; // Size of classroom
	}
	
	public Classroom(String name, int capacity) {
		this.name = name;
		this.capacity = capacity;
	}
	
	public ArrayList<Course> getCourses() {
		return courses;
	}
	
	public int getCapacity() {
		return capacity;
	}
	
	public String getName() {
		return name;
	}
	
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void addCourse(Course course) {
		if (courses.contains(course)) {
			System.out.println("Course has already been assigned to this classroom.");
			return;
		}
		
		courses.add(course);
	}
	
	public void removeCourse(Course course) {
		if (!courses.contains(course)) {
			System.out.println("Course is not assigned to this classroom.");
			return;
		}
		
		course.setClassroom(null);
		courses.remove(course);
	}
	
	@Override
	public String toString() {
		return "Classroom Name: " + name + 
				"\nCapacity: " + capacity + 
				"\nCourses: " + courses.toString();
	}
}
