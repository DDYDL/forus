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
<style>
.my {
   font-weight: bold;
   color: rgba(105, 233, 46);
}
</style>
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

   <form action="myProfileModify" method="post"
      enctype="multipart/form-data">
      <input type="hidden" name="id" value="${user.id}">
      <div class="mypage-content">
         <div>
            <h3>내 프로필</h3>
         </div>
         <div>
            <ul>
               <li>프로필 사진</li>
               <li><img src="image?file=${user.picture eq null? 'default.png': user.picture}&type=user&id=${user_id}" class="img-icon"
                  alt="이미지 선택" id="preview" width="100px"
                  onclick="document.getElementById('file').click();"> <input
                  type="file" name="file" id="file" accept="image/*"
                  onchange="readURL(this);" style="display: none"></li>

               <li class="title">이름</li>
               <br>
               <br>
               <li><input type="text" class="underline" name="name"
                  value=${user.name }></li>

               <li class="title">닉네임</li>
               <br>
               <br>
               <li><input type="text" class="underline" name="nickname"
                  value=${user.nickname }></li>

               <li class="title">이메일</li>
               <br>
               <br>
               <li><input type="text" class="underline" name="email"
                  readonly="readonly" value=${user.email }></li>

               <li class="title">비밀번호</li>
               <br>
               <br>
               <li><input type="password" class="underlinesmall"
                  name="password" value=${user.password }>
                  <button>변경하기</button></li>

               <li class="title">휴대전화번호</li>
               <br>
               <br>
               <li><input type="tel" class="underlinesmall" name="phone"
                  value=${user.phone }>
                  <button>변경하기</button></li>

               <li class="title">주소</li>
               <br>
               <br>
               <li><input class="tdinput" type="address"
                  id="sample6_postcode" placeholder="우편번호" name="h_address">

                  <button class="btn" onclick="sample6_execDaumPostcode()">주소찾기</button></li>
               <li><input class="tdinput" type="address" id="sample6_address"
                  placeholder="주소" name="h_address"> &nbsp; &nbsp;<input
                  class="tdinput" type="address" id="sample6_detailAddress"
                  placeholder="상세주소"></li>


               <script
                  src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js">
                  
               </script>
               <!-- 다음 줄 script 닫는걸 추천 -->
               <script>
                  function sample6_execDaumPostcode() {
                     new daum.Postcode(
                           {
                              oncomplete : function(data) {

                                 var addr = ''; // 주소 변수
                                 var extraAddr = ''; // 참고항목 변수

                                 if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                                    addr = data.roadAddress;
                                 } else { // 사용자가 지번 주소를 선택했을 경우(J)
                                    addr = data.jibunAddress;
                                 }

                                 if (data.userSelectedType === 'R') {
                                    if (data.bname !== ''
                                          && /[동|로|가]$/g
                                                .test(data.bname)) {
                                       extraAddr += data.bname;
                                    }
                                    if (data.buildingName !== ''
                                          && data.apartment === 'Y') {
                                       extraAddr += (extraAddr !== '' ? ', '
                                             + data.buildingName
                                             : data.buildingName);
                                    }
                                    if (extraAddr !== '') {
                                       extraAddr = ' ('
                                             + extraAddr + ')';
                                    }
                                    document
                                          .getElementById("sample6_detailAddress").value = extraAddr;
                                 } else {
                                    document
                                          .getElementById("sample6_detailAddress").value = '';
                                 }

                                 // 우편번호와 주소 정보를 해당 필드에 넣는다.
                                 document
                                       .getElementById('sample6_postcode').value = data.zonecode;
                                 document
                                       .getElementById("sample6_address").value = addr;
                                 // 커서를 상세주소 필드로 이동한다.
                                 document.getElementById(
                                       "sample6_detailAddress")
                                       .focus();
                              }
                           }).open();
                  }
               </script>
               <br>
               <br>
               <li class="title">생년월일</li>
               <br>
               <br>
               <li><input type="date" name="birthday" value=${user.birthday }></li>

               <%--                <li class="title">성별</li>
               <br>
               <br>
               <li><input type="radio" name="gender" value="M"
                  <%if ("M".equals("M")) {%> checked <%}%>> 남 <!-- 테스트 후 될 시 checkbox 테스트-->
                  <input type="radio" name="gender" value="F"
                  <%if ("F".equals("F")) {%> checked <%}%>> 여</li>  단축키 ctrl+shift+/ --%>
            </ul>

            <div class="find-btn">
               <input type="submit" title="Button push blue/green"
                  class=" btn btnPush btnBlueGreen find-btn1" value="수정완료">

            </div>
         </div>

      </div>
   </form>
</body>
</html>