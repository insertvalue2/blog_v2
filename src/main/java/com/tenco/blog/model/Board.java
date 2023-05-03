package com.tenco.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false, length = 100)
	private String title; 
	@Lob // 대용량 데이터 
	private String content;
	@ColumnDefault("0") // 숫자형은 - "숫자", 문자형 - "'문자'"
	private int count; 
	@CreationTimestamp
	private Timestamp createdDate;
	
	// Board 와 User 의 연관 관계는 N : 1  
	@ManyToOne // 연관 관계를 맺어 주어야 한다.  
	@JoinColumn(name = "userId") // board 테이블에 생성될 컬럼명 
	private User user; 
	
	// Board 정보를 가지고 올 때 댓글 정보도 들고 와야 한다면? 
	// Board 와 Reply 의 연관 관계는 1 : N 
	@OneToMany(mappedBy = "board", fetch = FetchType.LAZY) // 기본 값  
	//@OneToMany(mappedBy = "board", fetch = FetchType.EAGER)
	private List<Reply> reply; 
	// 주의 : Board 테이블에 reply_id 컬럼이 필요한가? (x) 
	// mappedBy 설정 -> 난 FK를 가지는 컬럼이 아니다
	// 즉 mappedBy를 설정하면 board 테이블에 컬럼이 생성 되지 않는다. 
	// 그럼 오브젝트가 생성이 될 때 데이터를 가지고만 와 달라 요청 가능
	// (mappedBy = "board") Reply 객체의 변수 명이어야 한다.
}
