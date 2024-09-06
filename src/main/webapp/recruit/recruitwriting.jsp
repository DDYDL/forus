<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>recruit writing</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css"> <!-- 캘린더 라이브러리 -->
<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
<script src="https://cdn.jsdelivr.net/npm/flatpickr/dist/l10n/ko.js"></script>
<link href="./css/recruit/recruitwriting.css?after" rel="stylesheet" type="text/css">
<link href="./css/recruit/calendar.css?after" rel="stylesheet" type="text/css">
<style>
	p { font-size: 25px; font-weight: bold; }
	#ss { font-size: 15px; }
</style>
</head>
<body>
	<%@ include file="../header.jsp" %>
	
	<div class="content-list">
		<form action="recruitWriting" method="post">
    		<p class="content-title">구인 등록</p>
			<br>
			<p class="content-inner-title">해당하는 동물을 선택해주세요</p>
    		<!-- 동물 프로필 가져오기 -->
    		<div class="box"><table id="pet_profile" class="pet_profile"></table></div>
			<br>
			<p class="content-inner-title">조건 설정</p>
		    <div class="box">
		    	<table class="setting">
		    		<tr>
			    		<td class="setting_td1"><label for="paytype">급여형태</label></td>
			    		<td class="setting_td2"><select name="post_form" id="paytype">
			            	<option value="시급">시급</option>
			            	<option value="일급">일급</option>
			        	</select></td>
			    	</tr>
			    	<tr>
			    		<td class="setting_td1"><label for="pay">급여</label></td>
			    		<td class="setting_td2"><input type="text" name="post_pay" id="pay">&nbsp;원</td>
			    	</tr>
			    	<tr>
			    		<td class="setting_td1"><label for="time">시간</label></td>
			    		<td class="setting_td2"><input type="time" name="post_start_time" id="time">&nbsp;~&nbsp;</td>
			    		<td class="setting_td3"><input type="time" name="post_end_time" id="time"></td>
			    	</tr>
			    	<tr><!-- 캘린더 로드 -->
			    		<td class="setting_td1"><label class="form-label" for="dayOfTheWeek">날짜</label></td>
			    		<td class="setting_td2" colspan="2"><div class="col-6">
			    			<input class="form-control" id="dayOfTheWeek" name="post_date" type="text" value="">
						</div></td>
			    	</tr>
		    	</table>
		    </div>
			<br>
		    <p class="content-inner-title">위치</p>
		    <!-- 검색바 -->
		    <div class="box">
		    <nav class="navbar navbar-expand-sm">
		        <div class="container-fluid">
		            <div class="collapse navbar-collapse" id="mynavbar">
		                <input class="form-control me-2" type="text" name="post_address" placeholder="지역명 검색">
		                <button class="btn" type="button"><img src="./img/search.png" style="width:20px;height:20px"></button>
		            </div>
		        </div>
		    </nav>
		    </div>
			<br>
		    <p class="content-inner-title">제목</p>
		    <div>
		        <input type="text" class="title" name="post_title" placeholder=" 제목을 입력하세요">
		    </div>
			<br>
		    <p class="content-inner-title">내용</p>
		    <div>
		        <textarea name="post_content" class="textarea" cols="50" rows="10" placeholder=" 내용을 입력하세요"></textarea>
		    </div>
		    <br>
		    <div class="btndiv"><input type="submit" class="button btnPush btnBlueGreen" value="등록"/></div>
		    <br><br>
	    </form>
	</div>
	    
	<script>
		flatpickr("#dayOfTheWeek", {
			"locale": "ko", // 한국어 설정
			"enableTime": false, // 시간 선택 비활성화
			"dateFormat": "Y-m-d (D)", // 요일 형식 설정 (요일만 출력) - 달력을 선택하면 요일로
			"inline": true, // 페이지 로드 시 자동으로 표시
			"mode" : "multiple",
		});
			
			
		// 로그인한 user의 pet 리스트 가져오기
		$.ajax({
    		url:'recruitWritingPetList',
    		type:'get',
    		async:true,
    		success:function(result) {
    			console.log(result);
    			var res = JSON.parse(result);
    			res.petList.forEach(function(pet) {
    				$('#pet_profile').append(`<tr><td class="pet_td1"><img src="\${pet.pet_picture}"></td><td class="pet_td2">\${pet.pet_name}</td><td class="pet_td3"><input type="radio" name="pet_name" value="\${pet.pet_name}"></td></tr>`);
    			})
    		}
    	})
	</script>
</body>
</html>