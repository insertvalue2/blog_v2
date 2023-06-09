package com.tenco.blog.model;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.NonNull;

@Data // Lombok 사용 추가
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank // 코드 추가
	@Column(nullable = false, length = 30)
	private String username;
	
	@Column(nullable = false, length = 30)
	private String password;
	@Column(nullable = false, length = 50)
	private String email; 
	@ColumnDefault("'user'") // 문자열 타입 이라고 명시 ('')
	private String role;  // user, admin, manager 
	@CreationTimestamp // 시간 자동 입력 
	private Timestamp createdDate;
}
