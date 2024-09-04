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

<link href="../css/my/mycommon.css" rel="stylesheet" type="text/css">
</head>

<body>
	<%@ include file="../mainmypage.jsp"%>
	<div class="mypage-content-list">
		<div class="mytitle">
			<p>지원현황</p>
		</div>
		<div id="listcnt">
			<p>총 2건</p>
		</div>
		<table class="table table-hover">
			<thead>
				<tr>
					<th id="apply_time">지원일</th>
					<th id="post_title">제목</th>
					<th id="post_status">게재상태</th>
					<th id="apply_status">지원상태</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>2024.8.19</td>
					<td>강아지 산책시켜주실 분!!(견주만)</td>
					<td>게재중</td>
					<td><button class="minibtn" type="submit">취소하기</button></td>
				</tr>
				<tr>
					<td>2024.8.19</td>
					<td>강아지 산책시켜주실 분!!(견주만)</td>
					<td>게재중</td>
					<td><button class="minibtn" type="submit">취소하기</button></td>
				</tr>
				<tr>
					<td>2024.8.19</td>
					<td>강아지 산책시켜주실 분!!(견주만)</td>
					<td>마감</td>
					<td></td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>