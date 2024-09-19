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

<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=94ab9074f87bbed0edf5b0d9cb32cdbd&libraries=services"></script>

<link
	href="https://cdn.rawgit.com/harvesthq/chosen/gh-pages/chosen.min.css"
	rel="stylesheet" />
<link rel="stylesheet" href="css/hmy/containerbox.css" />
<link rel="stylesheet" href="css/hmy/input.css" />
<link rel="stylesheet" href="css/hmy/font.css" />
<link rel="stylesheet" href="css/hmy/time.css" />
<link rel="stylesheet" href="css/hmy/button.css" />


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
	
	function(e){
		
	}
	
	
</script>


</head>


<body>
	<%@ include file="../mainmypage.jsp"%>

	<div class="mypage-content">

		<div class="bodybox">
			<form action="hmyNewHospital" method="post"
				enctype="multipart/form-data">
				<div class="containerbox">

					<div>
						<h3>신규 병원 등록</h3>
					</div>

					<h4>기본 정보</h4>

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
								<td class="tdinput"><input type="tel" id="tel"
									name="h_phone" required></td>
							</tr>

							<tr>
								<td><label for="name">병원 주소</label></td>
								<td><input class="tdinput" type="address"
									id="sample6_postcode" placeholder="우편번호" name="h_address">

									<button type="button" class="btn" onclick="sample6_execDaumPostcode()">주소찾기</button>
								</td>

							</tr>




							<tr>
								<td></td>
								<td><input class="tdinput" type="address"
									id="sample6_address" placeholder="주소" name="h_address">
									&nbsp; &nbsp;<input class="tdinput" type="address"
									id="sample6_detailAddress" placeholder="상세주소"></td>
							</tr>


							<tr>
								<td class="time"><label for="name">진료 시간</label></td>
								<td class="tdinput time">시작<input type="time" id="input"
									name="h_longitude"> &nbsp; &nbsp; 종료<input type="time"
									id="input" name="h_latitude"></td>
							</tr>
							<tr>
								<td class="time"><label for="name">점심 시간</label></td>
								<td class="tdinput time">시작<input type="time" id="input"
									name="h_lunch_time_start"> &nbsp; &nbsp; 종료 <input
									type="time" id="input" name="h_lunch_time_end"></td>
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
								<td><textarea id="input_box" rows="4"
										cols="30" name="h_memo"
										placeholder="방문자들의 편의를 위해 진료 가능한 동물 종류 다양하게 또는 가능한 업무(진료, 미용, 엑스레이, 수술) 등을 작성해주세요"></textarea></td>
							</tr>
							<tr>
								<td><label for="name">찾아오는 길</label></td>
								<td><textarea id="input_box" rows="4"
										cols="30" name="h_memo_road"
										placeholder="골목 또는 2층 이상의 찾기 어려운 위치일 경우 상세 입력 부탁합니다."></textarea></td>
							</tr>

							<tr>
								<td><h3>편의사항</h3></td>
							</tr>
							<tr>
								<td><label for="name"> SNS / Blog </label></td>
								<td class="tdinput"><input type="text" id="input"
									name="h_sns"></td>

							</tr>

							<tr>
								<td><label for="name"> 결제수단 </label></td>
								<td class="tdinput"><input type="checkbox" name="h_pay"
									value="1"> 현금 <input type="checkbox" name="h_pay"
									value="2"> 카드 <input type="checkbox" name="h_pay"
									value="3"> 제로페이</td>
							</tr>

							<tr>
								<td><label for="name"> 주차 가능 여부 </label></td>
								<td class="tdinput"><input type="radio" name="h_parking"
									value="T"> 가능 <input type="radio" name="h_parking"
									value="F"> 불가능</td>
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
							<input type="submit" title="Button push blue/green" class="btn btnPush btnBlueGreen find-btn1" value="병원 등록" onclick="submitForm(event);">
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>

</body>
</html>


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

						getLatLngFromAddress(addr);

						// 커서를 상세주소 필드로 이동한다.
						document
								.getElementById(
										"sample6_detailAddress")
								.focus();
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
