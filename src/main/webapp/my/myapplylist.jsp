<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지-지원현황</title>
<style>
body {
	padding-top: 5%;
	padding-left: 15%;
	padding-right: 15%;
}

table {
	width: 950px;
	border-top: 2px solid #444444;
	border-collapse: collapse;
	text-align: center;
	margin-left: 200px;
}

th, td {
	border-bottom: 1px solid #444444;
	padding: 10px;
}

.title {
	padding-top: 20px;
	padding-bottom: 50px;
	margin-right:100px;
}
.btn{
	width: 80px;
	height: 30px;
	font-size: 16px;
	font-weight: bold;
	border-radius:10px;
	border: 0;
    background: rgba(105, 233, 46, 0.37);
}
.btn:hover{
    background: rgba(44, 207, 39, 0.37);
}

#listcnt {
	text-align: left;
}
</style>

</head>

<body>
	<%@ include file="../mainmypage.jsp" %>
	<div class="mytitle">
		<h2>지원현황</h2>
	</div>
	<table>
		<thead>
			<div id="listcnt">
				<p>총 2건</p>
			</div>
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
				<td><button class="btn">취소하기</button></td>
			</tr>
			<tr>
				<td>2024.8.19</td>
				<td>강아지 산책시켜주실 분!!(견주만)</td>
				<td>게재중</td>
				<td><button type="submit" class="btn">취소하기</button></td>
			</tr>
			<tr>
				<td>2024.8.19</td>
				<td>강아지 산책시켜주실 분!!(견주만)</td>
				<td>마감</td>
				<td></td>
			</tr>
		</tbody>
	</table>
</body>
</html>