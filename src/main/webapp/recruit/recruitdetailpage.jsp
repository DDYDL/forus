<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>recruit detail page</title>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />
<link href="../css/recruit/recruitapplydetailpage.css" rel="stylesheet" type="text/css">
<link href="../css/recruit/modal.css" rel="stylesheet" type="text/css">
</head>
<body> 
	<%@ include file="../header.jsp" %>
	
	<div class="content">
		<p class="content-title">${post.post_title}</p>
	
	<!-- user 프로필 가져오기 -->
    <p class="content-inner-title">작성자</p>
    <div class="box">
    	<div class="profile">
    	<div><img src="${post_user.picture}"><p class="content-document">${post_user.name}</p></div>
    	<p class="content-document">${post_user.email}</p>
    	</div>
    </div>

	<!-- pet 프로필 가져오기 -->
    <p class="content-inner-title">이번에 맡길 동물은요</p>
    <div class="box"><table class="pet_profile"></table></div>
    
    <script>
	    $.ajax({
			url:'recruitWritingPetList',
			type:'get',
			async:true,
			success:function(result) {
				console.log(result);
				var res = JSON.parse(result);
				res.petList.forEach(function(pet) {
					$('#pet_profile').append(`<td><img src="\${pet.pet_picture}"></td><td>\${pet.pet_name}</td><td>(\${pet.pet_age}살, \${pet.pet_gender})</td><td>\${pet.pet_breed}</td><td>\${pet.pet_memo}</td>`);
				})
			}
		})
    </script>
    
    <p class="content-inner-title">조건</p>
    <div class="box">
    	<table>
    		<tr>
    			<td><label for="pay">급여</label></td>
    			<td>일급</td>
    			<td>30,000원</td>
    		</tr>
    		<tr>
    			<td><label for="time">시간</label></td>
    			<td>오전</td>
    			<td>9:00 ~ 12:00</td>
    		</tr>
    		<tr>
    			<td><label for="week">요일</label></td>
    			<td>월, 수, 금</td>
    		</tr>
    	</table>
    </div>

    <h5>지역</h5>
    <div class="box">
    	<!-- 지도 API -->
    	<table>
    		<tr><td colspan="3" width="0"><div style="width:100%"><img src="../img/map.png" width="100%"></div></td></tr>
    		<tr>
    			<td><label for="location">근무위치</label></td>
    			<td>서울시 금천구 독산동</td>
    		</tr>
    		<tr>
    			<td><label for="subway">인근지하철</label></td>
    			<td>독산역 1호선</td>
    			<td>도보 15분</td>
    		</tr>
    	</table>
	</div>
	
    <h5>상세요강</h5>
    <div class="box">
        <p>8월 한달동안 주 3회 강아지 산책해주실 분 구합니다.
			강아지가 활발해서 잘 달리시는 분 우대합니다~
			10살 노견이라 중간에 안아달라고 할 수도 있습니다!!</p>
    </div>
    
    <!-- 모달 -->
    <script>
	 	// Open modal in AJAX callback
	    $('#openmodal').click(function(event) {
	      event.preventDefault();
	      this.blur(); // Manually remove focus from clicked link.
	      $.get(this.href, function(html) {
	        $(html).appendTo('body').modal();
	      });
	    });
	 	// Close modal custom
    	$("#custom-close").modal({
    	  closeClass: 'icon-remove',
    	  closeText: 'x'
    	});
    </script>
    
    <div id="modal" class="modal">
    	<p class="content-title">지원하기</p>
    	<p class="content-inner-title">강아지 산책시켜주실 분!!</p>
		<form action="recruitapplyend.jsp" method="post">
			<p class="content-inner-title">제목</p>
    		<div>
        		<input type="text" class="title" placeholder=" 제목을 입력하세요">
    		</div>
			<p class="content-inner-title">전달 메시지</p>
		    <div>
		        <textarea name="" class="textarea" cols="63" rows="10" placeholder=" 전달할 메시지를 입력하세요"></textarea>
		    </div>
		    <div class="list-header">
		        <div><p class="content-inner-title">지원정보 확인</p></div>
		        <div class="btndiv"><a href="#" class="minibutton minibtnFade minibtnBlueGreen">회원정보수정</a></div>
		    </div>
			<table>
		    	<tr>
		    		<td class="left"><p class="content-document">${user.name}</p></td>
		    		<td class="right"><p class="content-document">(${user.gender}, ${user.birthday})</p></td>
		    	</tr>
		    	<tr>
		    		<td class="left"><p class="content-document">휴대폰</p></td>
		    		<td class="middle"><p class="content-document">${user.phone}</p></td>
		    		<td class="right"><p class="content-document">공개</p></td>
		    	</tr>
		    	<tr>
		    		<td class="left"><p class="content-document">이메일</p></td>
		    		<td class="middle"><p class="content-document">${user.email}</p></td>
		    		<td class="right"><p class="content-document">공개</p></td>
		    	</tr>
		    </table>
		    <div class="btndiv"><a href="#" rel="modal:close" class="button btnPush btnBlueGreen">지원</a></div>
    	</form>
    </div>
    
    <!-- 현재 로그인한 user id와 recruit_post의 user id가 같으면 지원하기 버튼을 수정하기 버튼으로 변경 -->
    <div class="btndiv">
    <c:if test="${apply ne null}">
    	<c:choose>
    		<c:when test="${apply eq true}">
    			<a href="#" id="modify" class="button btnPush btnBlueGreen">수정하기</a>
    		</c:when>
    		<c:otherwise>
    			<a href="#modal" id="openmodal" rel="modal:open" class="button btnPush btnBlueGreen">지원하기</a>
    		</c:otherwise>
    	</c:choose>
    </c:if>
    </div>
    </div>
</body>
</html>