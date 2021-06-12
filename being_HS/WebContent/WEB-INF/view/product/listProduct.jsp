<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>게시글 목록</title>
</head>
<body>

<table border="1">
	<tr>
		<td colspan="4"><a href="write.do">[상품등록]</a></td>
	</tr>
	<tr>
		<td>썸네일</td>
		<td>브랜드</td>
		<td>이름</td>
		<td>할인가</td>
		<td>평점</td>
		<td>무료배송</td>
	</tr>
<c:if test="${ProductPage.hasNoProducts()}">
	<tr>
		<td colspan="4">상품이 없습니다.</td>
	</tr>
</c:if>
<c:forEach var="product" items="${ProductPage.content}">
	<tr>
<!-- 	썸네일 -->
		<td>${product.num}</td>
<!-- 		브랜드 -->
		<td>${product.brand}</td>
		<td>
<!-- 		이름 -->
		<a href="read.do?no=${product.num}&pageNo=${ProductPage.currentPage}">
		<c:out value="${product.name}"/>
		</a>
		</td>
<!-- 		할인가 -->
		<td>${product.dcprice}</td>
<!-- 		평점 -->
		<td>${product.avggrade}</td>
<!-- 		무료배송 -->
		<td>
	<c:if test="${product.freeyn eq 'Y'}">
		무료배송
	</c:if>
	<c:if test="${product.freeyn ne 'Y'}">
		유료배송
	</c:if>
		
		</td>
	</tr>
</c:forEach>
<c:if test="${ProductPage.hasProducts()}">
	<tr>
		<td colspan="4">
			<c:if test="${ProductPage.startPage > 5}">
			<a href="list.do?pageNo=${ProductPage.startPage - 5}">[이전]</a>
			</c:if>
			<c:forEach var="pNo" 
					   begin="${ProductPage.startPage}" 
					   end="${ProductPage.endPage}">
			<a href="list.do?pageNo=${pNo}">[${pNo}]</a>
			</c:forEach>
			<c:if test="${ProductPage.endPage < ProductPage.totalPages}">
			<a href="list.do?pageNo=${ProductPage.startPage + 5}">[다음]</a>
			</c:if>
		</td>
	</tr>
</c:if>
</table>
</body>
</html>