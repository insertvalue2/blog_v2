package com.tenco.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Reply {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false, length = 200)
	private String content;
	@CreationTimestamp // insert 시 자동 입력 (now())
	private Timestamp createdDate; 
	
	// Reply 와 Board 관계 - N : 1
	@ManyToOne
	@JoinColumn(name = "boardId")
	private Board board;
	
	// Reply 와 User 관계 - N : 1
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
}
