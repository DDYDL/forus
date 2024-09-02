<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지-게시글관리</title>
<style>
/* body { */
/* 	padding-top: 5%; */
/* 	padding-left: 15%; */
/* 	padding-right: 15%; */
/* } */

/* table { */
/* 	width: 950px; */
/* 	border-top: 2px solid #444444; */
/* 	border-collapse: collapse; */
/* 	text-align: center; */
/* 	margin-left: 200px; */
/* } */

/* th, td { */
/* 	border-bottom: 1px solid #444444; */
/* 	padding: 10px; */
/* } */

/* .title { */
/* 	padding-top: 20px; */
/* 	padding-bottom: 50px; */
/* 	margin-right:100px; */
/* } */
/* .btn{ */
/* 	width: 100px; */
/* 	height: 40px; */
/* 	font-size: 16px; */
/* 	font-weight: bold; */
/* 	border-radius:10px; */
/* 	border: 0; */
/*     background: rgba(105, 233, 46, 0.37); */
/*     margin-right:700px; */
/*     margin-top:10px; */
/* } */
/* .btn:hover{ */
/*     background: rgba(44, 207, 39, 0.37); */
/* } */
/* #listcnt { */
/* 	text-align: left; */
/* } */
</style>
<link href="../css/my/mycommon.css" rel="stylesheet" type="text/css">
</head>
<body>
	<%@ include file="../mainmypage.jsp" %>
	<div class="title">
		<h2>게시글 관리</h2>
	</div>
	<div id="listcnt">
		<p>총 2건</p>
	</div>
	<table>
		<thead>
			<tr>
				<th></th>
				<th id="post_time">등록일</th>
				<th id="post_title">제목</th>
				<th id="post_status">게재상태</th>
				<th id="applyCnt">신청인원</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><input type="checkbox"></td>
				<td>2024.8.19</td>
				<td>강아지 산책시켜주실 분!!(견주만)</td>
				<td>게재중</td>
				<td>1명</td>
			</tr>
			<tr>
				<td><input type="checkbox"></td>
				<td>2024.8.19</td>
				<td>강아지 2마리 집에 같이 있어주세요</td>
				<td>지원마감</td>
				<td>2명</td>
			</tr>
		</tbody>
	</table>
	<div>
		<button type="submit" class="btn">삭제하기</button>
	</div>
</body>
</html>