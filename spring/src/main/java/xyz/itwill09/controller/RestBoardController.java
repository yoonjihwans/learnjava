package xyz.itwill09.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import lombok.RequiredArgsConstructor;
import xyz.itwill09.dto.RestBoard;
import xyz.itwill09.service.RestBoardService;

//REST 기능을 제공하는 Controller 클래스의 요청 처리 메소드는 Postman 또는 ARC와 같은 
//Restful API 검증 프로그램을 사용해 확인

//@RestController : REST 기능을 제공하는 요청 처리 메소드(Restful API)만 작성된 Controller 
//클래스를 Spring Bean으로 등록하기 위한 어노테이션
// => 요청 처리 메소드에 @ResponseBody 어노테이션을 사용하지 않아도 요청 처리 메소드의
//반환값을 사용해 클라이언트에게 문자열(JSON)로 응답 처리
@RestController
@RequestMapping("/rest")
@RequiredArgsConstructor
public class RestBoardController {
	private final RestBoardService restBoardService;
	
	//REST 기능을 제공하는 요청 처리 메소드는 @RequestMapping 어노테이션 대신 @GetMapping
	//, @PostMapping, @PutMapping, @PatchMapping, @DeleteMapping 등의 어노테이션을 사용하여
	//요청 페이지의 경로를 제공받아 요청 처리 메소드에 매핑 처리하는 것을 권장
	// => GET(검색), POST(삽입), PUT(전체 변경), PATCH(부분 변경), DELETE(삭제)
	
	//REST_BOARD 테이블에 저장된 행에서 요청 페이지 번호에 해당하는 행만 검색하여 JSON 형식의
	//문자열로 응답하는 요청 처리 메소드
	//@RequestMapping(value = "/board_list", method = RequestMethod.GET)
	@GetMapping("/board_list")
	//Controller 클래스에 @RestController 어노테이션을 사용해 Spring Bean으로 등록한 경우
	//요청 처리 메소드에 @ResponseBody 어노테이션을 사용하지 않아도 문자열(JSON)로 응답 처리
	//@ResponseBody
	public Map<String, Object> restBoardList(@RequestParam(defaultValue = "1") int pageNum
			, @RequestParam(defaultValue = "5") int pageSize) {
		return restBoardService.getRestBoardList(pageNum, pageSize);
	}
	
	//게시글을 전달받아 REST_BOARD 테이블의 행으로 삽입하고 실행결과를 문자열로 응답하는 요청 처리 메소드
	// => 게시글을 [application/json] 형식의 문자열로 전달받아 매개변수에 RestBoard 객체로 
	//저장하기 위해 @RequestBody 어노테이션 사용
	// => @RequestBody 어노테이션을 사용한 매개변수의 자료형을 DTO 클래스로 설정하면 JSON 
	//형식의 문자열로 저장된 값을 DTO 객체의 필드에 저장
	@PostMapping("/board_add")
	public String restBoardAdd(@RequestBody RestBoard restBoard) {
		//HtmlUtils.htmlEscape(String str) : 매개변수로 전달받은 문자열에 존재하는 HTML 태그
		//관련 문자를 회피문자로 변환하여 반환하는 정적메소드 - XSS 공격에 대한 방어
		restBoard.setContent(HtmlUtils.htmlEscape(restBoard.getContent()));
		restBoardService.addRestBoard(restBoard);
		return "success";
	}

	/*
	//글번호를 전달받아 REST_BOARD 테이블에 저장된 행을 검색하여 JSON 형식의 문자열로 응답하는 요청 처리 메소드
	// => 페이지 요청시 질의문자열(QueryString)로 값을 전달받아 매개변수에 저장해 요청 처리 메소드에서 사용
	@GetMapping("/board_view")
	public RestBoard restBoardView(@RequestParam int idx) {
		return restBoardService.getRestBoard(idx);
	}
	*/

	//글번호를 전달받아 REST_BOARD 테이블에 저장된 행을 검색하여 JSON 형식의 문자열로 응답하는 요청 처리 메소드
	// => 페이지 요청시 요청 URL 주소에 {이름} 형식으로 값을 전달받아 매개변수에 저장해 요청 처리 메소드에서 사용
	// => 요청 URL 주소로 제공된 값을 매개변수에 저장하여 사용하기 위해서는 @PathVariable 어노테이션 사용
	//@PathVariable : 페이지를 요청한 URL 주소로 값을 전달받아 매개변수에 저장하기 위한 어노테이션
	// => 페이지 요청 URL 주소로 값을 제공받기 위한 이름과 같은 이름으로 매개변수를 작성해야만
	//요청 URL 주소로 제공된 값을 매개변수에 저장 가능
	//value 속성 : 요청 URL 주소로 제공된 값이 저장된 이름을 속성값으로 설정
	@GetMapping("/board_view/{idx}")
	public RestBoard restBoardView(@PathVariable int idx) {
		return restBoardService.getRestBoard(idx);
	}

	
	//게시글을 전달받아 REST_BOARD 테이블에 저장된 행을 변경하고 실행결과를 문자열로 응답하는 요청 처리 메소드
	@PutMapping("/board_modify")
	public String restBoardModify(@RequestBody RestBoard restBoard) {
		restBoard.setContent(HtmlUtils.htmlEscape(restBoard.getContent()));
		restBoardService.modifyRestBoard(restBoard);
		return "success";
	}

	//글번호를 전달받아 REST_BOARD 테이블에 저장된 행을 삭제하고 실행결과를 문자열로 응답하는 요청 처리 메소드
	@DeleteMapping("/board_remove/{idx}")
	public String restBoardRemove(@PathVariable int idx) {
		restBoardService.removeRestBoard(idx);
		return "success";
	}
}