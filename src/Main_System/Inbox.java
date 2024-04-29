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
	
	public void viewInbox() {
		System.out.println("All Inbox:\n");
		for (Post post: received) {
			System.out.println(post.toString() + "\n");
		}
	}
	
	public void deletePost(Post post) {
		if (!received.contains(post)) {
			System.out.println("Post not found.\n");
			return;
		}
		
		received.remove(post);
	}
	
	public static void sendPost(User user, Post post) {
		user.getInbox().received.add(post);
	}
	
	public void replyPost(Post receivedPost, Post post) throws Exception {
		if (received.contains(receivedPost)) {
			throw new Exception("Calling function with received post that is outside of inbox");
		}
		
		receivedPost.getReplies().add(post);
	}
}
