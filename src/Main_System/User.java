package Main_System;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class User {
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private int id;
	private Inbox inbox;
	private LocalDate createdAt;
	
	public User() {
		firstName = "";
		lastName = "";
		userName = "";
		password = "";
		int id = 0;
		inbox = new Inbox();
		createdAt = LocalDate.now();
	}
	
	public User(String firstName, String lastName, String userName, String password, int id) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.id = id;
		inbox = new Inbox();
		createdAt = LocalDate.now();
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public int getId() {
		return id;
	}
	
	public Inbox getInbox() {
		return inbox;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public String getFullName() {
		return getFirstName() + " " + getLastName();
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setID(int iD) {
		this.id = iD;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Override
	public String toString() {
		return "First Name: " + firstName + 
				"\nLast Name: " + lastName + 
				"Username: " + userName +
				"\nID: " + id;
	}
	
	// Leo: Prints out the posts that is the inbox ArrayList 
	public void viewInbox() {
		if (inbox.getReceived().isEmpty()) {
			System.out.println("No posts currently in inbox.");
		}else {
			for (Post p: inbox.getReceived()) {
				System.out.println(p.toString() + "\n");
			}
		}
	}
	
	public void sendPost(User user, String subject, String message) {
		inbox.sendPost(user, new Post(subject, message, "message"));
	}
	
	// abstract void createPost();
}
