<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예약내역-이후예약</title>
<!-- table -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<link href="${pageContext.request.contextPath}/css/my/mycommon.css" rel="stylesheet" type="text/css">
</head>
<body>
	<%@ include file="../mainmypage.jsp" %>
	<div class = "mypage-content-list">
		<div class="mytitle">
			<a href="${pageContext.request.contextPath}/myAfterReserv">이후 예약</a>&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
			<a href="${pageContext.request.contextPath}/myBeforeReserv">지난 예약</a>
		</div>
			<div class="listcnt">
				<p>총 ${afterReservList.size() }건</p>
			</div>
	<table class="table table-hover mytable">
			<tr>
				<th id="date">날짜</th>
				<th id="time">시간</th>
				<th id="petname" colspan="2">반려동물</th>
				<th id="hospitalname">병원</th>
			</tr>
			<c:forEach items="${afterReservList }" var="reserv">
			<tr onclick="location.href='./reservdetail?reserv_id=\${reserv.reserv_id}'">
				<td>${reserv.reserv_date }</td>
				<td>${reserv.reserv_time }</td>
				<td><img src="image?file=${reserv.pet_picture}" width="80px"></td>
				<td class="textalign_left">${reserv.pet_name }</td>
				<td>${reserv.h_name }</td>
			</tr>
			</c:forEach>
	</table>
</div>
</body>
</html>