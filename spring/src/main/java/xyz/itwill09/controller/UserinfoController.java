package xyz.itwill09.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import xyz.itwill09.dto.Userinfo;
import xyz.itwill09.service.UserinfoService;

@Controller
@RequestMapping("/userinfo")
@RequiredArgsConstructor
public class UserinfoController {
	private final UserinfoService userinfoService;
	
	//회원정보를 입력받기 위한 JSP 문서의 뷰이름을 반환하는 요청 처리 메소드
	// => 관리자만 요청 가능한 페이지로 설정
	/*
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write(HttpSession session) {
		Userinfo loginUserinfo=(Userinfo)session.getAttribute("loginUserinfo");
		//try~catch 구문을 사용해 예외가 발생될 경우 에러페이지를 출력하는 JSP 문서의 뷰이름 반환
		// => 500 에러코드로 응답 처리하지 않고 뷰를 사용해 응답 처리
		try {
			//페이지를 요청한 사용자가 비로그인 사용자이거나 관리자가 아닌 경우 인위적 예외 발생
			if(loginUserinfo == null || loginUserinfo.getAuth() != 9) {
				//예외가 발생될 경우 클라이언트에게 500 에러코드를 전달하여 응답 처리
				throw new BadRequestException("비정상적인 방법으로 페이지를 요청 하였습니다.");
			}
		} catch (BadRequestException e) {
			return "userinfo/user_error";
		}
		return "userinfo/user_write";
	}
	*/
	
	//예외 처리 메소드를 사용해 예외 처리 기능을 구현하고 인터셉터를 사용해 권한 관련 처리 기능 구현
	// => 요청 처리 메소드에서는 예외 처리 및 권한 관련 명령 미작성
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write() {
		return "userinfo/user_write";
	}
	
	//회원정보를 전달받아 USERINFO 테이블의 행으로 삽입하고 로그인 페이지를 요청할 수 있는
	//URL 주소를 반환하는 요청 처리 메소드
	/*
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(@ModelAttribute Userinfo userinfo, Model model) {
		try {
			//매개변수로 전달받은 회원정보의 아이디가 중복될 경우 ExistsUserinfoException 발생
			userinfoService.addUserinfo(userinfo);
		} catch (ExistsUserinfoException e) {
			model.addAttribute("message", e.getMessage());
			return "userinfo/user_write";
		}
		return "redirect:/userinfo/login";
	}
	*/
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(@ModelAttribute Userinfo userinfo, Model model) {
		//매개변수로 전달받은 회원정보의 아이디가 중복될 경우 ExistsUserinfoException 발생
		userinfoService.addUserinfo(userinfo);
		return "redirect:/userinfo/login";
	}
	
