<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신규 병원 등록</title>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>


<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=94ab9074f87bbed0edf5b0d9cb32cdbd&libraries=services"></script>

<!-- 캘린더 라이브러리 -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
<script src="https://cdn.jsdelivr.net/npm/flatpickr/dist/l10n/ko.js"></script>

<link rel="stylesheet" href="css/hmy/containerbox.css" />
<link rel="stylesheet" href="css/hmy/input.css" />
<link rel="stylesheet" href="css/hmy/font.css" />
<link rel="stylesheet" href="css/hmy/time.css" />
<link rel="stylesheet" href="css/hmy/button.css" />
<link rel="stylesheet" href="css/my/profile.css" />


<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js">

	</script>


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
	<%@ include file="../mainmypage.jsp"%>

	<div class="mypage-content">

		<form action="hmyNewHospital" method="post"
			enctype="multipart/form-data">


			<div>
				<h2 align="center">신규 병원 등록</h2>
			</div>

			<h3 style="text-align: left">기본 정보</h3>

			<table class="container">

				<tbody>
					<tr>
						<td><label for="name">대표자 이름</label></td>
						<td class="tdinput"><input type="text" id="input"
							name="h_exponent_name" required></td>
						<td></td>
					</tr>

					<tr>
						<td><label for="name">사업자 등록번호</label></td>
						<td class="tdinput"><input type="text" id="input"
							name="h_num" required></td>
					</tr>
					<tr>
						<td><label for="name">의사 면허증</label></td>
						<td class=" tdinput filebox"><input class="upload-name"
							value="첨부파일" placeholder="첨부  파일"> <label for="file">파일찾기</label>
							<input type="file" id="file" name="h_license"></td>

					</tr>
					<script>
								$("#file").on('change', function() {
									var fileName = $("#file").val();
									$(".upload-name").val(fileName);
								});
							</script>


					<tr>
						<td><label for="name">병원 이름</label></td>
						<td class="tdinput"><input type="text" id="input"
							name="h_name" required></td>
					</tr>
					<tr>
						<td><label for="name">병원장 이름</label></td>
						<td class="tdinput"><input type="text" id="input"
							name="h_manager_name" required></td>
					</tr>
					<tr>
						<td><label for="name">병원 연락처</label></td>
						<td class="tdinput"><input type="tel" id="tel" name="h_phone"
							required></td>
					</tr>

					<tr>
						<td>병원 주소</td>
						<td><input type="text" id="postcode" placeholder=" 우편번호"
							class="user-input join-input-short">&nbsp; <input
							onclick="daumPostcode()"
							class="minibutton minibtnFade minibtnBlueGreen" value="주소 검색">
					</tr>
					<tr>
						<td></td>
						<td><input type="text" id="h_address" placeholder=" 주소"
							class="user-input join-input" name="h_address" required></td>

					</tr>

					<tr>

						<td class="setting_td1"><label for="time">점심시간</label></td>
						<td class="setting_td2"><input type="time" class="time_start"
							id="timepicker" name="h_lunch_time_start"> ~ <input
							type="time" class="time_end" id="timepicker"
							name="h_lunch_time_end"></td>

					</tr>

				</tbody>
			</table>
			<h3 style="text-align: left">진료 가능한 동물 목록</h3>

			<table class="container">

				<tbody>
					<tr>
						<td>포유류</td>
						<td><input type="checkbox" name="h_animal_type" value="강아지">강아지</td>
						<td><input type="checkbox" name="h_animal_type" value="고양이">고양이</td>
						<td><input type="checkbox" name="h_animal_type" value="햄스터">햄스터</td>
						<td><input type="checkbox" name="h_animal_type" value="토끼">토끼</td>
						<td><input type="checkbox" name="h_animal_type" value="기니피그">기니피그</td>
						<td><input type="checkbox" name="h_animal_type" value="고슴도치">고슴도치</td>
					</tr>
					<tr>
						<td></td>
						<td><input type="checkbox" name="h_animal_type" value="페럿">페럿</td>
						<td><input type="checkbox" name="h_animal_type" value="다람쥐">다람쥐</td>

						<td><input type="checkbox" name="h_animal_type" value="카피바라">
							카피바라</td>

						<td><input type="checkbox" name="h_animal_type" value="양">
							양</td>
						<td><input type="checkbox" name="h_animal_type" value="염소">
							염소</td>
						<td><input type="checkbox" name="h_animal_type" value="알파카">알파카</td>
					</tr>
					<tr>
						<td></td>

						<td><input type="checkbox" name="h_animal_type" value="돼지">돼지</td>
						<td><input type="checkbox" name="h_animal_type" value="소">소</td>
						<td><input type="checkbox" name="h_animal_type" value="말">말</td>
						<td><input type="checkbox" name="h_animal_type" value="당나귀">당나귀</td>
						<td><input type="checkbox" name="h_animal_type" value="노새">노새</td>
					</tr>
					<tr>
						<td>조류</td>
						<td><input type="checkbox" name="h_animal_type" value="거위">거위</td>
						<td><input type="checkbox" name="h_animal_type" value="공작">공작</td>
						<td><input type="checkbox" name="h_animal_type" value="닭">닭</td>
						<td><input type="checkbox" name="h_animal_type" value="비둘기">비둘기</td>
						<td><input type="checkbox" name="h_animal_type" value="앵무새">앵무새</td>
						<td><input type="checkbox" name="h_animal_type" value="오리">오리</td>
						<td><input type="checkbox" name="h_animal_type" value="타조">타조</td>
					</tr>
					<tr>
						<td>파충류</td>
						<td><input type="checkbox" name="h_animal_type" value="거북이">거북이</td>
						<td><input type="checkbox" name="h_animal_type" value="도마뱀">도마뱀</td>
						<td><input type="checkbox" name="h_animal_type" value="뱀">뱀</td>
						<td><input type="checkbox" name="h_animal_type" value="악어">악어</td>
					</tr>
					<tr>
						<td>양서류</td>
						<td><input type="checkbox" name="h_animal_type" value="도룡뇽">도룡뇽</td>
						<td><input type="checkbox" name="h_animal_type" value="개구리">개구리</td>
					</tr>
					<tr>
						<td>어류</td>
						<td><input type="checkbox" name="h_animal_type" value="어류">어류</td>
					</tr>




				</tbody>
			</table>


			<h3 style="text-align: left">병원 대표 사진 등록</h3>
			<table class="container">
				<tbody>
					<tr>
						<td><label for="name">대표 사진 등록</label></td>
						<td class="tdinput filebox"><img
							src="image?file=${hospital.h_picture eq null? 'default.png': hospital.h_picture}&type=hospital"
							alt="이미지 선택" id="preview" width="400px"> <label for="file">파일찾기</label>
							<input type="file" id="file" accept="image/*"
							onchange="readURL(this); "
							style="display: none; vertical-align: top;" name="h_picture"></td>

					</tr>
				</tbody>
			</table>

			<h3 style="text-align: left">추가 사항</h3>
			<table class="container">
				<tbody>
					<tr>
						<td style="vertical-align: top"><label for="name">상세
								설명</label></td>
						<td><textarea style="resize: none" class="tdinput"
								id="input_box" cols="30" rows="4" name="h_memo"
								placeholder="  방문자들의 편의를 위해 진료 가능한 동물 종류를  &#10;다양하게 또는 가능한 업무(진료, 미용, 엑스레이, 수술) 등을 작성해주세요"></textarea></td>
					</tr>
					<tr>
						<td style="vertical-align: top"><label for="name">찾아오는
								길</label></td>
						<td><textarea style="resize: none" id="input_box" cols="30"
								rows="4" name="h_memo_road"
								placeholder="  골목 또는 2층 이상의 찾기 어려운 위치일 경우  &#10; 상세 입력 부탁합니다."></textarea></td>
					</tr>

					<tr>
						<td><label for="name"> SNS / Blog </label></td>
						<td class="tdinput"><input type="text" id="input"
							name="h_sns" value="https://"></td>

					</tr>

					<tr>
						<td><label for="name"> 결제수단 </label></td>
						<td class="tdinput"><input type="checkbox" name="h_pay"
							value="현금"> 현금 <input type="checkbox" name="h_pay"
							value="카드"> 카드 <input type="checkbox" name="h_pay"
							value="제로페이"> 제로페이</td>
					</tr>

					<tr>

						<td class="name">주차 가능 여부</td>
						<td><input type="radio" class="car-check" name="h_parking"
							id="radioParkingTrue" value="true" autocomplete="off"> <label
							class="car-button" for="radioParkingTrue">가능</label> <input
							type="radio" class="car-check" name="h_parking"
							id="radioParkingFalse" value="false" autocomplete="off">
							<label class="car-button" for="radioParkingFalse">불가능</label></td>
					</tr>

				</tbody>
			</table>
			<div class="rowwrite">
				<p>
					<b>주의사항</b>
				</p>
				<p>1. 병원 정보는 즉시 등록됩니다.</p>
				<p>2. 병원정보는 등록 된 이후 '마이병원'을 통해 언제든 수정이 가능합니다.</p>
				<p>3. 잘못된 장소는 강제로 삭제될 수 있습니다.</p>
				<p>4. 병원 등록 후 진료 시간은 '마이병원'탭에서 설정해야합니다.</p>

			</div>
			<div>
				<input type="checkbox" required> 위 사항에 동의합니다.<br>
			</div>

			<input type="hidden" id="hospital_latitude" name="latitude">
			<input type="hidden" id="hospital_longitudeH" name="longitude">


			<br> <br> <br>
			<div class="row">
				<div class="find-btn">
					<input type="submit" title="Button push blue/green"
						class="btn btnPush btnBlueGreen find-btn1" value="병원 등록"
						onclick="submitForm(event);">
				</div>
			</div>
		</form>

	</div>

