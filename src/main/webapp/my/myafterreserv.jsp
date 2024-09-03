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
			<a href="myafterreserv.jsp">이후 예약</a>&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
			<a href="mybeforereserv.jsp">지난 예약</a>
		</div>
	<table class="table table-hover">
			<div>
				<p>총 2건</p>
			</div>
		<thead>
			<tr>
				<th id="date">날짜</th>
				<th id="time">시간</th>
				<th id="petname" colspan="2">반려동물</th>
				<th id="hospitalname">병원</th>
			</tr>
		</thead>
		<tbody>
			<tr onclick="location.href='#'"> <!-- reservdetail.jsp -->
				<td>2024.8.19</td>
				<td>14:00</td>
				<td><img src="../img/dog1.png" class="pet-img"></td>
				<td>또치</td>
				<td>올리브동물병원</td>
			</tr>
			<tr>
				<td>2024.8.31</td>
				<td>16:00</td>
				<td><img src="../img/dog2.png" class="pet-img"></td>
				<td>지구</td>
				<td>플라잉동물병원</td>
			</tr>
		</tbody>
	</table>
</div>
</body>
</html>