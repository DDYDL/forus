<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/my/containerbox.css" />
<link rel="stylesheet" href="css/my/profile.css" />
<link rel="stylesheet" href="css/my/input.css" />
<link rel="stylesheet" href="css/my/button.css" />
<style>
.my {
	font-weight: bold;
	color: rgba(105, 233, 46);
}
</style>

</head>
<body>

      
      
      
	<%@ include file="../mainmypage.jsp"%>

	<div class="mypage-content">
	
	<span id="authentication">병원관계자라면?&nbsp;
         <a href="/forus/hmyNewHospital"><img src="play.png" width="16px">&nbsp;병원인증하기</a>
      </span>
		<div>
			<h3>내 프로필</h3>
		</div>
		<div>
			<ul>
				<li>프로필 사진</li>
				<li><img src="img/profileicon.png" class="img-icon"></li>

				<li class="title">이름</li>
				<li class="line"><span>${user.name }</span></li>
				
				<li class="title">닉네임</li>
				<li class="line"><span>${user.nickname }</span></li>
				
				<li class="title">이메일</li>
				<li class="line"><span>${user.email }</span></li>
					
				<li class="title">비밀번호</li><br>
				<li class="line"><span>수정 페이지에서 변경가능</span></li>
				
				<li class="title">휴대전화번호</li>
				<li class="line"><span>${user.phone }</span></li>
				
				<li class="title">생년월일</li><br><br>
				<li><span>${user.birthday }</span></li>
				
				<li class="title">성별</li><br><br>
				<li><span>${user.gender }</span>
				</li>
			</ul>

			<div class="find-btn">
				<button type="button" title="Button push blue/green"
					class=" btn btnPush btnBlueGreen find-btn1"
					onclick="location.href='/forus/myProfileModify?id=${user.id}'">수정</button>

			</div>
		</div>

	</div>
</body>
</html>