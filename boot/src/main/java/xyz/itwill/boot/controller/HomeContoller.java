package xyz.itwill.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeContoller {
	@GetMapping("/")
	public String main(Model model) {
		model.addAttribute("name", "홍길동");
		return "index";
	}
}