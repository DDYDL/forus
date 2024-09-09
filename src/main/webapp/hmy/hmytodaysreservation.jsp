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
				onclick="location.href='#'">취소하기</button>
			<div>

				<table class="table table-hover">
					<thead>
						<tr>
							<th></th>
							<th>예약번호</th>
							<th>보호자</th>
							<th>연락처</th>
							<th>예약날짜</th>
							<th>시간</th>
							<th>펫 이름</th>
							<th>신청 날짜</th>
							<th>사항</th>
						</tr>
					</thead>
					<c:forEach items="${reservationList }" var="reservation">
						<tr>
							<td></td>
							<td>${r.reserv_id }</td>
							<td>${user.name }</td>
							<td>${user.phone }</td>
							<td>${reservation.reserv_date }</td>
							<td>${reservation.reserv_time }</td>
							<td>${pet.pet_name }</td>
							<td>${reservation.reserv_apply_time }</td>
							<td>${reservation.reserv_content }</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>
</html>