<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>recruit apply detail page</title>
<link href="./css/recruit/recruitapplydetailpage.css" rel="stylesheet" type="text/css">
</head>
<body>
	<%@ include file="../mainmypage.jsp" %>
	
	<div class="mypage-content">
		<p class="mypage-content-title">지원자</p>
    
    <!-- user 프로필 가져오기 -->
    <div class="box profile">
    	<p class="content-inner-title">${recruit_apply.apply_title}</p>
    	<div class="list-header">
    	<div class="user_img_div"><img src="image?file=${apply_user.picture eq null? 'default.png': apply_user.picture}&id=${user_id}&type=user" class="img-icon user_img"></div>
    	<table class="user_table">
    		<tr>
    			<td class="user_td1" colspan="2">${apply_user.name}(${apply_user.birthday}, ${apply_user.gender})</td>
    		</tr>
    		<tr>
    			<td class="user_td1">주소</td>
    			<td class="user_td2">${apply_user.address}</td>
    		</tr>
    		<tr>
    			<td class="user_td1">휴대폰</td>
    			<td class="user_td2">${apply_user.phone}</td>
    		</tr>
    		<tr>
    			<td class="user_td1">이메일</td>
    			<td class="user_td2">${apply_user.email}</td>
    		</tr>
    	</table>
    	</div>
    </div>
    <br>
    <!-- pet 프로필 가져오기 -->
    <div class="box profile">
    	<p class="content-inner-title">반려동물</p>
        <table>
        	<c:forEach items="${petList}" var="pet">
    		<tr>
    			<td class="pet_td1"><img src="image?file=${pet.pet_picture eq null? 'default.png': pet.pet_picture}&pet_id=${pet_id}&type=pet" class="img-icon pet_img"></td>
    			<td class="pet_td2">${pet.pet_name}</td>
    			<td class="pet_td3">(${pet.pet_age},&nbsp;${pet.pet_gender})</td>
    			<td class="pet_td4">${pet.pet_memo}</td>
    		</tr>
    		</c:forEach>
    	</table>
    </div>
    
    <br>
    <div class="box profile">
    	<p class="content-inner-title">전달 메시지</p>
    	<p class="content-document content_p">${recruit_apply.apply_content}</p>
    </div>
    <br>
    <div class="btndiv">
    	<c:choose>
	    	<c:when test="${recruit_post.post_status eq '마감'}">
	    		<a href="recruitApplyHire?apply_id=${recruit_apply.apply_id}" title="Button push blue/green" class="button btnPush btnBlueGreen">채용변경</a>
	    	</c:when>
	    	<c:otherwise>
	    		<a href="recruitApplyHire?apply_id=${recruit_apply.apply_id}" title="Button push blue/green" class="button btnPush btnBlueGreen">채용</a>
	    	</c:otherwise>
    	</c:choose>
    </div>
    </div>
</body>
</html>