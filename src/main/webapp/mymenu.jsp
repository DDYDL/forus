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
		<ul>
			<li><a href="${pageContext.request.contextPath}/myAfterReserv">예약내역</a></li><br>
			<li><a href="${pageContext.request.contextPath}/my/mypostlist.jsp">게시글 관리</a></li><br>
			<li><a href="${pageContext.request.contextPath}/my/myapplylist.jsp">지원현황</a></li><br>
			<li><a href="${pageContext.request.contextPath}/myProfile" class="my">내 프로필</a></li><br>
			<li><a href="${pageContext.request.contextPath}/myPetList" class="pet">반려동물 프로필</a></li>
		</ul>
	</aside>
</body>
</html>