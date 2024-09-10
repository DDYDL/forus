<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>recruit detail page</title>
<!-- 카카오 지도 API -->
<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=2a46ab533ae3e523e60dc7d0057f867b&libraries=services">
<!-- jquery 라이브러리 -->
<script src="http://code.jquery.com//jquery-latest.min.js"></script>
<!-- 모달 라이브러리 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />
<!-- css 파일 -->
<link href="./css/recruit/recruitapplydetailpage.css" rel="stylesheet" type="text/css">
<link href="./css/recruit/modal.css" rel="stylesheet" type="text/css">
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
    <div class="box"><table id="post_pet_profile"></table></div>
    
    <script>
    	// post user의 pet 리스트 가져오기
	    $.ajax({
			url:'recruitWritingPetList',
			type:'post',
			async:true,
			data:{post_user:${post_user.id}},
			success:function(result) {
				console.log(result);
				var res = JSON.parse(result);
				res.post_petList.forEach(function(pet) {
					$('#post_pet_profile').append(`<tr><td class="pet_td1"><img src="img?file=\${pet.pet_picture}"></td><td class="pet_td2">\${pet.pet_name}</td><td class="pet_td3">(\${pet.pet_age}살,\${pet.pet_gender})</td><td class="pet_td3">\${pet.pet_species}</td><td class="pet_td3">\${pet.pet_breed}</td><td class="pet_td3">\${pet.pet_memo}</td></tr>`);
				})
			}
		})
    </script>
    
    <p class="content-inner-title">조건</p>
    <div class="box">
    	<table>
    		<tr>
    			<td><label for="pay">급여</label></td>
    			<td>${post.post_form}</td>
    			<td>${post.post_pay}&nbsp;원</td>
    		</tr>
    		<tr>
    			<td><label for="time">시간</label></td>
    			<td id="time_td"></td>
    		</tr>
    		<tr>
    			<td><label for="week">요일</label></td>
    			<td>${post.post_date}</td>
    		</tr>
    	</table>
    </div>
    
    <script>
    	var start_time = `${post.post_start_time}`.slice(0,5);
		var end_time = `${post.post_end_time}`.slice(0,5);
		if(start_time !== null && end_time !== null){
			document.getElementById('time_td').innerText = start_time+" ~ "+end_time;
		}
    </script>

    <p class="content-inner-title">지역</p>
    <div class="box">
    	<!-- 지도 API -->
    	<table style="width:100%">
    		<tr><td colspan="3"><div id="map" style="width:100%; height:200px;"></div></td></tr>
    		<tr>
    			<td><label for="location">근무위치</label></td>
    			<td id="post_address">${post.post_address}</td>
    		</tr>
    		<tr>
    			<td><label for="subway">인근지하철</label></td>
    			<td>독산역 1호선</td>
    			<td>도보 15분</td>
    		</tr>
    	</table>
	</div>
	
	<!-- 카카오 지도 API -->
	<script>
		// 지도를 담을 컨테이너
		var container = document.getElementById('map');
		
		// 지도 옵션 설정
		var option = {
			center: new kakao.maps.LatLng(37.566826, 126.9786567), // recruit_post에 저장된 위치를 지도의 중앙으로 설정
			level: 3 // 지도 확대 레벨
		}
		
		// 지도 생성
		var map = new kakao.maps.Map(container, option);
		
		// recruit_post에 저장된 주소 가져오기
		var address = document.getElementById('post_address').innerHTML;
		console.log(address);
		// 앞의 주소만 가져오기
		const results = address.split("(");
		console.log(results[0]);
		
		// 주소-좌표 변환 객체 생성
		var geocoder = new kakao.maps.services.Geocoder();
		geocoder.addressSearch(results[0],function(result, status) {
			if (status === kakao.maps.services.Status.OK) {
				// 좌표 받아오기
				 var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
				// 지도 범위 재설정하기
				 var bound = new kakao.maps.LatLngBounds();
				 bound.extend(new kakao.maps.LatLng(result[0].y, result[0].x));
				 map.setBounds(bound);
				// 마커 그리기
				 displayMaker(coords);
			}
		});
		
		// 해당 위치에 마커 표시하기
		function displayMaker(coords) {
			var marker = new kakao.maps.Marker({
				map: map,
				position: coords
			});
		}
	</script>
	
    <p class="content-inner-title">상세요강</p>
    <div class="box">
        <p>${post.post_content}</p>
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
    	/* $("#custom-close").modal({
    	  closeClass: 'icon-remove',
    	  closeText: 'x'
    	}); */
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
    
    <br><br>
    <!-- 현재 로그인한 user id와 recruit_post의 user id가 같으면 지원하기 버튼을 수정하기 버튼으로 변경 -->
    <div class="btndiv">
    <c:if test="${user ne null}">
    	<c:choose>
    		<c:when test="${modify eq true}">
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