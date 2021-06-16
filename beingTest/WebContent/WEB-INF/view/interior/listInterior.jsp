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
		<td colspan="4"><a href="write.do">[업체등록]</a></td>
	</tr>
	<tr>
		<td>업체명</td>
		<td>업체 소개</td>
		<td>첨부파일1</td>
		<td>첨부파일2</td>
	</tr>
<c:if test="${interiorPage.hasNoInterior()}">
	<tr>
		<td colspan="4">업체가 없습니다.</td>
	</tr>
</c:if>
<c:forEach var="interior" items="${interiorPage.content}">
	<tr>
		<td>
<!-- 		업체명 -->
		<a href="read.do?no=${interior.num}&pageNo=${interiorPage.currentPage}">
		<c:out value="${interior.name}"/>
		</a>
		
		<!-- 	업체 소개 -->
		<td>${interior.introduce}</td>
<!-- 		첨부파일1 -->
		<td>${interior.imageA}</td>
<!-- 		첨부파일2 -->
		<td>${interior.imageB}</td>

	</tr>
</c:forEach>
<c:if test="${interiorPage.hasInterior()}">
	<tr>
		<td colspan="4">
			<c:if test="${interiorPage.startPage > 5}">
			<a href="list.do?pageNo=${interiorPage.startPage - 5}">[이전]</a>
			</c:if>
			<c:forEach var="pNo" 
					   begin="${interiorPage.startPage}" 
					   end="${interiorPage.endPage}">
			<a href="list.do?pageNo=${pNo}">[${pNo}]</a>
			</c:forEach>
			<c:if test="${interiorPage.endPage < interiorPage.totalPages}">
			<a href="list.do?pageNo=${interiorPage.startPage + 5}">[다음]</a>
			</c:if>
		</td>
	</tr>
</c:if>
</table>
</body>
</html>