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
<script>
	function readURL(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				document.getElementById('preview').src = e.target.result;
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
</script>

</head>


<body>
	<%@ include file="../mainhmypage.jsp"%>

	<div class="mypage-content">

		<div class="bodybox">
			<form action="./hmyHospitalModify" method="post" enctype="multipart/form-data">
				<input type="hidden" name="h_id" value="${hospital.h_id }">
				<div class="containerbox">

					<div>
						<h3>내 병원 수정하기</h3>
					</div>

					<h4>기본 정보</h4>

					<table class="container">

						<tbody>
							<tr>
								<td><label for="name">대표자 이름</label></td>
								<td class="tdinput"><input type="text" id="input"
									name="h_exponent_name" value="${hospital.h_exponent_name  }"
									required></td>
								<td></td>
							</tr>

							<tr>
								<td><label for="name">사업자 등록번호</label></td>
								<td class="tdinput"><input type="text" id="input"
									name="h_num" value="${hospital.h_num }" required></td>
							</tr>
							<tr>
								<td><label for="name">병원 이름</label></td>
								<td class="tdinput"><input type="text" id="input"
									name="h_name" value="${hospital.h_name }" required></td>
							</tr>
							<tr>
								<td><label for="name">병원장 이름</label></td>
								<td class="tdinput"><input type="text" id="input"
									name="h_manager_name" value="${hospital.h_manager_name }"
									required></td>
							</tr>
							<tr>
								<td><label for="name">병원 연락처</label></td>
								<td class="tdinput"><input type="tel" id="tel"
									name="h_phone" value="${hospital.h_phone }" required></td>
							</tr>

							<tr>
								<td><label for="name">병원 주소</label></td>
								<td><input class="tdinput" type="address"
									id="sample6_postcode" placeholder="우편번호" name="h_address">

									<button class="btn" onclick="sample6_execDaumPostcode()">주소찾기</button>
								</td>

							</tr>

							<script
								src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js">
								
							</script>
							<script>
								function sample6_execDaumPostcode() {
									new daum.Postcode(
											{
												oncomplete : function(data) {

													var addr = ''; // 주소 변수
													var extraAddr = ''; // 참고항목 변수

													if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
														addr = data.roadAddress;
													} else { // 사용자가 지번 주소를 선택했을 경우(J)
														addr = data.jibunAddress;
													}

													if (data.userSelectedType === 'R') {
														if (data.bname !== ''
																&& /[동|로|가]$/g
																		.test(data.bname)) {
															extraAddr += data.bname;
														}
														if (data.buildingName !== ''
																&& data.apartment === 'Y') {
															extraAddr += (extraAddr !== '' ? ', '
																	+ data.buildingName
																	: data.buildingName);
														}
														if (extraAddr !== '') {
															extraAddr = ' ('
																	+ extraAddr
																	+ ')';
														}
														document
																.getElementById("sample6_detailAddress").value = extraAddr;
													} else {
														document
																.getElementById("sample6_detailAddress").value = '';
													}

													// 우편번호와 주소 정보를 해당 필드에 넣는다.
													document
															.getElementById('sample6_postcode').value = data.zonecode;
													document
															.getElementById("sample6_address").value = addr;
													// 커서를 상세주소 필드로 이동한다.
													document
															.getElementById(
																	"sample6_detailAddress")
															.focus();
												}
											}).open();
								}
							</script>

							<tr>
								<td></td>
								<td><input class="tdinput" type="address"
									id="sample6_address" placeholder="주소" name="h_address">
									&nbsp; &nbsp;<input class="tdinput" type="address"
									id="sample6_detailAddress" placeholder="상세주소"></td>
							</tr>




							<tr>
								<td class="time"><label for="name">점심 시간</label></td>
								<td class="tdinput time">시작<input type="time" id="input"
									name="h_lunch_time_start"
									value="${hospital.h_lunch_time_start }"> &nbsp; &nbsp;

									종료 <input type="time" id="input" name="h_lunch_time_end"
									value="${hospital.h_lunch_time_end }"></td>
							</tr>
							<tr>
								<td><label for="name"> 진료가능 목록 </label></td>
								<td class="tdinput"><input type="checkbox" name="h_pay"
									value="강아지"> 강아지 <input type="checkbox" name="h_pay"
									value="고양이"> 고양이 <input type="checkbox" name="h_pay"
									value="파충류"> 파충류
									<input type="checkbox" name="h_pay"
									value="파충류"> 포유류
									<input type="checkbox" name="h_pay"
									value="파충류"> 조류
									<input type="checkbox" name="h_pay"
									value="파충류"> 곤충류</td>
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
									onchange="readURL(this);" style="display: none"
									name="h_picture"></td>

							</tr>
						</tbody>
					</table>


					<h4>추가 사항</h4>
					<table class="container">
						<tbody>
							<tr>
								<td><label for="name">상세 설명</label></td>
								<td><textarea class="tdinput" id="input_box" rows="4"
										cols="30">${hospital.h_memo }</textarea></td>
							</tr>
							<tr>
								<td><label for="name">찾아오는 길</label></td>
								<td><textarea class="tdinput" id="input_box" rows="4"
										cols="30">${hospital.h_memo_road }</textarea></td>
							</tr>

							<tr>
								<td><h3>편의사항</h3></td>
							</tr>
							<tr>
								<td><label for="name"> SNS / Blog </label></td>
								<td class="tdinput"><input type="text" id="input"
									name="h_sns" value="${hospital.h_sns }" required></td>

							</tr>

							<tr>
								<td><label for="name"> 결제수단 </label></td>
								<td class="tdinput"><input type="checkbox" name="h_pay"
								value="현금"checked> 현금 <input type="checkbox" name="h_pay"
								value="카드" checked> 카드 <input type="checkbox" name="h_pay"
								value="제로페이"> 제로페이</td>
							</tr>

							<tr>
								<td><label for="name"> 주차 가능 여부 </label></td>
								<td class="tdinput"><input type="radio" name="h_parking"
								value="가능"> 가능 <input type="radio" name="h_parking"
								value="불가능"> 불가능</td>
							</tr>

						</tbody>
					</table>


					<br> <br> <br>
					<div class="row">
						<div class="find-btn">
							<input type="submit" title="Button push blue/green"
								class=" btn btnPush btnBlueGreen find-btn1" value="수정 완료">

						</div>
					</div>
				</div>
			</form>
		</div>
	</div>

</body>
</html>

