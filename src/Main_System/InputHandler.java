package Main_System;

import java.util.InputMismatchException;

public class InputHandler {
	public static String promptLine() {
		String paragraphInput = "";
		
		while (paragraphInput.length() == 0) {
			paragraphInput = Database.scanner.nextLine();
			
			if (paragraphInput.length() == 0) {
				System.out.println("Input cannot be blank. Try again: ");
			}
		}
		
		return paragraphInput;
	}
	
	public static int promptNumber() {
		boolean success = false;
		int numberInput = 0;
		
		while (!success) {
			try {
				numberInput = Database.scanner.nextInt();
				System.out.println();
				success = true;
			} catch (InputMismatchException e) {
				System.out.println("Only numbers are allowed. Try again: ");
				Database.scanner.nextLine();
			}
		}
		
		return numberInput;
	}
}
