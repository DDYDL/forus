<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
.pet {
	font-weight: bold;
	color: rgba(105, 233, 46);
}
</style>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<link rel="stylesheet" href="css/my/font.css" />

<link rel="stylesheet" href="css/my/containerbox.css" />
<link rel="stylesheet" href="css/my/list.css" />
<script>
	function delete_list(num) {
		// let name = this.$('li').text()
		$.ajax({
			type : "POST",
			url : "/list/delete",
			data : {
				num_give : num
			},
			success : function(response) {
				alert(response["msg"])
				window.location.reload()
			}
		});
	}
</script>
</head>
<body>
	<%@ include file="../mainmypage.jsp"%>

	<div class="mypage-content">
		<div>
			<h3 align="center">반려동물 프로필</h3>
		</div>

		<table class="table table-hover">
			<thead>
				<tr>
					<th></th>
					<th>프로필사진</th>
					<th>이름</th>
					<th>종류</th>
					<th>품종</th>
					<th>성별</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><input type="checkbox" /></td>
					<td><div class="profilebox">
							<img class="profile" src="/img/dog1.png">
						</div></td>
					<td><a href="/forus/myPetModify">또치</a></td>
					<td>개</td>
					<td>저먼셰퍼드</td>
					<td>남</td>

				</tr>
				<tr>
					<td><input type="checkbox" /></td>
					<td><div class="profilebox">
							<img class="profile" src="../img/dog2.png">
						</div></td>
					<td><a href="/forus/myPetModify">지구</a></td>
					<td>새</td>
					<td>사랑앵무</td>
					<td>여</td>

				</tr>

				<tr>
					<td><input type="checkbox" /></td>
					<td><div class="profilebox">
							<img class="profile" src="../img/dog3.png">
						</div></td>
					<td><a href="/forus/myPetModify">후추</a></td>
					<td>고양이</td>
					<td>아메리칸 숏헤어</td>
					<td>남</td>

				</tr>
			</tbody>
		</table>
		<div>
			<button class="btnright" onclick="location.href='/forus/myNewPet'">등록</button>
			<button class="btnright" onclick="delete_list()">삭제</button>
		</div>
	</div>



</body>
</html>