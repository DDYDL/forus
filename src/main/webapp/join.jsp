<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
<script src="https://cdn.jsdelivr.net/npm/flatpickr/dist/l10n/ko.js"></script>
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	// 카카오(다음) 주소 입력
    function daumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                var roadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 참고 항목 변수
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }
                document.getElementById("postcode").value = data.zonecode;
                document.getElementById("address").value = roadAddr + extraRoadAddr;
            }
        }).open();
    }
</script>
<script>
    document.addEventListener('DOMContentLoaded', function() {
        // form 내 Enter 키 submit 방지
        document.getElementById('join').addEventListener('keydown', function(event) {
            if (event.key === 'Enter') {
                event.preventDefault(); // 기본 동작(폼 제출) 방지
            }
        });
    });
</script>
<link href="css/my/mycommon.css" rel="stylesheet" type="text/css">
</head>
<body>
	<%@ include file="mainpage.jsp"%>
	<div class="content user-content">
		<form action="join" method="post" id="join">
			<p class="content-title">회원가입</p>
			<br>
			<br>
			<table class="join-table">
				<tr>
					<td class="columnsize-title">이메일<br></td>
					<td class="columnsize"><input type="text"
						placeholder=" 이메일을 입력해주세요" name="email"
						class="user-input join-input-short">&nbsp;
						<button class="minibtn">중복 확인</button></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" placeholder=" 비밀번호 입력해주세요" name="password"
					class="user-input join-input"></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" placeholder=" 이름을 입력해주세요" name="name" 
					class="user-input join-input"></td>
				</tr>
				<tr>
					<td>닉네임</td>
					<td><input type="text" placeholder=" 닉네임을 입력해주세요" name="nickname"
					class="user-input join-input"></td>
				</tr>
				<tr>
					<td>휴대전화번호</td>
					<td><input type="text" placeholder=" 휴대전화번호를 입력해주세요" name="phone"
					class="user-input join-input"></td>
				</tr>
				<tr>
					<td>인증번호</td>
					<td><input type="text" placeholder=" 인증번호를 입력해주세요" class="user-input join-input-short">&nbsp;
						<button class="minibtn">인증번호 요청</button></td>
				</tr>
				<tr>
					<td>생년월일</td>
					<td><input id="justDate" type="text" placeholder=" 날짜를 선택해주세요" name="birthday"
					class="user-input join-input"></td>
				</tr>
				<tr>
					<td>성별</td>
					<td><input type="radio" name="gender" value="M">&nbsp;남&nbsp;&nbsp;<input type="radio" name="gender" value="F">&nbsp;여</td>
				</tr>
				<tr>
					<td>주소</td>
					<td><input type="text" id="postcode" placeholder=" 우편번호" class="user-input join-input-short">&nbsp;
						<input type="button" onclick="daumPostcode()" class="minibtn" value="주소 검색">
						<input type="text" id="address" placeholder=" 주소" class="user-input join-input" name="address">
					</td>
				</tr>
			</table>
			<br>
			<br>
			<div>
				<input type="submit" value="가입하기" class="bigbtn">
			</div>
		</form>
	</div>
	<br>
	<br>
	<br>
</body>
<script>
	flatpickr("#justDate", {
		"locale" : "ko",
		"dateFormat" : "Y-m-d",
	});
</script>
</html>