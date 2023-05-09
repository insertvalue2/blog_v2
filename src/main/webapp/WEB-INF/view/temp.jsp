<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<!-- Popper JS -->
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

	<div class="container">
		<h1>form 테스트</h1>
		<form action="/temp/signup2" method="post">
			<div class="form-group">
				<label for="username">username:</label> <input type="text" class="form-control" placeholder="Enter username" id="username" name="username" value="홍1">
			</div>
			<div class="form-group">
				<label for="password">password:</label> <input type="password" class="form-control" placeholder="Enter password" id="password" name="password" value="1234">
			</div>
			<div class="form-group">
				<label for="email">email:</label> <input type="email" class="form-control" placeholder="Enter email" id="email" name="email" value="a@naver.com">
			</div>
			<!-- <button id=""  type="submit" class="btn btn-primary">회원가입</button> -->
		</form>

		<button id="signup--submit" class="btn btn-primary">회원가입</button> 
	</div>

	<script type="text/javascript">
		$(document).ready(function() {
			$('#signup--submit').on('click', function() {
				let data = {
					username : $('#username').val(),
					password : $('#password').val(),
					email : $('#email').val()
				};

				$.ajax({
					type : 'POST',
					url : '/temp/signup2',  // 1, 2, 3 테스트 
					data : JSON.stringify(data), // object -> json 문자열로 변경 
					contentType : 'application/json; charset=utf-8',
					dataType : 'json'
				}).done(function(data) {
					console.log(data);
					console.log(typeof data);
					alert("회원가입성공");
				}).fail(function(error) {
					console.log(error);
					alert('서버 오류');
				});
			});
		});
	</script>
</body>
</html>