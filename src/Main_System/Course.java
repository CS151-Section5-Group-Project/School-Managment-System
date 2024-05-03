package Main_System;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Course {
	private static String name; // name of course
	private Teacher teacher; // teacher teaching the course
	private String term; // term of course
	private int unit; // unit of the course
	private Classroom classroom; // classroom the course is being taught at
	private LocalTime startTime; // time the course starts
	private LocalTime endTime; // time the course ends
	private ArrayList<DayOfWeek> days; // the days the course is taught at
	private static HashMap<Student, ArrayList<Assignment>> assignments; // the assignment given
	
	public Course() { // default constructor
		name = "";
		teacher = null;
		term = "";
		classroom = null;
		startTime = null;
		endTime = null;
		days = new ArrayList<DayOfWeek>();
		assignments = new HashMap<Student, ArrayList<Assignment>>();
		
		if (!Database.containCourse(this)) {
			Database.addCourse(this);
		}
	}
	
	// constructor for classes with a time
	public Course(String name, Teacher teacher, String term, Classroom classroom, LocalTime startTime, LocalTime endTime, ArrayList<DayOfWeek> day) { 
		Course.name = name;
		this.teacher = teacher;
		this.term = term;
		this.classroom = classroom;
		this.startTime = startTime;
		this.endTime = endTime;
		this.days = day;
		assignments = new HashMap<Student, ArrayList<Assignment>>();
		
		if (!Database.containCourse(this)) {
			Database.addCourse(this);
			teacher.getCourses().add(this);
		}
	}
	
	// constructor for classes without a time
	public Course(String name, Teacher teacher, String term) { // constructor for in person classes 
		Course.name = name;
		this.teacher = teacher;
		this.term = term;
		classroom = null;
		startTime = null;
		endTime = null;
		days = new ArrayList<DayOfWeek>();
		assignments = new HashMap<Student, ArrayList<Assignment>>();
		
		if (!Database.containCourse(this)) {
			Database.addCourse(this);
			teacher.getCourses().add(this);
		}
	}
	
	public static HashMap<Student, ArrayList<Assignment>> getAssignments() {
		return assignments;
	}
	
	public int getUnit() {
		return unit;
	}
	
	public Classroom getClassroom() {
		return classroom;
	}
	
	public ArrayList<DayOfWeek> getDays() {
		return days;
	}
	
	public LocalTime getEndTime() {
		return endTime;
	}
	
	public String getName() {
		return name;
	}
	
	public LocalTime getStartTime() {
		return startTime;
	}
	
	public Teacher getTeacher() {
		return teacher;
	}
	
	public String getTerm() {
		return term;
	}
	
	public static int getCourseSize() {
		return assignments.size();
	}
	
	public boolean containStudent(String studentUsername) {
		User user = Database.getUser(studentUsername);
		
		if (user == null) {
			return false;
		}
		
		Student student = (Student) user;
		
		if (!assignments.containsKey(student)) {
			System.out.println("Student is not enrolled in this course");
			return false;
		}
		
		return true;
	}
	
	public Student getStudent(String studentUsername) {
		boolean studentInCourse = containStudent(studentUsername);
		
		if (!studentInCourse) {
			return null;
		}
		
		return (Student) Database.getUser(studentUsername);
	}
	
	public double getStudentGrade(Student student) {
		ArrayList<Assignment> AssignmentList = assignments.get(student);
		
		int totalScore = 0;
		int gradedScore = 0;
		
		for (Assignment assignment: AssignmentList) {
			totalScore += assignment.getTotalScore();
			gradedScore += assignment.getGradedScore();
		}
		
		return ((double)gradedScore/totalScore) * 100;
	}
	
	public double getCourseAverageScore() {
		double average = 0.0;
		double count = 0;
		
		for (Entry<Student, ArrayList<Assignment>> entry: assignments.entrySet()) {
			count++;
			average += getStudentGrade(entry.getKey());
		}
		
		if (count == 0) {
			System.out.println("No student found.");
			return 0.0;
		}
		
		return average/count;
	}
	
	public double getAssignmentAverageScore(String assignmentName) {
		ArrayList<Assignment> assignmentList = getAllAssignmentOfName(assignmentName);
		
		double average = 0.0;
		double count = 0;
		
		for (Assignment assignment: assignmentList) {
			count++;
			average += assignment.getPercentScore();
		}
		
		if (count == 0) {
			System.out.println("No student found.");
			return 0.0;
		}
		
		return average/count;
	}
	
	// Returns all assignment under the passed name
	public static ArrayList<Assignment> getAllAssignmentOfName(String assignmentName) {
		ArrayList<Assignment> assignmentListResult = new ArrayList<Assignment>();
		HashMap<Student, Integer> assignmentPositions = findAssignment(assignmentName);
		
		for (Entry<Student, Integer> entry: assignmentPositions.entrySet()) {
			ArrayList<Assignment> assignmentList = assignments.get(entry.getKey());
			
			assignmentListResult.add(assignmentList.get(entry.getValue().intValue()));
		}
		
		return assignmentListResult;
	}
	
	// Returns the assignment from student when found
	public Assignment getAssignmentFromStudent(Student student, String assignmentName) {
		// Check if there are students to assign assignments
		ArrayList<Assignment> AssignmentList = assignments.get(student);
		
		for (Assignment assignment: AssignmentList) {
			if (assignment.getName() == assignmentName) {
				// Assignment found
				return assignment;
			}
		}
		
		System.out.println("Assignment not found.");
		return null;
	}
	
	// Returns the position where the assignment is found
	public static HashMap<Student, Integer> findAssignment(String assignmentName) {
		HashMap<Student, Integer> assignmentPositions = new HashMap<Student, Integer>();
		
		// Check if there are students to assign assignments
		if (getCourseSize() < 0) {
			System.out.println("No students have enrolled to this course. Add students to use this function.");
			return assignmentPositions;
		}
		
		for (Entry<Student, ArrayList<Assignment>> entry: assignments.entrySet()) {
			Student student = entry.getKey();
			ArrayList<Assignment> assignmentList = entry.getValue();
			
			int PositionFound = -1;
			
			for (Assignment assignment: assignmentList) {
				PositionFound++;
				
				if (assignment.getName() == name) {
					// assignment found
					assignmentPositions.put(student, PositionFound);
					break;
				}
			}
		}
		
		return assignmentPositions;
	}
	
	public void sendAnnouncement(Post post) throws CloneNotSupportedException {
		for (Entry<Student, ArrayList<Assignment>> entry: assignments.entrySet()) {
			Inbox.sendPost(entry.getKey(), post.clone());
		}
	}
	
	public void gradeAssignment(Assignment assignment, int score) {
		assignment.gradeAssignment(score);
	}
	
	public boolean addAssignment(String assignmentName, String assignmentDescription, int totalScore) {
		// Check if assignment already exist by name
		for (Entry<Student, ArrayList<Assignment>> entry: assignments.entrySet()) {
			if (getAssignmentFromStudent(entry.getKey(), assignmentName) != null) {
				System.out.println("Assignment has already been added under this name. Choose a different name to add.");
				return false;
			}
		}
		
		for (Entry<Student, ArrayList<Assignment>> entry: assignments.entrySet()) {
			entry.getValue().add(new Assignment(entry.getKey(), this, assignmentName, assignmentDescription, totalScore));
		}
		
		return true;
	}
	
	public void removeAssignment(String assignmentName) {
		HashMap<Student, Integer> assignmentPositions = findAssignment(assignmentName);
		
		for (Entry<Student, Integer> entry: assignmentPositions.entrySet()) {
			ArrayList<Assignment> assignmentList = assignments.get(entry.getKey());
			
			assignmentList.remove(entry.getValue().intValue());
		}
	}
	
	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}
	
	public void addDay(DayOfWeek day) {
		if (days.contains(day)) {
			System.out.println(day.toString() + " already exist in this course.");
			return;
		}

		days.add(day);
	}
	
	public void removeDay(DayOfWeek day) {
		if (!days.contains(day)) {
			System.out.println(day.toString() + " no day exist in this course.");
			return;
		}
		
		days.remove(day);
	}
	
	public boolean isFull() {
		if (getCourseSize() == classroom.getCapacity()) {
			return true;
		}
		
		return false;
	}
	
	public void addStudent(Student student) {
		if (isFull()) {
			System.out.println("Course is full.");
			return;
		}
		
		assignments.put(student, new ArrayList<Assignment>());
	}
	
	public void addStudent(Student student, boolean override) {
		if (isFull() && !override) {
			System.out.println("Course is full.");
			return;
		}
		
		assignments.put(student, new ArrayList<Assignment>());
	}
	
	public void dropStudent(Student student) {
		if (!assignments.containsKey(student)) {
			System.out.println(student.getFullName() + " does not exist in this course");
			return;
		}
		
		assignments.remove(student);
	}
	
	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Course)) {
			return false;
		}
		
		Course course = (Course) object;
		
		if (course.toString().equals(this.toString())) {
			return true;
		}
		
		return false;
	}
	
	
	
	@Override
	public String toString() {
		String result = "Course Name: " + name + 
				"\nInstructor Teaching: " + teacher.getFullName() + 
				"\nLocation: " + classroom.getName() + 
				"\nTerm: " + term + 
				"\nTime: " + startTime.toString() + " - " + endTime.toString() + 
				"\nDays: " + days.toString() +
				"\nStudents enrolled: ";
		
		// convert student arraylist to contain only usernames
		if (assignments.isEmpty()) {
			result += "No student enrolled";
		} else {
			for (Entry<Student, ArrayList<Assignment>> entry: assignments.entrySet()) {
				result += entry.getKey().getFirstName() + ", ";
			}
		}
		
		return result;
	}
}
