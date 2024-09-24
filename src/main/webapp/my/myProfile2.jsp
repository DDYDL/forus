<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/my/containerprofile.css" />
<link rel="stylesheet" href="css/my/profile.css" />
<link rel="stylesheet" href="css/my/input.css" />
<link rel="stylesheet" href="css/my/button.css" />
<style>
.my {
	font-weight: bold;
	color: rgba(105, 233, 46);
}

.mypage {
	font-weight: bold;
	color: rgba(105, 233, 46);
}
</style>

</head>
<body>
	<%@ include file="../mainmypage.jsp"%>

	<div class="mypage-content">

		<div>
			<h2 align="center">내 프로필</h2>
		</div>

		<div style="text-align: right; padding-right: 90px">
			<c:if test="${user.ishospital eq 0 }">
				<span id="authentication">병원관계자라면?&nbsp; <a
					href="./hmyNewHospital"><img src="./img/play.png" width="16px">&nbsp;병원인증하기</a>
				</span>
			</c:if>
		</div>
		<table class="containerprofile">
			<tbody>
				<tr>
					<td><label class="title">프로필 사진</label></td>
				</tr>
				<tr>
					<td><img
						src="image?file=${user.picture eq null? 'userdefault.png': user.picture}&id=${user_id}&type=user"
						class="img-icon"></td>
				</tr>

				<tr>
					<td><label for="name" class="title">이름</label></td>
					<td class="line"><span>${user.name }</span></td>
				</tr>

				<tr>
					<td><label for="nickname" class="title">닉네임 </label></td>
					<td class="line"><span>${user.nickname }</span></td>
				</tr>

				<tr>
					<td><label for="emailid" class="title">이메일</label></td>
					<td class="line"><span>${user.email }</span></td>
				</tr>

				<tr>
					<td><label for="password" class="title">비밀번호 </label></td>
					<td class="line"><span>수정에서 변경가능</span></td>
				</tr>

				<tr>
					<td><label for="tel" class="title">휴대전화번호</label></td>
					<td class="line"><span>${user.phone }</span></td>
				</tr>

				<tr>
					<td><label for="address" class="title">주소</label></td>
					<td class="line"><span>${user.address }</span></td>
				</tr>

				<tr>
					<td><label for="birth" class="title">생년월일</label></td>
					<td class="line"><span>${user.birthday }</span></td>
				</tr>

				<tr>
					<td><label for = "gender" class="title">성별 </label></td>
					<td class="line"><span> <c:choose>
								<c:when test="${user.gender eq 'M' }"> 남자</c:when>
								<c:when test="${user.gender eq 'F' }"> 여자</c:when>
							</c:choose></span></td>
				</tr>
			</tbody>

		</table>
	

	<br>
	<br>
	<br>
	<div class="find-btn">
		<button type="button" title="Button push blue/green"
			class=" btn btnPush btnBlueGreen find-btn1"
			onclick="location.href='./myProfileModify?id=${user.id}'">수정</button>

	</div>
	<br>
	<br>
</div>
	
</body>
</html>