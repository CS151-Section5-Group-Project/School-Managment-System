package Main_System;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Course {
	private String name; // name of course
	private Teacher teacher; // teacher teaching the course
	private String term; // term of course
	private int unit; // unit of the course
	private Classroom classroom; // classroom the course is being taught at
	private LocalTime startTime; // time the course starts
	private LocalTime endTime; // time the course ends
	private ArrayList<DayOfWeek> days; // the days the course is taught at
	private ArrayList<Student> students; // the students enrolled
	private HashMap<Student, ArrayList<Assignment>> assignments; // the assignment given
	
	public Course() { // default constructor
		name = "";
		teacher = null;
		term = "";
		classroom = null;
		startTime = null;
		endTime = null;
		days = new ArrayList<DayOfWeek>();
		students = new ArrayList<Student>();
	}
	
	// constructor for classes with a time
	public Course(String name, Teacher teacher, String term, Classroom classroom, LocalTime startTime, LocalTime endTime, ArrayList<DayOfWeek> day) { 
		this.name = name;
		this.teacher = teacher;
		this.term = term;
		this.classroom = null;
		this.startTime = LocalTime.of(0, 0);
		this.endTime = LocalTime.of(0, 0);
		this.days = new ArrayList<DayOfWeek>();
		this.students = new ArrayList<Student>();
	}
	
	// constructor for classes without a time
	public Course(String name, Teacher teacher, String term) { // constructor for in person classes 
		this.name = name;
		this.teacher = teacher;
		this.term = term;
		this.classroom = null;
		this.startTime = null;
		this.endTime = null;
		this.days = new ArrayList<DayOfWeek>();
		this.students = new ArrayList<Student>();
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
	
	public ArrayList<Student> getStudents() {
		return students;
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
	
	public int getCourseSize() {
		return students.size();
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
	public ArrayList<Assignment> getAllAssignmentOfName(String assignmentName) {
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
		if (students.size() < 0) {
			System.out.println("No students have enrolled to this course. Add students to begin assigning assignments.");
			return null;
		}
		
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
	public HashMap<Student, Integer> findAssignment(String assignmentName) {
		HashMap<Student, Integer> assignmentPositions = new HashMap<Student, Integer>();
		
		// Check if there are students to assign assignments
		if (students.size() < 0) {
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
		for (Student student: students) {
			Inbox.sendPost(student, post.clone());
		}
	}
	
	public void gradeAssignment(Assignment assignment, int score) {
		assignment.gradeAssignment(score);
	}
	
	public void addAssignment(String assignmentName, int totalScore) {
		// Check if assignment already exist by name
		Entry<Student, ArrayList<Assignment>> firstEntry = assignments.entrySet().iterator().next();
		Student firstStudent = firstEntry.getKey();

		if (getAssignmentFromStudent(firstStudent, assignmentName) != null) {
			System.out.println("Assignment has already been added under this name. Choose a different name to add.");
			return;
		}
		
		for (Entry<Student, ArrayList<Assignment>> entry: assignments.entrySet()) {
			entry.getValue().add(new Assignment(entry.getKey(), assignmentName, totalScore));
		}
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
		if (students.size() == classroom.getCapacity()) {
			return true;
		}
		
		return false;
	}
	
	public void addStudent(Student student) {
		if (isFull()) {
			System.out.println("Course is full.");
			return;
		}
		
		students.add(student);
		assignments.put(student, new ArrayList<Assignment>());
	}
	
	public void addStudent(Student student, boolean override) {
		if (isFull() && !override) {
			System.out.println("Course is full.");
			return;
		}
		
		students.add(student);
		assignments.put(student, new ArrayList<Assignment>());
	}
	
	public void dropStudent(Student student) {
		if (!students.contains(student)) {
			System.out.println(student.getFullName() + " does not exist in this course");
			return;
		}
		
		students.remove(student);
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
				"\nLocation: " + classroom + 
				"\nTerm: " + term + 
				"\nTime: " + startTime + " - " + endTime + 
				"\nDays: " + days +
				"\nStudents enrolled: ";
		
		// convert student arraylist to contain only usernames
		for (int i = 0; i < students.size(); i++) {
			if (i == students.size() - 1) { // check if index is at last index (array size)
				result += students.get(i).getUserName();
				break;
			}
			
			result += students.get(i).getUserName() + ", ";
		}
		
		return result;
	}
}
