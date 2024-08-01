package xyz.itwill.exception;

//인증이 실패된 경우 발생될 예외 정보를 저장하기 위한 클래스
public class AuthFailException extends Exception {
	private static final long serialVersionUID = 1L;

	public AuthFailException() {
		// TODO Auto-generated constructor stub
	}
	
	public AuthFailException(String message) {
		super(message);
	}
}