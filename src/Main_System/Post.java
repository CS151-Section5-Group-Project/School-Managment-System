package Main_System;

import java.time.LocalDate;

public class Post {
	private String subject;
	private String message;
	private LocalDate createdAt;
	private String type;
	
	public Post() {
		
	}
	
	public Post(String subject, String message, String type) {
		this.subject = subject;
		this.message = message;
		this.createdAt = LocalDate.now();
		this.type = type;
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
		return "SUBJECT: " + subject + 
				"\nDATE: " + createdAt + 
				"\nMESSAGE: " + message;
	}
}
