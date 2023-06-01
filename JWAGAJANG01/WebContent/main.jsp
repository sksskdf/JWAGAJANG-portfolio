<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/reset.css">
    <link rel="stylesheet" href="css/header_footer.css">
    <link rel="shortcut icon" href="img/favicon/favicon.ico">
    <link rel="stylesheet" href="css/swiper-bundle.css">
    <link rel="stylesheet" href="css/index.css">
    <title>좌가장</title>
</head>
<body>
<div id="pagewrap">
<jsp:include page="H&F/header.jsp"/>
	<section>
    <!--슬라이더 영역-->
    <div class="swiper">
      <div class="swiper-wrapper">
        <div class="swiper-slide"><img src="img/slider1.jpg"></div>
        <div class="swiper-slide"><img src="img/slider2.jpg"></div>
        <div class="swiper-slide"><img src="img/slider3.jpg"></div>
      </div>
      <div class="swiper-button-prev"></div>
      <div class="swiper-button-next"></div>
      <div class="swiper-pagination"></div>
   </div>

   <div id="contentwrap">
    <div class="bestTitle">
      <h1>BEST</h1><p><a href="#"> 더보기 > </a></p>
    </div>
    <div class="bestContents">
     <c:forEach var="best" items="${bestList }">
     <a href="goods.do?md_code=${best.md_code}">
      	<div class="bitem"><img src="img/mdimg/${best.img_main }">
      <p><span class="title">${best.md_name }</span><br>
        <span class="salerate">${best.md_dc }%</span> <span class="saleprice"><fmt:formatNumber pattern="#,##0" value="${fn:substringBefore(best.md_price-(best.md_price*best.md_dc/100), '.')}"/>원</span><br>
        <span class="price"><fmt:formatNumber pattern="#,##0" value="${best.md_price}"/>원</span></p>
      </div>
      </a>
      </c:forEach>
    </div>
      <div class="newTitle">
        <h1>NEW</h1><p><a href="#"> 더보기 > </a></p>
      </div>
      <div class="newContents">
      <c:forEach var="newList" items="${newList}">
      <a href="goods.do?md_code=${newList.md_code}">
      	<div class="nitem"><img src="img/mdimg/${newList.img_main}">
      <p><span class="title">${newList.md_name }</span><br>
        <span class="salerate">${newList.md_dc }%</span> <span class="saleprice"><fmt:formatNumber pattern="#,##0" value="${fn:substringBefore(newList.md_price-(newList.md_price*newList.md_dc/100), '.')}"/>원</span><br>
        <span class="price"><fmt:formatNumber pattern="#,##0" value="${newList.md_price }"/>원</span></p>
      </div>
      </a>
      </c:forEach>
    </div> 
  </div>
</section>
<jsp:include page="H&F/footer.jsp"/>
</div>
    <script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
    <script src="js/index.js"></script>
</body>
</html>