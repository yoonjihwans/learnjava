package xyz.itwill07.dao;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StudentApp {
	public static void main(String[] args) {
		ApplicationContext context=new ClassPathXmlApplicationContext("07_dao.xml");
		
		System.out.println("=============================================================");
	    StudentService service = context.getBean("studentService", StudentService.class);
	    
	    List<Student> studentList = service.getStudnentList();
	    for(Student student: studentList) {
	    	System.out.println(student);
	    	
	    }
		System.out.println("=============================================================");
		((ClassPathXmlApplicationContext)context).close();	
	}

}
