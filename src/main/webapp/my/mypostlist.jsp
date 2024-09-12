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

<link href="${pageContext.request.contextPath}/css/my/mycommon.css" rel="stylesheet" type="text/css">
</head>
<body>
	<%@ include file="../mainmypage.jsp" %>
		<div class = "mypage-content-list">
	<div class="mytitle">
		<p>게시글 관리</p>
	</div>
	<div class="listcnt">
		<p>총 ${postList.size() }건</p>
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
			<c:forEach items="${myPostList }" var="post">
			<tr onclick="location.href='./recruitApplyDetailpage?post_id=\${post.post_id}'">
				<td><input type="checkbox" value="${post.post_id}"></td>
				<td>${post.post_date }</td>
				<td>${post.post_title }</td>
				<td class="textalign_left">${post.post_status }</td>
				<td>${post.applycnt }명</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<div>
		<button type="submit" class="minibtn">삭제하기</button>
	</div>
	</div>
</body>
</html>