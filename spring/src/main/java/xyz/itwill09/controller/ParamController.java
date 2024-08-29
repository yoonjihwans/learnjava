
package xyz.itwill09.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ParamController {
	@RequestMapping(value = "/param", method = RequestMethod.GET)
	public String form() {
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

	//페이지 요청시 전달되는 값의 이름과 같은 이름으로 String 클래스(원시형 - Wrapper 클래스)의
	//매개변수를 작성하면 Front Controller는 전달값을 반환받아 매개변수에 저장하여 사용할 수 있도록 제공
	// => 전달값의 이름과 매개변수의 이름이 같지 않은 경우 매개변수에는 [null] 저장
	// => 매개변수의 자료형이 String 클래스가 아닌 경우 전달값의 이름과 매개변수의 이름이 같지
	//않거나 매개변수의 자료형과 맞지 않는 값이 전달될 경우 400 에러코드 발생
	//매개변수에 전달값을 제공받아 사용하기 위해서는 전달값에 대한 문자형태(캐릭터셋)을 변경
	//되도록 인코딩 필터 사용
	//필터(Filter) : Front Controller 실행 전 또는 후에 필요한 기능을 제공하기 위한 웹프로그램
	// => Filter 인터페이스를 상속받은 자식클래스를 작성하고 [web.xml] 파일에 Filter 클래스를
	//필터로 등록하고 필터가 실행될 수 있는 요청 URL 주소(패턴) 설정
	@RequestMapping(value = "/param", method = RequestMethod.POST)
	public String action(String food, Model model) {
		model.addAttribute("food", food);
		return "param_display";
	}
}
