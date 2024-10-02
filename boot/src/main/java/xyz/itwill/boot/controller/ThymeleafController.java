package xyz.itwill.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import xyz.itwill.boot.dto.User;

//Thymeleaf : 서버에서 HTML 문서 또는 XML 문서를 생성하여 제공하는 Server Side Template Engine
// => HTML 태그를 기반으로 [th:속성명="속성값"]을 추가하여 동적으로 뷰(View)를 생성하여 제공
// => 서버를 실행하지 않으면 정적인 HTML 문서로 사용되며 서버를 실행하면 동적인 HTML 문서로 
//생성되어 사용 - Natural Templates
// => JSP 태그 및 커스텀 태그(JSTL)를 사용할 수 없어 코드의 재사용 불가능 - JavaScript 사용

@Controller
@RequestMapping("/thymeleaf")
public class ThymeleafController {
	@GetMapping("/text")
	public String text(Model model) {
		model.addAttribute("dataOne", "Hi, Thymeleaf!!!");
		model.addAttribute("dataTwo", "<font color='red'>Hi, Thymeleaf!!!</font>");
		
		User user=User.builder().id("abc123").name("홍길동").email("abc@itwill.xyz").build();
		model.addAttribute("user", user);
		
		return "result";
	}
	
	
}







