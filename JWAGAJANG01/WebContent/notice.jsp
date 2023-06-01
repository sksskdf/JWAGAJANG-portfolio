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
	<!-- 섹션 영역 -->
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
			<table>
				<tr class="brdbox">
					<th>구분</th>
					<th>제목</th>
					<th>작성일</th>
					<th>조회수</th>
				</tr>
				<c:set var="page" value="${param.p}" />
				<c:forEach var="board" items="${listModel.noticeList}"> 
					<tr class="record">
						<td class="record_1">${board.notice_label }</td>
						<td class="record_2"><a href="noticeView.do?notice_code=${board.notice_code}&p=${page}&f=${param.searchoption}&q=${param.searchkeyword}"> ${board.notice_title} </a></td>
						<td class="record_3"><fmt:formatDate value="${board.notice_regdate}" /></td>
						<td class="record_4">${board.notice_count}</td>
					</tr>
				</c:forEach>
			</table>
			<c:if test="${tbc == 0}">
				<h4>검색된 글이 없습니다.</h4>
			</c:if>
			<c:if test="${sessionScope.member.grade == 1}">
			<div class="writebox">
				<a href="noticeWrite.do?&p=${param.p}"><input type="submit" value="글쓰기" name="write" class="writebtn"></a>
			</div>
			</c:if>
		</div>

		<div class="pg_wrap">
		<c:set var="paging" value="${listModel.paging}"/> <!-- 변수 선언 -->
			<%-- <c:if> 태그는 test 속성 내의 EL(${ }) 의 결과가 참이면 실행, else는 없음
			 1. test : 필수 속성값으로 EL 비교식을 가집니다.
			 2. var : 조건 결과를 저장할 변수를 지정합니다.
			 3. scope : 조건 결과를 지정할 변수의 저장 scope 를 지정합니다. --%>
			<c:if test="${paging.startPageNo > paging.sizeOfPage}">
				<a class="pg_prev" href="<c:url value="/noticeList.do?&p=${paging.prevPageNo}"/>">
				<span class="prev"></span></a>
			</c:if>
			
			<%-- 페이지 번호 반복문 --%>
			<c:forEach var="pno" begin="${paging.startPageNo}" end="${paging.endPageNo}">
				<a href="<c:url value="/noticeList.do?&p=${pno}"/>">
				<input type="button" class="pg_currpno ${(param.p==(pno))?'pg_currpnosel':''}" value="${pno}"></a>
			</c:forEach>
			
			<c:if test="${paging.endPageNo < paging.finalPageNo}">
				<a class="pg_next" href="<c:url value="/noticeList.do?&p=${paging.nextPageNo}"/>">
				<span class="next"></span></a>
				</c:if>
		</div>
		
	  <form name="searchfrm" class="noticeSrc" action="noticeSearch.do" onsubmit="searchCheck()"> <!-- method="post"방식으로 할 때 파라미터를 암호화할 수 있음. 그러나 get방식으로해도 상관xx -->
			<select name="searchoption" class="srcoption">	
			<option ${(param.f == "notice_title")? "selected":""} value="notice_title">제목</option> <!-- 페이지가 바꼈을때 유지 -->
			<option ${(param.f == "notice_content")? "selected":""} value="notice_content">내용</option>
			</select>
			<input class="searchtext" value="${param.searchkeyword}" type="text" name="searchkeyword">
			<input class="searchbtn" value="찾기" type="submit" onclick="return searchCheck()">
			<!-- false일 때 액션이 실행되지 않게 하려면 return이 필요하다. -->
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