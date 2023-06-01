<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/reset.css">
    <link rel="stylesheet" href="css/list.css">
    <link rel="stylesheet" href="css/header_footer.css">
    <link rel="shortcut icon" href="img/favicon/favicon.ico">
    <title>좌가장</title>
    <script src="js/jquery.min.js"></script>
    <script src="js/goods.js"></script>
</head>
<body>
	<div id="top"></div>
    <!-- 헤더 -->
    <jsp:include page="H&F/header.jsp"/>
    <!-- 본문  -->
    <div id="wrap">
    	<p class="result_tit">"${searchkeyword}" 검색결과 (${searchCount}개)</p>
        <div id="goodsList">
            <div class="sort">
                <div class="sort_menu">
                	
                    <ul class="select_sort" data-c1="${category_main}" data-c2="${param.category_sub}">
                        <li><a class="orderBy" data-oper="1">최신상품</a></li>
                        <div class="line"></div>
                        <li><a class="orderBy" data-oper="2">판매량순</a></li>
                        <div class="line"></div>
                        <li><a class="orderBy" data-oper="3">상품평</a></li>
                        <div class="line"></div>
                        <li><a class="orderBy" data-oper="4">낮은가격</a></li>
                        <div class="line"></div>
                        <li><a class="orderBy" data-oper="5">높은가격</a></li>
                    </ul>
                </div>
            </div>
            <div class="list_goods">
                <div class="inner_listgoods">
                    <ul class="goods">
                    <c:forEach var="md" items="${mdList}">
                        <li>
                            <div class="item">
                            	<div class="thumb">
                                    <a href="/goods.do?md_code=${md.md_code}">
                                    	<img class="thumbimg" alt="" src="img/${md.img_main}">
                                    </a>
                                </div>
                                <a href="/goods.do?md_code=${md.md_code}" class="goods_info">
                                    <span class="goods_title">${md.md_name}</span><br>
                                    <span class="goods_dc">${md.md_dc}%</span>
                                    <span class="cost"><fmt:formatNumber pattern="###,###,##0" value="${(md.md_price-(md.md_price*(md.md_dc/100)))}"/>원</span><br>
                                    <span class="price"><fmt:formatNumber pattern="###,###,##0" value="${md.md_price}"/>원</span>
                                </a>
                            </div>
                        </li>
					</c:forEach>
                    </ul>
                </div>
            </div>
        </div>
		<div class="paging">
		    <div class="pagediv">
		        <c:set var="paging" value="${listModel.paging}"/>
		        <c:if test="${paging.startPageNo > paging.sizeOfPage}">
					<a href="<c:url value="/list.do?category_main=${param.category_main}&p=${paging.prevPageNo}"/>">이전</a>
				</c:if>
		        <c:forEach var="pno" begin="${paging.startPageNo}" end="${paging.endPageNo}">
					<a href="<c:url value="/list.do?category_main=${param.category_main}&p=${pno}" />">[${pno}]</a>
				</c:forEach>
		        <c:if test="${paging.endPageNo < paging.finalPageNo}">
					<a href="<c:url value="/list.do?category_main=${param.category_main}&p=${paging.nextPageNo}"/>">다음</a>
				</c:if>
		    </div>
		</div>
        
    </div>
    <!-- 푸터 -->
    <jsp:include page="H&F/footer.html"/>
    <script src="js/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
    <script src="js/index.js"></script>
</body>
</html>