<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<link rel="stylesheet" href="css/my/containerbox.css" />
<link rel="stylesheet" href="css/hmy/list.css" />
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

	<div class="mypage-content-list">
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
				<c:forEach items="${petList }" var="pet">
					<tr id="petlist">
						<td><input type="checkbox" name="petIds"
							value="${pet.pet_id}" /></td>
						<td><div class="profilebox">
								<img
									src="image?file=${pet.pet_picture eq null? 'petdefault.png': pet.pet_picture}&id=${pet_id}&type=pet"
									class="img-icon">
							</div></td>
						<td><a href="./myPetModify?pet_id=${pet.pet_id}">${pet.pet_name }</a></td>
						<td>${pet.pet_species }</td>
						<td>${pet.pet_breed }</td>
						<td>${pet.pet_gender }</td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
		<div style="text-align: right">
			<button class="minibutton minibtnFade minibtnBlueGreen"
				onclick="location.href='./myNewPet'">등록</button>
			<button class="minibutton minibtnFade minibtnBlueGreen" type="button"
				onclick="deleteSelectedPets()">삭제</button>
			<!--  <button class="btnright" onclick="location.href='./myPetDelete?pet_id=${pet_id}'">삭제</button>-->
		</div>
	</div>



</body>
</html>

<script>
	function deleteSelectedPets() {
		// 선택된 체크박스가 있는지 확인
		var checkboxes = document
				.querySelectorAll('input[name="petIds"]:checked');
		if (checkboxes.length > 0) {
			// 첫 번째 체크된 체크박스의 pet_id 값을 가져와서 서버로 전송
			var firstCheckedCheckbox = checkboxes[0];
			var petId = firstCheckedCheckbox.value;

			// GET 방식으로 pet_id를 URL에 포함
			window.location.href = './myPetDelete?pet_id=' + petId;
		} else {
			alert("삭제할 펫을 선택해 주세요.");
		}
	}
</script>