</body>
<script>
	flatpickr("#timepicker", {
		noCalendar : true,
		allowInput : true, // 직접 입력 허용
		enableTime : true, // 시간 사용
		dateFormat : "H:i",
		time_24hr : true,
		defaultHour : 12,
		defaultMinute : 0,
	});
</script>
</html>


<script>
function daumPostcode() {
	new daum.Postcode({
		oncomplete : function(data) {
			var roadAddr = data.roadAddress; // 도로명 주소 변수
			var extraRoadAddr = ''; // 참고 항목 변수
			if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
				extraRoadAddr += data.bname;
			}
			if (data.buildingName !== '' && data.apartment === 'Y') {
				extraRoadAddr += (extraRoadAddr !== '' ? ', '
						+ data.buildingName : data.buildingName);
			}
			if (extraRoadAddr !== '') {
				extraRoadAddr = ' (' + extraRoadAddr + ')';
			}
						document.getElementById('postcode').value = data.zonecode;
						document.getElementById("h_address").value = roadAddr
						+ extraRoadAddr;
						getLatLngFromAddress(roadAddr);

					}
				}).open();
	}
</script>


<script>
	//위도 경도 코드
	function getLatLngFromAddress(address) {
		var geocoder = new kakao.maps.services.Geocoder();

		geocoder.addressSearch(address, function(result, status) {
			if (status === kakao.maps.services.Status.OK) {
				var coords = result[0];
				var latitude = coords.y; // 위도
				var longitude = coords.x; // 경도


				console.log('Latitude:', latitude, 'Longitude:', longitude);

				// 예시: 위도와 경도를 숨겨진 input에 저장
				document.getElementById('hospital_latitude').value = latitude;
				document.getElementById('hospital_longitudeH').value = longitude;

				console.log('Hidden Field Values:',
						'Latitude:', document.getElementById('hospital_latitude').value,
						'Longitude:', document.getElementById('hospital_longitudeH').value);



			} else {
				alert('주소로 위도와 경도를 찾을 수 없습니다.');
			}
		});
	}



</script>