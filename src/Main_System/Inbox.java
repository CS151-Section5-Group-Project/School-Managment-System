package Main_System;

import java.util.ArrayList;

public class Inbox {
	private ArrayList<Post> received;
	private ArrayList<Post> sent;
	
	public Inbox() {
		received = new ArrayList<Post>();
		sent = new ArrayList<Post>();
	}
	
	public ArrayList<Post> getReceived() {
		return received;
	}
	
	public ArrayList<Post> getSent() {
		return sent;
	}
	
	public void deletePost(Post post) {
		if (!received.contains(post)) {
			System.out.println("Post not found.");
			return;
		}
		
		received.remove(post);
	}
	
	public void sendPost(User user, Post post) {
		user.getInbox().received.add(post);
	}
}
