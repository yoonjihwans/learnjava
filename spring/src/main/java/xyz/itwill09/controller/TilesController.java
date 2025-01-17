package xyz.itwill09.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//TilesView 클래스를 사용해 생성된 뷰(View)로 응답 처리하기 위한 방법
// => TilesView 클래스 : 요청 처리 메소드의 반환값(ViewName)을 제공받아 다수의 JSP 문서가 결합된
//뷰(View)로 응답하기 위한 기능을 제공하는 ViewResolver 클래스
//1.TilesView 관련 라이브러리(tiles-extras 라이브러리)를 프로젝트에 빌드 처리 - 메이븐 사용 : pom.xml 
//2.요청 처리 메소드의 반환값(ViewName)을 제공받아 응답할 JSP 문서(템플릿 페이지) 설정
// => TilesView 클래스가 제공받아 사용하기 위한 환경설정파일 작성 - /WEB-INF/spring/appServlet/tiles.xml
//3.Front Controller(DispatcherServlet 객체)가 요청 처리 메소드의 반환값(ViewName)을 제공받아
//TilesView 클래스를 ViewResolver 객체로 응답 처리되도록 Spring Bean Configuration File
//(servlet-context.xml)에 환경 설정
// => InternalResourceViewResolver 객체보다 TilesView 객체가 먼저 실행되도록 우선순위 설정

@Controller
public class TilesController {
	@RequestMapping("/")
	public String tiles() {
		return "main";
	}
	
	@RequestMapping("/tiles")
	public String tiles1() {
		return "layout/tiles";
	}
	
	@RequestMapping("/admin/")
	public String admin() {
		return "admin";
	}
}





