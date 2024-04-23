package Main_System;

import java.time.LocalDate;

public class Announcement {
	private String subject;
	private String message;
	private LocalDate createdAt;
	
	public Announcement() {
		
	}
	
	public Announcement(String subject, String message) {
		this.subject = subject;
		this.message = message;
		this.createdAt = LocalDate.now();
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
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String toString() {
		return "SUBJECT: " + subject + 
				"\nDATE: " + createdAt + 
				"\nMESSAGE: " + message;
	}
}
