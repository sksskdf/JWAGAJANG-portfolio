<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" href="img/favicon/favicon.ico">
<link rel="stylesheet" href="css/reset.css">
<link rel="stylesheet" href="css/header_footer.css">
<link rel="stylesheet" href="css/complete.css">
<title>주문완료</title>
</head>
<body>
	<div id="pagewrap">
	<jsp:include page="H&F/header.jsp"/>
	<section>
	<div class="pagenav">홈<span class="navarrow"></span>장바구니<span class="navarrow"></span>주문완료</div>
	<div id="completewrap">
	<h1>주문완료</h1>
    	<div class="compnav">1. 장바구니<span class="navarrow"></span>
    	2. 주문서 작성 / 결제<span class="navarrow"></span>
    	<span class="nowpage">3. 주문완료</span></div>
		<div class="completesec">
			<h1>주문이 완료되었습니다.</h1><br><br>
			<h2>마이페이지 - 주문내역에서 주문하신 내역을 확인 하실 수 있습니다.</h2>
			<input type="button" class="chkmyorder" value="홈으로" onclick="location.href='main.do'"/>
		</div>
	</div>
	</section>
	<jsp:include page="H&F/footer.jsp"/>
	</div>
	
</body>
</html>