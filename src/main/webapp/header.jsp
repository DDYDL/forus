<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
        <div class="login"><a href="login.jsp">로그인</a>&nbsp;&nbsp;<a href="join.jsp">회원가입</a></div>
    </div>
    <hr>
    
    <script>
	    $('a').click(function() {
	        toggleClass(".active-color");
	    });
    </script>
</body>
</html>