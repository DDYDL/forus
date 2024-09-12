<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지-지원현황</title>
<!-- table -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<link href="./css/my/mycommon.css" rel="stylesheet" type="text/css">
</head>

<body>
	<%@ include file="../mainmypage.jsp"%>
	<div class="mypage-content-list">
		<div class="mytitle">
			<p>지원현황</p>
		</div>
		<div class="listcnt">
			<p>총 ${myApplyList.size()}건</p>
		</div>
		<table class="table table-hover mytable">
			<thead>
				<tr>
					<th id="apply_time">지원일</th>
					<th id="post_title">제목</th>
					<th id="post_status">게재상태</th>
					<th id="apply_status">지원상태</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${myApplyList }" var="apply">
			<tr onclick="location.href='./recruitApplyDetailpage?apply_id=${apply.apply_id}'">
				<td>${apply.apply_date }</td>
				<td>${apply.apply_title }</td>
				<td class="textalign_left">${apply.post_status }</td>
			 	<c:choose>
					<c:when test="${apply.apply_status eq '지원완료'}">
						<td><button class="minibtn" type="submit">취소하기</button></td>
					</c:when>
					<c:otherwise>
						<td><p>${apply.apply_status}</p>
					</c:otherwise>
				</c:choose>
			</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>