	//인증정보를 입력받기 위한 JSP 문서의 뷰이름을 반환하는 요청 처리 메소드
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "userinfo/user_login";
	}
	
	//인증정보를 전달받아 USERINFO 테이블에 저장된 행을 검색하여 로그인 처리하고 환영메세지를
	//출력하는 JSP 문서의 뷰이름 반환하는 요청 처리 메소드
	// => 로그인 처리 : 인증 성공시 세션에 권한 관련 정보(회원정보)를 속성값으로 저장
	/*
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute Userinfo userinfo, Model model, HttpSession session) {
		try {
			//매개변수로 전달받은 회원정보로 인증이 실패한 경우 LoginAuthFailException 발생
			Userinfo authUserinfo=userinfoService.loginAuth(userinfo);
			session.setAttribute("loginUserinfo", authUserinfo);
		} catch (LoginAuthFailException e) {
			model.addAttribute("message", e.getMessage());
			model.addAttribute("userid", userinfo.getUserid());
			return "userinfo/user_login";
		}
		
		return "userinfo/user_login";
	}
	*/
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute Userinfo userinfo, Model model, HttpSession session) {
		//매개변수로 전달받은 회원정보로 인증이 실패한 경우 LoginAuthFailException 발생
		Userinfo authUserinfo=userinfoService.loginAuth(userinfo);
		session.setAttribute("loginUserinfo", authUserinfo);
		return "userinfo/user_login";
	}
	
	//로그아웃 처리하고 로그인 페이지를 요청할 수 있는 URL 주소를 반환하는 요청 처리 메소드
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		//session.removeAttribute("loginUserinfo");
		session.invalidate();
		return "redirect:/userinfo/login";
	}
	
	//USERINFO 테이블에 저장된 모든 행을 검색하여 Request Scope 속성값으로 저장하고 회원목록을
	//출력하는 JSP 문서의 뷰이름을 반환하는 요청 처리 메소드
	// => 로그인 사용자만 요청 가능한 페이지로 설정
	/*
	@RequestMapping("/list")
	public String list(Model model, HttpSession session) {
		try {
			if(session.getAttribute("loginUserinfo") == null) {
				throw new BadRequestException("비정상적인 방법으로 페이지를 요청 하였습니다.");
			}
		} catch (BadRequestException e) {
			return "userinfo/user_error";
		}
		model.addAttribute("userinfoList", userinfoService.getUserinfoList());
		return "userinfo/user_list";
	}
	*/
	@RequestMapping("/list")
	public String list(Model model) {
		model.addAttribute("userinfoList", userinfoService.getUserinfoList());
		return "userinfo/user_list";
	}


	//아이디를 전달받아 USERINFO 테이블에 저장된 행을 검색하여 Request Scope 속성값으로 저장하고
	//회원정보를 출력하는 JSP 문서의 뷰이름을 반환하는 요청 처리 메소드
	// => 로그인 사용자만 요청 가능한 페이지로 설정
	/*
	@RequestMapping("/view")
	public String view(@RequestParam String userid, Model model, HttpSession session) {
		try {
			if(session.getAttribute("loginUserinfo") == null) {
				throw new BadRequestException("비정상적인 방법으로 페이지를 요청 하였습니다.");
			}
		} catch (BadRequestException e) {
			return "userinfo/user_error";
		}
		model.addAttribute("userinfo", userinfoService.getUserinfo(userid));
		return "userinfo/user_view";
	}
	*/
	@RequestMapping("/view")
	public String view(@RequestParam String userid, Model model) {
		model.addAttribute("userinfo", userinfoService.getUserinfo(userid));
		return "userinfo/user_view";
	}
	
	//아이디를 전달받아 USERINFO 테이블에 저장된 행을 검색하여 Request Scope 속성값으로 저장하고
	//회원정보를 변경하는 JSP 문서의 뷰이름을 반환하는 요청 처리 메소드
	// => 관리자만 요청 가능한 페이지로 설정
	/*
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modify(@RequestParam String userid, Model model, HttpSession session) {
		Userinfo loginUserinfo=(Userinfo)session.getAttribute("loginUserinfo");
		try {
			//페이지를 요청한 사용자가 비로그인 사용자이거나 관리자가 아닌 경우 인위적 예외 발생
			if(loginUserinfo == null || loginUserinfo.getAuth() != 9) {
				throw new BadRequestException("비정상적인 방법으로 페이지를 요청 하였습니다.");
			}
		} catch (BadRequestException e) {
			return "userinfo/user_error";
		}
		model.addAttribute("userinfo", userinfoService.getUserinfo(userid));
		return "userinfo/user_modify";
	}
	*/
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modify(@RequestParam String userid, Model model) {
		model.addAttribute("userinfo", userinfoService.getUserinfo(userid));
		return "userinfo/user_modify";
	}
	
	
	//변경할 회원정보를 전달받아 USERINFO 테이블에 저장된 행을 변경하고 회원정보 출력 페이지를 
	//요청할 수 있는 URL 주소를 반환하는 요청 처리 메소드
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(@ModelAttribute Userinfo userinfo, HttpSession session) {
		userinfoService.modifyUserinfo(userinfo);
		
		//로그인 사용자와 변경 처리된 사용자가 동일한 경우 세션에 저장된 권한 관련 속성값 변경
		Userinfo loginUserinfo=(Userinfo)session.getAttribute("loginUserinfo");
		if(loginUserinfo.getUserid().equals(userinfo.getUserid())) {
			session.setAttribute("loginUserinfo", userinfoService.getUserinfo(userinfo.getUserid()));
		}
		
		return "redirect:/userinfo/view?userid="+userinfo.getUserid();
	}
	
	//아이디를 전달받아 USERINFO 테이블에 저장된 행을 삭제하고 회원목록 출력 페이지를 요청할
	//수 있는 URL 주소를 반환하는 요청 처리 메소드
	// => 관리자만 요청 가능한 페이지로 설정
	/*
	@RequestMapping("/remove")
	public String remove(@RequestParam String userid, HttpSession session) {
		Userinfo loginUserinfo=(Userinfo)session.getAttribute("loginUserinfo");
		try {
			//페이지를 요청한 사용자가 비로그인 사용자이거나 관리자가 아닌 경우 인위적 예외 발생
			if(loginUserinfo == null || loginUserinfo.getAuth() != 9) {
				throw new BadRequestException("비정상적인 방법으로 페이지를 요청 하였습니다.");
			}
		} catch (BadRequestException e) {
			return "userinfo/user_error";
		}
		
		userinfoService.removeUserinfo(userid);
		
		//로그인 사용자와 삭제 처리된 회원정보의 아이디가 동일한 경우 로그아웃 페이지로 
		//리다이렉트 이동 처리
		if(loginUserinfo.getUserid().equals(userid)) {
			return "redirect:/userinfo/logout";
		}
		
		return "redirect:/userinfo/list";
	}
	*/
	@RequestMapping("/remove")
	public String remove(@RequestParam String userid, HttpSession session) {
		Userinfo loginUserinfo=(Userinfo)session.getAttribute("loginUserinfo");
		
		userinfoService.removeUserinfo(userid);
		
		//로그인 사용자와 삭제 처리된 회원정보의 아이디가 동일한 경우 로그아웃 페이지로 
		//리다이렉트 이동 처리
		if(loginUserinfo.getUserid().equals(userid)) {
			return "redirect:/userinfo/logout";
		}
		
		return "redirect:/userinfo/list";
	}
	
	/*
	//@ExceptionHandler : 예외 처리 기능을 제공하는 메소드를 설정하기 위한 어노테이션
	// => Controller 클래스의 요청 처리 메소드에서 예외가 발생될 경우 예외 처리를 위해
	//Front Controller가 자동으로 호출하는 메소드 - 예외 처리 메소드(Spring AOP 기능 사용)
	// => 예외 처리 메소드에 매개변수를 작성하면 예외 처리에 필요한 객체를 Front Controller로부터
	//제공받아 사용할 수 있으며 클라이언트에게 응답할 뷰의 뷰이름 반환 - 리다이렉트 이동 가능
	//value 속성 : 예외 처리하기 위한 클래스의 Class 객체를 속성값으로 설정
	// => value 속성외에 다른 속성이 없는 경우 속성값만 설정 가능
	@ExceptionHandler(value = BadRequestException.class)
	public String badRequestException() {
		return "userinfo/user_error";
	}
	
	@ExceptionHandler(ExistsUserinfoException.class)
	public String existsUserinfoException(ExistsUserinfoException exception, Model model) {
		model.addAttribute("message", exception.getMessage());
		model.addAttribute("userinfo", exception.getUserinfo());
		return "userinfo/user_write";
	}
	
	@ExceptionHandler(LoginAuthFailException.class)
	public String loginAuthFailException(LoginAuthFailException exception, Model model) {
		model.addAttribute("message", exception.getMessage());
		model.addAttribute("userid", exception.getUserid());
		return "userinfo/user_login";
	}
	
	@ExceptionHandler(UserinfoNotFoundException.class)
	public String userinfoNotFoundException() {
		return "userinfo/user_error";
	}
	
	@ExceptionHandler(Exception.class)
	public String exception() {
		return "userinfo/user_error";
	}
	*/
}












