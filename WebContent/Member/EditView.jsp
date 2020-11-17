<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8"); %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<head>
		<meta charset="UTF-8">
		<title>Mr.Daebak</title>
		<link rel="stylesheet" type="text/css" href="/MrDaebak_2MM/layout/layout.css?ssss">
</head>
<body>
<jsp:include page = "/layout/header.jsp">
	<jsp:param name="title" value="나의 페이지"/>
</jsp:include>
<div class="container">
<c:set var = "dto" value = "${requestScope.member}" scope = "page"/>
<c:remove var ="dto" scope = "request"/>
	<c:if test = "${dto == null }">
		<script>
			alert("오류가 발생하였습니다");
			history.back();
		</script>
	</c:if>
	<div>
	<form action = "Update.myprofile" method = "post">
		<input type = "hidden" name = "user_no" value = "${dto.no}">
		<table border = "0">
			<tr>
				<th>ID</th>
				<td>${dto.id}</td>
			</tr>
			
			<tr>
				<th rowspan = "2">Password</th>
				<td> <input class="input" type = "password" name = "user_password" value = "${dto.pw }" required></td>
			</tr>
			
			<tr>
				<td> <input class="input" type = "password" name = "user_repassword" value = "${dto.pw }" required></td>
			</tr>
			
			<tr>
				<th>Name</th>
				<td><input class="input" type = "text" name = "user_name" value = "${dto.name }" required></td>
			</tr>
			
			<tr>
				<th>Mobile</th>
				<td><input class="input" type = "text" name = "user_mobile" value = "${dto.mobile }" required></td>
			</tr>
			<tr>
				<th>Address</th>
				<td><input class="input" type = "text" name = "user_address" value = "${dto.address }" required></td>
			</tr>
			
			<c:if test = "${dto.id != sessionScope.Id}"> 
				<tr>
					<th>이사람은 직원입니다.</th>
				</tr>	
			</c:if>	
		</table>
		<button class="btn" type = "submit" >수정</button>
		</form>
		
		
		<form action = "Delete.myprofile" style="margin-top:3px; margin-bottom:3px;"  method = "post">  
			<input type = "hidden" name = "user_no" value = "${dto.no}">
			<button class="btn" type = "submit" >삭제</button> 
		</form>
		
		<form action = "Check.myprofile" method = "post"> <button class="btn" type = "submit" >확인</button> </form>
	</div>
	</div>
<jsp:include page = "/layout/footer.jsp"/>
</body>