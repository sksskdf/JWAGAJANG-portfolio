<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon" href="img/favicon/favicon.ico">
<link rel="stylesheet" href="css/reset.css">
<link rel="stylesheet" href="css/board.css">
<link rel="stylesheet" href="css/header_footer.css">
<link rel="stylesheet" href="css/productList.css">
<title>좌가장 : 제품관리</title>

</head>

<body>
   <!-- 헤더영역    -->
                <jsp:include page="H&F/header.jsp" />

                <!-- 제품 관리-->
                <section>
                    <form name="frm" method="post" action="productList.do">
                                             
                        <div class="pagenav">
						<a href="index.jsp">홈</a>
						<span class="navarrow"></span>
						<a href="mypage.do">마이페이지</a>
						<span class="navarrow"></span>
						<a href="productList.do">제품관리</a>
						</div>
						
                        <div class="titleproductList">
                            <h1>제품관리        &nbsp;&nbsp;
                                <input type="button" value="제품등록" onclick="location.href='productWrite.do'" style="cursor:pointer;">
                            </h1>
                        </div>

                        <div class="sort">
	                        <select id="selBoxFirst" name="selBox" title="대분류" onChange="mainCategoryFilter()">
								<option value="0"  selected>전체보기</option>
								<option value="100">채소·과일</option>
								<option value="200">쌀·견과류</option>
								<option value="300">수산·해산</option>
								<option value="400">정육·계란</option>
							</select>
                            <!-- <select id="selBoxSecond" name="selBox" title="소분류" onChange="">
							<option value="0" selected>소분류</option>
							<option>채소·과일</option>
							<option>쌀·견과류</option>
							<option>수산·해산</option>
							<option>정육·계란</option>
							</select> -->
						</div>

                           
                        <br>
                        <br>

                        <!-- th만 작성시 가로로 데이터 작성됨-->
                        <div class="filter">
                        <div class="productList">
                            <table class="productListtable">
                                <colgroup>
                                    <col style="width: 300px; height:300px;">
                                    <col style="width: 900px; height:300px;">
                                    <col style="width: 200px; height:300px;">
                                    <col style="width: 400px; height:300px;">
                                    <col style="width: 400px; height:300px;">
                                    <col style="width: 179px; height:300px;">
                                </colgroup>
                                <tr class="tabletitle">
                                    <th colspan="2">상품명</th>
                                    <th>재고</th>
                                    <th>제품등록일</th>
                                    <th>제품금액</th>
                                    <th>리뷰</th>
                                </tr>
                                
                                <c:forEach var="f" items="${productList}">
                                <tr>
                                	<td><a href="/goods.do?md_code=${f.md_code}">
                                	<img class="thumbimg" src="img/mdimg/${f.img_main}"></a></td>
                                    <td><a href="/goods.do?md_code=${f.md_code}">${f.md_name}</a></td>
                                    <td>${f.md_stock }</td>
                                    <td>${f.md_regdate}</td>
                                    <td>${f.md_price }</td>
                                    <td><input type="button" value="수정" class="editbtn" onclick="location.href='productUpdate.do?mdcode=${f.md_code}'">
                                        <br><input type="button" value="삭제" class="deletebtn" onclick="location.href='productList.do?del=${f.md_code}&p=1&id=${sessionScope.id}'"></td>
                                </tr>
                                </c:forEach>
                            </table>
                        </div>
						<br>
						
						<div class="pg_wrap">
						<c:set var="paging" value="${listModel.paging}"/> <!-- 변수 선언 -->
							<%-- <c:if> 태그는 test 속성 내의 EL(${ }) 의 결과가 참이면 실행, else는 없음
							 1. test : 필수 속성값으로 EL 비교식을 가집니다.
							 2. var : 조건 결과를 저장할 변수를 지정합니다.
							 3. scope : 조건 결과를 지정할 변수의 저장 scope 를 지정합니다. --%>
							<c:if test="${paging.startPageNo > paging.sizeOfPage}">
								<a class="pg_prev" href="<c:url value="/productList.do?&p=${paging.prevPageNo}"/>">
								<span class="prev"></span></a>
							</c:if>
							
							<%-- 페이지 번호 반복문 --%>
							<c:forEach var="pno" begin="${paging.startPageNo}" end="${paging.endPageNo}">
								<a href="<c:url value="/productList.do?&p=${pno}"/>">
								<input type="button" class="pg_currpno ${(param.p==(pno))?'pg_currpnosel':''}" value="${pno}"></a>
							</c:forEach>
							
							<c:if test="${paging.endPageNo < paging.finalPageNo}">
								<a class="pg_next" href="<c:url value="/productList.do?&p=${paging.nextPageNo}"/>">
								<span class="next"></span></a>
								</c:if>
						</div>
	             </div>				

                    </form>
                </section>


                <!-- 푸터영역 -->
                <jsp:include page="H&F/footer.html" />
                <script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
                <script src="js/jquery.min.js"></script>
                <script src="js/productList.js"></script>
            </body>

            </html>