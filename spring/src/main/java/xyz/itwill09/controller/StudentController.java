package xyz.itwill09.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.RequiredArgsConstructor;
import xyz.itwill09.dto.Student;
import xyz.itwill09.service.StudentService;

//SpringMVC 기능을 사용해 웹프로그램을 작성하는 방법
// => 테이블 >> DTO 클래스 >> DAO 클래스(MyBatis) >> Service 클래스 >> Controller 클래스
// >> 단위 프로그램 테스트 - 테스트 프로그램(JUnit) >> JSP 문서(HTML 문서)와 결합하여 실행(통합 테스트)

//Mybatis 프레임워크의 로그 팩토리에 의해 발생되는 로그 이벤트를 Spring 프레임워크의 로그
//구현체로 기록하는 방법
//1.log4jdbc-log4j2-jdbc4 라이브러리를 프로젝트에 빌드 처리 - 메이븐 : pom.xml 
//2.Spring Bean Configuration File(root-context.xml)에서 DataSource 관련 클래스를 Spring Bean으로
//등록한 bean 엘리먼트에서 driverClassName 필드와 url 필드에 저장된 값 변경
//3.[src/main/resources] 폴더에 [log4jdbc.log4j2.properties] 파일 작성
// => Mybatis 프레임워크에서 발생되는 로그 이벤트를 Spring 프레임워크의 로그 구현체에게 제공하기
//위한 SpyLogDelegator 클래스를 설정하기 위한 파일
//4.SpyLogDelegator 객체에 의해 발생된 로그 이벤트를 Spring 프레임워크의 로그 구현체로 기록되도록
//환경설정파일(log4j.xml) 변경 - logger 엘리먼트 추가  

//Controller 클래스 : 클라이언트의 요청을 처리하기 기능을 제공하기 위한 클래스
// => Controller 클래스의 요청 처리 메소드에서는 데이타 처리에 필요한 명령으로 Service 객체로
//메소드를 호출해 작성 

//Controller 클래스는 Front Controller에서 객체로 제공받아 사용할 수 있도록 Spring Bean으로 등록
//=> Controller 클래스는 @Controller 어노테이션을 사용하여 Spring Bean으로 등록 처리
//=> @Controller 어노테이션을 스프링 컨테이너가 처리하기 위해서는 반드시 클래스가 작성된 패키지를 
//Spring Bean Configuration File(servlet-context.xml)의 component-scan 엘리먼트로 검색되도록 설정
@Controller
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {
	//Controller 클래스의 메소드에서는 Service 클래스의 메소드를 호출할 수 있도록 Service 객체 필요
	// => Service 객체가 저장될 수 있는 필드를 작성해 스프링 컨테이너로부터 객체를 제공받아
	//필드에 저장되도록 의존성 주입 - 생성자 레벨의 의존성 주입
	private final StudentService studentService;
	
	//학생정보를 입력받기 위한 JSP 문서의 뷰이름을 반환하는 요청 처리 메소드
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add() {
		return "student/student_add";
	}
	
	//학생정보를 전달받아 STUDENT 테이블에 행으로 삽입하고 학생목록을 출력하는 페이지를 요청할
	//수 있는 URL 주소로 응답하는 요청 처리 메소드
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@ModelAttribute Student student, Model model) {
		try {
			studentService.addStudent(student);
		} catch (Exception e) {
			model.addAttribute("message", "학생번호가 중복되거나 입력값에 문제가 있습니다.");
			return "student/student_add";
		}
		return "redirect:/student/display";//리다이렉트 이동
	}
	
	//STUDENT 테이블에 저장된 모든 행을 검색해 반환받은 학생목록을 Request Scope 속성값으로
	//저장하고 학생목록을 출력하는 JSP 문서의 뷰이름을 반환하는 요청 처리 메소드
	@RequestMapping("/display")
	public String display(Model model) {
		model.addAttribute("studentList", studentService.getStudentList());
		return "student/student_display";
	}
}










