package Main_System;

import java.time.LocalDate;
import java.util.ArrayList;

public class Post implements Cloneable{
	private User sender;
	private String subject;
	private String message;
	private LocalDate createdAt;
	private String type;
	private ArrayList<Post> replies;
	
	public Post() {
		sender = null;
		subject = "";
		message = "";
		createdAt = LocalDate.now();
		type = "";
		replies = new ArrayList<Post>();
	}
	
	public Post(User sender, String  subject, String message, String type) {
		this.sender = sender;
		this.subject = subject;
		this.message = message;
		this.createdAt = LocalDate.now();
		this.type = type;
		replies = new ArrayList<Post>();
	}
	
	public Post clone() throws CloneNotSupportedException 
    { 
        return (Post) super.clone(); 
    } 
	
	public String getSubject() {
		return subject;
	}
	
	public String getMessage() {
		return message;
	}
	
	public LocalDate getCreatedAt() {
		return createdAt;
	}
	
	public String getType() {
		return type;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String toString() {
		return "SENDER: " + sender +
				"\nSUBJECT: " + subject + 
				"\nMESSAGE: " + message +
				"\nDATE: " + createdAt;
	}
}
