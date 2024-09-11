package xyz.itwill09.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import xyz.itwill09.dto.RestMember;

//REST(Representational State Transfer) : 자원(Resource)의 표현(Representational)에 의해
//상태(State)를 전달(Transfer)하는 것을 의미
// => 페이지 요청에 대한 실행결과를 XML 문서 또는 JSON 형식의 문서로 클라이언트에게 응답 처리
//Restful API : REST 기능을 사용해 두 컴퓨터의 시스템이 안전하게 값을 주고 받기 위한 프로그램
// => 스마트기기의 프로그램(앱) 실행에 필요한 정보를 전달받거나 실행결과를 제공하기 위해 작성

@Controller
@RequestMapping("/rest")
public class RestfulController {
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() {
		return "rest/input";
	}
	
	/*
	//페이지 요청시 요청 처리 메소드를 호출해 요청 처리 후 실행결과를 Model 객체로 Request
	//Scope 속성값으로 저장하고 뷰이름(ViewName)을 반환하여 뷰(View)로 응답 처리
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@RequestParam String id, @RequestParam String name, Model model) {
		model.addAttribute("id", id);
		model.addAttribute("name", name);
		return "rest/output";
	}
	*/
	
	//@ResponseBody : 요청 처리 메소드의 반환값을 리스폰즈 메세지 몸체부에 저장하여 일반 
	//문서로 클라이언트에게 전달하여 응답하는 기능을 제공하는 어노테이션
	// => Front Controller가 요청 처리 메소드의 반환값을 뷰로 변환하여 응답 처리하지 않고
	//요청 처리 메소드의 반환값으로 직접 응답 처리
	// => @ResponseBody 어노테이션 대신 ResponseEntity 클래스를 요청 처리 메소드의 반환형으로
	//작성해 응답 처리 가능
	// => 페이지 요청시 JSON 형식의 문자열로 클라이언트에게 응답 처리하기 위해 사용
	//@RequestBody : 리퀘스트 메세지 몸체부에 저장된 모든 전달값을 문자열로 제공하기 위한 어노테이션
	// => POST, PUT, PATCH, DELETE 등의 요청방식으로 페이지를 요청한 경우 리퀘스트 메세지 몸체부에
	//저장된 모든 전달값을 문자열로 제공받아 매개변수에 저장
	// => GET 방식으로 페이지를 요청한 경우 리퀘스트 메세지 몸체부 비어 있으므로 @RequestBody 어노테이션 사용 불가능
	// => 페이지 요청시 JSON 형식으로 전달된 값을 제공받아 매개변수에 저장하기 위해 사용
	// => @RequestBody 어노테이션 대신 RequestEntity 클래스로 요청 처리 메소드의 매개변수를
	//작성해 전달값을 제공받아 매개변수에 저장	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	@ResponseBody
	public String join(@RequestBody String input) {
		return input;
	}
	
	/*
	//@ResponseBody 어노테이션을 사용해 요청 처리 메소드의 반환값(RestMember 객체)을 리스폰즈
	//메세지 몸체부에 문자열로 저장하여 클라이언트에게 응답 처리
	//문제점 : 리스폰즈 메세지 몸체부에 Java 객체를 저장해 응답 처리 불가능 - 406 에러코드 발생
	//해결법 : 요청 처리 메소드에 의해 반환받는 Java 객체를 문자열(XML 또는 JSON)로 변환하여
	//리스폰즈 메세지 몸체부에 저장해 응답 처리
	// => jackson-databind 라이브러리를 프로젝트에 빌드 처리하면 요청 처리 메소드에 의해 반환되는
	//Java 객체를 JSON 형식의 문자열로 변경하여 리스폰즈 메세지 몸체부에 저장해 응답 처리 가능
	@RequestMapping("/member")
	@ResponseBody
	public RestMember restMember() {
		//RestMember 객체를 자바스크립트의 Object 객체로 변환하여 응답 처리
		return RestMember.builder().id("abc123").name("홍길동").email("abc@itwill.xyz").build();
	}
	*/
	
	//@ResponseBody 어노테이션 대신 요청 처리 메소드의 반환형을 ResponseEntity 클래스로 설정해
	//ResponseEntity 객체를 반환해도 문자열로 응답 처리 가능
	// => ResponseEntity 클래스의 제네릭에는 응답 처리될 Java 객체의 자료형을 설정
	@RequestMapping("/member")
	public ResponseEntity<RestMember> restMember() {
		try {
			RestMember member=RestMember.builder().id("abc123").name("홍길동").email("abc@itwill.xyz").build();
			return new ResponseEntity<RestMember>(member, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<RestMember>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping("/member_list")
	@ResponseBody
	public List<RestMember> restMemberList() {
		List<RestMember> list=new ArrayList<RestMember>();
		list.add(RestMember.builder().id("abc123").name("홍길동").email("abc@itwill.xyz").build());
		list.add(RestMember.builder().id("opq456").name("임꺽정").email("opq@itwill.xyz").build());
		list.add(RestMember.builder().id("xyz789").name("전우치").email("xyz@itwill.xyz").build());
		//List 객체를 자바스크립트의 Array 객체로 변환하여 응답 처리
		// => List 객체의 요소값(RestMember 객체)는 자바스크립트의 Object 객체로 변환
		return list;
	}
	
	@RequestMapping("/member_map")
	@ResponseBody
	public Map<String, Object> restMemberMap() {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("id", "abc123");		
		map.put("name", "홍길동");		
		map.put("email", "abc@itwill.xyz");		
		//Map 객체를 자바스크립트의 Object 객체로 변환하여 응답 처리
		return map;
	}
	
	@RequestMapping(value = "/board", method = RequestMethod.GET)
	public String restBoard() {
		return "rest/board";
	}
}












