<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>recruit apply detail page</title>
<link href="./css/recruitapplydetailpage.css" rel="stylesheet" type="text/css">
</head>
<body>
	<%@ include file="../mainmypage.jsp" %>
	
	<div class="mypage-content">
		<p class="mypage-content-title">지원자</p>
    
    <!-- user 프로필 가져오기 -->
    <div class="box">
    	<p class="content-inner-title">${recruit_apply.apply_title}</p>
    	<div class="list-header">
    	<img src="../img/users2.png">
    	<table>
    		<tr>
    			<td>${apply_user.name}(${apply_user.birthday}, ${apply_user.gender})</td>
    			<td></td>
    		</tr>
    		<tr>
    			<td>주소</td>
    			<td>${apply_user.address}</td>
    		</tr>
    		<tr>
    			<td>휴대폰</td>
    			<td>${apply_user.phone}</td>
    		</tr>
    		<tr>
    			<td>이메일</td>
    			<td>${apply_user.email}</td>
    		</tr>
    	</table>
    	</div>
    </div>
    <br>
    <!-- pet 프로필 가져오기 -->
    <div class="box">
    	<h5>반려동물</h5>
        <table>
        	<c:forEach items="${petList}" var="pet">
    		<tr>
    			<td><img src="${pet.pet_picture}"></td>
    			<td>${pet.pet_name}</td>
    			<td>(${pet.pet_age}, ${pet.pet_gender})</td>
    			<td>${pet.pet_memo}</td>
    		</tr>
    		</c:forEach>
    	</table>
    </div>
    
    <br>
    <div class="box">
    	<h5>전달 메시지</h5>
    	<p>${recruit_apply.apply_content}</p>
    </div>
    <div class="btndiv"><a href="recruitApplyList" title="Button push blue/green" class="button btnPush btnBlueGreen">채용</a></div>
    </div>
</body>
</html>