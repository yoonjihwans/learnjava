package xyz.itwill09.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ParamController {
	@RequestMapping(value = "/param", method = RequestMethod.GET)
	public String form1() {
		return "param_form";
	}
	
	/*
	//페이지 요청시 전달되는 값을 HttpServletRequest 객체로 메소드를 호출하여 전달값을 반환받아 사용 
	@RequestMapping(value = "/param", method = RequestMethod.POST)
	public String action(HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		String food=request.getParameter("food");
		request.setAttribute("food", food);
		return "param_display";
	}
	*/

	/*
	//페이지 요청시 전달되는 값의 이름과 같은 이름으로 String 클래스(원시형 - Wrapper 클래스)의
	//매개변수를 작성하면 Front Controller는 전달값을 반환받아 매개변수에 저장하여 사용할 수 있도록 제공
	// => 전달값의 이름과 매개변수의 이름이 같지 않은 경우 매개변수에는 [null] 저장
	// => 매개변수의 자료형이 String 클래스가 아닌 경우 전달값의 이름과 매개변수의 이름이 같지
	//않거나 매개변수의 자료형과 맞지 않는 값이 전달될 경우 400 에러코드 발생
	//매개변수에 전달값을 제공받아 사용하기 위해서는 전달값에 대한 문자형태(캐릭터셋)을 변경되도록
	//인코딩 필터 사용 - Front Controller 실행 전에 전달값에 대한 문자형태 변경 
	//필터(Filter) : 웹프로그램 실행 전 또는 후에 필요한 기능을 제공하기 위한 웹프로그램
	// => Filter 인터페이스를 상속받은 자식클래스를 작성하고 [web.xml] 파일에 Filter 클래스를
	//필터로 등록하고 필터가 실행될 수 있는 요청 URL 주소(패턴) 설정
	@RequestMapping(value = "/param", method = RequestMethod.POST)
	public String action(String food, Model model) {
		model.addAttribute("food", food);
		return "param_display";
	}
	*/
	
	/*
	//전달값을 제공받아 저장하기 위해 매개변수에는 @RequestParam 어노테이션 사용 가능 - 권장
	//@RequestParam : 전달값을 제공받아 매개변수에 저장하기 위한 어노테이션
	// => 매개변수의 이름과 같은 이름으로 전달된 값이 없는 경우 400 에러코드 발생
	// => 전달값의 이름과 매개변수의 이름이 같도록 작성해 전달값이 매개변수에 저장되도록 사용하는 어노테이션
	// => 전달값의 이름과 매개변수의 이름이 같은 경우 클라이언트로부터 전달된 값이 비어 경우
	//변수에는 [null] 저장 - 400 에러코드 미발생
	@RequestMapping(value = "/param", method = RequestMethod.POST)
	public String action(@RequestParam String food, Model model) {
		model.addAttribute("food", food);
		return "param_display";
	}
	*/

	/*
	//required 속성 : false 또는 true(기본값)를 속성값으로 설정
	// => 속성값으로 [false]를 설정하면 매개변수의 이름과 같은 이름으로 전달되는 값이 없어도
	//400 에러코드가 미발생되고 [true]로 설정하면 매개변수의 이름과 같은 이름으로 전달되는 
	//값이 없으면 400 에러코드 발생
	//value 속성 : 전달값의 이름을 속성값으로 설정
	// => value 속성외에 다른 속성이 없는 경우 속성값만 설정 가능
	// => 전달값의 이름과 매개변수의 이름이 다른 경우 전달값을 제공받아 매개변수에 저장하기 위해 사용
	@RequestMapping(value = "/param", method = RequestMethod.POST)
	public String action(@RequestParam(required = true, value = "food") String foodname, Model model) {
		model.addAttribute("food", foodname);
		return "param_display";
	}
	*/
	
	//defaultValue 속성 : 매개변수에 저장될 기본값을 속성값으로 설정
	// => 매개변수의 이름과 같은 이름으로 전달되는 값이 없거나 매개변수의 이름과 같은 이름으로
	//전달되는 값이 비어있는 경우 매개변수에 저장될 기본값 설정
	@RequestMapping(value = "/param", method = RequestMethod.POST)
	public String action(@RequestParam(required = false, defaultValue = "라면") String food, Model model) {
		model.addAttribute("food", food);
		return "param_display";
	}
	
	@RequestMapping(value = "/paramValues", method = RequestMethod.GET)
	public String form2() {
		return "paramValues_form";
	}
	
	/*
	//같은 이름으로 전달되는 값이 여러개인 경우 매개변수를 String 배열로 작성하여 
	//전달값이 요소에 차례대로 저장된 배열을 제공받아 매개변수에 저장하여 사용
	@RequestMapping(value = "/paramValues", method = RequestMethod.POST)
	public String action(@RequestParam("hobby") String[] hobbyList, Model model) {
		model.addAttribute("hobbyList", hobbyList);
		return "paramValues_display";
	}
	*/
	
	//같은 이름으로 전달되는 값이 여러개인 경우 매개변수를 List 인터페이스로 작성해 
	//전달값이 요소에 차례대로 저장된 List 객체를 제공받아 매개변수에 저장하여 사용
	@RequestMapping(value = "/paramValues", method = RequestMethod.POST)
	public String action(@RequestParam("hobby") List<String> hobbyList, Model model) {
		model.addAttribute("hobbyList", hobbyList);
		return "paramValues_display";
	}
}