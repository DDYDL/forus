<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="http://code.jquery.com//jquery-latest.min.js"></script>
<link href="${pageContext.request.contextPath}/css/common/header.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/common/font.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/common/body.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/common/content.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/common/bigbutton.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/common/minibutton.css" rel="stylesheet" type="text/css">
</head>
<body>
    <div class="header">
    	<div class="logo_box"><img src="${pageContext.request.contextPath}/img/logo2.png" title="logo"/></div>
        <div class="menu_box">
            <a href="${pageContext.request.contextPath}/reserv/hospitallist.jsp">병원예약</a>&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="${pageContext.request.contextPath}/recruit/recruitlist.jsp">Help Us</a>&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="${pageContext.request.contextPath}/my/myafterreserv.jsp">마이페이지</a>&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="${pageContext.request.contextPath}/hmy/hmytodaysresrvation.jsp">마이병원</a>
        </div>
        <div class="login">
	        <c:choose>
				<c:when test="${user eq null}">
			        <a href="login">로그인</a>
        &nbsp;&nbsp;<a href="${pageContext.request.contextPath}/join">회원가입</a>
				</c:when>
				<c:otherwise>
					<b>[${user.nickname eq null ? user.name:user.nickname } 님]</b>
					<a href="logout" class="logout">로그아웃</a>
				</c:otherwise>
			</c:choose>
        </div>
    </div>
    <hr>
    
    <script>
	    $('a').click(function() {
	        toggleClass(".active-color");
	    });
    </script>
</body>
</html>