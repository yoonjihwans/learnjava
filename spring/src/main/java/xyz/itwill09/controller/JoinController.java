package xyz.itwill09.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class JoinController {
	//사용자로부터 회원정보를 입력받기 위한 JSP 문서의 뷰이름을 반환하는 요청 처리 메소드
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() {
		return "join_form";
	}
	
	/*
	//전달값(회원정보)을 매개변수로 제공받아 Request Scope 속성값으로 저장하고 속성값을 출력하는
	//JSP 문서의 뷰이름을 반환하는 요청 처리 메소드
	// => 전달값과 같은 이름으로 매개변수를 작성해 전달값 제공받아 사용
	// => 전달값을 제공받기 위해 매개변수에 @RequestParam 어노테이션을 사용해 매개변수의
	//이름과 같은 이름으로 전달된 값이 없는 경우 400 에러코드가 발생되도록 작성
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@RequestParam String id, @RequestParam String passwd
			, @RequestParam String name, @RequestParam String email, Model model) {
		model.addAttribute("id", id);
		model.addAttribute("passwd", passwd);
		model.addAttribute("name", name);
		model.addAttribute("email", email);
		return "join_display";
	}
	*/
	
	/*
	//@ModelAttribute : 값(객체)를 뷰(View)에게 제공하기 위한 어노테이션
	// => 전달값을 제공받아 저장하기 위한 매개변수에 @ModelAttribute 어노테이션을 사용하면 
	//매개변수에 저장된 전달값을 요청 처리 메소드의 뷰이름으로 생성된 뷰에게 제공
	// => value 속성을 사용해  value 속성값으로 전달값을 얻어와 뷰에서 속성값을 사용할 수 있도록
	//반드시 속성명(전달값의 이름)을 설정
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@ModelAttribute("id") String id, @ModelAttribute("passwd") String passwd
			, @ModelAttribute("name") String name, @ModelAttribute("email") String email) {
		return "join_display";
	}
	*/
	
	/*
	//요청 처리 메소드의 매개변수 자료형을 DTO 클래스로 작성하면 Front Controller는 DTO 클래스로
	//객체(Command 객체)를 생성하여 매개변수에 저장되도록 제공 
	// => 전달값의 이름과 같은 이름의 필드에 전달값을 자동으로 저장하여 제공
	//Command 객체 : 전달값이 필드에 저장된 객체로 매개변수에 저장되고 매개변수에 저장된
	//객체를 뷰에서 사용할 수 있도록 제공하는 객체
	// => @ModelAttribute 어노테이션을 사용하지 않아도 Command 객체는 뷰에게 제공
	// => @ModelAttribute 어노테이션에 value 속성을 생략하면 매개변수의 자료형을 자동으로
	//속성명으로 사용되도록 설정 - 자료형의 첫번째 문자는 소문자로 변환
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@ModelAttribute Member member) {
		return "join_display";
	}
	*/
	
	/*
	//@ModelAttribute 어노테이션의 value 속성값을 변경하여 뷰에게 제공될 속성값의 속성명 변경 가능
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@ModelAttribute("hewon") Member member) {
		return "join_display";
	}
	*/
	
	/*
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@ModelAttribute("hewon") Member member, Model model) {
		if(member.getId().equals("abc123")) {//아이디가 중복된 경우
			model.addAttribute("message", "이미 사용중인 아이디를 입력 하였습니다.");
			return "join_form";
		}
		return "join_display";
	}
	*/
	
	//요청 처리 메소드의 매개변수 자료형을 Map 인터페이스로 작성하면 Front Controller는 
	//Map 객체를 생성하여 매개변수에 저장되도록 제공
	// => 전달값의 이름은 맵키(String)로 사용하고 전달값은 맵값(String)으로 사용한 엔트리가 
	//저장된 Map 객체를 매개변수에 저장하여 사용되도록 제공
	// => 전달값이 저장된 Map 객체를 제공받기 위해서는 반드시 매개변수에 @RequestParam 
	//어노테이션을 사용하여 작성
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@RequestParam Map<String, String> map, Model model) {
		model.addAttribute("hewon", map);
		return "join_display";
	}
}