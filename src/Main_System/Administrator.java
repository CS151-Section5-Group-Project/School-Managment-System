package Main_System;

import java.util.*;

public class Administrator extends User {
	private ArrayList<Announcement> announcements;
	
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
		announcements = new ArrayList<>();
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
	
	public void createAnnouncement(String subject, String message) {	
		Announcement a = new Announcement(subject, message);
		announcements.add(a);
		System.out.println("Announcement Created:\n" + a.toString() + "\n");
	}
	
	public ArrayList<Announcement> getAnnouncements() {
		return announcements;
	}
}
