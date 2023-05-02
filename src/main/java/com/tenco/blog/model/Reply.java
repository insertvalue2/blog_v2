package com.tenco.blog.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Reply {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; 
	private String content;
	// 오류 발생 
	//private Board boardId; 
	//private User userId; 
	
	@CreationTimestamp // insert 시 자동 입력 (now())
	private Timestamp createdDate; 
}
