package xyz.itwill08.spring;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

//spring-webmvc 라이브러리로 제공된 Controller 인터페이스를 상속받아 요청 처리 클래스 작성 - Controller 클래스

//클라이언트가 [/list.do]의 URL 주소로 요청한 경우 컨트롤러에 의해 클래스의 요청 처리 메소드를
//호출하여 클라이언트의 요청 처리
public class ListController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//ModelAndView 객체 : 처리결과와 뷰이름을 저장하기 위한 객체
		ModelAndView modelAndView=new ModelAndView();
		
		List<Product> productList=new ArrayList<Product>();
		productList.add(new Product(1000, "컴퓨터"));
		productList.add(new Product(2000, "노트북"));
		productList.add(new Product(3000, "테블릿"));
		
		//ModelAndView.addObject(String attributeName, Object attributeValue) : ModelAndView
		//객체에 요청 처리 메소드의 실행결과를 속성값으로 저장하기 위한 메소드 - Request Scope
		// => JSP 문서에서 ModelAndView 객체에 저장된 속성값을 제공받아 응답 처리 가능
		modelAndView.addObject("productList", productList);
		
		//ModelAndView.setViewName(String viewName) : ModelAndView 객체의 뷰이름을 변경하는 메소드
		modelAndView.setViewName("product_list");
		
		return modelAndView;
	}

}





