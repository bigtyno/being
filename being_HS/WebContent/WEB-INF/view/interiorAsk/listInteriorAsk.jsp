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
		<td>신청 제목</td>
		<td>예산</td>
		<td>공급 면적</td>
		<td>시공 분야</td>
		<td>시공 희망일</td>
		<td>완료 희망일</td>
		<td>전달 사항</td>
		<td>전화번호</td>
	</tr>
<c:if test="${interiorAskPage.hasNoInteriorAsk()}">
	<tr>
		<td colspan="4">업체가 없습니다.</td>
	</tr>
</c:if>
<c:forEach var="interiorAsk" items="${interiorAskPage.content}">
	<tr>
		<td>
<!-- 		신청 제목 -->
		<a href="read.do?no=${interiorAsk.num}&pageNo=${interiorAskPage.currentPage}">
		<c:out value="${interiorAsk.title}"/>
		</a>
		<td>${interiorAsk.budget}</td>
		<td>${interiorAsk.area}</td>
		<td>${interiorAsk.fieldOf}</td>
		<td>${interiorAsk.dateStart}</td>
		<td>${interiorAsk.dateDone}</td>
		<td>${interiorAsk.message}</td>
		<td>${interiorAsk.tel}</td>

	</tr>
</c:forEach>
<c:if test="${interiorAskPage.hasInteriorAsk()}">
	<tr>
		<td colspan="4">
			<c:if test="${interiorAskPage.startPage > 5}">
			<a href="list.do?pageNo=${interiorAskPage.startPage - 5}">[이전]</a>
			</c:if>
			<c:forEach var="pNo" 
					   begin="${interiorAskPage.startPage}" 
					   end="${interiorAskPage.endPage}">
			<a href="list.do?pageNo=${pNo}">[${pNo}]</a>
			</c:forEach>
			<c:if test="${interiorAskPage.endPage < interiorAskPage.totalPages}">
			<a href="list.do?pageNo=${interiorAskPage.startPage + 5}">[다음]</a>
			</c:if>
		</td>
	</tr>
</c:if>
</table>
</body>
</html>