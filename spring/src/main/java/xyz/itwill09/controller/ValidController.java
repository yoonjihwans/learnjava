package xyz.itwill09.controller;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import xyz.itwill09.dto.Employee;

//Spring Validation 기능을 사용해 요청 처리 메소드의 매개변수에 저장된 Command 객체의 필드값
//(전달값) 검증하는 방법
//1.validation-api 라이브러리와 hibernate-validator 라이브러리를 프로젝트에 빌드 처리 - 메이븐 사용 : pom.xml
//2.HTML 태그 대신 Spring 태그를 사용해 JSP 문서 작성 - 에러메세지를 전달받아 출력 처리
//3.요청 처리 메소드에서 Command 객체가 저장된 매개변수에 @Valid 어노테이션을 사용해 Command
//객체의 필드에 저장된 전달값을 검증 처리되도록 설정
//4.Command 객체를 저장한 DTO 클래스의 필드에 검증 관련 어노테이션을 사용해 전달값 검증

@Controller
//@RequestMapping 어노테이션을 클래스에 작성하면 Controller 클래스의 모든 요청 처리 메소드에
//매핑된 요청 URL 주소 앞부분에 공통적으로 사용될 URL 주소 제공
@RequestMapping("/valid")
public class ValidController {
	//@RequestMapping(value = "/valid/html", method = RequestMethod.GET)
	@RequestMapping(value = "/html", method = RequestMethod.GET)
	public String html() {
		return "valid/html_form";		
	}
	
	//@RequestMapping(value = "/valid/html", method = RequestMethod.POST)
	@RequestMapping(value = "/html", method = RequestMethod.POST)
	public String html(@ModelAttribute Employee employee, Model model) {
		if(employee.getId() == null || employee.getId().equals("")) {
			model.addAttribute("idMsg", "아이디를 입력해 주세요");
			return "valid/html_form";		
		}
		
		String idReg="^[a-zA-Z]\\w{5,19}$";
		if(!Pattern.matches(idReg, employee.getId())) {
			model.addAttribute("idMsg", "아이디를 형식에 맞게 입력해 주세요");
			return "valid/html_form";		
		}
		
		return "valid/result";		
	}
	
	//@RequestMapping(value = "/valid/spring", method = RequestMethod.GET)
	@RequestMapping(value = "/spring", method = RequestMethod.GET)
	public String spring(@ModelAttribute Employee employee, Model model) {
		//Arrays.asList(Object ... args) : 매개변수로 전달받은 값(객체)가 요소값으로 저장된
		//List 객체를 생성하여 반환하는 정적메소드
		//model.addAttribute("genderList", Arrays.asList("남자", "여자"));
		return "valid/spring_form";		
	}
	
	//요청 처리 메소드의 매개변수 자료형을 Errors 인터페이스로 작성하면 Front Controller에게
	//Errors 객체를 제공받아 사용
	// => Errors 객체 : 전달값에 대한 검증 실패시 발생되는 모든 에러 관련 정보를 저장한 객체
	//@RequestMapping(value = "/valid/spring", method = RequestMethod.POST)
	@RequestMapping(value = "/spring", method = RequestMethod.POST)
	public String spring3(@ModelAttribute @Valid Employee employee, Errors errors, Model model) {
		//Errors.hasErrors() : Errors 객체에 에러 관련 정보가 저장된 경우 [true]를 반환하는 메소드
		if(errors.hasErrors()) {
			//model.addAttribute("genderList", Arrays.asList("남자", "여자"));
			return "valid/spring_form";		
		}
		
		return "valid/result";		
	}
	
	//메소드의 반환값을 Controller 클래스의 모든 요청 메소드의 뷰에게 제공
	@ModelAttribute("genderList")
	public List<String> genderList() {
		return Arrays.asList("남자", "여자");
	}
}