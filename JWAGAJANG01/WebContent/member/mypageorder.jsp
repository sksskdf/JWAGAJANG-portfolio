<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="shortcut icon" href="img/favicon/favicon.ico">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="../css/reset.css">
  <link rel="stylesheet" href="../css/custom.css">
  <link rel="stylesheet" href="../css/header_footer.css">

  <title>좌가장</title>
</head>
<body>
  <!-- 헤더 -->
  <jsp:include page="../H&F/header.jsp"/>
  <!-- 본문 -->
  <div class="container-fluid d-flex mt-5 mb-5 mypagesec justify-content-center">
      <div class="row d-flex justify-content-center">
      <div class="col-3 sidebar d-flex justify-content-center">
      	<ul class="nav d-flex flex-column align-content-center justify-content-center">
      		<li class="nav-item">
      			<a class="nav-link nf" href="mypage.do">회원 정보 수정</a>
      		</li>
      		<li class="nav-item">
      			<a class="nav-link mpa nf" href="mypageorder.do?p=1&id='"+${sessionScope.id}+"'">주문내역</a>
      		</li>
      		<li class="nav-item">
      			<a class="nav-link nf" href="favlist.do?p=1&id='"+${sessionScope.id}+"'">찜목록</a>
      		</li>
      	</ul>
      </div>
      <div class="col-9" style="margin-left:40px; width:953px;">
        <h5 class="mph" style="margin-top: 38px;">주문내역</h5>
      		<table class="table table-borderless" style="margin-top:40px;">
			  <thead class="table-secondary  " style="height:40px;">
			    <tr class="text-center">
			      <th class="col-1 va"></th>
			      <th class="col-5 va">상품명</th>
			      <th class="col-2 va">주문날짜</th>
			      <th class="col-2 va">구매금액</th>
			      <th class="col-3 va">리뷰</th>
			    </tr>
			  </thead>
			  <tbody>
			  <c:forEach var="o" items="${orderlist}" begin="0" end="4">
			    <tr class="text-center">
			      <td><img class="orderPic"alt="" src="img/mdimg/${o.mdpic}"> </td>
			      <td class="thf va">${o.mdname}</td>
			      <td class="va">${o.mdorderdate}</td>
			      <td class="va">${o.mdprice}</td>
			      <td class="va"><button class="btn btn-success">리뷰쓰기</button></td>
			    </tr>
			    </c:forEach>
			  </tbody>
			</table>
			<ul class="d-flex justify-content-center align-content-center">
				<c:set var="page" value="${(param.p == null)?1:param.p}" />
				<c:set var="startNum" value="${page-(page-1)%5}" />
				<c:set var="lastNum" value="${fn:substringBefore(Math.ceil(count/5),'.')}" />
				<li style="margin-top:4px">
					<c:if test="${startNum>1}">
						<a class="m0 btn mt-1" href="?p=${startNum-1}"><img src="../img/left.svg"></a>
					</c:if>
					<c:if test="${startNum<=1}">
						<span class="m0 btn mt-1" onclick="alert('이전 페이지가 없습니다.');"><img src="../img/left.svg"></span>
					</c:if>
				</li>
				<!--  p값으로 오는 번호의 시작번호를 알아내면 됨 -->
				<!--  ex) p값이 19이다 -> 19가 포함되는 16부터 20까지 숫자를 표시해야하니까 16이라는 시작번호를 알아내면 해당범위페이지 숫자를 표현할 수 있음 -->
				<c:forEach var="i" begin="0" end="4">
					<c:if test="${startNum+i <= lastNum}">				
						<li class="align-self-center">
							<a class="pager ${(param.p==(startNum+i))?'highlight link-success':''} " href="?p=${startNum+i}">${startNum+i}</a>
						</li>
					</c:if>
				</c:forEach>
				<li style="margin-top:4px">
					<c:if test="${startNum+5<=lastNum}">
						<a class="m0 btn mt-1" href="?p=${startNum+5}"><img src="../img/right.svg"></a>
					</c:if>
					<c:if test="${startNum+5>=lastNum}">
						<span class="m0 btn mt-1" onclick="alert('다음 페이지가 없습니다.');"><img src="../img/right.svg"></span>
					</c:if>
				</li>
			</ul>
			
      </div>
  	</div>
  </div>

  <!-- 푸터 -->
<jsp:include page="../H&F/footer.jsp"/>
  
  <script src="../js/jquery.js"></script>
  <script src="../js/index.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
  <script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
  <script src="../js/join.js"></script>
  
</body>
</html>