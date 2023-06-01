<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% if(session.getAttribute("id") != null) {
		response.sendRedirect("/index.do");
	}%>



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
  <!--  본문 -->
  <div class="container-fluid bg d-flex mt-3">
  	<div class="container d-flex justify-content-center" id="bg-white">
      <form method="post" action="/login.do">
        <h5 class="lfb" style="text-align: center; margin-top: 34px;">로그인</h5>
        <input class="lib d-block lis mt-4 py-3 ps-3 cfs" name="id" type="text" placeholder="아이디" autocomplete="off">
        <input class="lib d-block lis py-3 ps-3 mt-2 cfs" name="pwd" type="password" placeholder="비밀번호">
        <input class="lib d-block btn btn-login cfs" style="margin-top: 27px;" type="submit" value="LOGIN">
        <a class="lib d-block btn mt-2 jbs cfs d-flex justify-content-center align-items-center" href="/join.do">회원가입</a>
      </form>
  	</div>
  </div>

  <!-- 푸터 -->
<jsp:include page="../H&F/footer.jsp"/>
  <script src="../js/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
  <script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
  <script src="../js/index.js"></script>

</body>
</html>