package com.tenco.blog.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class BlogControllerTest {

	@GetMapping("/hello")
	public String hello() {
		return "<div>hello spring boot</div>";
	}
}
