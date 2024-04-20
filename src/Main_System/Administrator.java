package Main_System;

import java.util.ArrayList;

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

}
