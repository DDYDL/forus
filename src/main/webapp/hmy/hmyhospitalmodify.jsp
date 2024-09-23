<%@page import="dto.Hospital"%>
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
<link rel="stylesheet" href="css/common/minibutton.css" />
<link href="./css/recruit/calendar.css?after" rel="stylesheet"
	type="text/css">
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

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

	// 카카오(다음) 주소 입력
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
				document.getElementById("postcode").value = data.zonecode;
				document.getElementById("h_address").value = roadAddr
						+ extraRoadAddr;
			}
		}).open();
	}
</script>

</head>


<body>
	<%@ include file="../mainhmypage.jsp"%>

	<div class="mypage-content">

		<form action="./hmyHospitalModify" method="post"
			enctype="multipart/form-data">
			<input type="hidden" name="h_id" value="${hospital.h_id }">

			<div>
				<h2 align="center">내 병원 정보 수정</h2>
			</div>

			<h3 style="text-align: left">기본 정보</h3>

			<table class="container">

				<tbody>
					<tr>
						<td><label for="name">대표자 이름</label></td>
						<td class="tdinput"><input type="text" id="input"
							name="h_exponent_name" value="${hospital.h_exponent_name  }"
							required></td>
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
						<td class="tdinput"><input type="tel" id="tel" name="h_phone"
							value="${hospital.h_phone }" required></td>
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
							class="user-input join-input" name="h_address"
							value="${hospital.h_address }"></td>

					</tr>




					<tr>
						<%-- <td class="time"><label for="name">점심 시간</label></td>
								<td class="tdinput time"><input type="time" id="input"
									name="h_lunch_time_start"
									value="${hospital.h_lunch_time_start }"> ~ <input
									type="time" id="input" name="h_lunch_time_end"
									value="${hospital.h_lunch_time_end }"></td> --%>

						<td class="setting_td1"><label for="time">점심시간</label></td>
						<td class="setting_td2"><input type="time" class="time_start"
							id="timepicker" name="h_lunch_time_start"
							value="${hospital.h_lunch_time_start }"> ~ <input
							type="time" class="time_end" id="timepicker"
							name="h_lunch_time_end" value="${hospital.h_lunch_time_end }"></td>

					</tr>

				</tbody>
			</table>
			<h3 style="text-align: left">진료 가능한 동물 목록</h3>

			<table class="container">

				<tbody>
					<tr>
						<td><label for="name"> 진료가능 목록 </label></td>
						<td class="tdinput"><input id='강아지' type="checkbox"
							name="h_animal_type" value="강아지"> 강아지 <input id='고양이'
							type="checkbox" name="h_animal_type" value="고양이"> 고양이 <input
							id='페럿' type="checkbox" name="h_animal_type" value="페럿">페럿

							<c:forEach items="${h_animal_types}" var="h_animal_types">

								<c:if test="${h_animal_types eq '강아지'}">
									<script>
										$("input[id='강아지']").prop("checked",
												true);
									</script>
								</c:if>

								<c:if test="${h_animal_types eq '고양이'}">
									<script>
										$("input[id='고양이']").prop("checked",
												true);
									</script>
								</c:if>

								<c:if test="${h_animal_types eq '페럿'}">
									<script>
										$("input[id='페럿']").prop("checked",
												true);
									</script>
								</c:if>
							</c:forEach></td>
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
							class="img-icon" alt="이미지 선택" id="preview" style="width: 400px">
							<label for="file">파일찾기</label> <input type="file" name="file"
							id="file" accept="image/*" onchange="readURL(this);"
							style="display: none" name="h_picture"></td>

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
								id="input_box" cols="30" rows="4" name="h_memo">${hospital.h_memo }</textarea></td>
					</tr>
					<tr>
						<td style="vertical-align: top"><label for="name">찾아오는
								길</label></td>
						<td><textarea style="resize: none" class="tdinput"
								id="input_box" cols="30" rows="4" name="h_memo_road">${hospital.h_memo_road }</textarea></td>
					</tr>
					<tr>
						<td><label for="name"> SNS / Blog </label></td>
						<td class="tdinput"><input type="text" id="input"
							name="h_sns" value="${hospital.h_sns }" required></td>

					</tr>

					<tr>
						<td><label for="name"> 결제수단 </label></td>
						<td class="tdinput"><input id="현금" type="checkbox"
							name="h_pay" value="현금"> 현금 <input id="카드"
							type="checkbox" name="h_pay" value="카드"> 카드 <input
							id="제로페이" type="checkbox" name="h_pay" value="제로페이"> 제로페이

							<c:forEach items="${h_pays}" var="h_pays">
								<c:if test="${h_pays eq '현금'}">
									<script>
										$("input[id='현금']").prop("checked",
												true);
									</script>
								</c:if>
								<c:if test="${h_pays eq '카드'}">
									<script>
										$("input[id='카드']").prop("checked",
												true);
									</script>
								</c:if>
								<c:if test="${h_pays eq '제로페이'}">
									<script>
										$("input[id='제로페이']").prop("checked",
												true);
									</script>
								</c:if>
							</c:forEach></td>
					</tr>

					<tr>
						<td><label for="name"> 주차 가능 여부 </label></td>
						<td class="tdinput"><input type="radio" name="h_parking"
							value="true"
							<c:if test="${hospital.h_parking eq true}">checked</c:if>>
							가능 <input type="radio" name="h_parking" value="false"
							<c:if test="${hospital.h_parking eq false}">checked</c:if>>
							불가능</td>
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

