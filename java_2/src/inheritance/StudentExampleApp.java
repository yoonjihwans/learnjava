package inheritance;

import java.util.Iterator;

public class StudentExampleApp {
	public static void main(String[] args) {
		StudentExample[] students = { new StudentExample(2000, "윤지환", 43, 34, 23),
				new StudentExample(3000, "송바다", 42, 54, 13), new StudentExample(4000, "박종성", 63, 14, 73),
				new StudentExample(5000, "김정민", 93, 34, 83), new StudentExample(6000, "김준일", 83, 84, 33)

		};
		
	     for(StudentExample student : students) {
	    	 
	    	 student.display();
	    	 
	    	 
	     }

	};
}
