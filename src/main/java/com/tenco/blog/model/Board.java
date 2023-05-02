package com.tenco.blog.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; 
	private int title; 
	private String content; 
	private int count; 
	// 오류 발생 
	//private User userId; 
	private Timestamp createdDate;
}
