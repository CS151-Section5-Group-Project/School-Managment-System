package Main_System;

import java.util.Map.Entry;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Administrator extends User {
	public Administrator() {
		super();
	}
	
	public Administrator(String firstName, String lastName, String userName, String password) {
		super(firstName, lastName, userName, password);
	}
	
	@Override
	public void onLogin() throws CloneNotSupportedException {
		while (true) {
			System.out.println("Enter one of the following commands: "
					+ "\n	\"1\"	View inbox"
					+ "\n	\"2\"	View user information"
					+ "\n	\"3\"	Create post"
					+ "\n	\"4\"	Add student account"
					+ "\n	\"5\"	Add teacher account"
					+ "\n	\"6\"	Add admin account"
					+ "\n	\"7\"	Add course"
					+ "\n	\"8\"	Remove user"
					+ "\n	\"9\"	View courses"
					+ "\n	\"10\"	View classrooms"
					+ "\n	\"11\"	View users"
					+ "\n	\"q\"	Logout");
			
	    	System.out.print("Enter Command: ");
	    	String input = Database.scanner.next();
	    	System.out.println();
	    	
	    	switch(input) {
	    	  case "1": {
	    		 viewInbox();
	    	     break;
	    	  }
	    	     
	    	  case "2": {
	    		 System.out.println(this.toString());
		    	 break;
	    	  }
		    	     
	    	  case "3": {
	    		 createPost();
	    	     break; 
	    	  }
	    	     
		    	     
	    	  case "4": {
	    		 addStudentAccount();
	    	     break; 
	    	  }
	    	     
	    	  
	    	  case "5": {
	    		  
	    	  }
	    	     addTeacherAccount();
	    	     break;
		    	     
	    	  case "6": {
	    		 addAdminAccount();
	    	     break; 
	    	  }
	    	     
	    	    
	    	  case "7": {
	    		 addCourse();
	    	     break; 
	    	  }
	    	     
	    	     
	    	  case "8": {
	    		 removeUser();
	    	     break;
	    	  }
	    	     
	    	  case "9": {
	    		 if (Database.getCourses().isEmpty()) {
	    			 System.out.println("No courses to display.\n");
	    			 break;
	    		 }
	    		 
	    		 System.out.println("Courses: " + Database.getCourses().toString());
	    	     break;
	    	  }
	    	  
	    	  case "10": {
	    		 System.out.println("Classrooms: " + Database.getClassrooms().toString());
	    	     break;
	    	  }
	    	  
	    	  case "11": {
	    		  System.out.println("All users:\n");
	    		  
	    		 for (Entry<String, User> entry: Database.getUsers().entrySet()) {
	    			 System.out.println(entry.getValue().toString() + "\n");
    			 }
	    		 
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
		
		System.out.println("Logging out of " + getUserName() + "...");
	}
	
	public static void addStudentAccount(String firstName, String lastName, String username, String password, LocalDate enrollmentDate) {
		Student student = new Student(firstName, lastName, username, password, enrollmentDate);
		Database.addAccount(student);
	}
	
	public void addStudentAccount() {
		String firstName = "";
		String lastName = "";
		String username = "";
		String password = "";
		int month = 0;
		int day = 0;
		int year = 0;
		LocalDate enrollmentDate = null;
		
		int state = 0;
		Database.scanner.nextLine();
		
		while (true) {
			if (state == 0) { // listening for first name
				System.out.println("Press \"q\" to exit.");
				System.out.print("Enter first name: ");
				firstName = Database.scanner.nextLine();
				System.out.println("");
				
				if (firstName.equals("q")) {
					break;
				}
				
				state++;
			}
			
			if (state == 1) { // listening for last name
				System.out.println("Press \"q\" to go back.");
				System.out.print("Enter last name: ");
				lastName = Database.scanner.nextLine();
				System.out.println("");
				
				if (lastName.equals("q")) {
					state--;
					continue;
				}
				
				state++;
			}
			
			if (state == 2) { // listening for username
				System.out.println("Press \"q\" to go back.");
				System.out.print("Enter username: ");
		        username = Database.scanner.nextLine();
		        System.out.println("");
				
		        if (username.equals("q")) {
					state--;
					continue;
				}
		        
		        if (Database.getUsers().containsKey(username)) {
		        	System.out.println("Username already exists. Choose another one.");
		        	continue;
		        }
		        
		        state++;
			}
			
			if (state == 3) { // listening for password
				System.out.println("Press \"q\" to go back.");
				System.out.print("Enter password: ");
		        password = Database.scanner.nextLine();
		        System.out.println("");
				
		        if (password.equals("q")) {
					state--;
					continue;
				}
		        
		        if (password.length() <= 4) {
					System.out.println("Password must be greater than 4 letters");
					continue;
				}
		        
		        state++;
			}
			
			if (state == 4) { // listening for month
				System.out.println("Press \"0\" to go back.");
				System.out.println("Enter the student's enrollment date.");
				System.out.print("Enter month (#): ");
		        month = Database.scanner.nextInt();
		        System.out.println("");
				
		        if (month == 0) {
					state--;
					continue;
				}
		        
		        if (month < 0 || month > 13) {
					System.out.println("Year must be greater than 0 and less than 13.");
					continue;
				}
		        
		        state++;
			}
			
			if (state == 5) { // listening for day
				System.out.println("Press \"0\" to go back.");
				System.out.print("Enter day (#): ");
		        day = Database.scanner.nextInt();
		        System.out.println("");
				
		        if (day == 0) {
					state--;
					continue;
				}
		        
		        if (day < 0) {
					System.out.println("Day must be greater than 0.");
					continue;
				}
		        
		        if (month % 2 != day % 2 && day > 29 && day < 32 || day > 31) { // check if day is 30 or 31 according to the month (excluding day 29 on february and leap year)
		        	System.out.print("Specified day is not within the month");
		        	continue;
		        }
		        
		        state++;
			}
			
			if (state == 6) { // listening for year
				System.out.println("Press \"0\" to go back.");
				System.out.print("Enter year (####): ");
		        month = Database.scanner.nextInt();
		        System.out.println("");
				
		        if (month == 0) {
					state--;
					continue;
				}
		        
		        if (month <= 1970) {
					System.out.println("Year must be greater than 1970.");
					continue;
				}
		        
		        enrollmentDate = LocalDate.of(year, month, day);
		        
		        addStudentAccount(firstName, lastName, username, password, enrollmentDate);
				break;
			}
		}
		
		System.out.println("Exiting addStudentAccount method.");
	}
	
	public static void addTeacherAccount(String firstName, String lastName, String username, String password) {
		Teacher teacher = new Teacher(firstName, lastName, username, password);
		Database.addAccount(teacher);
	}
	
	public void addTeacherAccount() {
		String firstName = "";
		String lastName = "";
		String username = "";
		String password = "";
		
		int state = 0;
		Database.scanner.nextLine();
		
		while (true) {
			if (state == 0) { // listening for first name
				System.out.println("Press \"q\" to exit.");
				System.out.print("Enter first name: ");
				firstName = Database.scanner.nextLine();
				System.out.println("");
				
				if (firstName.equals("q")) {
					break;
				}
				
				state++;
			}
			
			if (state == 1) { // listening for last name
				System.out.println("Press \"q\" to go back.");
				System.out.print("Enter last name: ");
				lastName = Database.scanner.nextLine();
				System.out.println("");
				
				if (lastName.equals("q")) {
					state--;
					continue;
				}
				
				state++;
			}
			
			if (state == 2) { // listening for username
				System.out.println("Press \"q\" to go back.");
				System.out.print("Enter username: ");
		        username = Database.scanner.nextLine();
		        System.out.println("");
				
		        if (username.equals("q")) {
					state--;
					continue;
				}
		        
		        if (Database.getUsers().containsKey(username)) {
		        	System.out.println("Username already exists. Choose another one.");
		        	continue;
		        }
		        
		        state++;
			}
			
			if (state == 3) { // listening for password
				System.out.println("Press \"q\" to go back.");
				System.out.print("Enter password: ");
		        password = Database.scanner.nextLine();
		        System.out.println("");
				
		        if (password.equals("q")) {
					state--;
					continue;
				}
		        
		        if (password.length() <= 4) {
					System.out.println("Password must be greater than 4 letters");
					continue;
				}
		        
		        addTeacherAccount(firstName, lastName, username, password);
				break;
			}
		}
		
		System.out.println("Exiting addTeacherAccount method."); 
	}
	
	public static void addAdminAccount(String firstName, String lastName, String username, String password) {
		Administrator administrator = new Administrator(firstName, lastName, username, password);
		Database.addAccount(administrator);
	}
	
	public void addAdminAccount() {
		String firstName = "";
		String lastName = "";
		String username = "";
		String password = "";
		
		int state = 0;
		
		while (true) {
			if (state == 0) { // listening for first name
				System.out.println("Press \"q\" to exit.");
				System.out.print("Enter first name: ");
				firstName = Database.scanner.nextLine();
				System.out.println("");
				
				if (firstName.equals("q")) {
					break;
				}
				
				state++;
			}
			
			if (state == 1) { // listening for last name
				System.out.println("Press \"q\" to go back.");
				System.out.print("Enter last name: ");
				lastName = Database.scanner.nextLine();
				System.out.println("");
				
				if (lastName.equals("q")) {
					state--;
					continue;
				}
				
				state++;
			}
			
			if (state == 2) { // listening for username
				System.out.println("Press \"q\" to go back.");
				System.out.print("Enter username: ");
		        username = Database.scanner.nextLine();
		        System.out.println("");
				
		        if (username.equals("q")) {
					state--;
					continue;
				}
		        
		        if (Database.getUsers().containsKey(username)) {
		        	System.out.println("Username already exists. Choose another one.");
		        	continue;
		        }
		        
		        state++;
			}
			
			if (state == 3) { // listening for password
				System.out.println("Press \"q\" to go back.");
				System.out.print("Enter password: ");
		        password = Database.scanner.nextLine();
		        System.out.println("");
				
		        if (password.equals("q")) {
					state--;
					continue;
				}
		        
		        if (password.length() <= 4) {
					System.out.println("Password must be greater than 4 letters");
					continue;
				}
		        
		        addAdminAccount(firstName, lastName, username, password);
				break;
			}
		}
		
		System.out.println("Exiting addAdministratorAccount method."); 
	}
	
	public static void addCourse() {
		String confirmation = "";
		String name = "";
		String teacherUsername = "";
		Teacher teacherObject = null;
		String term = "";
		String classroom = "";
		Classroom classroomObject = null;
		int startHour = 0;
		int startMin = 0;
		int endHour = 0;
		int endMin = 0;
		int dayOfTheWeek = 0;
		ArrayList<DayOfWeek> dayOfWeeks = new ArrayList<DayOfWeek>();
		
		int state = 0;
		Database.scanner.nextLine();
		
		while (true) {
			if (state == 0) { // listening for confirmation
				System.out.println("Press \"q\" to exit.");
				System.out.print("Is there a time assigned to this course? Enter (Y/N): ");
				confirmation = Database.scanner.nextLine();
				System.out.println("");
				
				if (confirmation.equals("q")) {
					break;
				}
				
				state++;
			}
			
			if (state == 1) { // listening for course name
				System.out.println("Press \"q\" to go back.");
				System.out.print("Enter name of course: ");
				name = Database.scanner.nextLine();
				System.out.println("");
				
				if (term.equals("q")) {
					state--;
					continue;
				}
				
				state++;
			}
			
			if (state == 2) { // listening for teacher name
				System.out.println("Press \"q\" to go back.");
				System.out.print("Enter username of teacher: ");
		        teacherUsername = Database.scanner.nextLine();
		        System.out.println("");
				
		        if (teacherUsername.equals("q")) {
					state--;
					continue;
				}
		        
		        if (!Database.containUser(teacherUsername)) { // check if such teacher exist
		        	System.out.println("No teacher exists under " + teacherUsername);
		        	continue;
		        }
		        
		        if (!(Database.getUser(teacherUsername) instanceof Teacher)) { // check if user is a teacher
		        	System.out.println(teacherUsername + " is not a teacher.");
		        	continue;
		        }
		        
		        teacherObject = (Teacher) Database.getUsers().get(teacherUsername);
		        
		        state++;
			}
			
			if (state == 3) { // listening for term
				System.out.println("Press \"q\" to go back.");
				System.out.print("Enter term: ");
		        term = Database.scanner.nextLine();
		        System.out.println("");
				
		        if (term.equals("q")) {
					state--;
					continue;
				}
		        
		        if (confirmation.equals("N")) {
		        	addCourse(name, teacherObject, term);
		        	break;
		        }
		        
		        state++;
			}
			
			if (state == 4) { // listening for classroom
				System.out.println("Press \"q\" to go back.");
				System.out.print("Enter classroom: ");
		        classroom = Database.scanner.nextLine();
		        System.out.println("");
				
		        if (term.equals("q")) {
					state--;
					continue;
				}
		        
		        if (!Database.containClassroom(classroom)) {
		        	System.out.println("Classroom not found.");
		        	continue;
		        }
		        
		        classroomObject = Database.getClassroom(classroom);
		        state++;
			}
			
			if (state == 5) { // listening for start Hour
				System.out.println("Press \"-1\" to go back.");
				System.out.print("Enter start hour (0-23): ");
		        startHour = Database.scanner.nextInt();
		        System.out.println("");
				
		        if (startHour == -1) {
					state--;
					continue;
				}
		        
		        if (startHour < 0 || startHour > 23) {
		        	System.out.println("Hour must be between 0-23");
		        	continue;
		        }
		        
		        state++;
			}
			
			if (state == 6) { // listening for start Minute
				System.out.println("Press \"-1\" to go back.");
				System.out.print("Enter start minute (0-59): ");
		        startMin = Database.scanner.nextInt();
		        System.out.println("");
				
		        if (startMin == -1) {
					state--;
					continue;
				}
		        
		        if (startMin < 0 || startMin > 59) {
		        	System.out.println("Minute must be between 0-23");
		        	continue;
		        }
		        
		        state++;
			}
			
			if (state == 7) { // listening for end Hour
				System.out.println("Press \"-1\" to go back.");
				System.out.print("Enter start hour (0-23): ");
		        endHour = Database.scanner.nextInt();
		        System.out.println("");
				
		        if (endHour == -1) {
					state--;
					continue;
				}
		        
		        if (endHour < 0 || endHour > 23) {
		        	System.out.println("Hour must be between 0-23");
		        	continue;
		        }
		        
		        state++;
			}
			
			if (state == 8) { // listening for end Minute
				System.out.println("Press \"-1\" to go back.");
				System.out.print("Enter start minute (0-59): ");
		        endMin = Database.scanner.nextInt();
		        System.out.println("");
				
		        if (endMin == -1) {
					state--;
					continue;
				}
		        
		        if (endMin < 0 || endMin > 59) {
		        	System.out.println("Minute must be between 0-23");
		        	continue;
		        }
		        
		        state++;
			}
			
			if (state == 9) { // listening for day of the week
				System.out.println("Press \"-1\" to go back.");
				System.out.print("Enter the number corresponding to the day of the week (1 = SUNDAY, ..., 7 = SATURDAY) [Enter 0 to confirm]: ");
		        dayOfTheWeek = Database.scanner.nextInt();
		        System.out.println("");
				
		        switch (dayOfTheWeek) {
					case 1: {
						if (dayOfWeeks.contains(DayOfWeek.SUNDAY)) {
							dayOfWeeks.remove(DayOfWeek.SUNDAY);
							System.out.println("Removed SUNDAY");
							break;
						}
						
						dayOfWeeks.add(DayOfWeek.SUNDAY);
						System.out.println("Added SUNDAY");
						break;
					}
					
					case 2: {
						if (dayOfWeeks.contains(DayOfWeek.MONDAY)) {
							dayOfWeeks.remove(DayOfWeek.MONDAY);
							System.out.println("Removed MONDAY");
							break;
						}
						
						dayOfWeeks.add(DayOfWeek.MONDAY);
						System.out.println("Added MONDAY");
						break;
					}
					
					case 3: {
						if (dayOfWeeks.contains(DayOfWeek.TUESDAY)) {
							dayOfWeeks.remove(DayOfWeek.TUESDAY);
							System.out.println("Removed TUESDAY");
							break;
						}
						
						dayOfWeeks.add(DayOfWeek.TUESDAY);
						System.out.println("Added TUESDAY");
						break;
					}
					
					case 4: {
						if (dayOfWeeks.contains(DayOfWeek.WEDNESDAY)) {
							dayOfWeeks.remove(DayOfWeek.WEDNESDAY);
							System.out.println("Removed WEDNESDAY");
							break;
						}
						
						dayOfWeeks.add(DayOfWeek.WEDNESDAY);
						System.out.println("Added WEDNESDAY");
						break;
					}
					
					case 5: {
						if (dayOfWeeks.contains(DayOfWeek.THURSDAY)) {
							dayOfWeeks.remove(DayOfWeek.THURSDAY);
							System.out.println("Removed THURSDAY");
							break;
						}
						
						dayOfWeeks.add(DayOfWeek.THURSDAY);
						System.out.println("Added THURSDAY");
						break;
					}
					
					case 6: {
						if (dayOfWeeks.contains(DayOfWeek.FRIDAY)) {
							dayOfWeeks.remove(DayOfWeek.FRIDAY);
							System.out.println("Removed FRIDAY");
							break;
						}
						
						dayOfWeeks.add(DayOfWeek.FRIDAY);
						System.out.println("Added FRIDAY");
						break;
					}
					
					case 7: {
						if (dayOfWeeks.contains(DayOfWeek.SATURDAY)) {
							dayOfWeeks.remove(DayOfWeek.SATURDAY);
							System.out.println("Removed SATURDAY");
							break;
						}
						
						dayOfWeeks.add(DayOfWeek.SATURDAY);
						System.out.println("Added SATURDAY");
						break;
					}
					
					case -1: {
						state--;
						break;
					}

					case 0: {
						
						
						state++;
						break;
					}
					
					default: {
						System.out.println("Invalid number/days.");
					}
		        }
		        
		        System.out.println("Day of the week: " + dayOfWeeks.toString());
			}
			
			if (state == 10) { // Forming needed arguments
				LocalTime startTime = LocalTime.of(startHour, startMin);
				LocalTime endTime = LocalTime.of(endHour, endMin);
				
				Boolean success = addCourse(name, teacherObject, term, classroomObject, startTime, endTime, dayOfWeeks);
				
				if (!success) {
					System.out.println("Adding course unsuccessful, returning to previous option.");
					state--;
					continue;
				}
				
				break;
			}
		}
		
		System.out.println("Exiting addAdministratorAccount method."); 
	}
	
	public static boolean addCourse(String name, Teacher teacher, String term, Classroom classroom, LocalTime startTime, LocalTime endTime, ArrayList<DayOfWeek> day) {
		Course course = new Course(name, teacher, term, classroom, startTime, endTime, day);
		return Database.addCourse(course);
	}
	
	public static boolean addCourse(String name, Teacher teacher, String term) {
		Course course = new Course(name, teacher, term);
		return Database.addCourse(course);
	}
	
	public Post sendAnnouncement(String subject, String message) throws CloneNotSupportedException {
		Post post = new Post(this, subject, message, "announcement");
		
		for (Entry<String, User> entry: Database.getUsers().entrySet()) {
			Inbox.sendPost(entry.getValue(), post.clone());
		}
		
		return post;
	}
	
	public void removeUser() {
		String username = "";
		String confirmation = "";
		
		Database.scanner.nextLine();
		
		while (true) {
			System.out.println("Press \"q\" to exit.");
			System.out.print("Enter username to remove: ");
			username = Database.scanner.nextLine();
			System.out.println("");
			
			if (username.equals("q")) {
				break;
			}
			
			if (!Database.getUsers().containsKey(username)) {
				System.out.println("User not found.");
				continue;
			}
			
			if (Database.getUsers().get(username) == this) {
				System.out.println("You cannot remove the account you are currently logged into.");
				continue;
			}
			
			while (!confirmation.equals("N") && !confirmation.equals("Y")) {
				System.out.print("Are you sure you want to remove " + username + "? Enter (Y to confirm/N to return): ");
				confirmation = Database.scanner.nextLine();
				System.out.println("");
				
				if (!confirmation.equals("N") && !confirmation.equals("Y")) {
					System.out.println("Invalid command.");
				}
			}
			
			if (confirmation.equals("Y")) {
				System.out.println("Removing " + username + "...");
				Database.getUsers().remove(username);
				break;
			}
		}
		
		System.out.println("Exiting removeUser method...");
	}
	
	public void createPost() throws CloneNotSupportedException {
		String receiverUsername = "";
		User receiverObjectFound = null;
		String subject = "";
		String message = "";
		String postType = "";
		
		int state = 0;
		Database.scanner.nextLine();
		
		while (true) {
	    	if (state == 0) { // listening for type of post
	    		System.out.println("Press \"q\" to exit.");
				System.out.print("Enter type (message or announcement): ");
				
		        postType = Database.scanner.nextLine();
		        System.out.println("");
		        
		        if (postType.equals("q")) {
					break;
				}
		        
		        if (!postType.equals("message") && !postType.equals("announcement")) {
		        	System.out.println("The following choice is not valid. Try again.");
		        	continue;
		        }
		        
		        state++;
			}
	    	
			if (state == 1) { // listening for receiver (by Username)
				System.out.println("Press \"q\" to go back.");
				System.out.print("Enter username to send to: ");
				receiverUsername = Database.scanner.nextLine();
				System.out.println();
				
				if (receiverUsername.equals("q")) {
					state--;
					continue;
				}
				
				if (!Database.getUsers().containsKey(receiverUsername)) { // check if user exist
					System.out.println("User does not exist.");
					continue;
				}
				
				receiverObjectFound = Database.getUsers().get(receiverUsername);
				
				state++;
			}
			
			if (state == 2) { // listening for subject
				System.out.println("Press \"q\" to go back.");
				System.out.print("Enter subject: ");
				subject = Database.scanner.nextLine();
				System.out.println("");
				
				state++;
			}
			
			if (state == 3) { // listening for message
				System.out.println("Press \"q\" to go back.");
				System.out.print("Enter message: ");
		        message = Database.scanner.nextLine();
		        System.out.println("");
		        
		        if (message.equals("q")) {
		        	state--;
					continue;
				}
		        
		        if (postType.equals("message")) {
		        	Post post = sendPost(receiverObjectFound, subject, message);
					
					System.out.println("Message sent: \n" + post.toString() + "\n");
		        	break;
		        }
		        
		        if (postType.equals("announcement")) {
		        	Post post = sendAnnouncement(subject, message);
					
					System.out.println("Message sent: \n" + post.toString() + "\n");
		        	break;
		        }
			}
		}
		
		System.out.println("Exiting createPost method.\n");
	}
}
