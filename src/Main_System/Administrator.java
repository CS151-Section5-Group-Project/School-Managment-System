package Main_System;

import java.util.Scanner;
import java.util.Map;

public class Administrator extends User {
	public enum Level {
		I,II
	}
	Level i;
	public Administrator() {
		super();
		i = null;
	}
	public Administrator(String last, String first, String user, String pass, int id, Level i) {
		super(last, first, user, pass, id);
		this.i = i;
	}
	public String levelNames() {
		switch(i) {
		case I:
			return "Moderator";
		case II:
			return "Administrator";
		default:
			return " ";
		}
	}
	public void setLevel(Level i) {
		this.i = i;
	}
	public String toString() {
		return super.toString() + "\nLevel: " + i;
	}
	
	/*
	 * Leo: Allows administrator to create posts and 
	 * depending on their type (Announcement or Message)
	 * will either add the post to every user in the Accounts class
	 * or add the post to a specific user
	 */
	public void createPost() {	
		Scanner scan = new Scanner(System.in);
		System.out.println("SUBJECT:");
		String subject = scan.nextLine();
        System.out.println("MESSAGE:");
        String message = scan.nextLine();
        System.out.println("(Announcement or Message) TYPE:");
        String type = scan.nextLine();
		Post a = new Post(subject, message, type);
		
		if (type.equals("Announcement")) {
			for(User account: Accounts.users.values()) {
				account.getInbox().add(a);
			}
			System.out.println("Post Created:\n" + a.toString() + "\n");
		} else if (type.equals("Message")){
			System.out.println("(username) TO:");
			String user = scan.nextLine();
			if (Accounts.users.containsKey(user)) {
				Accounts.users.get(user).getInbox().add(a);
				System.out.println("Post Created:\n" + a.toString() + "\n");
			}else {
				System.out.println("User does not exist.");
			}
		}else {
			System.out.println("Invalid type");
		}
	}
	public void removeUser(String user) {
		Scanner scan = new Scanner(System.in);
    		if (Accounts.users.containsKey(user)) {
    			System.out.println("Are you sure you want to delete this person?\n1. Yes\n2. No");
    			int confirm = scan.nextInt();
    			if (confirm == 1) {
    				Accounts.users.remove(user);
    				System.out.println("Removal Completed");
    			} else {
    				System.out.println("Removal Cancelled");
    			}
    		}
	}
}
