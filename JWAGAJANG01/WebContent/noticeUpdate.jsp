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
<title>좌가장 : 공지사항 수정하기</title>
</head>
<body>
<!-- 헤더영역   -->
  <jsp:include page="H&F/header.jsp"/>
	<section>
		<div class="pagenav">
			<a href="index.jsp">홈</a>
			<span class="navarrow"></span>
			<a href="noticeList.do">게시판</a>
			<span class="navarrow"></span>
			<a href="noticeList.do">공지사항</a>
		</div>
		<div class="notice">
			<h1>공지사항</h1>

			<form name="frm" method="post" action="noticeUpdate.do">
			
			<!-- notice_code 값을 가져오기 위해 입력해줘야 하는 코드!!!-->
			<input type="hidden" name="notice_code" value="${board.notice_code}">
			
			<table class="brdWritebox">
				<tr>
					<th width="150px">구분</th>
					<th>
						<select class="sortinput" name="notice_label">
							<option>${board.notice_label}</option>
							<option>공지</option>
							<option>이벤트</option>
						</select>
					</th>
				</tr>
				<tr>
					<th width="150px">제목</th>
					<th><input class="titleinput" type="text" name="notice_title" value="${board.notice_title}"></th>
				</tr>
				<tr>
					<th id="textarea">본문</th>
					<th><textarea style="resize: none;" name="notice_content">${board.notice_content}</textarea></th>
				</tr>
			</table>
			<c:set var="page" value="${param.p}" />
			<div class="noticeWritebtn">
				<input type="submit" value="수정" name="send" class="sendbtn">
				<input type="submit" value="목록" name="list" class="noticelistbtn" onclick="location.href='noticeSearch.do?p=${param.p}&searchoption=${param.searchoption}&searchkeyword=${param.searchkeyword}'">
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