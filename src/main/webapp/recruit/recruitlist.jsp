<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HelpUs</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<link href="../css/recruit/recruitlist.css" rel="stylesheet" type="text/css">
</head>
<body>
	<%@ include file="../header.jsp" %>
	
	<div class="content-list">
    	<p class="content-title">Help Us</p>

    <!-- 검색 기능 -->
    <div class="container mt-3">
        <div class="dropdown">
            <button type="button" class="btn btn-primary dropdown-toggle" data-bs-toggle="collapse" data-bs-target="#demo1">지역</button>
            <button type="button" class="btn btn-primary dropdown-toggle" data-bs-toggle="collapse" data-bs-target="#demo2">동물</button>
            <div class="searchbox">
            	<input type="text" placeholder="검색 조건을 선택하세요"/>
                <div id="demo1" class="collapse">
                    <table class="table">
                        <thead><tr><th>시·도</th><th>시·구·군</th><th>동·읍·면</th></tr></thead>
                        <tbody>
                        	<tr>
                        		<td class="tabletd">
	                        		<select name="" id="" size="5">
			                        <option value="seoul" data-bs-toggle="collapse" data-bs-target="#seoul">서울</option>
			                        <option value="" data-bs-toggle="collapse" data-bs-target="#seoul">경기</option>
			                        <option value="" data-bs-toggle="collapse" data-bs-target="#seoul">인천</option>
			                        <option value="" data-bs-toggle="collapse" data-bs-target="#seoul">강원</option>
			                        <option value="" data-bs-toggle="collapse" data-bs-target="#seoul">대전</option>
			                        <option value="" data-bs-toggle="collapse" data-bs-target="#seoul">부산</option>
			                    	</select>
                        		</td>
                        		<td class="tabletd">
	                        		<div id="seoul" class="collapse">
		                        	<select name="" id="" size="5">
		                            <option value="all_seoul" data-bs-toggle="collapse" data-bs-target="#seoul">서울전체</option>
		                            <option value="kangnam" data-bs-toggle="collapse" data-bs-target="#kangnam">강남구</option>
		                            <option value="" data-bs-toggle="collapse" data-bs-target="#seoul">강동구</option>
		                            <option value="" data-bs-toggle="collapse" data-bs-target="#seoul">강북구</option>
		                            <option value="" data-bs-toggle="collapse" data-bs-target="#seoul">강서구</option>
		                        	</select>
		                    		</div>
                        		</td>
                        		<td class="tabletd">
	                        		<div id="kangnam" class="collapse">
		                        	<select name="" id="" size="5">
		                            <option class="area" value="all_seoul" data-bs-toggle="collapse" data-bs-target="#seoul">강남구 전체</option>
		                            <option class="area" value="" data-bs-toggle="collapse" data-bs-target="#seoul">개포동</option>
		                            <option class="area" value="" data-bs-toggle="collapse" data-bs-target="#seoul">논현1동</option>
		                        	</select>
		                    		</div>
                        		</td>
                        	</tr>
                        </tbody>
                    </table>
                    <input type="button" value="초기화">
                </div>
                <div id="demo2" class="collapse">
                    <table class="table">
                        <thead><tr><th>종</th><th>동물종</th><th>품종</th></tr></thead>
                        <tbody>
                        	<tr>
                        		<td class="tabletd">
                        			<select name="" id="" size="5">
				                    <option value="mammalia" data-bs-toggle="collapse" data-bs-target="#mammalia">포유류</option>
				                    <option value="">파충류</option>
				                    <option value="">조류</option>
				                    <option value="">어류</option>
				                    <option value="">양서류</option>
	                				</select>
                        		</td>
                        		<td class="tabletd">
                        			<div id="mammalia" class="collapse">
	                    			<select name="" id="" size="5">
			                    	<option class="animal" value="dog" data-bs-toggle="collapse" data-bs-target="#dog">강아지</option>
			                        <option class="animal" value="">고양이</option>
			                        <option class="animal" value="">고라니</option>
			                        <option class="animal" value="">곰</option>
			                        <option class="animal" value="">오리너구리</option>
			                        <option class="animal" value="">너구리</option>
	                    			</select>
	                				</div>
                        		</td>
                        	</tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    
    <!-- 글 목록 -->
    <br>
    <div class="list-header">
    	<div class="list-count"><p>총 2건</p></div>
    	<div class="list-btn btndiv"><a href="../recruit/recruitwriting.jsp" class="minibutton minibtnFade minibtnBlueGreen">구인등록</a></div>
    </div>
    <table class="table table-hover">
        <thead>
            <tr>
                <th>지역</th><th>제목</th><th>급여(원)</th><th>근무시간</th><th>등록일</th>
            </tr>
        </thead>
        <tbody>
            <tr onclick="location.href='recruitdetailpage.jsp'">
                <td>금천구 가산동</td>
                <td>강아지 산책</td>
                <td>30000</td>
                <td>7:00~10:00</td>
                <td>2024.08.16</td>
            </tr>
            <tr>
                <td>금천구 독산동</td>
                <td>온도조절</td>
                <td>15000</td>
                <td>18:00~22:00</td>
                <td>2024.08.16</td>
            </tr>
        </tbody>
    </table>
    </div>
    <div><button id="moreBtn">더보기</button></div>
    
    <script>
    	var page=0;
    	var maxPage=0;
    	
    	function requestData() {
    		var areas = document.querySelectorAll('option[class="area"]').innerHTML(); // 선택한 지역 배열 들어감
    		var species = document.querySelectorAll('option[class="animal"]').innerHTML(); // 선택한 동물 배열 들어감
    		var param = {areas:areas, species:species, page:page+1}
    		
    		$.ajax({
    			url:'recruitBypage',
    			type:'post',
    			async:true,
    			data:{param:JSON.stringify(param)}, // 객체를 JSON 문자열로 변환
    			success:function(result) {
    				concole.log(result);
    				var res = JSON.parse(result); // JSON 문자열을 객체로 변환
    				res.locs.forEach(function(loc)) {
    					
    				}
    			}
    		})
    	}
    </script>
</body>
</html>