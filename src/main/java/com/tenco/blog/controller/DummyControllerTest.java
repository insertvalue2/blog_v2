package com.tenco.blog.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tenco.blog.model.User;
import com.tenco.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {

	// 테스트 : 서비스 없이 바로 (DAO 사용해보기)
	// @Autowired
	private UserRepository userRepository;

	public DummyControllerTest(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@DeleteMapping("/user/{id}")
	public String deleteUser(@PathVariable int id) {
		// 반드시 인증검사!  
		try {
			// 원시코드 확인하고 코드 설계 하기
			userRepository.deleteById(id); // 없는 id를 호출하면 오류 발생
		} catch (Exception e) {
			return "없는 사용자 입니다";
		}
		return  id + " 사용자가 삭제되었습니다 "; 
	}
	
	// email, password 변경 
	@Transactional // 함수 종료시에 commit 가 된다. 
	@PutMapping("/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) {
		
		User user = userRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("수정에 실패하였습니다.");
		});
		// 객체 상태값 변경 
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
	
		return user; 
	}

	// 한 페이지당 2 건의 데이터를 리턴
	// http://localhost:9090/blog/dummy/user?page=0 (요청은 0부터 시작한다)
	// List 타입에서 추후 Page 타입으로 변경
	// 다시 Page 타입에서 List 로 변경하기
	@GetMapping("/dummy/users")
	public List<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Direction.DESC) Pageable pageable) {
//			Page<User> pageUser = userRepository.findAll(pageable);
//			if(pageUser.isFirst()) {
//				
//			}
		// content 만 필요 하다면
		List<User> users = userRepository.findAll(pageable).getContent();
		return users;
	}

	// http://localhost:8080/dummy/join
	@PostMapping("/dummy/join")
	public String join(@Validated @RequestBody User user) {

		// 인증 검사
		// 유효성 검사
		System.out.println("username : " + user.getUsername());
		System.out.println("pasword : " + user.getPassword());
		System.out.println("email : " + user.getEmail());
		System.out.println("id : " + user.getId());
		System.out.println("date : " + user.getCreatedDate());
		System.out.println("role : " + user.getRole());

		// Entity Type 이여야 한다.
		user.setRole("user");
		User userEntity = userRepository.save(user);
		System.out.println("result : " + userEntity);

		return "회원가입에 성공했네요\n" + userEntity.toString();
	}

	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		// Optional : user 가 null 일 수 도 있다.
		// Optional 로 User 객체를 감싸서 가져오 겠다.
		// 1. get() null 이 리턴될 일이 없다.
		// 2. orElseGet
		// 3. orElseThrow
//		User user =  userRepository.findById(id).orElseGet(new Supplier<User>() {
//			@Override
//			public User get() {
//				return new User();
//			}
//		});
		User user = userRepository.findById(id).orElseThrow(() -> {
			// Advice 처리 하지 않음
			return new IllegalArgumentException("해당 유저는 없습니다. id : " + id);
		});
		// 요청 web browser
		// user 객체는 = 자바 object (변환 문자열)
		// 스프링부트 = MessageConverter 응답시에 Jackson 라이브러리를 호출해서
		// user 오브젝트가 json type 로 보이게 된다.
		return user;
	}
	

}
