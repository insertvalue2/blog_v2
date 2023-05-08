package com.tenco.blog.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tenco.blog.dto.ApiErrorResponse;
import com.tenco.blog.dto.ExceptionFieldMessage;

@RestControllerAdvice
public class RestExceptionHandler {
	
	@ExceptionHandler(value = Exception.class)
	public void exception(Exception e) {
		System.out.println("------------------");
		System.out.println(e.getClass().getName());
		System.out.println(e.getMessage());
		System.out.println("------------------");
	}
	
	@ExceptionHandler(value = IllegalArgumentException.class)
	public String illegalArgumentException(IllegalArgumentException e) {
		return "<h1>" + e.getMessage()+"</h1>";
	}
	
//	@ExceptionHandler(value = MethodArgumentNotValidException.class)
//	public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException e, BindingResult bindingResult) {
//		System.out.println("IllegalArgumentException 발생");
//		List<ExceptionFieldMessage> eList = new ArrayList<>();
//		
//		bindingResult.getAllErrors().forEach( action -> {
//			FieldError fieldError =  (FieldError) action;
//			String fieldName = fieldError.getField(); 
//			String message = fieldError.getDefaultMessage(); 
//			
//			ExceptionFieldMessage exceptionFieldMessage = new ExceptionFieldMessage(); 
//			exceptionFieldMessage.setField(fieldName);
//			exceptionFieldMessage.setMessage(message);
//			eList.add(exceptionFieldMessage);
//		});
//		// TODO 
//		// ApiErrorResponse 는 추후 처리 
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(eList);
//	}
//	
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ApiErrorResponse methodArgumentNotValidException(MethodArgumentNotValidException e, BindingResult bindingResult) {
		System.out.println("IllegalArgumentException 발생");
		List<ExceptionFieldMessage> eList = new ArrayList<>();
		
		bindingResult.getAllErrors().forEach( action -> {
			FieldError fieldError =  (FieldError) action;
			String fieldName = fieldError.getField(); 
			String message = fieldError.getDefaultMessage(); 
			
			ExceptionFieldMessage exceptionFieldMessage = new ExceptionFieldMessage(); 
			exceptionFieldMessage.setField(fieldName);
			exceptionFieldMessage.setMessage(message);
			eList.add(exceptionFieldMessage);
		});
		return ApiErrorResponse.builder()
						.code("-1")
						.statusCode(HttpStatus.BAD_REQUEST.value())
						.resultCode("fail")
						.message("잘못된 요청입니다.")
						.exceptionFieldMessages(eList).build();
	}
	
}
