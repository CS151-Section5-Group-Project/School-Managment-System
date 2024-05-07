package Main_System;

import java.util.LinkedHashMap;

public class GradeSystem {
	private static final LinkedHashMap<Integer, Double> percentageToGPA = new LinkedHashMap<Integer, Double>();
	
	static {
		// Initialize the mapping of percentage ranges to GPA values 
		// Based on SJSU undergraduate grading policies
        percentageToGPA.put(97, 4.0);
        percentageToGPA.put(93, 3.7);
        percentageToGPA.put(90, 3.3);
        percentageToGPA.put(87, 3.0);
        percentageToGPA.put(83, 2.7);
        percentageToGPA.put(80, 2.3);
        percentageToGPA.put(77, 2.0);
        percentageToGPA.put(73, 1.7);
        percentageToGPA.put(70, 1.3);
        percentageToGPA.put(67, 1.0);
        percentageToGPA.put(65, 0.7);
	}
	
	public static double percentageToGPA(double percentage) {
		for (int percent: percentageToGPA.keySet()) {
			if (percentage >= percent) {
				return percentageToGPA.get(percent);
			}
		}
		
		// Default GPA
		return 0.0;
	}
}
