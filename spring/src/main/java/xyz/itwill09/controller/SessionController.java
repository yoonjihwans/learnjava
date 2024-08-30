package xyz.itwill09.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import xyz.itwill09.dto.Hewon;

@Controller
//@SessionAttributes : 요청 처리 메소드에서 Model 객체를 사용해 저장된 속성값을 @SessionAttributes
//어노테이션을 사용한 Controller 클래스의 모든 요청 처리 메소드와 뷰에게 제공하기 위한 어노테이션
// => Model 객체를 사용해 저장된 속성값을 Request Scope 속성값이 아닌 제한적인 Session Scope
//속성값으로 사용하는 기능 제공
// => 뷰(JSP)에서 페이지 요청시 식별자 전달이 불필요하며 식별자로 정보를 검색하지 않아도 
//요청 처리 메소드와 뷰에서 사용 가능
//value 속성 : 제한적인 Session Scope 속성값으로 사용할 속성명을 속성값으로 설정
// => value 속성외에 다른 속성이 없는 경우 속성값만 설정 가능
@SessionAttributes("hewon")
public class SessionController {
	//아이디를 전달받아 회원정보를 검색해 반환하는 메소드 - Service 클래스의 메소드
	public Hewon getHewon(String id) {
		return Hewon.builder().id("abc123").name("홍길동").email("abc@itwill.xyz").build();
	}
	
	//아이디를 전달받아 아이디의 회원정보를 검색해 Model 객체의 속성값으로 저장하여 뷰에게 
	//제공하기 위한 요청 처리 메소드
	// => Model 객체의 속성값으로 회원정보를 출력하는 JSP 문서로 포워딩 하는 뷰이름 반환 
	@RequestMapping("/hewon_view")
	public String view(@RequestParam(defaultValue = "abc123") String id, Model model) {
		//Service 클래스의 메소드를 호출하여 매개변수로 전달한 아이디의 회원정보를 반환받아 저장 
		Hewon hewon=getHewon(id);
		
		//반환받은 회원정보를 Model 객체의 속성값으로 저장
		// => 요청 처리 메소드와 뷰에서만 속성값을 제공받아 사용 가능 - Request Scope
		//@SessionAttributes 어노테이션에 의해 Model 객체에 저장된 속성값은 제한적인 Session
		//Scope 속성값으로 사용되어 Controller 클래스의 모든 요청 처리 메소드와 뷰에서 사용 가능
		//model.addAttribute("hewon", hewon);
		//Model 객체에 속성명을 생략하여 속성값 저장 가능 
		// => 속성값에 대한 자료형을 속성명으로 사용 - 자료형의 첫문자는 소문자로 변환
		// => 속성값의 자료형이 String 클래스 또는 원시형(Wrapper 클래스)인 경우 속성명 생략 불가능
		model.addAttribute(hewon);
		
		return "hewon_view";
	}
	
	/*
	//아이디를 전달받아 아이디의 회원정보를 검색해 Model 객체의 속성값으로 저장하여 뷰에게 
	//제공하기 위한 요청 처리 메소드
	// => Model 객체의 속성값을 입력태그의 입력값으로 사용한 회원정보의 변경값을 입력는 
	//JSP 문서로 포워딩 하는 뷰이름 반환
	@RequestMapping(value = "/hewon_update", method = RequestMethod.GET)
	public String update(@RequestParam(defaultValue = "abc123") String id, Model model) {
		Hewon hewon=getHewon(id);
		model.addAttribute(hewon);
		return "hewon_update";
	}
	
	//회원정보를 전달받아 뷰에게 제공하는 요청 처리 메소드
	@RequestMapping(value = "/hewon_update", method = RequestMethod.POST)
	public String update(@ModelAttribute Hewon hewon) {
		return "hewon_result";
	}
	*/
	
	//@SessionAttributes 어노테이션에 의해 전달값(아이디)을 제공받아 회원정보를 검색하거나
	//속성값을 저장하는 명령 불필요
	@RequestMapping(value = "/hewon_update", method = RequestMethod.GET)
	public String update() {
		return "hewon_update";
	}
	
	//회원정보를 전달받아 뷰에게 제공하는 요청 처리 메소드
	// => 요청 처리 메소드의 매개변수에는 @SessionAttributes 어노테이션으로 제공된 객체를 전달
	//받아 저장하고 전달값(변경값)으로 필드값을 변경하여 사용되도록 제공
	//요청 처리 메소드의 매개변수 자료형을 SessionStatus 인터페이스로 작성하면 Front Controller에게
	//SessionStatus 객체를 제공받아 사용
	// => SessionStatus 객체 : @SessionAttributes 어노테이션에 의해 제공된 제한적인 Session
	//Scope 속성값의 상태정보를 저장하기 위한 객체
	@RequestMapping(value = "/hewon_update", method = RequestMethod.POST)
	public String update(@ModelAttribute Hewon hewon, SessionStatus sessionStatus) {
		//SessionStatus.setComplete() : @SessionAttributes 어노테이션에 의해 제공된 제한적인
		//Session Scope 속성값을 종료하는 메소드
		sessionStatus.setComplete();
		return "hewon_result";
	}
}











