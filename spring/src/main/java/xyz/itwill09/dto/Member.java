package xyz.itwill09.dto;

import lombok.Data;

//회원정보를 저장하기 위한 클래스 - DTO 클래스
// => 데이타 처리를 목적으로 작성된 클래스의 메소드에게 정보를 전달할 목적으로 작성된 클래스지만
//전달값을 매개변수에 저장하기 위해 사용 가능 - 전달값의 이름과 같은 이름으로 필드 작성
@Data
public class Member {
	private String id;
	private String passwd;
	private String name;
	private String email;
}