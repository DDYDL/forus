<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script
	src="https://cdn.rawgit.com/harvesthq/chosen/gh-pages/chosen.jquery.min.js"></script>
<link
	href="https://cdn.rawgit.com/harvesthq/chosen/gh-pages/chosen.min.css"
	rel="stylesheet" />
<link rel="stylesheet" href="css/hmy/containerbox.css" />
<link rel="stylesheet" href="css/hmy/input.css" />
<link rel="stylesheet" href="css/hmy/font.css" />
<link rel="stylesheet" href="css/hmy/time.css" />
<link rel="stylesheet" href="css/hmy/button.css" />
<!-- <script>
	$(function(e) {
		e.preventDefault();
	});
	document.addEventListener('DOMContentLoaded', function() {
		// form 내 Enter 키 submit 방지
		document.getElementById('join').addEventListener('keydown',
				function(e) {
					if (e.key === 'Enter') {
						e.preventDefault(); // 기본 동작(폼 제출) 방지
					}
				});
	});
	function readURL(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				document.getElementById('preview').src = e.target.result;
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
</script> -->

</head>


<body>
	<%@ include file="../mainhmypage.jsp"%>

	<div class="mypage-content">

		<div class="bodybox">

			<div class="containerbox">

				<div>
					<h3>내 병원 수정하기</h3>
				</div>

				<h4>기본 정보</h4>

				<table class="container">
					<tbody>
						<tr>
							<td><label for="name">대표자 이름</label></td>
							<td class="tdinput"><span>${hospital.h_exponent_name  }</span></td>
							<td></td>
						</tr>

						<tr>
							<td><label for="name">사업자 등록번호</label></td>
							<td class="tdinput"><span>${hospital.h_num }</span></td>
						</tr>
						<tr>
							<td><label for="name">병원 이름</label></td>
							<td class="tdinput"><span>${hospital.h_name }</span></td>
						</tr>
						<tr>
							<td><label for="name">병원장 이름</label></td>
							<td class="tdinput"><span>${hospital.h_manager_name }</span></td>
						</tr>
						<tr>
							<td><label for="name">병원 연락처</label></td>
							<td class="tdinput"><span>${hospital.h_phone }</span></td>
						</tr>

						<tr>
							<td><label for="name">병원 주소</label></td>
							<td><span>${hospital.h_address}</span></td>

						</tr>
						<tr>
							<td class="time"><label for="name">점심 시간</label></td>
							<td class="tdinput time">
							
							시작<input type="time" id="h_lunch_time_start" name="h_lunch_time_start"
								value="${hospital.h_lunch_time_start }"> &nbsp; &nbsp;
								
								종료 <input type="time" id="h_lunch_time_end" name="h_lunch_time_end"
								value="${hospital.h_lunch_time_end }"></td>
						</tr>
						<tr>
							<td><label for="name">진료 가능 동물 목록</label></td>
							<td class="tdinput"><span>${hospital.h_animal_type }</span>
						</tr>

					</tbody>
				</table>


				<h4>병원 대표 사진 등록</h4>
				<table class="container">
					<tbody>
						<tr>
							<td><label for="name">대표 사진 등록</label></td>
							<td class=" tdinput filebox"><input class="upload-name"
								value="첨부파일"> <label for="file">파일찾기</label> <input
								type="file" name="file" id="file" accept="image/*"
								onchange="readURL(this);" style="display: none" name="h_picture"></td>

						</tr>
					</tbody>
				</table>


				<h4>추가 사항</h4>
				<table class="container">
					<tbody>
						<tr>
							<td><label for="name">상세 설명</label></td>
							<td><span>${hospital.h_memo }</span></td>
						</tr>
						<tr>
							<td><label for="name">찾아오는 길</label></td>
							<td><span>${hospital.h_memo_road }</span></td>
						</tr>

						<tr>
							<td><h3>편의사항</h3></td>
						</tr>
						<tr>
							<td><label for="name"> SNS / Blog </label></td>
							<td class="tdinput"><span>${hospital.h_sns }</span></td>

						</tr>

						<tr>
							<td><label for="name"> 결제수단 </label></td>
							<td class="tdinput"><input type="checkbox" name="h_pay"
								value="1" disabled checked> 현금 <input type="checkbox" name="h_pay"
								value="2" disabled checked> 카드 <input type="checkbox" name="h_pay"
								value="3" disabled> 제로페이</td>
						</tr>

						<tr>
							<td><label for="name"> 주차 가능 여부 </label></td>
							<td class="tdinput"><input type="radio" name="h_parking"
								value="T"> 가능 <input type="radio" name="h_parking"
								value="F"> 불가능</td>
						</tr>

					</tbody>
				</table>

				<br> <br> <br>
				<div class="row">
					<div class="find-btn">
						<button type="button" title="Button push blue/green"
							class=" btn btnPush btnBlueGreen find-btn1"
							onclick="location.href='/forus/hmyHospitalModify'">수정</button>

					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>

