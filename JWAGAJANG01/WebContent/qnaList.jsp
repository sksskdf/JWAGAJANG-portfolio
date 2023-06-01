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
<title>좌가장 : Q&amp;A</title>
</head>
<body>
	<!-- 헤더영역    -->
	<jsp:include page="H&F/header.jsp" />
	<!-- 섹션 영역 -->
	<section>
		<div class="pagenav">
			<a href="index.jsp">홈</a> <span class="navarrow"></span>
			<a href="qnaList.do?&p=1">게시판</a> <span class="navarrow"></span>
			<a href="qnaList.do?&p=1">Q&amp;A</a>
		</div>
		<div class="notice">
			<h1>Q&amp;A</h1>
			<table>
				<tr class="qnabrdbox">
					<th>처리상태</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
					<th>조회수</th>
				</tr>

				<c:set var="page" value="${param.p}" />
				<c:forEach var="board" items="${qnalistModel.qnaList}">
					<tr class="record">
						<td class="record_1">${board.qna_label }</td>
						<td class="record_2">
						<a href="qnaView.do?qna_code=${board.qna_code}&p=${page}&q=${param.searchkeyword}">
								${board.qna_title} </a></td>
						<td class="record_3">${board.user_id}</td>
						<td class="record_4"><fmt:formatDate value="${board.qna_regdate}" /></td>
						<td class="record_5">${board.qna_count}</td>
					</tr>
				</c:forEach>
			</table>
			<c:if test="${tbc == 0}">
				<h4>검색된 글이 없습니다.</h4>
			</c:if>
			<div class="writebox">
				<a href="qnaWrite.do"><input type="submit" value="글쓰기"
					name="write" class="writebtn"></a>
			</div>
		</div>

			
			<%-- <c:if> 태그는 test 속성 내의 EL(${ }) 의 결과가 참이면 실행, else는 없음
			 1. test : 필수 속성값으로 EL 비교식을 가집니다.
			 2. var : 조건 결과를 저장할 변수를 지정합니다.
			 3. scope : 조건 결과를 지정할 변수의 저장 scope 를 지정합니다. --%>
			 
		<div class="pg_wrap">
		<!-- 변수 선언 -->
			<c:set var="paging" value="${qnalistModel.paging}" />

			<c:if test="${paging.startPageNo > paging.sizeOfPage}">
				<a class="pg_prev"
					href="<c:url value="/qnaList.do?&p=${paging.prevPageNo}"/>">
					<span class="prev"></span>
				</a>
			</c:if>
			<%-- 페이지 번호 반복문 --%>
			<c:forEach var="pno" begin="${paging.startPageNo}" end="${paging.endPageNo}">
				<a href="<c:url value="/qnaList.do?&p=${pno}"/>">
				
				<input type="button" class="pg_currpno ${(param.p==(pno))?'pg_currpnosel':''}" value="${pno}"></a>
			</c:forEach>
			<c:if test="${paging.endPageNo < paging.finalPageNo}">
				<a class="pg_next"
					href="<c:url value="/qnaList.do?&p=${paging.nextPageNo}"/>">
					<span class="next"></span>
				</a>
			</c:if>
		</div>

		<form name="srcfrm" class="noticeSrc" action="qnaSearch.do"
			onsubmit="qnasearchCheck()">
				<input type="hidden" value="${param.p }" name="p"/>
				<input class="searchtext" value="${param.searchkeyword}" type="text" name="searchkeyword" placeholder="작성자로 검색">
				<input class="searchbtn" value="찾기" type="submit"  onclick="return qnasearchCheck()">
		</form>
	</section>


	<!-- 푸터영역 -->
	<jsp:include page="H&F/footer.html" />
	<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
	<script src="js/jquery.min.js"></script>
	<script src="js/index.js"></script>
	<script src="js/board.js"></script>
</body>
</html>