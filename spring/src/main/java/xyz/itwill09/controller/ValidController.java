package xyz.itwill09.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class ValidController {
	@RequestMapping(value = "/valid/html", method = RequestMethod.GET)
	public String html() {
		 return "valid/html_form";
	}
}
