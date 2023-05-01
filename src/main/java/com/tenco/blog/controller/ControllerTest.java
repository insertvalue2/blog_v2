package com.tenco.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerTest {

	@GetMapping("/temp")
	public String tempPage() {
		// viewResolver 동작 
		return "/temp";
	}

}
