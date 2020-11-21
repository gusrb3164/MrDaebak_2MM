<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%request.setCharacterEncoding("UTF-8"); %>
<head>
		<meta charset="UTF-8">
		<title>Mr.Daebak</title>
		<link rel="stylesheet" type="text/css" href="/MrDaebak_2MM/layout/layout.css?aftedrd">
		<script type ="text/javascript" src = "script.js?sd"></script>
</head>
<body>

<jsp:include page = "/layout/header.jsp">
		<jsp:param name="title" value="주문하기"/>
	</jsp:include>
<div class="container">
	<div id="order">
		<div id="dinners">
			<div align="center" class="text">Dinner</div>
			<div class="sub-container">
				<p id="dinner-info">원하는 디너를 선택하세요.</p>
				<div>
					<input type="radio" name="dinner-radio" value="french" onclick="click_dinner(this.value)">프렌치
					<input type="radio" name="dinner-radio" value="english" onclick="click_dinner(this.value)">잉글리시
					<input type="radio" name="dinner-radio" value="champagne" onclick="click_dinner(this.value)">샴페인
				</div>
			</div>
		</div>
		<div id="styles">
			<div align="center" class="text">Styles</div>
			<div class="sub-container">
				<p id="style-info">원하는 스타일을 선택하세요. </p>
				<div>
					<input type="radio" class="valentine" name="style-radio" value="valentine" onclick="click_style(this.value)"><label class="valentine" for="valentine">발렌타인</label>
					<input type="radio" class="simple" name="style-radio" value="simple"  onclick="click_style(this.value)"><label class="simple" for="simple">심플</label>
					<input type="radio" name="style-radio" value="grand" onclick="click_style(this.value)">그랜드
					<input type="radio" name="style-radio" value="deluxe" onclick="click_style(this.value)">딜럭스
				</div>
			</div>
		</div>
		<div id="details">
			<div align="center" class="text">Details</div>
			<div id="detail-box">
				<div class="detail">
					<div class="detail-name">와인</div>
					<input class="num-box" type="number" value="0" min="0" />
				</div>
				<div class="detail">
					<div class="detail-name">와인</div>
					<input class="num-box" type="number" value="0" min="0" />
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page = "/layout/footer.jsp"/>
</body>