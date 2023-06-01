<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
