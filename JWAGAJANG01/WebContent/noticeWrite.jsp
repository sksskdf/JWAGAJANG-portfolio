<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon" href="img/favicon/favicon.ico">
<link rel="stylesheet" href="css/reset.css">
<link rel="stylesheet" href="css/board.css">
<link rel="stylesheet" href="css/header_footer.css">
<title>좌가장 : 공지사항 작성하기</title>
</head>
<body>
  <jsp:include page="H&F/header.jsp"/>
	<section>
		<div class="pagenav">
			<a href="index.jsp">홈</a>
			<span class="navarrow"></span>
			<a href="noticeList.do?&p=1">게시판</a>
			<span class="navarrow"></span>
			<a href="noticeList.do?&p=1">공지사항</a>
		</div>
		<div class="notice">
			<h1>좌가장 : 공지사항 작성하기</h1>
			<form name="frm" method="post" action="noticeWrite.do">
			<table class="brdWritebox">
				<tr>
					<th width="150px">구분</th>
					<th>
						<select class="sortinput" name="notice_label">
							<option>선택해주세요.</option>
							<option>공지</option>
							<option>이벤트</option>
						</select>
					</th>
				</tr>
				<tr>
					<th width="150px">제목</th>
					<th><input class="titleinput" type="text" name="notice_title"></th>
				</tr>
				<tr>
					<th id="textarea">본문</th>
					<th><textarea style="resize: none;" name="notice_content"></textarea></th>
				</tr>
			</table>
			
			<div class="noticeWritebtn">
				<input type="reset" value="다시쓰기" name="reset" class="resetbtn">
				<input type="submit" value="등록" name="send" class="sendbtn" onclick="return noticeCheck()">
				<input type="button" value="목록" name="noticelist" class="noticelistbtn" onclick="location.href='noticeList.do?&p=${param.p}'">
				<!-- type을 submit으로하면 무조건 제출해버림. 목록누르면 등록되니까 타입을 버튼으로! -->
			</div>
			</form>
		</div>
	</section>
	<!-- 푸터영역 -->
	<jsp:include page="H&F/footer.html" />
	<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
	<script src="js/jquery.min.js"></script>
	<script src="js/index.js"></script>
	<script src="js/board.js"></script>
</body>
</html>