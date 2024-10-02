package xyz.itwill.security;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.HtmlUtils;

import lombok.RequiredArgsConstructor;
import xyz.itwill.dto.SecurityBoard;
import xyz.itwill.service.SecurityBoardService;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class SecurityBoardController {
	private final SecurityBoardService securityBoardService;
	
	//페이지 요청시 모든 전달값을 Map 객체로 제공받아 사용
	@RequestMapping("/list")
	public String list(@RequestParam Map<String, Object> map, Model model) {
		model.addAttribute("resultMap", securityBoardService.getSecurityBoardList(map));
		model.addAttribute("searchMap", map);
		return "board/board_list";
	}
	
	//로그인 사용자만 요청 처리 메소드를 호출할 수 있도록 권한 설정
	//@PreAuthorize : 요청 처리 메소드가 실행되기 전에 권한을 설정하기 위한 어노테이션
	// => EL 표현식 및 EL 연산자를 사용해 복합적인 권한 설정 가능
	//value 속성 : 권한(ROLE)을 속성값으로 설정 - SpEL 사용 가능
	// => value 속성외에 다른 속성이 없는 경우 속성값만 설정 가능
	//@PostAuthorize : 요청 처리 메소드가 실행된 후에 권한을 설정하기 위한 어노테이션
	//@Secured : 요청 처리 메소드가 실행되기 전에 권한을 설정하기 위한 어노테이션 - 단순한 권한 설정만 가능
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value= "/register", method = RequestMethod.GET)
	public String register() {
		return "board/board_register";
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value= "/register", method = RequestMethod.POST)
	public String register(@ModelAttribute SecurityBoard board) {
		board.setSubject(HtmlUtils.htmlEscape(board.getSubject()));
		board.setContent(HtmlUtils.htmlEscape(board.getContent()));
		securityBoardService.addSecurityBoard(board);
		return "redirect:/board/list";
	}
	
	@RequestMapping("/detail")
	public String detail(@RequestParam Map<String, Object> map, Model model) {
		int num=Integer.parseInt((String)map.get("num"));
		model.addAttribute("securityBoard", securityBoardService.getSecurityBoardByNum(num));
		model.addAttribute("searchMap", map);
		return "board/board_detail";
	}
	
	//로그인 사용자 중 관리자 또는 게시글 작성자인 경우에만 요청 처리 메소드를 호출할 수 있도록 권한 설정
	// => # 표현식을 사용하여 요청 처리 메소드에서 전달값이 저장된 매개변수 사용 가능
	@PreAuthorize("hasRole('ROLE_ADMIN') or principal.userid eq #map['writer'] ")
	@RequestMapping(value= "/modify", method = RequestMethod.GET)
	public String modify(@RequestParam Map<String, Object> map, Model model) {
		int num=Integer.parseInt((String)map.get("num"));
		model.addAttribute("securityBoard", securityBoardService.getSecurityBoardByNum(num));
		model.addAttribute("searchMap", map);
		return "board/board_modify";
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') or principal.userid eq #map['writer']")
	@RequestMapping(value= "/modify", method = RequestMethod.POST)
	public String modify(@ModelAttribute SecurityBoard board, 
			@RequestParam Map<String, Object> map, Model model) throws UnsupportedEncodingException {
		board.setSubject(HtmlUtils.htmlEscape(board.getSubject()));
		board.setContent(HtmlUtils.htmlEscape(board.getContent()));
		securityBoardService.modifySecurityBoard(board);
		
		String pageNum=(String)map.get("pageNum");
		String pageSize=(String)map.get("pageSize");
		String column=(String)map.get("column");
		String keyword=URLEncoder.encode((String)map.get("keyword"), "utf-8");
		
		return "redirect:/board/detail?num="+board.getNum()+"&pageNum="+pageNum
			+"&pageSize="+pageSize+"&column="+column+"&keyword="+keyword;
	}
	
	//로그인 사용자 중 관리자 또는 게시글 작성자인 경우에만 요청 처리 메소드를 호출할 수 있도록 권한 설정
	@PreAuthorize("hasRole('ROLE_ADMIN') or principal.userid eq #writer ")
	@RequestMapping("/remove")
	public String remove(@RequestParam int num, @RequestParam String writer) {
		securityBoardService.removeSecurityBoard(num);
		return "redirect:/board/list";
	}
}
