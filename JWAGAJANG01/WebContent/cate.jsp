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
        <div class="pagenav">
	       <a href="/index.do">홈</a>
	       <c:if test="${(param.category_main != 'All')}">
	       <span class="navarrow"></span>
           <a href="list.do?category_main?=${mdList[0].category_main}">${mdList[0].category_main_name}</a>

	       <span class="navarrow"></span>
           </c:if>
           <a href="#">전체보기</a>
        </div>
        <div class="goodsCtgry">
        	<c:if test="${(category_main == 'All')}">
        		<ul class="ctgry_list">
	                <li><a class="on">전체보기</a></li>
        		</ul>
        	</c:if>
        	<c:if test="${(category_main == '100')}">
	            <ul class="ctgry_list">
	                <li><a class="${(empty param.category_sub)?'on':''}" href="/list.do?category_main=100" class="on">전체보기</a></li>
	                <li><a class="${(param.category_sub==110)?'on':''}" href="/list.do?category_main=100&category_sub=110">고구마·감자·당근</a></li>
	                <li><a class="${(param.category_sub==120)?'on':''}" href="/list.do?category_main=100&category_sub=120">시금치·쌈채소·나물</a></li>
	                <li><a class="${(param.category_sub==130)?'on':''}" href="/list.do?category_main=100&category_sub=130">브로콜리·파프리카·양배추</a></li>
	                <li><a class="${(param.category_sub==140)?'on':''}" href="/list.do?category_main=100&category_sub=140">양파·마늘·대파·배추</a></li>
	                <li><a class="${(param.category_sub==150)?'on':''}" href="/list.do?category_main=100&category_sub=150">오이·호박·고추</a></li>
	                <li><a class="${(param.category_sub==160)?'on':''}" href="/list.do?category_main=100&category_sub=160">콩나물·버섯</a></li>
	                <li><a class="${(param.category_sub==170)?'on':''}" href="/list.do?category_main=100&category_sub=170">과일</a></li>
	            </ul>
            </c:if>
            <c:if test="${(category_main == '200')}">
	            <ul class="ctgry_list">
	                <li><a class="${(empty param.category_sub)?'on':''}" href="/list.do?category_main=200" class="on">전체보기</a></li>
	                <li><a class="${(param.category_sub==210)?'on':''}" href="/list.do?category_main=200&category_sub=210">쌀·잡곡</a></li>
	                <li><a class="${(param.category_sub==220)?'on':''}" href="/list.do?category_main=200&category_sub=220">견과류</a></li>
	            </ul>
            </c:if>
            <c:if test="${(category_main == '300')}">
	            <ul class="ctgry_list">
	                <li><a class="${(empty param.category_sub)?'on':''}" href="/list.do?category_main=300" class="on">전체보기</a></li>
	                <li><a class="${(param.category_sub==310)?'on':''}" href="/list.do?category_main=300&category_sub=310">생선류</a></li>
	                <li><a class="${(param.category_sub==320)?'on':''}" href="/list.do?category_main=300&category_sub=320">해산물·조개류</a></li>
	                <li><a class="${(param.category_sub==330)?'on':''}" href="/list.do?category_main=300&category_sub=330">김·미역·해조류</a></li>
	            </ul>
            </c:if>
            <c:if test="${(category_main == '400')}">
	            <ul class="ctgry_list">
	                <li><a class="${(empty param.category_sub)?'on':''}" href="/list.do?category_main=400" class="on">전체보기</a></li>
	                <li><a class="${(param.category_sub==410)?'on':''}" href="/list.do?category_main=400&category_sub=410">소고기·돼지고기</a></li>
	                <li><a class="${(param.category_sub==420)?'on':''}" href="/list.do?category_main=400&category_sub=420">닭·오리고기</a></li>
	                <li><a class="${(param.category_sub==430)?'on':''}" href="/list.do?category_main=400&category_sub=430">계란</a></li>
	            </ul>
            </c:if>
        </div>
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
                <c:set var="mdList" value="${listModel.mdList}" />
                    <ul class="goods">
                    <c:forEach var="md" items="${mdList}">
                        <li>
                            <div class="item">
                            	<div class="thumb">
                                    <a href="/goods.do?md_code=${md.md_code}">
                                    	<img class="thumbimg" alt="" src="img/mdimg/${md.img_main}">
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
					<a href="<c:url value="/cate.do?category_main=${param.category_main}&order=${param.order}&p=${paging.prevPageNo}"/>">이전</a>
				</c:if>
		        <c:forEach var="pno" begin="${paging.startPageNo}" end="${paging.endPageNo}">
					<a href="<c:url value="/cate.do?category_main=${param.category_main}&order=${param.order}&p=${pno}" />">[${pno}]</a>
				</c:forEach>
		        <c:if test="${paging.endPageNo < paging.finalPageNo}">
					<a href="<c:url value="/cate.do?category_main=${param.category_main}&order=${param.order}&p=${paging.nextPageNo}"/>">다음</a>
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