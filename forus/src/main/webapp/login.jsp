<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<style type="text/css">
.title {
	text-align: center;
}

.textbox {
	width: 300px;
	height: 35px;
	font-size: 16px;
	font-color: #ccc;
	border: #ccc 1px solid;
	border-width: 0 0 1px;
}

.loginbtn {
	width: 200px;
	height: 50px;
	font-size: 20px;
	font-weight: bold;
	border-radius: 10px;
	border: 0;
	background: rgba(105, 233, 46, 0.37);
}

.loginbtn:hover {
	background: rgba(44, 207, 39, 0.37);
}

.joinbtn {
	width: 200px;
	height: 50px;
	font-size: 20px;
	font-weight: bold;
	border-radius: 10px;
	border: 0;
}

.joinbtn:hover {
	background: rgba(44, 207, 39, 0.37);
}

.columnsize {
	width: 100px;
	font-size: 20px;
	font-weight: bold;
}
</style>
</head>
<body>
	<%@ include file="header.jsp"%>
	<br>
	<br>
	<h2 class=title>로그인</h2>
	<br>
	<br>
	<div>
		<input type="text" placeholder=" 이메일 아이디" class="textbox"><br>
		<br> <input type="text" placeholder=" 비밀번호" class="textbox"><br>
		<br>
		<br>
		<br>
	</div>
	<div>
		<input type="submit" value="로그인" class="loginbtn"
			onclick="location.href='#'"><br>
		<br> <input type="submit" value="신규 회원가입" class="joinbtn"
			onclick="location.href='join.jsp'">
	</div>
</body>
</html>