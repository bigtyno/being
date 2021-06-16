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
<c:if test="${storePage.hasNoStores()}">
	<tr>
		<td colspan="4">상품이 없습니다.</td>
	</tr>
</c:if>
<c:forEach var="store" items="${storePage.content}">
	<tr>
<!-- 	썸네일 -->
		<td>${store.num}</td>
<!-- 		브랜드 -->
		<td>${store.brand}</td>
		<td>
<!-- 		이름 -->
		<a href="read.do?no=${store.num}&pageNo=${storePage.currentPage}">
		<c:out value="${store.name}"/>
		</a>
		</td>
<!-- 		할인가 -->
		<td>${store.dcprice}</td>
<!-- 		평점 -->
		<td>${store.avggrade}</td>
<!-- 		무료배송 -->
		<td>
	<c:if test="${store.freeyn eq 'Y'}">
		무료배송
	</c:if>
	<c:if test="${store.freeyn ne 'Y'}">
		유료배송
	</c:if>
		
		</td>
	</tr>
</c:forEach>
<c:if test="${articlePage.hasArticles()}">
	<tr>
		<td colspan="4">
			<c:if test="${articlePage.startPage > 5}">
			<a href="list.do?pageNo=${articlePage.startPage - 5}">[이전]</a>
			</c:if>
			<c:forEach var="pNo" 
					   begin="${articlePage.startPage}" 
					   end="${articlePage.endPage}">
			<a href="list.do?pageNo=${pNo}">[${pNo}]</a>
			</c:forEach>
			<c:if test="${articlePage.endPage < articlePage.totalPages}">
			<a href="list.do?pageNo=${articlePage.startPage + 5}">[다음]</a>
			</c:if>
		</td>
	</tr>
</c:if>
</table>
</body>
</html>