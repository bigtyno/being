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

<tr>
	<td>업체명</td>
	<td><u:pre value='${interior.name}'/></td>
</tr>

<tr>
	<td>업체소개</td>
	<td><u:pre value='${interior.introduce}'/></td>
</tr>

<tr>
	<td>이미지A</td>
	<td><u:pre value='${interior.imageA}'/></td>
</tr>

<tr>
	<td>이미지B</td>
	<td><u:pre value='${interior.imageB}'/></td>
</tr>



	 <td colspan="2">
		<c:set var="pageNo" value="${empty param.pageNo ? '1' : param.pageNo}" />
		<a href="list.do?pageNo=${pageNo}">[목록]</a>
		<%--  <c:if test="${authUser.id == storeData.article.writer.id}"> --%>
		<u:isAdmin>
		<a href="modify.do?no=${interior.num}">[게시글수정]</a>
		<a href="delete.do?no=${interior.num}">[게시글삭제]</a> 
		</u:isAdmin>
		<%--</c:if> ---%>
		<u:isAdmin></u:isAdmin>
	</td> 
</tr>
</table>

</body>
</html>