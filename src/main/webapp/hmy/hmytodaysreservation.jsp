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

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
	
	
<script>
    function deleteSelectedPets() {
        // 선택된 체크박스가 있는지 확인
        var checkboxes = document.querySelectorAll('input[name="reservIds"]:checked');
        if (checkboxes.length > 0) {
            // 첫 번째 체크된 체크박스의 pet_id 값을 가져와서 서버로 전송
            var firstCheckedCheckbox = checkboxes[0];
            var petId = firstCheckedCheckbox.value;
            
            // GET 방식으로 pet_id를 URL에 포함
            window.location.href = './hmyTodaysReservationDelete?reserv_id=' + petId;
        } else {
            alert("삭제할 펫을 선택해 주세요.");
        }
    }
</script>

<link rel="stylesheet" href="css/hmy/font.css" />
<link rel="stylesheet" href="css/hmy/list.css" />
</head>
<body>
	<%@ include file="../mainhmypage.jsp"%>

	<div class="mypage-content-list">
		<div>
			<h2 align="center">오늘 예약 일정</h2>
		</div>
		<div style="text-align: left;">
			<button type="button" title="Button fade blue/green"
				class="btn btnFade btnBlueGreen find-btn1"
				onclick="location.href='./hmyTodaysReservation?reserv_id=${reserv_id }'">취소하기</button>
			<div>

				<table class="table table-hover">
					<thead>
						<tr>
							<th></th>
							<th></th>
							<th>예약번호</th>
							<th>보호자</th>
							<th>연락처</th>

							<th>시간</th>
							<th>펫 이름</th>
							<th>신청 날짜</th>
							<th>사항</th>
						</tr>
					</thead>
					<c:forEach items="${reservList }" var="reservation">
					<c:set var="i" value="${i+1 }" />
						<tr>
							<td><input type="checkbox" name="reservIds" value="${reservation.reserv_id}"/></td>
							<td>${i }</td>
							<td>${reservation.reserv_id }</td>
							<td>${reservation.name }</td>
							<td>${reservation.phone }</td>

							<td>${reservation.reserv_time}</td>
							<td>${reservation.pet_name }</td>
							<td>${reservation.reserv_apply_time}</td>
							<td>${reservation.reserv_content }</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>
</html>