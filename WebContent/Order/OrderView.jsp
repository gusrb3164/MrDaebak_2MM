<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%request.setCharacterEncoding("UTF-8"); %>
<head>
		<meta charset="UTF-8">
		<title>Mr.Daebak</title>
		<link rel="stylesheet" type="text/css" href="/MrDaebak_2MM/layout/layout.css?12s">
		<script type ="text/javascript" src = "/MrDaebak_2MM/Order/script.js?asfdsdgsr"></script>
</head>
<script>
var member={"id":${requestScope.member.id},"name":'${requestScope.member.name}',"mobile":'${requestScope.member.mobile}',"address":'${requestScope.member.address}'}
var menu={"info":[],"availableStyle":[],"available":[],"menuDetailListNo":[],"menuDetailListEa":[],"extraDetailListNo":[]};
var style={"name":[],"price":[],"info":[]};
<c:forEach var = "menu" items = "${ requestScope.menulist }" varStatus="status">
	menu.info.push('${menu.info}');
	menu.available.push('${menu.available}');
	var temp=[];
	var tempEa=[];
	<c:forEach var = "item" items = "${ menu.menuDetailList }" varStatus="status">
		temp.push('${item.stockNo}');
		tempEa.push('${item.ea}');
	</c:forEach>
	menu.menuDetailListNo.push(temp);
	menu.menuDetailListEa.push(tempEa);
	var temp2=[];
	<c:forEach var = "item" items = "${ menu.extraDetailList }" varStatus="status">
		temp2.push('${item.stockNo}');
	</c:forEach>
	menu.extraDetailListNo.push(temp2);
	menu.availableStyle.push(${menu.availableStyle});
</c:forEach>
<c:forEach var = "style" items = "${ requestScope.stylelist }" varStatus="status">
	style.name.push('${style.name}');
	style.info.push('${style.info}');
	style.price.push('${style.price}');
</c:forEach>
</script>
<body>

<c:if test = "${requestScope.altmsg != null}">
		<script>
			alert( "${requestScope.altmsg}");
		</script>
</c:if>

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
					<c:forEach var = "menu" items = "${ requestScope.menulist }" varStatus="status">
						<input type="radio" name="dinner-radio" value="${menu.no}" onclick="click_dinner(this.value)">${menu.name}
					</c:forEach>	
				</div>
			</div>
		</div>
		<div id="styles">
			<div align="center" class="text">Style</div>
			<div class="sub-container">
				<p id="style-info">원하는 스타일을 선택하세요. </p>
				<div id="style-check">
				</div>
			</div>
		</div>
		<div id="details">
			<div align="center" class="text">Detail</div>
			
			<div id="detail-box">
				<c:forEach var = "detailMenu" items = "${ requestScope.menulist[0].menuDetailList }" varStatus="status">
					<div class="detail">
						<div class="detail-name">${detailMenu.name}</div>
						<input class="num-box" name='${detailMenu.stockNo}' type="number" value="0" min="0" max="${detailMenu.ea}"/>
					</div>
				</c:forEach>
				<c:forEach var = "extraMenu" items = "${ requestScope.menulist[0].extraDetailList }" varStatus="status">
					<div class="detail">
						<div class="detail-name">${extraMenu.name}</div>
						<input class="num-box" id='${extraMenu.stockNo }' type="number" value="0" min="0" max="${extraMenu.ea}"/>
					</div>
				</c:forEach>
			</div>
		</div>
		<div id="buttons">
		<button class="btn" id="border-right" onclick="click_cart()">장바구니 담기</button>
		<button class="btn" onclick="click_order()">선택 완료</button>
		</div>
		<div id="texts">
		<p class="text-info">장바구니:</p>
		<p class="text-info" id='cart-num'></p>
		<p class="text-info">가격:</p>
		<p class="text-info" id='total-price'></p>
		</div>
		<div id="final-box">
			<div id="user-inputs">
				<p class="text-info">주문자 이름:</p>
				<input type = "text" class="input" id = "name" placeholder="${requestScope.member.name}">
				<p class="text-info">전화번호:</p>
				<input type = "text" class="input" id = "mobile" placeholder="${requestScope.member.mobile}">
				<p class="text-info">배달 주소:</p>
				<input type = "text" class="input" id = "address" placeholder="${requestScope.member.address}">
			</div>
				<button class="btn" id='order-btn' onclick="do_order()">주문 완료 </button>
			</div>
		</div>
	<!--  request로 넘어온 결과 확인용 -->		
	  <%-- <div>
		<c:forEach var = "menu" items = "${ requestScope.menulist }" varStatus="status">
						<p>${menu.name} ${menu.price} ${menu.info} </p>
					</c:forEach>
				
					<c:forEach var = "style" items = "${ requestScope.stylelist }" varStatus="status">
						<p>${style.name} ${style.price} ${style.info}</p>
					</c:forEach>
					
					세부항목
					<c:forEach var = "detailMenu" items = "${ requestScope.menulist[1].menuDetailList }" varStatus="status">
						<p>메뉴항목 : ${detailMenu.menuNo} ${detailMenu.name} ${detailMenu.ea} </p>
					</c:forEach>
					기타항목
					<c:forEach var = "extraMenu" items = "${ requestScope.menulist[1].extraDetailList }" varStatus="status">
						<p>기타항목 : ${extraMenu.menuNo} ${extraMenu.name} ${extraMenu.ea} </p>
		</c:forEach>
		
		<p> ${requestScope.member.id} ${requestScope.member.name} ${requestScope.member.mobile} ${requestScope.member.address} </p>
	</div>   --%>


</div>
<jsp:include page = "/layout/footer.jsp"/>		

</body>