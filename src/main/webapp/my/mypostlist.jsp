<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지-게시글관리</title>
<!-- table -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<link href="../css/my/mycommon.css" rel="stylesheet" type="text/css">
<link href="../css/my/mycommon.css" rel="stylesheet" type="text/css">
</head>
<body>
	<%@ include file="../mainmypage.jsp" %>
		<div class = "mypage-content-list">
	<div class="mytitle">
		<p>게시글 관리</p>
	</div>
	<div id="listcnt">
		<p>총 2건</p>
	</div>
	<table class="table table-hover mytable">
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
		<button type="submit" class="minibtn">삭제하기</button>
	</div>
	</div>
</body>
</html>