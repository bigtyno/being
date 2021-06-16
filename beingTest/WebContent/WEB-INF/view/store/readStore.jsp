<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<title>게시글 읽기</title>
</head>
<body>
<table border="1" width="100%">
<!-- <tr> -->
<!-- 	<td>번호</td> -->
<%-- 	<td>${articleData.article.number}</td> --%>
<!-- </tr> -->
<%-- <tr>
	<td>작성자</td>
	<td>${articleData.article.writer.name}</td>
	<td>${articleData.article.writer.id}</td>
</tr>
<tr>
	<td>제목</td>
	<td><c:out value='${storeData.store.title}' /></td>
</tr> --%>
<tr>
	<td>상품이름</td>
	<td><u:pre value='${storeData.store.name}'/></td>
</tr>
<tr>
   <td>상품사진</td>
   <td><u:pre value='${storeData.store.thumbnail}'/></td>
</tr>
<tr>
   <td>상품소개</td>
   <td><u:pre value='${storeData.store.infoimage}'/></td>
</tr>
<tr>
   <td>상품가격</td>
   <td><u:pre value='${storeData.store.introduce}'/></td>
</tr>
<tr>
   <td>할인가격</td>
   <td><u:pre value='${storeData.store.dcprice}'/></td>
</tr>
<tr>
<tr>
   <td>카테고리</td>
   <td><u:pre value='${storeData.store.category}'/></td>
</tr>
<tr>
<tr>
   <td>무료배송여부</td>
   <td><u:pre value='${storeData.store.freeyn}'/></td>
</tr>
<tr>
<tr>
   <td>판매사이트링크</td>
   <td><u:pre value='${storeData.store.link}'/></td>
</tr>
<tr>
<tr>
   <td>평균평점</td>
   <td><u:pre value='${storeData.store.avggrade}'/></td>
</tr>


	 <td colspan="2">
		<c:set var="pageNo" value="${empty param.pageNo ? '1' : param.pageNo}" />
		<a href="list.do?pageNo=${pageNo}">[목록]</a>
		<a href="${pageContext.request.contextPath}/storeReview/write.do?no=${storeData.store.num}">[상품평 등록]</a>
		<%-- <c:if test="${authUser.id == storeData.article.writer.id}"> --%>
		<u:isAdmin>
		<a href="modify.do?no=${storeData.store.num}">[게시글수정]</a>
		<a href="delete.do?no=${storeData.store.num}">[게시글삭제]</a>
		</u:isAdmin>
		<%-- </c:if> --%>
	</td> 
</tr>
</table>

</body>
</html>