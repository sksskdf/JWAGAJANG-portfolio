<%@page import="java.sql.Statement"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
<link rel="stylesheet" href="css/productWrite.css">
<title>좌가장 : 상품등록</title>
</head>

<body>
   
   <!-- 헤더영역    -->
                <jsp:include page="H&F/header.jsp" />
   <!-- 제품등록  -->
<section>
		
		<div class="pagenav">
			<a href="index.jsp">홈</a>
			<span class="navarrow"></span>
			<a href="mypage.do">마이페이지</a>
			<span class="navarrow"></span>
			<a href="productList.do">제품관리</a>
			<span class="navarrow"></span>
			<a href="productWrite.do">제품등록</a>
		</div>
		
		
		<div class="productWrite">
			<h1>제품등록</h1>
			<form name="frm" method="post" action="productWrite.do" enctype="multipart/form-data">
			<table class="prodWritebox">
				<tr>
					<th width="150px">구분</th>
					<th>
						<select class="sortinput" id="catemain" name="category_main" onchange="sortChange()">
							<option value="0">대분류를 선택해주세요.</option>
							<option value="100">채소·과일</option>
							<option value="200">쌀·견과류</option>
							<option value="300">수산·해산</option>
							<option value="400">정육·계란</option>
						</select>
						<select class="sortinput" id="catesub1" name="category_sub">
							<option value="110">고구마·감자·당근</option>
							<option value="120">시금치·쌈채소·나물</option>
							<option value="130">브로콜리·파프리카·양배추</option>
							<option value="140">양파·마늘·대파·배추</option>
							<option value="150">오이·호박·고추</option>
							<option value="160">콩나물·버섯</option>
							<option value="170">과일</option>
							<option value="210">쌀·잡곡</option>
							<option value="220">견과류</option>
							<option value="310">생선류</option>
							<option value="320">해산물·조개류</option>
							<option value="330">김·미역·해조류</option>
							<option value="410">소고기·돼지고기</option>
							<option value="420">닭·오리고기</option>
							<option value="430">계란</option>
						</select>
					</th>
				</tr>
				<tr>
					<th width="150px">상품</th>
					<th><input class="titleinput" type="text" name="md_name" placeholder="제목을 입력해주세요." style="border: 0px" autocomplete="off"></th>
				</tr>
				<tr>
					<th width="150px">상품가격</th>
					<th><input class="titleprice" type="text" name="md_price" placeholder="가격을 입력해주세요." style="border: 0px; width:145px" autocomplete="off"> 원 </th>
				</tr>
				<tr>
					<th width="150px">할인율</th>
					<th><input class="titledc" type="text" name="md_dc" style="width: 90px" autocomplete="off"> % </th>
				</tr>
				<tr>
					<th width="150px">재고</th>
					<th><input class="titlestock" type="text" name="md_stock" style="width: 90px" autocomplete="off"></th>
				</tr>
				<tr>
					<th width="150px">배송비</th>
					<th><input class="titledeliprice" type="hidden" name="">&nbsp;&nbsp;&nbsp;&nbsp;  2,500원</th>
				</tr>
				<tr>
					<th width="150px">메인 이미지 첨부</th>
					<th><input class="titleimg" type="file" name="img_main"></th>
				</tr>
				<tr>
					<th width="150px">상세 이미지 첨부</th>
					<th><input class="subimg" type="file" name="img_detail"></th>
				</tr>
			</table>
			
			<div class="productWritebtn">
				<input type="reset" value="다시쓰기" name="reset" class="resetbtn">
				<input type="submit" value="등록" name="send" class="sendbtn">
				<input type="button" value="목록" name="writelist" class="writelistbtn" onclick="location.href='productList.jsp'">
			</div>
			</form>
		</div>		
	</section>

                <!-- 푸터영역 -->
                <jsp:include page="H&F/footer.html" />
                <script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
                <script src="js/jquery.min.js"></script>
            </body>

            </html>