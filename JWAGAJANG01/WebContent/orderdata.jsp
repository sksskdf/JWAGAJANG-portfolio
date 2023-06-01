<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<section>
	<div class="pagenav">홈<span class="navarrow"></span>장바구니<span class="navarrow"></span>주문서 작성</div>
	<div id="orderwrap">
	<h1>주문서 작성 / 결제</h1>
   	<div class="cartnav">1. 장바구니<span class="navarrow"></span>
   	<span class="nowpage">2. 주문서 작성 / 결제</span><span class="navarrow"></span>
   	3. 주문완료</div>
   	<table class="orderlist">
   	<tr>
   		<td>주문상품</td>
   		<td>${param.mdname}</td>
  	</tr>
 		</table>
  	<form action="buy.do" class="orderform" name="orderfrm" method="post">
  	<input type="hidden" value="${param.md_code}" name="md_code">
  	<table class="ordertable">
  	
  		<tr>
  			<td>배송지 정보</td>
  			<td style="text-align: right;"><input type="checkbox" id="checkbox" name="checkbox" checked/>주문자 정보와 동일</td>
  		</tr>
  		<tr>
  			<td>수령인</td>
  			<td><input type="text" name="name" id="name" value="${param.ordername}"/></td>
  		</tr>
  		<tr>
  			<td>휴대폰번호</td>
  			<td><input type="text" name="phone" placeholder="'-' 포함 입력" value="${param.mobile}" /></td>
  		</tr>
  		<tr>
  			<td >주소</td>
  			<td><input type="text" id="postnum" name="postnum" placeholder="우편번호" readonly="readonly"/><input type="button" value="우편번호 찾기" class="findpostnum" onclick="execDaumPostcode()"/> <br />
   			<input type="text" id="address" name="address" placeholder="주소" readonly="readonly" value="${param.address}" /><br />
   			<input type="text" id="addDetail" name="addDetail" placeholder="상세주소" value="${param.address2}" /></td>
   		</tr>
   		<tr>
   			<td>배송요청사항</td>
   			<td><input type="text" name="request" /></td>
   		</tr>
   	</table>
   	<input type="submit" value="결제하기" class="paybutton" />
   	</form>
   </div>
</section>