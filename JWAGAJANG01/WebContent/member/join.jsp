<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
  <div class="container-fluid bg d-flex mt-3">
  	<div class="container d-flex justify-content-center" id="bg-white-join">
      
      <form id="joinForm" method="post" action="/join.do">
        <h5 class="lfb" style="text-align: center; margin-top: 38px; font-size: 20px/23px;">회원가입</h5>
        <div class="container row d-flex mt-5" style="margin-top: 13px;">
          <div class="col-md-3 d-flex align-items-center">
            <label class="join-label-font">아이디</label>
          </div>
          <div class="col-md-7 d-flex align-items-center">
            <input class="join-label-font lib lis py-3 ps-3 cfs" name="id" id="id" type="text" placeholder="아이디를 입력해주세요." autocomplete="off">
          </div>
          <div class="col-md-2 d-flex align-items-center">
            <input class="join-button-font btn jbs cfs" type="button" id="duplicateCheck" value="중복확인">
          </div>
        </div>
        <div class="container row d-flex justify-content-center align-items-center">
        <p id="idcheck" style="margin-top:10px;"></p>
        </div>
        <div class="container row d-flex">
          <div class="col-md-3 d-flex align-items-center">
            <label class="join-label-font">비밀번호</label>
          </div>
          <div class="col-md-7 d-flex align-items-center">
            <input class="join-label-font lib lis py-3 ps-3 cfs" name="pwd" id="pwd" type="password" placeholder="비밀번호를 입력해주세요.">
          </div>
        </div>
        <p id="pwdcheck" style="margin-top:10px;"></p>
        <div class="container row d-flex">
          <div class="col-md-3 d-flex align-items-center">
            <label class="join-label-font">비밀번호 확인</label>
          </div>
          <div class="col-md-7 d-flex align-items-center">
            <input class="join-label-font lib lis py-3 ps-3 cfs" name="pwd2" id="pwd2" type="password" placeholder="비밀번호를 확인해주세요.">
          </div>
        </div>
        <p id="pwd2check" style="margin-top:10px;"></p>
        <div class="container row d-flex">
          <div class="col-md-3 d-flex align-items-center">
            <label class="join-label-font">이메일</label>
          </div>
          <div class="col-md-7 d-flex align-items-center">
            <input class="join-label-font lib lis py-3 ps-3 cfs" name="email" id="email" type="email" placeholder="이메일을 입력하세요."autocomplete="off">
          </div>
        </div>
        <div class="container row d-flex">
          <div class="col-md-3 d-flex align-items-center">
            <label class="join-label-font">연락처</label>
          </div>
          <div class="col-md-7 d-flex align-items-center">
            <input class="join-label-font lib lis py-3 ps-3 cfs" name="mobile" id="mobile" type="text" placeholder="연락처를 입력하세요."autocomplete="off">
          </div>
        </div>
        <div class="container row d-flex">
          <div class="col-md-3 d-flex align-items-center">
            <label class="join-label-font">주소</label>
          </div>
          <div class="col-md-7 d-flex align-items-center">
            <input class="join-label-font lib lis py-3 ps-3 cfs" type="text" id="postnum" name="postnum" placeholder="우편번호" readonly="readonly" autocomplete="off" />
          </div>
          <div class="col-md-2 d-flex align-items-center">
            <input class="join-button-font btn jbs cfs findpostnum" type="button" value="우편번호 찾기" onclick="execDaumPostcode()">
          </div>
        </div>
        <div class="container row d-flex">
          <div class="col-md-3 d-flex align-items-center">
          </div>
          <div class="col-md-7 d-flex align-items-center">
            <input class="join-label-font lib lis py-3 ps-3 cfs" type="text" id="address" name="address" placeholder="주소" readonly="readonly" autocomplete="off" />
          </div>
        </div>
        <div class="container row d-flex">
          <div class="col-md-3 d-flex align-items-center">
          </div>
          <div class="col-md-7 d-flex align-items-center">
            <input class="join-label-font lib lis py-3 ps-3 cfs" type="text" type="text" id="addDetail" name="addDetail" placeholder="상세주소" autocomplete="off" />
          </div>
        </div>

        <div class="row d-flex justify-content-center">
          <input class="lib btn btn-login cfs" style="margin-top: 27px;" id="submit" type="submit" value="회원가입" >
        </div>
      </form>
  	</div>
  </div>

  <!-- 푸터 -->
<jsp:include page="../H&F/footer.jsp"/>
  <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
  <script src="../js/jquery.js"></script>
  <script src="../js/join.js"></script>
  
</body>
</html>