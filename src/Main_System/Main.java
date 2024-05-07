package Main_System;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        Database.initialize2();
        boolean firstVisit = false;
        
        System.out.println("Press \"q\" to terminate console during user selection.");
	    
	    while (true) {
	    	String username = "";
	    	String password = "";
	    	User currentUser = null;
	    	boolean loginSuccess = false;
	    	
	    	if (firstVisit) {
	    		Database.scanner.nextLine();
	    	}
	    	
	    	while (currentUser == null) {
	    		System.out.print("Enter username: ");
				username = InputHandler.promptLine();
				System.out.println();
				
				if (username.equals("q")) {
					break;
				}
				
				if (!Database.getUsers().containsKey(username)) { // check if user exist
					System.out.println("User does not exist.");
				}
				
				currentUser = Database.getUsers().get(username);
			}
	    	
	    	if (currentUser == null) {
	    		break;
	    	}
	    	
	    	System.out.println("Enter \"q\" to switch to another user.");
	    	
	    	while (!password.equals(currentUser.getPassword())) {
	    		System.out.print("Enter password: ");
				password = InputHandler.promptLine();
				System.out.println();
				
				if (password.equals("q")) {
					System.out.println("Choose another username to login to.");
					break;
				}
				
				if (!password.equals(currentUser.getPassword())) { // check if user exist
					System.out.println("Password is incorrect.");
					continue;
				}
				
				loginSuccess = true;
	    	}
	    	
	    	if (!loginSuccess) {
	    		continue;
	    	}
	    	
	    	System.out.println("Login success");
	    	currentUser.onLogin();
	    	firstVisit = true;
		}
        
	    Database.scanner.close();
	    System.out.println("Console terminated.");
    }
}
