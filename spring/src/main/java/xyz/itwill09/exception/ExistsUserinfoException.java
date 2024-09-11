package xyz.itwill09.exception;

import lombok.Getter;
import xyz.itwill09.dto.Userinfo;

//회원정보를 등록할 때 사용자부터 입력받은 회원정보의 아이디가 기존 회원정보의 아이디와 
//중복될 경우 발생될 예외를 생성하기 위한 예외클래스 
// => Exception 클래스(RuntimeException 클래스) 상속받아 작성
public class ExistsUserinfoException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	//예외 처리에 필요한 값을 저장하기 위한 필드 작성
	// => 사용자로부터 입력받은 회원정보를 필드에 
	@Getter
	private Userinfo userinfo;
	
	public ExistsUserinfoException() {
		// TODO Auto-generated constructor stub
	}
	
	public ExistsUserinfoException(String message, Userinfo userinfo) {
		super(message);
		this.userinfo=userinfo;
	}
}