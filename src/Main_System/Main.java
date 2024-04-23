package Main_System;

import java.io.*;
import java.util.*;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        HashMap<String, User> users = new HashMap<String, User>();
        users.put("leo.truong", new Student("Truong", "Leo", "leo.truong", 
                "leopass123", 174, 3.1, "2025"));
        users.put("teacher.user", new Teacher("Teacher", "Mr", "teacher.user",
                "teachpass", 82, "MATH"));
        
        User currentUser;
        
        try(Scanner scan = new Scanner(System.in)){
            while(true) {
                System.out.println("Username:");
                String user = scan.next();
                System.out.println("Password:");
                String pass = scan.next();
                if (users.containsKey(user) && users.get(user).getPassword().equals(pass)) {
                    System.out.println("Login successful");
                    currentUser = users.get(user);
                    break;
                }else {
                    System.out.println("Invalid username or password");
                }
            }
            
             if(currentUser instanceof Main_System.Administrator) {
                System.out.println("Directory:\n1. Admin Information \n2. Admin.Method2 \n3. Quit");
                int command = scan.nextInt();
                if (command == 1) {
                	System.out.println(currentUser.toString());
                } else if (command == 2) {
                	System.out.println("Method 2");
                } else {
                	System.exit(0);
                }
            } else if (currentUser instanceof Main_System.Teacher) {
                System.out.println("Directory:\n1. Teacher Information \n2. Teach.Method2 \n3. Quit");
                int command = scan.nextInt();
                if (command == 1) {
                	System.out.println(currentUser.toString());
                } else if (command == 2) {
                	System.out.println("Method 2");
                } else {
                	System.exit(0);
                }
            }else {
                System.out.println("Directory:\n1. Student Information \n2. Stu.Method2 \n3. Quit");
                int command = scan.nextInt();
                if (command == 1) {
                	System.out.println(currentUser.toString());
                } else if (command == 2) {
                	System.out.println("Method 2");
                } else {
                	System.exit(0);
                }
            }
        }
    }
}
