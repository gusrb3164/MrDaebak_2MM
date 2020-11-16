<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%request.setCharacterEncoding("UTF-8"); %>
<head>
		<meta charset="UTF-8">
		<title>Mr.Daebak</title>
		<link rel="stylesheet" type="text/css" href="/MrDaebak_2MM/layout/layout.css?ssss">
</head>
<body>


<c:if test = "${!empty requestScope.altmsg}">
		<script>
			alert( "${requestScope.altmsg}");
		</script>
</c:if>

<jsp:include page = "/layout/header.jsp">
		<jsp:param name="title" value="로그인 결과!"/>
	</jsp:include>
<div class="container">
	<div class = "main-menu">
		<button class="menu" type="button" onclick="location.href='/MrDaebak_2MM/Member/loginView.jsp'">로그인</button>
		<button class="menu" type="button" onclick="location.href='/MrDaebak_2MM/Member/signUpView.jsp'">회원가입</button>
		<button class="menu" type="button" onclick="location.href='/MrDaebak_2MM/Order/Order.jsp'">비회원 주문</button>
	</div>
</div>
<jsp:include page = "/layout/footer.jsp"/>
</body>