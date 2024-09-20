<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>예약상세정보</title>
<link href="${pageContext.request.contextPath}/css/my/mycommon.css" rel="stylesheet" type="text/css">
</head>
<body>
	<%@ include file="../mainmypage.jsp" %>
<div id="reservationDetail" class="mypage-content">
	<div class="mytitle">
		<p>예약 상세 정보</p>
	</div>
  <div class="pet-selection">
    <h3>반려동물</h3>
    <ul>
      <li>
<%--       <input type="radio" name="pet_id" value="${reserv.pet_id}" disabled>  --%>
		<c:choose>			
			<c:when test="${reserv.pet_picture eq null}">
			<img src="upload/pet/petdefault.png" style="width:50px;"></c:when>
			<c:otherwise><img src="image?file=${reserv.pet_picture}&type=pet" style="width:50px;"></c:otherwise>
		</c:choose>
		${reserv.pet_name}</li>
    </ul>
  </div>

  <div class="reserve-item">
    <h3>예약 내용</h3>
    <input type="checkbox" 
               <c:choose>
                   <c:when test="${reserv_content eq '진료'}">checked</c:when>
                   <c:otherwise>unchecked</c:otherwise>
               </c:choose> disabled> 진료
        <input type="checkbox" 
               <c:choose>
                   <c:when test="${reserv_content eq '상담'}">checked</c:when>
                   <c:otherwise>unchecked</c:otherwise>
               </c:choose> disabled> 상담
        <input type="checkbox" 
               <c:choose>
                   <c:when test="${reserv_content eq '미용'}">checked</c:when>
                   <c:otherwise>unchecked</c:otherwise>
               </c:choose> disabled> 미용
        <input type="checkbox" 
               <c:choose>
                   <c:when test="${reserv_content ne '진료' && reserv_content ne '상담' && reserv_content ne '미용'}">checked</c:when>
                   <c:otherwise>unchecked</c:otherwise>
               </c:choose> disabled> 기타
        <input type="text" value="${reserv_content}" readonly />
  </div>

  <div class="reserve-date">
    <p>병원이름: ${reserv.h_name}</p>
    <label for="reserv_date">날짜</label>
    <input type="date" id="reserv_date" value="${reserv.reserv_date}" readonly>
    <label for="reserv_time">시간</label>
    <input type="time" id="reserv_time" value="${reserv.reserv_time}" readonly>
  </div>

  <div class="guardian-info">
    <h3>보호자 정보</h3>
    <label for="name">이름</label>
    <input type="text" id="name" value="${user.name }" readonly>
    <label for="phone">휴대폰</label>
    <input type="text" id="phone" value="${user.phone }" readonly>
    <label for="email">이메일</label>
    <input type="text" id="email" value="${user.email }" readonly>
  </div>
</div>

</body>
</html>