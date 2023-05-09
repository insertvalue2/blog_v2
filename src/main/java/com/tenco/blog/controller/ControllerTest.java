package com.tenco.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tenco.blog.dto.UserDto;
import com.tenco.blog.model.User;

@Controller
public class ControllerTest {

	@GetMapping("/temp")
	public String tempPage() {
		// viewResolver 동작 
		return "/temp";
	}
	
	// 기본 파싱전략 1 :  key=value  
	// Query String 처리 ?name="홍"&password=1234&email="a@naver.com"
	@PostMapping("/temp/signup")
	@ResponseBody // 응답을 데이터로 내려 주고 싶다면 처리 
	public User temp1(String username, String password, String email) {
		
		User reqUser = new User();
		reqUser.setUsername(username);
		reqUser.setPassword(password);
		reqUser.setEmail(email);
		return reqUser;
	}
	
	// REST API 처리시 Object Mapper 처리하기 위해 어노테이션을 붙어야 한다.
	// @RequestBody 미 작성시 오류 발생 
	@PostMapping("/temp/signup2")
	@ResponseBody // 응답 처리
	public UserDto temp2(@RequestBody UserDto userDto) {
		System.out.println("POST 동작 : " + userDto);
		return userDto;
	}
	

	@PutMapping("/temp/signup3")
	@ResponseBody // 응답 처리
	public UserDto temp3(@RequestBody UserDto userDto) {
		System.out.println("PUT 동작 : " + userDto);
		return userDto;
	}

}
