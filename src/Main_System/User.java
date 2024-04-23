package Main_System;

import java.util.ArrayList;

public abstract class User {
	private String last_name;
	private String first_name;
	private String user_name;
	private String password;
	private int ID;
	private ArrayList<Post> inbox;
	
	public User() {
		last_name = " ";
		first_name = " ";
		user_name = " ";
		password = " ";
		ID = 0;
		inbox = new ArrayList<Post>();
	}
	public User(String last, String first, String user, String pass, int Id) {
		last_name = last;
		first_name = first;
		user_name = user;
		password = pass;
		ID = Id;
		inbox = new ArrayList<Post>();
	}
	public String getLastName() {
		return last_name;
	}
	public String getFirstName() {
		return first_name;
	}
	public String getUserName() {
		return user_name;
	}
	public String getPassword() {
		return password;
	}
	public int getID() {
		return ID;
	}
	public ArrayList<Post> getInbox(){
		return inbox;
	}
	public void setLastName(String last) {
		last_name = last;
	}
	public void setFirstName(String first) {
		first_name = first;
	}
	public void setUser(String user) {
		user_name = user;
	}
	public void setPass(String pass) {
		password = pass;
	}
	public void setID(int id) {
		ID = id;
	}
	public String toString() {
		return "First Name: " + first_name + "\nLast Name: " + last_name + "\nID: " + ID;
	}
	
	public void viewInbox() {
		if (inbox.isEmpty()) {
			System.out.println("No posts currently in inbox.");
		}else {
			for (Post p: inbox) {
				System.out.println(p.toString() + "\n");
			}
		}
	}
}
