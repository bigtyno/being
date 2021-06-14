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
	<td>상품이름</td>
	<td><u:pre value='${product.name}'/></td>
</tr>
<tr>
   <td>상품사진</td>
   <td><u:pre value='${product.thumbnail}'/></td>
</tr>
<tr>
   <td>상품소개</td>
   <td><u:pre value='${product.infoimage}'/></td>
</tr>
<tr>
   <td>상품가격</td>
   <td><u:pre value='${product.introduce}'/></td>
</tr>
<tr>
   <td>할인가격</td>
   <td><u:pre value='${product.dcprice}'/></td>
</tr>
<tr>
<tr>
   <td>카테고리</td>
   <td><u:pre value='${product.category}'/></td>
</tr>
<tr>
<tr>
   <td>무료배송여부</td>
   <td><u:pre value='${product.freeyn}'/></td>
</tr>
<tr>
<tr>
   <td>판매사이트링크</td>
   <td><u:pre value='${product.link}'/></td>
</tr>
<tr>
   <td>평균평점</td>
   <td><u:pre value='${product.avggrade}'/></td>
</tr>
<tr>
	 <td colspan="2">
		<c:set var="pageNum" value="${empty param.pageNum ? '1' : param.pageNum}" />
		<a href="list.do?pageNum=${pageNum}">[목록]</a>
		<c:if test="${authUser.lvl == 1}"> 
		<a href="modify.do?num=${product.num}">[게시글수정]</a>
		<a href="delete.do?num=${product.num}">[게시글삭제]</a> 
		</c:if> 
	</td> 
</tr>
</table>

</body>
</html>