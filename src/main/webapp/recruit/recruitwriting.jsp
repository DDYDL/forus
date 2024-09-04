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
<link href="../css/recruit/recruitwriting.css?after" rel="stylesheet" type="text/css">
<link href="../css/recruit/calendar.css?after" rel="stylesheet" type="text/css">
<style>
	p { font-size: 25px; font-weight: bold; }
	#ss { font-size: 15px; }
</style>
</head>
<body>
	<%@ include file="../header.jsp" %>
	
	<div class="content-list">
    	<p class="content-title">구인 등록</p>
		<br>
    	<h5>해당하는 동물을 선택해주세요</h5> 
    	<!-- 동물 프로필 가져오기 -->
    	<div class="box">
    		<table class="pet_profile">
	    		<tr>
	    			<td class="pet_td1"><img src="../img/dog1.png"></td>
	    			<td class="pet_td2">또치</td>
	    			<td class="pet_td3"><input type="radio" name="select"></td>
	    		</tr>
	    		<tr>
	    			<td class="pet_td1"><img src="../img/dog2.png"></td>
	    			<td class="pet_td2">두치</td>
	    			<td class="pet_td3"><input type="radio" name="select"></td>
	    		</tr>
	    		<tr>
	    			<td class="pet_td1"><img src="../img/dog3.png"></td>
	    			<td class="pet_td2">한치</td>
	    			<td class="pet_td3"><input type="radio" name="select"></td>
	    		</tr>
    		</table>
    	</div>
		<br>
	    <h5>조건 설정</h5>
	    <div class="box">
	    	<table class="setting">
	    		<tr>
		    		<td class="setting_td1"><label for="paytype">급여형태</label></td>
		    		<td class="setting_td2"><select name="" id="paytype">
		            	<option value="hour">시급</option>
		            	<option value="day">일급</option>
		        	</select></td>
		    	</tr>
		    	<tr>
		    		<td class="setting_td1"><label for="pay">급여</label></td>
		    		<td class="setting_td2"><input type="text" id="pay">&nbsp;원</td>
		    	</tr>
		    	<tr>
		    		<td class="setting_td1"><label for="time">시간</label></td>
		    		<td class="setting_td2"><input type="text" id="time"></td>
		    	</tr>
		    	<tr><!-- 캘린더 로드 -->
		    		<td class="setting_td1"><label class="form-label" for="dayOfTheWeek">날짜</label></td>
		    		<td class="setting_td2"><div class="col-6">
		    			<input class="form-control" id="dayOfTheWeek" type="text" value="">
					</div></td>
		    	</tr>
	    	</table>
	    </div>
		<br>
	    <h5>위치</h5>
	    <!-- 검색바 -->
	    <div class="box">
	    <nav class="navbar navbar-expand-sm">
	        <div class="container-fluid">
	            <div class="collapse navbar-collapse" id="mynavbar">
	                <input class="form-control me-2" type="text" placeholder="지역명 검색">
	                <button class="btn" type="button"><img src="../img/image.png" style="width:20px;height:20px"></button>
	            </div>
	        </div>
	    </nav>
	    </div>
		<br>
	    <h5>제목</h5>
	    <div>
	        <input type="text" class="title" placeholder=" 제목을 입력하세요">
	    </div>
		<br>
	    <h5>내용</h5>
	    <div>
	        <textarea name="" id="" class="textarea" cols="50" rows="10" placeholder=" 내용을 입력하세요"></textarea>
	    </div>
	    <br>
	    <div class="btndiv"><a href="../recruit/recruitlist.jsp" title="Button push blue/green" class="button btnPush btnBlueGreen">등록</a></div>
	    <br><br>
	    </div>
	    
	    <script>
			flatpickr("#dayOfTheWeek", {
				"locale": "ko", // 한국어 설정
				"enableTime": false, // 시간 선택 비활성화
				"dateFormat": "Y-m-d (D)", // 요일 형식 설정 (요일만 출력) - 달력을 선택하면 요일로
				"inline": true, // 페이지 로드 시 자동으로 표시
				"mode" : "multiple",
			});
		</script>
</body>
</html>