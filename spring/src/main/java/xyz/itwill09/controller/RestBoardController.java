package xyz.itwill09.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import xyz.itwill09.service.RestBoardService;

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
	
	//@RequestMapping(value = "/board_list", method = RequestMethod.GET)
	@GetMapping("/board_list")
	//Controller 클래스에 @RestController 어노테이션을 사용해 Spring Bean으로 등록한 경우
	//요청 처리 메소드에 @ResponseBody 어노테이션을 사용하지 않아도 문자열(JSON)로 응답 처리
	//@ResponseBody
	public Map<String, Object> restBoardList(@RequestParam(defaultValue = "1") int pageNum
			, @RequestParam(defaultValue = "5") int pageSize) {
		return restBoardService.getRestBoardList(pageNum, pageSize);
	}
}











