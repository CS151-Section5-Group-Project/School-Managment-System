package Main_System;

import java.time.LocalDate;

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
		id = Database.getUsers().size();
		inbox = new Inbox();
		createdAt = LocalDate.now();
	}
	
	public User(String firstName, String lastName, String userName, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.id = Database.getUsers().size();;
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
		return "Full name: " + getFullName() +
				"\nUsername: " + userName +
				"\nID: " + id +
				"\nAccount created at: " + createdAt;
	}
	
	public void viewInbox() {
		if (inbox.getReceived().isEmpty()) {
			System.out.println("No posts currently in inbox.");
			return;
		}
		
		inbox.viewInbox();
	}
	
	public Post sendPost(User user, String subject, String message) {
		Post post = new Post(this, subject, message, "message");
		
		Inbox.sendPost(user, post);
		
		return post;
	}
	
	public void createPost() throws CloneNotSupportedException {
		String receiver = "";
		User receiverObject = null;
		String subject = "";
		String message = "";
		
		int state = 0;
		
		while (true) {
			if (state == 0) { // listening for sender
				System.out.println("Press \"q\" to exit.");
				System.out.print("Enter username to send to: ");
				receiver = Database.scanner.nextLine();
				System.out.println("\n");
				
				if (receiver.equals("q")) {
					break;
				}
				
				if (!Database.getUsers().containsKey(receiver)) { // check if user exist
					System.out.println("User does not exist.");
					continue;
				}
				
				receiverObject = Database.getUsers().get(receiver);
				
				state++;
			}
			
			if (state == 1) { // listening for subject
				System.out.println("Press \"q\" to go back.");
				System.out.print("Enter subject: ");
				subject = Database.scanner.nextLine();
				System.out.println("\n");
				
				if (receiver.equals("q")) {
					state--;
					continue;
				}
				
				state++;
			}
			
			if (state == 2) { // listening for message
				System.out.println("Press \"q\" to go back.");
				System.out.print("Enter message:");
		        message = Database.scanner.nextLine();
		        System.out.println("\n");
				
		        if (receiver.equals("q")) {
					state--;
					continue;
				}
		        
				Post post = sendPost(receiverObject, subject, message);
				
				System.out.println("Message sent: \n" + post);
				break;
			}
		}
		
		System.out.println("Exiting create post method...");
	}

	public abstract void onLogin() throws CloneNotSupportedException;
}
