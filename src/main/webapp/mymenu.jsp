<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="${pageContext.request.contextPath}/css/common/menu.css" rel="stylesheet" type="text/css">
</head>
<body>
	<aside id="left">
		<hr id="menuhr">
		<ul class="height">
			<li><img src="img/booking-icon.png" class="icon">&nbsp;&nbsp;<a href="myAfterReserv" class ="reserv">예약내역</a></li><br>
			<li><img src="img/animal-icon.png" class="icon">&nbsp;&nbsp;<a href="myPostList" class ="request">Help 요청</a></li><br>
			<li><img src="img/hand-icon.png" class="icon">&nbsp;&nbsp;<a href="myApplyList" class ="support">Help 지원</a></li><br>
			<li><img src="img/user-icon.png" class="icon">&nbsp;&nbsp;<a href="myProfile" class="my">내 프로필</a></li><br>
			<li><img src="img/pet-icon.png" class="icon">&nbsp;&nbsp;<a href="myPetList" class="pet">반려동물 프로필</a></li>
		</ul>
	</aside>
</body>
</html>