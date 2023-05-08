package com.tenco.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tenco.blog.model.User;

//<T, ID> 테이블, PK 데이터 타입 명시
// @Repository  // 생략 가능 
public interface UserRepository extends JpaRepository<User, Integer> {
	
	//select, selectAll, insert, update, delete 등 - 자동 생성
}
