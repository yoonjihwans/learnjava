package xyz.itwill09.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import xyz.itwill09.dto.Member;

@Controller
public class LoginController {
	//사용자로부터 인증정보를 입력받기 위한 JSP 문서의 뷰이름을 반환하는 요청 처리 메소드
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login_form";
	}
	
	/*
	//전달값(인증정보)을 반환받아 인증 처리하고 인증 성공시 권한 관련 정보를 Session Scope 속성값으로 
	//저장한 후 인증 성공 메세지를 출력하는 JSP 문서의 뷰이름을 반환하는 요청 처리 메소드
	// => 전달값과 같은 이름의 String 클래스의 매개변수를 작성해 전달값을 제공받아 사용
	//요청 처리 메소드의 매개변수 자료형을 HttpSession 인터페이스로 작성하면 Front Controller는
	//세션 바인딩 처리된 HttpSession 객체를 매개변수에 저장하여 사용되도록 제공 
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam String id, @RequestParam String passwd
			, Model model, HttpSession session) {
		if(!id.equals("abc123") || !passwd.equals("123456")) {//인증 실패
			//Request Scope : 요청 처리 메소드와 포워드 이동된 뷰에서만 속성값을 제공받아 사용 가능
			model.addAttribute("message", "아이디 또는 비밀번호를 잘못 입력 하였습니다.");
			model.addAttribute("id", id);
			return "login_form";
		}
		
		//인증 성공 - Session Scope 속성값으로 권한 관련 정보를 저장
		//Session Scope : 동일한 세션을 사용하는 모든 요청 처리 메소드와 뷰에서 속성값을 제공받아 사용 가능
		session.setAttribute("loginId", id);
		
		return "login_display";
	}
	*/
	
	//매개변수의 자료형을 DTO 클래스로 작성해 전달값과 같은 이름의 필드에 전달값이 저장된
	//DTO 객체를 제공받아 매개변수에 저장하여 사용
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute Member member, Model model, HttpSession session) {
		if(!member.getId().equals("abc123") || !member.getPasswd().equals("123456")) {
			model.addAttribute("message", "아이디 또는 비밀번호를 잘못 입력 하였습니다.");
			return "login_form";
		}
		session.setAttribute("loginId", member.getId());
		return "login_display";
	}
	
	//로그아웃 처리 후 로그아웃 메세지를 출력하는 JSP 문서의 뷰이름을 반환하는 요청 처리 메소드
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		//session.removeAttribute("loginId");
		session.invalidate();
		
		return "logout_display";
	}

	//로그인 사용자만 요청 처리 메소드의 명령을 실행해 응답 처리되도록 작성
	@RequestMapping("/login_user")
	public String login(HttpSession session, Model model) {
		if(session.getAttribute("loginId") == null) {//비로그인 사용자인 경우
			model.addAttribute("message", "로그인 사용자만 접근 가능합니다.");
			return "login_form";
		}
		
		return "login_display";
	}
}









