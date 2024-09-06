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
<link href="../css/my/mycommon.css" rel="stylesheet" type="text/css">
</head>
<body>
	<%@ include file="../mainmypage.jsp" %>
	<div class = "mypage-content-list">
		<div class="mytitle">
			<a href="${pageContext.request.contextPath}/myAfterReserv">이후 예약</a>&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
			<a href="mybeforereserv.jsp">지난 예약</a>
		</div>
			<div class="listcnt">
				<p>총 2건</p>
			</div>
	<table class="table table-hover mytable">
		<thead>
			<tr>
				<th id="date">날짜</th>
				<th id="time">시간</th>
				<th id="petname" colspan="2">반려동물</th>
				<th id="hospitalname">병원</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${afterReservList }" var="reserv">
			<tr>
				<td>${reserv.date }</td>
				<td>${reserv.time }</td>
				<td><img src="image?file=${reserv.pet_picture}" width="100px"></td>
				<td>${reserv.pet_name }</td>
				<td>${reserv.h_name }</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
</body>
</html>