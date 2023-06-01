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
<title>좌가장 : 공지사항</title>
</head>
<body>
	<!-- 헤더영역    -->
	<jsp:include page="H&F/header.jsp" />

	<section>
		<div class="pagenav">
			<a href="index.jsp">홈</a>
			<span class="navarrow"></span>
			<a href="noticeList.do?&p=1">게시판</a>
			<span class="navarrow"></span>
			<a href="noticeList.do?&p=1">공지사항</a>
		</div>
		
		<div class="notice">
			<h1>공지사항</h1>
			<table class="brdView">
				<tr class="brdViewbox">
					<th class="brdView_label">${board.notice_label}</th>
					<th>${board.notice_title}</th>
					<th><fmt:formatDate value="${board.notice_regdate}"/></th>
					<th>${board.notice_count}</th>
				</tr>
				<tr>
					<td class="content" colspan="4"><pre>${board.notice_content}</pre></td>
				</tr>
			</table>
				<c:set var="page" value="${param.p}" />
			<div class="list">
			<c:if test="${sessionScope.member.grade == 1}">
				<input type="submit" value="수정" class="upbtn" onclick="location.href='noticeUpdate.do?notice_code=${param.notice_code}'">
				<input type="submit" value="삭제" class="delbtn" onclick="location.href='noticeDelete.do?notice_code=${param.notice_code}'">
				</c:if>
				<c:if test="${empty param.f}">
				<input type="submit" value="목록" class="listbtn" onclick="location.href='noticeSearch.do?p=${param.p}'">
				</c:if>
				<c:if test="${!empty param.f}">
				<input type="submit" value="목록" class="listbtn" onclick="location.href='noticeSearch.do?p=${param.p}&searchoption=${param.f}&searchkeyword=${param.q}'">
				</c:if>
			</div>
		</div>

	</section>
	<jsp:include page="H&F/footer.html" />
	<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
	<script src="js/jquery.min.js"></script>
	<script src="js/index.js"></script>
</body>
</html>