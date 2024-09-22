<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
    <h3>반려동물</h3>
  <div class="pet-selection box">
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

    <h3>예약 내용</h3>
  <div class="reserve-item box">
      	<table class="reserv_table">
    		<tr>
    			<td class="user_td1">병원 이름</td>
    			<td class="user_td2">${reserv.h_name}</td>
    		</tr>
    		<tr>
    			<td class="user_td1"><label for="reserv_date">날짜</label></td>
    			<td class="user_td2">${reserv.reserv_date}<br>
</td>
    		</tr>
    		<tr>
    			<td class="user_td1"><label for="reserv_time">시간</label></td>
    			<td class="user_td2">${reserv.reserv_time}"<br>
</td>
    		</tr>
    		<tr>
    			<td class="user_td1" colspan="2">
    			<input type="radio" class="radio-hidden" id="consult" name="reserv_content"
               <c:choose>
                   <c:when test="${reserv.reserv_content eq '진료'}">checked</c:when>
                   <c:otherwise>unchecked</c:otherwise>
               </c:choose> disabled>
               <label for="consult" class="label-btnstyle">진료</label>
        		<input type="radio" class="radio-hidden" id="counseling" name="reserv_content"
               <c:choose>
                   <c:when test="${reserv.reserv_content eq '상담'}">checked</c:when>
                   <c:otherwise>unchecked</c:otherwise>
               </c:choose> disabled>
               <label for="counseling" class="label-btnstyle">상담</label>
        	<input type="radio" class="radio-hidden" id="beauty" name="reserv_content"
               <c:choose>
                   <c:when test="${reserv.reserv_content eq '미용'}">checked</c:when>
                   <c:otherwise>unchecked</c:otherwise>
               </c:choose> disabled>
               <label for="beauty" class="label-btnstyle">미용</label>
        	<input type="radio" class="radio-hidden" id="etc" name="reserv_content"
               <c:choose>
                   <c:when test="${reserv.reserv_content ne '진료' && reserv.reserv_content ne '상담' && reserv.reserv_content ne '미용'}">checked</c:when>
                   <c:otherwise>unchecked</c:otherwise>
               </c:choose> disabled> 
               <label for="etc" class="label-btnstyle">기타</label>
               <c:if test="${reserv.reserv_content ne '진료' && reserv.reserv_content ne '상담' && reserv.reserv_content ne '미용'}"> - "${reserv.reserv_content}"</c:if>
  			</td>
    		</tr>
    	</table>
    </div>
    
    <h3>보호자 정보</h3>
  <div class="guardian-info box">
  <table class="reserv_table">
    <tr>
	    <td><label for="name">이름</label></td>
	    <td>${user.name }</td>
    </tr>
    <tr>
	    <td><label for="phone">휴대폰</label></td>
	    <td>${user.phone }"</td>
    </tr>
    <tr>
	    <td><label for="email">이메일</label></td>
	    <td>${user.email }</td>
  	</tr>
  	</table>
  </div>
</div>

</body>
</html>