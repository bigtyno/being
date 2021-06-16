<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>업체 수정</title>
</head>
<body>
<form action="modify.do" method="post">
<input type="hidden" name="no" value="${modReq.num}">
<p>
	번호:<br/>${modReq.num}
</p>
<p>
	업체명:<br/><input type="text" name="name" value="${modReq.name}">
	<c:if test="${errors.name}">제목을 입력하세요.</c:if>
</p>
<p>
	업체소개:<br/>
	<textarea name="introduce" rows="5" cols="30">${modReq.introduce}</textarea>
</p>

<p>
	이미지A:<br/>
	<textarea name="imageA" rows="5" cols="30">${modReq.imageA}</textarea>
</p>

<p>
	이미지B:<br/>
	<textarea name="imageB" rows="5" cols="30">${modReq.imageB}</textarea>
</p>


<input type="submit" value="상품 수정">
</form>
</body>

</html>