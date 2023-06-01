<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" href="img/favicon/favicon.ico">
<link rel="stylesheet" href="css/reset.css">
<link rel="stylesheet" href="css/header_footer.css">
<link rel="stylesheet" href="css/cart.css" />
<title>장바구니</title>
</head>
<body>
	<div id="pagewrap">
		<jsp:include page="H&F/header.jsp" />
		<section>
			<div class="pagenav">
				홈<span class="navarrow"></span>장바구니
			</div>
			<div id="cartwrap">
				<h1>장바구니</h1>
				<div class="cartnav">
					<span class="nowpage">1. 장바구니</span><span class="navarrow"></span>
					2. 주문서 작성 / 결제<span class="navarrow"></span> 3. 주문완료
				</div>
				<!-- <form action="order.do" class="cartform" method="post"> -->
				 <div class="cartform">
					<table class="cartconts">
						<thead>
							<tr>
								<th width="8.3945435%"><input type="checkbox" id="allchk" checked/></th>
								<th width="49.162644%">상품명</th>
								<th width="11.962224%">구매수량</th>
								<th width="18.258132%">주문금액</th>
								<th width="11.962224%">선택</th>
							</tr>
						</thead>
						<tbody>
						<c:set var = "orderP" value = "${0}"/>
						<c:if test="${empty cartList}">
							<tr>
								<td colspan="5">장바구니가 비었습니다.</td>
							</tr>
						</c:if>
							<c:if test="${!empty cartList}">
							<c:forEach items="${cartList }" var="cart">
							<tr>
								<fmt:parseNumber var="saleP" integerOnly="true" value="${cart.md_price-(cart.md_price*cart.md_dc/100) }"/>
								<td><input type="checkbox" class="mdchk" name="mdchk" value="${saleP * cart.md_count}" 
								checked data-cartcode="${cart.md_code }"/>
								<input type="hidden" name="md_code" value="${cart.md_code }" /></td>
								<td class="md" style="text-align: left"><a href="goods.do?md_code=${cart.md_code }" target='_blank'>
								<img src="${cart.img_main }" /></a> <span class="mddesc"><a href="goods.do?md_code=${cart.md_code }" target='_blank'>${cart.md_name }</a></span></td>
								<td>
									<input type="number" min="1" max="99" value="${cart.md_count }" />
								</td>
								<td><span class="originprice"><fmt:formatNumber pattern="#,##0" value="${cart.md_price * cart.md_count}"/>원</span><br />
								<span class="saleprice"><fmt:formatNumber pattern="#,##0" value="${saleP * cart.md_count}"/>원</span></td>
								<td><input type="button" class="order_one colorbtn" value="바로구매" data-cartcode="${cart.md_code }" />
									<input type="button" class="delete_one normalbtn" value="삭제"
									style="margin-top: 5px" data-cartcode="${cart.md_code }"/></td>
							</tr>
							<c:set var = "orderP" value="${orderP + (saleP * cart.md_count) }"/>
							</c:forEach>
						</c:if>
						</tbody>
					</table>
					<div class="pricewrap">
						<div class="pricelist">
							<span class="pricedesc">주문 금액</span><br /> <span class="price"><span id="odrPrice">
							<fmt:formatNumber pattern="#,##0" value="${orderP}"/></span>원</span>
						</div>
						<div class="operator">+</div>
						<div class="pricelist">
							<span class="pricedesc">배송비</span><br/><span class="price"><span id="deliPrice">
							<c:if test="${empty cartList}">
							<c:set var="deliP" value="0"/><c:out value="${deliP}"/>
							</c:if>
							<c:if test="${!empty cartList}">
							<c:set var="deliP" value="2500"/><fmt:formatNumber pattern="#,##0" value="${deliP}"/>
							</c:if></span>원</span>
						</div>
						<div class="operator">=</div>
						<div class="pricelist">
							<span class="pricedesc">총 주문금액</span><br /> <span class="price"><span id="totalOdrPrice">
							<fmt:formatNumber pattern="#,##0" value="${orderP + deliP}"/></span>원</span>
						</div>
					</div>
					<div class="buttonsec">
						<div class="btnsecL">
							<input type="button" class="normalbtn" value="선택삭제" id="delete_select"/>
						</div>
						<div class="btnsecR">
							<input type="button" class="colorbtn" id="order_select" value="선택 상품 주문하기" /> 
							<input type="button" class="normalbtn" value="계속 쇼핑하기" onclick="location.href='javascript:history.go(-2)'" />
						</div>
					</div>
				<!--</form> -->
				 </div> 
			</div>
		</section>
		<jsp:include page="H&F/footer.jsp" />
	</div>
	<!-- 선택 상품 삭제 -->
	<script src="js/cart.js"></script>
</body>
</html>