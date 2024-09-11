package xyz.itwill09.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import xyz.itwill09.exception.BadRequestException;
import xyz.itwill09.exception.ExistsUserinfoException;
import xyz.itwill09.exception.LoginAuthFailException;
import xyz.itwill09.exception.UserinfoNotFoundException;

//@ControllerAdvice : 예외 처리 메소드만 작성된 Controller 클래스를 Spring Bean으로 등록하기 위한 어노테이션
// => 모든 Controller 클래스의 요청 처리 메소드에서 발생된 예외를 제공받아 예외 처리 가능
@ControllerAdvice
public class ExceptionController {
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
	
	/*
	@ExceptionHandler(Exception.class)
	public String exception() {
		return "userinfo/user_error";
	}
	*/
}