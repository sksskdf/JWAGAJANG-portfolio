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
    <link rel="stylesheet" href="css/goods.css">
    <link rel="stylesheet" href="css/header_footer.css">
    <link rel="shortcut icon" href="img/favicon/favicon.ico">
    <script src="js/jquery.min.js"></script>
    <script src="js/goods.js"></script>
    <title>좌가장</title>
</head>
<body>
	<div id="top"></div>
    <!-- 헤더 -->
    <jsp:include page="H&F/header.jsp"/>
    <!-- 본문  -->
    <div id="wrap">
        <div class="pagenav">
	       <a href="/index.do">홈</a>
	       <span class="navarrow"></span>
           <a href="list.do?category_main=${md.category_main}">${md.category_main_name}</a>
        </div>
        <div class="goods">
        	<div><img class="goods_img" alt="" src="img/mdimg/${md.img_main}"></div>
            <div class="goods_info">
                <p class="goods_title">${md.md_name}</p>
                <p class="goods_price">
                    <span class="price"><fmt:formatNumber pattern="#,##0" value="${md.md_price}"/></span>원
                    <span class="goods_dc">
                    <c:set var="dccost" value="${(md.md_price-(md.md_price*(md.md_dc/100)))}"/>
                        <span id="cost" data-oper="${dccost}"><fmt:formatNumber pattern="#,##0" value="${(md.md_price-(md.md_price*(md.md_dc/100)))}"/>원</span>
                        <span class="dc">${md.md_dc}%</span>
                    </span>
                </p>
                <hr>
                <dl class="shipping">
                    <dt><p>택배배송</p></dt>
                    <dd>
                        <p>2,500원 <span class="orderpay">(주문시 결제)</span></p>
                        <p class="addshipping">제주, 도서지역 추가 5,000원</p>
                    </dd>
                </dl>
                <dl class="count">
                    <dt><p>주문수량</p></dt>
                    <dd>
                	<c:if test="${md.md_stock == 0 || md.md_stock == null}">
                		<p>재고없음</p>
                	</c:if>
                	<c:if test="${md.md_stock > 0}">
                        <span class="countbtn">
                            <button type="button" class="minusbtn"><a href="javascript:change_qty('minus')">-</a></button>
                            <input type="number" id="goods_qty" readonly="readonly" min="1" max="${md.md_stock}" value="1">
                            <button type="button" class="plusbtn"><a href="javascript:change_qty('plus')">+</a></button>
                        </span>
                    </c:if>
                    </dd>
                </dl>
                <hr>
                <dl class="total">
                    <dt>구매금액</dt>
                    <dd>
                        <em><fmt:formatNumber pattern="#,##0" value="${fn:substringBefore(md.md_price-(md.md_price*md.md_dc/100), '.')}"/></em>원 &nbsp; x &nbsp;
                        <em class="total_qty">1</em>개
                    </dd>
                </dl>
                <div class="total_price">
                    <p class="won"><span class="total_cost">
                    <fmt:formatNumber pattern="#,##0" value="${fn:substringBefore(md.md_price-(md.md_price*md.md_dc/100), '.')}"/></span>원</p>
                </div>
                <div class="btn">
                	<input type="hidden" name="mdcode" value="${md.md_code}">
                	<input type="hidden" name="user_id" value="${sessionScope.id}">
                	<input type="hidden" name="grade" value="${sessionScope.member.grade}">
                    <button type="button" class="buynowbtn" onclick="location.href='order.do?md_code=${md.md_code}'">바로구매</button>
                    <c:if test="${not empty sessionScope.id}">
                    <button type="button" class="cartbtn">장바구니</button>
                    </c:if>
                    <c:if test="${empty sessionScope.id}">
                    <button type="button" class="cartbtn" >장바구니</button>
                    </c:if>
                    <button type="button" class="favbtn" >찜하기</button>

                </div>
            </div>
            
            <div id="goods_detail">
                <ul class="goods_tab">
                    <li><a href="#goods_detail" class="tab_on detail_anchor">상품 상세 정보</a></li>
                    <li><a href="#goods_review" class="tab_off review_anchor">상품평 (${count}건)</a></li>
                </ul>

                <img src="img/mdimg/${md.img_detail}" class="goods_img_detail">
            </div>
            <div id="goods_review">
                <ul class="goods_tab">
                    <li><a href="#goods_detail" class="tab_off detail_anchor">상품 상세 정보</a></li>
                    <li><a href="#goods_review" class="tab_on review_anchor">상품평 (${count}건)</a></li>
                </ul>
                <div class="review_title">
                    <p class="review_tit">상품평</p>
                    <p class="review_desc">· 상품에 대한 후기를 남기는 공간입니다. 상품평 운영원칙 및 법령에 위반되는 경우에는 해당 상품평에 대한 필요한 조치가 취해질 수 있습니다.</p>
                </div>
                <table class="review_list">
                <c:if test="${count == 0 || count == null}">
                	<p class="noReview">등록된 상품평이 없습니다.</p>
                </c:if>
                <c:if test="${count > 0}">
	                <c:forEach var="review" items="${reviewList}">
	                	<tr>
	                        <td class="review_name">${review.user_id}</td>
	                        <td class="review_rate"><c:forEach var="i" begin="1" end="5">
	                        <c:if test="${i <= review.review_rate}"><img src="../img/star-full.png"></c:if>
	                        </c:forEach>
	                        </td>
                        
	                        <td class="review_content">${review.review_content}</td>
	                        <td class="review_date"><fmt:formatDate value="${review.review_regdate}" pattern="yyyy-MM-dd"/></td>
	                        <c:if test="${sessionScope.id==review.user_id}">
		                        <td class="review_del">
		                        	<button id="delete" name="${review.review_code}"
		                        		onclick="del(this)">x</button>
		                        </td>
		                    </c:if>
	                    </tr>
	                </c:forEach>
				</c:if>
                </table>
                <input type="hidden" name="md_code" value="${md.md_code}">
                <input type="hidden" name="favdupchk" value="${dupchk}">
                <table class="review_write">
				<c:if test="${empty sessionScope.id}">
					<p class="reveiwLogin">상품평을 작성하시려면 <a href="/login.do">로그인</a> 하세요</p>	<%-- 로그인하고 다시 돌아올 수 있나? --%>
				</c:if>
				<c:if test="${!empty sessionScope.id}">
					<tr>
                        <td class="write_name">
                        	${sessionScope.id}
                            <input type="hidden" id="writer" value="${sessionScope.id}">
                        </td>
                     <!--    <td class="write_rate">
                        	<label class="starRadio">
                        		<input type="radio" name="star" id="">
                        		<span class="starRadio_img"><span class="blind">1</span></span>
                        	</label>
                        	<label class="starRadio">
                        		<input type="radio" name="star" id="">
                        		<span class="starRadio_img"><span class="blind">2</span></span>
                        	</label>
                        	<label class="starRadio">
                        		<input type="radio" name="star" id="">
                        		<span class="starRadio_img"><span class="blind">3</span></span>
                        	</label>
                        	<label class="starRadio">
                        		<input type="radio" name="star" id="">
                        		<span class="starRadio_img"><span class="blind">4</span></span>
                        	</label>
                        	<label class="starRadio">
                        		<input type="radio" name="star" id="">
                        		<span class="starRadio_img"><span class="blind">5</span></span>
                        	</label>
                        </td> --> 
                        <td class="write_rate">
   						<input type="hidden" class="score"></input>
						<div class="stars"></div>
                           <!--  <input type="number" id="stars" placeholder="★★★★★"
                                onfocus="this.placeholder=''" onblur="this.placeholder='★★★★★'"> -->
                        </td>
                        <td class="write_content">
                            <textarea type="text" id="writervw" placeholder="상품평을 작성해주세요."
                                onfocus="this.placeholder=''" onblur="this.placeholder='상품평을 작성해주세요.'"
                                onkeydown="resize(this)" onkeyup="resize(this)"></textarea>
                        </td>
                        <td class="write_submit">
                            <input type="submit" id="submitbtn" value="등록"></input>
                        </td>
                    </tr>
				</c:if>
                </table>
            </div>
        </div>
        <div class="footnav">
            <div class="footnav_list">
                <ul>
                    <a href="/index.do"><li>홈</li></a>
                    <div class="line"></div>
                    <a href="/cartPut.do"><li>장바구니</li></a>
                    <div class="line"></div>
                    <a href="favlist.do?p=1"><li>찜리스트</li></a>
                </ul>
            </div>
            <div class="footnav_latest">
                <p>최근 본 상품</p>
                <ul>
                <c:forEach var="goods" items="${visited}">
                	<li><a href="goods.do?md_code=${goods.md_code}"><img src="img/${goods.img_main}" alt="${goods.md_name}"></a></li>
                </c:forEach>
                    
                </ul>
                <a href="#top" class="top_btn"><img src="img/up.png"></a>
            </div>
        </div>
    </div>
    <!-- 푸터 -->
    <jsp:include page="H&F/footer.html"/>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
	<script src="js/rating.js"></script>



</body>
</html>