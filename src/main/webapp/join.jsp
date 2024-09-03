<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
<script src="https://cdn.jsdelivr.net/npm/flatpickr/dist/l10n/ko.js"></script>
<style type="text/css">
body {

padding-top: 5%;

/* padding-left: 15%; */

padding-right: 15%;

}
.title {
	text-align: center;
}

table {
	text-align: left;
	margin-top: 50px;
	margin-left: 680px;
}

tr {
	height: 60px;
}

.textbox {
	width: 400px;
	height: 35px;
	font-size: 16px;
	font-color: #ccc;
	border: #ccc 1px solid;
	border-radius: 5px;
}

.btn {
	width: 200px;
	height: 50px;
	font-size: 20px;
	font-weight: bold;
	border-radius: 10px;
	border: 0;
	background: rgba(105, 233, 46, 0.37);
}

.btn:hover {
	background: rgba(44, 207, 39, 0.37);
}

.columnsize {
	width: 150px;
}

#justDate {
	width: 200px;
	height: 35px;
	font-size: 16px;
	border: #ccc 1px solid;
	border-radius: 5px;
}
.fixed{
	width: 1920px;
}
</style>
</head>
<body>
	<div class="fixed">
	<%@ include file="header.jsp"%>
	<br>
	<h2 class=title>회원가입</h2>
		<table>
			<tr>
				<td class="columnsize">이메일<br></td>
				<td><input type="text" placeholder=" 이메일을 입력해주세요"
					class="textbox"></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="text" placeholder=" 비밀번호 입력해주세요"
					class="textbox"></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" placeholder=" 이름을 입력해주세요"
					class="textbox"></td>
			</tr>
			<tr>
				<td>닉네임</td>
				<td><input type="text" placeholder=" 닉네임을 입력해주세요"
					class="textbox"></td>
			</tr>
			<tr>
				<td>휴대전화번호</td>
				<td><input type="text" placeholder=" 휴대전화번호를 입력해주세요"
					class="textbox"></td>
			</tr>
			<tr>
				<td>인증번호</td>
				<td><input type="text" placeholder=" 인증번호를 입력해주세요"
					class="textbox"></td>
			</tr>
			<tr>
				<td>생년월일</td>
				<td><input id="justDate" type="text" value="2024-08-26"></td>
			</tr>
			<tr>
				<td>성별</td>
				<td><input type="radio" name="gender" value="male">남&nbsp;&nbsp;<input
					type="radio" name="gender" value="female">여</td>
			</tr>
		</table>
		<br>
		<br>
		<div>
			<input type="submit" value="가입하기" class="btn"
				onclick="location.href='login.jsp'">
		</div>
	</div>
</body>
<script>
	flatpickr("#justDate", {
		"locale" : "ko",
		"dateFormat" : "Y-m-d",
	});
</script>
</html>