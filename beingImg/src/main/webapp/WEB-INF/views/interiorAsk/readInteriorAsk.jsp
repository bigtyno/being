<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zxx" class="no-js">
<head>
<title>게시글 읽기</title>
</head>
<body>
<table border="1" width="100%">

<tr>
	<td>신청 제목</td>
	<td><u:pre value='${interiorAskVO.title}'/></td>
</tr>

<tr>
	<td>예산</td>
	<td><u:pre value='${interiorAskVO.budget}'/></td>
</tr>

<tr>
	<td>공급 면적</td>
	<td><u:pre value='${interiorAskVO.area}'/></td>
</tr>

<tr>
	<td>시공 분야</td>
	<td><u:pre value='${interiorAskVO.fieldof}'/></td>
</tr>

<tr>
	<td>시공 희망일</td>
	<td><u:pre value='${interiorAskVO.datestart}'/></td>
</tr>

<tr>
	<td>완료 희망일</td>
	<td><u:pre value='${interiorAskVO.datedone}'/></td>
</tr>

<tr>
	<td>전달 사항</td>
	<td><u:pre value='${interiorAskVO.message}'/></td>
</tr>

<tr>
	<td>전화번호</td>
	<td><u:pre value='${interiorAskVO.tel}'/></td>
</tr>

<tr>
	<td>업체명</td>
	<td><u:pre value='${interiorAskVO.cname}'/></td>
</tr>

<tr>
	<td>업체 답변</td>
	<td><u:pre value='${interiorAskVO.answer}'/></td>
</tr>

<tr>
	<td>평점 </td>
	<td><u:pre value='${interiorAskVO.grade}'/></td>
</tr>

<tr>
	<td>총평</td>
	<td><u:pre value='${interiorAskVO.contentof}'/></td>
</tr>

</table>
			<!-- 목록 -->
			<div class="list">
				<td colspan="2"><c:set var="pageNo"
						value="${empty param.pageNo ? '1' : param.pageNo}" />
						<a class="btn" href="${pageContext.request.contextPath}/interiorAsk/listInteriorAsk?page=${param.page}">목록</a>
						<u:isAdmin>
						<a class="btn" href="${pageContext.request.contextPath}/interiorAsk/remove?num=${interiorAskVO.num}">게시글 삭제</a>
						</u:isAdmin>
						<c:if test="${login.email == interiorAskVO.email}">
						<a class="btn" href="${pageContext.request.contextPath}/interiorAsk/modifyInteriorAskForm?num=${interiorAskVO.num}">게시글 수정</a>
						<a class="btn" href="${pageContext.request.contextPath}/interiorAsk/remove?num=${interiorAskVO.num}">게시글 삭제</a>
					</c:if></td>
			</div>
</body>
</html>