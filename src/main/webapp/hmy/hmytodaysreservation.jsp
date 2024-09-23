<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
.todays {
	font-weight: bold;
	color: rgba(105, 233, 46);
}
</style>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="css/hmy/doctorCalendar.css">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>


<link rel="stylesheet" href="css/hmy/font.css" />
<link rel="stylesheet" href="css/hmy/list.css" />
</head>
<body>
	<%@ include file="../mainhmypage.jsp"%>


	<div id="overlay" onclick="closeSidebar()"></div>
	<div id="sidebar">
		<h3>예약 상세 정보</h3>
		<p>
			<strong>예약자 이름:</strong> <span id="reserverName"></span>
		</p>
		<p>
			<strong>동물:</strong> <span id="animalType"></span>
		</p>
		<p>
			<strong>종:</strong> <span id="animalBreed"></span>
		</p>
		<p>
			<strong>펫이름:</strong> <span id="animalName"></span>
		</p>
		<p id="eventContent"></p>

		<label for="vetNote"></label>
		<textarea id="vetNote" placeholder="메모를 입력하세요..."></textarea>
		<button id="saveNoteButton" onclick="saveNote()">메모 저장</button>
		<button id="closeSidebarButton" onclick="closeSidebar()">닫기</button>
	</div>


	<div class="mypage-content-list">
		<div>
			<h2 align="center">오늘 예약 일정</h2>
		</div>

		<table class="table table-hover">
			<thead>
				<tr>
					<th></th>
					<th>예약번호</th>
					<th>보호자</th>
					<th>연락처</th>
					<th>시간</th>
					<th>펫 이름</th>
					<th>신청 날짜</th>
					<th>사항</th>
					<th>상태</th>
				</tr>
			</thead>
			<c:forEach items="${reservList }" var="reservation">
				<c:set var="i" value="${i+1 }" />
				<tr onclick="getReservationDetails(this)"
					data-reservation-id="${reservation.reserv_id}"
					data-reservation-memo="${reservation.reserv_memo}"
					data-reservation-content="${reservation.reserv_content}"
					data-reservation-time="${reservation.reserv_time}">

					<td>${i }</td>
					<td>${reservation.reserv_id }</td>
					<td>${reservation.name }</td>
					<td>${reservation.phone }</td>
					<td>${reservation.reserv_time}</td>
					<td>${reservation.pet_name }</td>
					<td>${reservation.reserv_apply_time}</td>
					<td>${reservation.reserv_content }</td>
					<td>${reservation.reserv_status }</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	</div>
	</div>
</body>
</html>

<script>
	function openSidebar() {
		document.getElementById('sidebar').style.display = 'block';
		document.getElementById('overlay').style.display = 'block';
	}

	function closeSidebar() {
		document.getElementById('sidebar').style.display = 'none';
		document.getElementById('overlay').style.display = 'none';
	}

	function getReservationDetails(row) {

		var reservationId = $(row).data('reservation-id');
		var reservationMemo = $(row).data('reservation-memo');
		var reservationContent = $(row).data('reservation-content');
		var reservationTime = $(row).data('reservation-time');

		$.ajax({
			url : 'doctorCalendarDetail',
			type : 'get',
			dataType : 'json',
			data : {
				reservationId : reservationId
			},
			success : function(data) {
				generateReservationDetails(data, reservationId,
						reservationMemo, reservationContent, reservationTime);
				openSidebar();
			},
			error : function() {
				alert('예약 상세 정보를 불러오는데 실패했습니다.');
			}
		});
	}

	function generateReservationDetails(data, reservationId, reservationMemo,
			reservationContent, reservationTime) {
		$('#eventContent').empty();
		document.getElementById('reserverName').textContent = data.userName;
		document.getElementById('animalType').textContent = data.petSpecies;
		document.getElementById('animalBreed').textContent = data.petBreed;
		document.getElementById('animalName').textContent = data.petName;

		$('#vetNote').val(reservationMemo || '');

		$('#eventContent').append(
				'<p><strong>내용:</strong> ' + reservationContent + '</p>');
		$('#eventContent').append(
				'<p><strong>시간:</strong> ' + reservationTime + '</p>');

		$('#eventContent').data('reservationId', reservationId);

	}
</script>


<script>
	function saveNote() {
		var reservationId = $('#eventContent').data('reservationId');

		if (!reservationId) {
			alert('Reservation ID가 올바르지 않습니다.');
			return;
		}
		var memo = $('#vetNote').val();

		$.ajax({
			url : 'doctorCalendarDetail',
			type : 'post',
			dataType : 'json',
			data : {
				reservationId : reservationId,
				memo : memo
			},
			success : function(response) {
				alert('메모가 저장되었습니다.');

				// 메모 입력창에 입력한 메모를 표시
				$('#vetNote').val(memo);

				// 메모를 저장한 row의 메모 데이터를 업데이트
				$('tr[data-reservation-id="' + reservationId + '"]').data(
						'reservation-memo', memo);
			},
			error : function(xhr, status, error) {
				console.error(xhr.responseText);
				alert('메모 저장에 실패했습니다.');
			}
		});
	}
</script>