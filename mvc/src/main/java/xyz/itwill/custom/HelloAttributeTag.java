package xyz.itwill.custom;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

//태그 속성이 있으며 태그 내용이 없는 커스텀 태그를 생성하기 위한 클래스
public class HelloAttributeTag extends TagSupport {
	private static final long serialVersionUID = 1L;

	//커스텀 태그의 속성값을 저장하기 위한 필드
	// => 커스텀 태그의 속성명과 같은 이름으로 필드 작성
	private String name;
	
	//태그 클래스를 객체로 만들 때 필드에 초기값 저장
	public HelloAttributeTag() {
		//커스텀 태그의 속성을 생략할 경우 기본적으로 사용될 속성값을 필드에 저장하여 설정
		// => 커스텀 태그의 속성이 필수 속성으로 설정한 경우 필드 초기값 저장 생략 가능
		name="홍길동";
	}

	public String getName() {
		return name;
	}

	//커스텀 태그의 속성을 사용하여 속성값을 설정할 때 필드의 Setter 메소드 자동 호출
	public void setName(String name) {
		this.name = name;
	}
	
	//커스텀 태그 사용시 실행될 명령을 작성하기 위한 메소드만 오버라이드 선언
	// => 오버라이드 선언하지 않은 메소드는 부모클래스의 명령이 없는 메소드를 자동 호출
	@Override
	public int doStartTag() throws JspException {
		try {
			if(name.equals("홍길동")) {
				pageContext.getOut().println("<h3>관리자님, 안녕하세요.</h3>");
			} else {
				pageContext.getOut().println("<h3>"+name+"님, 안녕하세요.</h3>");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return SKIP_BODY;
	}
	
}









