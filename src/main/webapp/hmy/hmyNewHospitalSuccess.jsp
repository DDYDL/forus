<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" href="css/hmy/list.css" />
<link rel="stylesheet" href="css/hmy/font.css" />
<link rel="stylesheet" href="css/hmy/button.css" />


</head>
<body>
	<%@ include file="../mainmypage.jsp"%>
	<div>
		<div>
			<img src="img/mail.png">
			<h2>병원 등록이 완료 되었습니다.</h2>
			<hr width="10%">
			<p>병원 정보는 언제든 수정 가능합니다!</p>
			<div class="find-btn">
				<button type="button" title="Button push blue/green"
					class="btn btnPush btnBlueGreen find-btn1"
					onclick="location.href='../my/myProfile.jsp'"> 홈으로 </button>

			</div>
		</div>
	</div>
</body>
</html>