<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<!-- calendar -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
<script src="https://cdn.jsdelivr.net/npm/flatpickr/dist/l10n/ko.js"></script>
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
	function daumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
            	// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
                // 도로명 주소의 노출 규칙에 따라 주소를 표시.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기.
                var roadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 참고 항목 변수
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }
             // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById("postcode").value = data.zonecode;
                document.getElementById("address").value = roadAddr + extraRoadAddr;
            }
        }).open();
    }
	</script>
<link href="css/my/mycommon.css" rel="stylesheet" type="text/css">
</head>
<body>
	<%@ include file="../mainpage.jsp" %>
	<div class="content user-content">
	<p class="content-title">회원가입</p><br><br>
		<table class="join-table">
			<tr>
				<td class="columnsize-title">이메일<br></td>
				<td class="columnsize"><input type="text" placeholder=" 이메일을 입력해주세요"
					class="user-input join-input-short">&nbsp;
				<button class="minibtn">중복 확인</button></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="text" placeholder=" 비밀번호 입력해주세요"
					class="user-input join-input"></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" placeholder=" 이름을 입력해주세요"
					class="user-input join-input"></td>
			</tr>
			<tr>
				<td>닉네임</td>
				<td><input type="text" placeholder=" 닉네임을 입력해주세요"
					class="user-input join-input"></td>
			</tr>
			<tr>
				<td>휴대전화번호</td>
				<td><input type="text" placeholder=" 휴대전화번호를 입력해주세요"
					class="user-input join-input"></td>
			</tr>
			<tr>
				<td>인증번호</td>
				<td><input type="text" placeholder=" 인증번호를 입력해주세요"
					class="user-input join-input-short">&nbsp;
					<button class="minibtn">인증번호 요청</button></td>
			</tr>
			<tr>
				<td>생년월일</td>
				<td><input id="justDate" type="text" placeholder=" 날짜를 선택해주세요" class="user-input join-input"></td>
			</tr>
			<tr>
				<td>성별</td>
				<td><input type="radio" name="gender" value="male">남&nbsp;&nbsp;<input
					type="radio" name="gender" value="female">여</td>
			</tr>
			<tr>
				<td>주소</td>
				<td><input type="text" id="postcode" placeholder=" 우편번호" class="user-input join-input-short">&nbsp;
				<button onclick="daumPostcode()" class="minibtn">주소 검색</button>
				<input type="text" id="address" placeholder=" 주소" class="user-input join-input">
	</td>
			</tr>
		</table>
		<br><br>
		<div>
			<input type="submit" value="가입하기" class="bigbtn"
				onclick="location.href='login.jsp'">
		</div>
	</div>
	<br><br><br>
</body>
<script>
	flatpickr("#justDate", {
		"locale" : "ko",
		"dateFormat" : "Y-m-d",
	});
</script>
</html>