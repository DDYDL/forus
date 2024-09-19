<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
			<c:choose>
				<c:when test="${afterReservList eq null}"><br><br><p class="center">남은 예약이 없습니다.</p></c:when>
				<c:otherwise>
				<table class="table table-hover mytable">
						<tr>
							<th id="date">날짜</th>
							<th id="time">시간</th>
							<th id="petname" colspan="2">반려동물</th>
							<th id="hospitalname">병원</th>
						</tr>
						<c:forEach items="${afterReservList }" var="reserv">
						<tr onclick="location.href='./reservDetail?reserv_id=${reserv.reserv_id}'">
							<td onclick="event.cancelbubble = true;">${reserv.reserv_date }</td>
							<td>${reserv.reserv_time }</td>
							<td><img src="image?file=${reserv.pet.picture eq null? 'default.png': reserv.pet.picture}&id=${pet_id}&type=pet"" style="width:50px;"></td>
							<c:choose>
								<c:when test="${reserv.pet_name eq null}"><td>삭제된 펫입니다!</td></c:when>
								<c:otherwise><td class="textalign_left">${reserv.pet_name }</td></c:otherwise>
							</c:choose>
							<td>${reserv.h_name }</td>
						</tr>
						</c:forEach>
				</table>
				</c:otherwise>
				</c:choose>
</div>
</body>
</html>