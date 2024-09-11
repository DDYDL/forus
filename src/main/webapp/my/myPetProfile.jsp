<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/my/containerbox.css" />
<link rel="stylesheet" href="css/my/profile.css" />
<link rel="stylesheet" href="css/my/input.css" />
<link rel="stylesheet" href="css/my/button.css" />

</head>

<script>
	function readURL(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				document.getElementById('preview').src = e.target.result;
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
</script>
<body>
	<%@ include file="../mainmypage.jsp"%>
	<form action="myPetModify?pet_id=${pet.pet_id}" method="post" enctype="multipart/form-data">
		<div class="mypage-content">
			<div>
				<h3>반려동물 프로필 수정</h3>
			</div>
			<div>
				<ul>
					<li>프로필 사진</li>
					<li><img src="../img/dog1.png" class="img-icon" alt="이미지 선택"
						id="preview" width="100px"
						onclick="document.getElementById('file').click();"> <input
						type="file" name="file" id="file" accept="image/*"
						onchange="readURL(this);" style="display: none"></li>

					<li>반려동물 이름</li>
					<li class="line"><span>${pet.pet_name }</span></li>

					<li>종류</li>
					<li class="line"><span>${pet.pet_species }</span></li>

					<li>품종</li>
					<li class="line"><span>${pet.pet_breed }</span></li>

					<li>동물등록번호</li>
					<li class="line"><span>${pet.pet_num }</span></li>

					<li class="title">성별</li>
					<br>
					<br>
					<li><form>
							<input type="radio" name="gender" value="M"> 남 <input
								type="radio" name="gender" value="F"> 여
						</form></li>

					<li class="title">나이</li>
					<br>
					<br>
					<li><select name="age">
							<option value="0">0살</option>
							<option value="1">1살</option>
							<option value="2">2살</option>
							<option value="3">3살</option>
							<option value="4">4살</option>
							<option value="5">5살</option>
							<option value="6">6살</option>
							<option value="7">7살</option>
							<option value="8">8살</option>
							<option value="9">9살</option>
							<option value="10">10살</option>
							<option value="11">11살</option>
							<option value="12">12살</option>
							<option value="13">13살</option>
							<option value="14">14살</option>
							<option value="15">15살</option>
							<option value="16">16살</option>
							<option value="17">17살</option>
							<option value="18">18살</option>
							<option value="19">19살</option>
							<option value="20">20살</option>
							<option value="20+">20살 이상</option>
					</select></li>
					<li class="title">특이사항</li>
					<br>
					<br>
					<li><textarea class="tdinput" id="input_box" rows="4"
							cols="30">${pet.pet_memo }</textarea></li>
					<br>
					<br>
					<br>

				</ul>
				<br> <br> <br> <br> <br> <br>
				<div class="find-btn">
					<input type="submit" title="Button push blue/green"
						class=" btn btnPush btnBlueGreen find-btn1"
						onclick="location.href='/forus/myPetList'" value="수정완료">

				</div>
			</div>

		</div>
	</form>
</body>
</html>
