<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
                <span class="cost"><fmt:formatNumber pattern="#,##0" value="${(md.md_price-(md.md_price*(md.md_dc/100)))}"/>원</span><br>
                <span class="price"><fmt:formatNumber pattern="#,##0" value="${md.md_price}"/>원</span>
            </a>
        </div>
    </li>
</c:forEach>