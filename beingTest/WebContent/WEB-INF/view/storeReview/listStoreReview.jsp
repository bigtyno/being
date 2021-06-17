<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>게시글 목록</title>
</head>
<body>
	${storeReviewPage.avgGrade}

<table border="1">
	<tr>
		<td colspan="4"><a href="write.do">[상품평등록]</a></td>
	</tr>
	<tr>
		
		<td>평점</td>
		<td>후기</td>
	</tr>
<c:if test="${storeReviewPage.hasNoStores()}">
	<tr>
		<td colspan="4">상품이 없습니다.</td>
	</tr>
</c:if>
<c:forEach var="storeReview" items="${storeReviewPage.content}">	
	<tr>
		<a href="read.do?no=${storeReview.num}&pageNo=${storeReviewPage.currentPage}">
		<c:out value="${storeReview.prodNum}"/>
		</a>
		
		<td>${storeReview.grade}</td>
		
		<td>${storeReview.content}</td>
	</tr>
</c:forEach>
<c:if test="${storeReviewPage.hasStores()}">
	<tr>
		<td colspan="4">
			<c:if test="${storeReviewPage.startPage > 5}">
			<a href="list.do?pageNo=${storeReviewPage.startPage - 5}">[이전]</a>
			</c:if>
			<c:forEach var="pNo" 
					   begin="${storeReviewPage.startPage}" 
					   end="${storeReviewPage.endPage}">
			<a href="list.do?pageNo=${pNo}">[${pNo}]</a>
			</c:forEach>
			<c:if test="${storeReviewPage.endPage < storeReviewPage.totalPages}">
			<a href="list.do?pageNo=${storeReviewPage.startPage + 5}">[다음]</a>
			</c:if>
		</td>
	</tr>
</c:if>
</table>
</body>
</html>