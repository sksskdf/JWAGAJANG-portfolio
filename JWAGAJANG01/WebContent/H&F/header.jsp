<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

    <script src="js/jquery.min.js"></script>
    <script src="js/goods.js"></script>
    
	<header>   
       <div class="headerwrap">
        <div class="logo">
          <a href="/main.do"><img class="logo_img" src="../img/logo.svg" alt="로고"></a>
        </div>
        <form class="search" action="search.do">
        	<input type="hidden" name="category_main" value="All">
			<input type="text" id="schText" name="schText"><input type="submit" src="../img/search.svg" id="schButton" value="">
        </form>
        <nav>
          <ul class="gnbmy">
            <c:if test="${sessionScope.id == null}">
            <li><a href="/login.do">로그인</a></li>
            </c:if>
            <c:if test="${sessionScope.id != null}">
            <li><a href="/logout.do">로그아웃</a></li>
            </c:if>
            <c:if test="${sessionScope.id != null && sessionScope.member.grade == 0}">
            <li><a href="/cartPut.do">장바구니</a></li>
            </c:if>
            <c:if test="${sessionScope.id == null}">
            <li><a href="/join.do">회원가입</a></li>
            </c:if>
            <c:if test="${sessionScope.id != null}">
            <li><a href="/mypage.do">마이페이지</a></li>
            </c:if>
            <c:if test="${sessionScope.id == null}">
            </c:if>
          </ul>
        </nav>
        <nav>
          <ul class=gnb >
            <li class="gnb_li"><a href="/list.do?category_main=All&p=1" class="ctgry"><img src="../img/allctgrybtn.svg" style="height: 12px;"> 전체상품</a>
              <table class="lnb">
                <tr class="lnb_tr"><th class="lnb_th"><a href="/list.do?category_main=100&p=1">채소·과일</a></th></tr>
                  <tr><td class="lnb_td"><a href="/list.do?category_main=100&category_sub=110&p=1">고구마·감자·당근</a></td></tr>
                  <tr><td class="lnb_td"><a href="/list.do?category_main=100&category_sub=120&p=1">시금치·쌈채소·나물</a></td></tr>
                  <tr><td class="lnb_td"><a href="/list.do?category_main=100&category_sub=130&p=1">브로콜리·파프리카·양배추</a></td></tr>
                  <tr><td class="lnb_td"><a href="/list.do?category_main=100&category_sub=140&p=1">양파·마늘·대파·배추</a></td></tr>
                  <tr><td class="lnb_td"><a href="/list.do?category_main=100&category_sub=150&p=1">오이·호박·고추</a></td></tr>
                  <tr><td class="lnb_td"><a href="/list.do?category_main=100&category_sub=160&p=1">콩나물·버섯</a></td></tr>
                  <tr><td class="lnb_td"><a href="/list.do?category_main=100&category_sub=170&p=1">과일</a></td></tr>
                <tr class="lnb_tr"><th class="lnb_th"><a href="/list.do?category_main=200&p=1">쌀·견과류</th></tr>
                  <tr><td class="lnb_td"><a href="/list.do?category_main=200&category_sub=210&p=1">쌀·잡곡</a></td></tr>
                  <tr><td class="lnb_td"><a href="/list.do?category_main=200&category_sub=220&p=1">견과류</a></td></tr>
                <tr class="lnb_tr"><th class="lnb_th"><a href="/list.do?category_main=300&p=1">수산·해산</th></tr>
                  <tr><td class="lnb_td"><a href="/list.do?category_main=300&category_sub=310&p=1">생선류</a></td></tr>
                  <tr><td class="lnb_td"><a href="/list.do?category_main=300&category_sub=320&p=1">해산물·조개류</a></td></tr>
                  <tr><td class="lnb_td"><a href="/list.do?category_main=300&category_sub=330&p=1">김·미역·해조류</a></td></tr>
                <tr class="lnb_tr"><th class="lnb_th"><a href="/list.do?category_main=400">정육·계란</th></tr>
                  <tr><td class="lnb_td"><a href="/list.do?category_main=400&category_sub=410&p=1">소고기·돼지고기</a></td></tr>
                  <tr><td class="lnb_td"><a href="/list.do?category_main=400&category_sub=420&p=1">닭·오리고기</a></td></tr>
                  <tr><td class="lnb_td"><a href="/list.do?category_main=400&category_sub=430&p=1">계란</a></td></tr>
              </table>
            </li>
            <li class="cate gnb_li"><a class="ctgry" href="/cate.do?category_main=All&order=1">신상품</a></li>
            <li class="cate gnb_li"><a class="ctgry" href="/cate.do?category_main=All&order=2">베스트</a></li>
            <li class="cate gnb_li"><a class="ctgry" href="/cate.do?category_main=All&order=3">인기상품</a></li>
            <li class="cate gnb_li" ><a class="ctgry" href="/cate.do?category_main=All&order=4">알뜰상품</a></li>
            <li class="gnb_li"><a class="ctgry" href="noticeList.do?&p=1">공지사항</a></li>
            <li class="gnb_li"><a class="ctgry" href="qnaList.do?&p=1">문의게시판</a></li>
          </ul>
        </nav>
       </div>
    </header>