package com.tenco.blog.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiErrorResponse {
	
	private int statusCode; 
	private String code; 
	private String message; 
	private String resultCode;
	private List<ExceptionFieldMessage> exceptionFieldMessages;
	
}
