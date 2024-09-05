<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HelpUs</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="http://code.jquery.com//jquery-latest.min.js"></script>
<link href="./css/recruit/recruitlist.css" rel="stylesheet" type="text/css">
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
	                        		<select size="5">
				                        <option value="seoul" data-bs-toggle="collapse" data-bs-target="#seoul">서울</option>
				                        <option value="gyeonggi" data-bs-toggle="collapse" data-bs-target="#gyeonggi">경기</option>
				                        <option value="incheon" data-bs-toggle="collapse" data-bs-target="#incheon">인천</option>
				                        <option value="gangwon" data-bs-toggle="collapse" data-bs-target="#gangwon">강원</option>
				                        <option value="daejeon" data-bs-toggle="collapse" data-bs-target="#daejeon">대전</option>
				                        <option value="busan" data-bs-toggle="collapse" data-bs-target="#busan">부산</option>
			                    	</select>
                        		</td>
                        		<td class="tabletd">
	                        		<div id="seoul" class="collapse">
		                        	<select size="5">
			                            <option value="all_seoul" data-bs-toggle="collapse" data-bs-target="#all_seoul">서울전체</option>
			                            <option value="kangnam" data-bs-toggle="collapse" data-bs-target="#kangnam">강남구</option>
			                            <option value="kangdong" data-bs-toggle="collapse" data-bs-target="#kangdong">강동구</option>
			                            <option value="kangbuk" data-bs-toggle="collapse" data-bs-target="#kangbuk">강북구</option>
			                            <option value="kangsae" data-bs-toggle="collapse" data-bs-target="#kangsae">강서구</option>
		                        	</select>
		                    		</div>
                        		</td>
                        		<td class="tabletd">
	                        		<div id="kangnam" class="collapse">
		                        	<select id="areas" onclick="selectSearch_area()" size="5">
			                            <option class="areas" value="강남구 전체">강남구 전체</option>
			                            <option class="areas" value="개포동">개포동</option>
			                            <option class="areas" value="논현1동">논현1동</option>
			                            <option class="areas" value="독산동">독산동</option>
		                        	</select>
		                    		</div>
                        		</td>
                        	</tr>
                        </tbody>
                    </table>
                </div>
                <div id="demo2" class="collapse">
                    <table class="table">
                        <thead><tr><th>종</th><th>동물종</th><th>품종</th></tr></thead>
                        <tbody>
                        	<tr>
                        		<td class="tabletd">
                        			<select size="5">
					                    <option value="mammalia" data-bs-toggle="collapse" data-bs-target="#mammalia">포유류</option>
					                    <option value="reptile" data-bs-toggle="collapse" data-bs-target="#reptile">파충류</option>
					                    <option value="bird" data-bs-toggle="collapse" data-bs-target="#bird">조류</option>
					                    <option value="pisces" data-bs-toggle="collapse" data-bs-target="#pisces">어류</option>
					                    <option value="amphibian" data-bs-toggle="collapse" data-bs-target="#amphibian">양서류</option>
	                				</select>
                        		</td>
                        		<td class="tabletd">
                        			<div id="mammalia" class="collapse">
	                    			<select id="species" onclick="selectSearch_specie()" size="5">
				                    	<option class="species" value="강아지">강아지</option>
				                        <option class="species" value="고양이">고양이</option>
				                        <option class="species" value="햄스터">햄스터</option>
				                        <option class="species" value="토끼">토끼</option>
				                        <option class="species" value="다람쥐">다람쥐</option>
	                    			</select>
	                				</div>
                        		</td>
                        	</tr>
                        </tbody>
                    </table>
                </div>
                
                <div id="search_btn"></div>
                <input type="button" onclick="delete_selectall()" value="초기화">
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
        <tbody id="recruitlist_body"></tbody>
    </table>
    <div><button id="moreBtn">더보기</button></div>
    </div>
    
    <script>
    	var page=0;
    	var maxPage=0;
    	var areas=new Array();
    	var species=new Array();
    	
    	// recruit_post 리스트를 가져와서 페이지 수에 맞게 보여주는 함수
    	function requestData() {
    		console.log(areas);
    		console.log(species);
    		var param = {areas:areas, species:species, page:page+1}
    		
    		$.ajax({
    			url:'recruitBypage',
    			type:'post',
    			async:true,
    			data:{param:JSON.stringify(param)}, // 객체를 JSON 문자열로 변환
    			success:function(result) { // recruitBypage 서블릿에서 넘어온 recruit_post 리스트가 result에 담겨옴(JSON 문자열 형식)
    				console.log(result);
    				var res = JSON.parse(result); // JSON 문자열을 객체로 변환
    				res.recruit_postList.forEach(function(recruit_post) {
    					console.log(recruit_post);
    					// 테이블 안에 recruit_post 한 행을 추가
    					$('#recruitlist_body').append(`<tr onclick="location.href='./recruitDetailpage?post_id=\${recruit_post.post_id}'"><td>\${recruit_post.post_address}</td><td>\${recruit_post.post_title}</td><td>\${recruit_post.post_pay}</td><td>\${recruit_post.post_start_time}~\${recruit_post.post_end_time}</td><td>\${recruit_post.post_time}</td></tr>`);
    				})
    				maxPage = res.maxPage; // 넘어온 페이지로 설정
    				page = res.page;
    				
    				// 현재 페이지수가 최대 페이지수와 같거나 크면 더보기 버튼 안 보이게 함
    				if(page>=maxPage) {
    					document.getElementById("moreBtn").style.visibility = 'hidden';
    				} else {
    					document.getElementById("moreBtn").style.visibility = 'visible';
    				}
    			}
    		})
    	}
    	
    	// 더보기 버튼 눌리면 함수 한 번 실행
    	document.getElementById("moreBtn").onclick = function() {
    		requestData();
    	}
    	
    	
    	// 검색 조건 선택 시 실행되는 함수, 검색바에서 마지막 select 누르면 버튼 생김
    	function selectSearch_area() {
    		var area = document.getElementById("areas").value; // 선택한 지역 이름
    		areas.push(area);
    		
    		$('#search_btn').append(`<button class="make_btn" onclick="deleteBtn_area(this)">\${area}</button>`); // 버튼 생성
    		btn_click();
    	}
    	function selectSearch_specie() {
    		var specie = document.getElementById("species").value; // 선택한 동물 이름
    		species.push(specie);
    		
    		$('#search_btn').append(`<button class="make_btn" onclick="deleteBtn_species(this)">\${specie}</button>`);
    		btn_click();
    	}
    	function deleteBtn_area(btn) { // 버튼 클릭시 삭제
    		for(let i=0; i<areas.length; i++) {
    			if(areas[i]===btn.innerHTML) {
    				areas.splice(i, 1); // 해당하는 버튼 배열에서 삭제
    			}
    		}
    		btn.remove(); // 버튼 요소 삭제
    		btn_click();
    	}
    	function deleteBtn_species(btn) {
    		for(let i=0; i<species.length; i++) {
    			if(species[i]===btn.innerHTML) {
    				species.splice(i, 1); // 해당하는 버튼 배열에서 삭제
    			}
    		}
    		btn.remove();
    		btn_click();
    	}
    	// 모든 버튼 삭제
    	function delete_selectall() {
    		document.getElementById("search_btn").replaceChildren(); // 모든 버튼 삭제
			
    		areas=new Array(); // 배열 초기화
        	species=new Array();
        	btn_click();
    	}
    	
    	// 버튼이 눌렸을 때 실행되는 함수들
    	function btn_click() {
    		// 페이지가 첫 페이지면 기존의 리스트 삭제
    		document.getElementById("recruitlist_body").replaceChildren();
    		page=0; // 페이지 수 초기화
        	maxPage=0;
    		requestData(); // 리스트 다시 가져오기
    	}
    	
    	// 처음 한 번 실행
    	requestData();
    </script>
</body>
</html>