<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>업체관리</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/interiorwrite.css">
</head>

<header>
<div class="login-logo">
         <a href="${pageContext.request.contextPath}/main/index.do">
            <img src="${pageContext.request.contextPath}/resources/img/logo4.png" alt="" title="" width="75px" height="75px"/>
         Being House
         </a>
      </div>
</header>
<body>
<form action="write.do" method="post">

<p>
	업체명:<br/><input type="text" name="name" value="${param.name}">
	<c:if test="${errors.name}">업체명을 입력하세요.</c:if>
</p>
<p>
	업체소개:<br/>
	<textarea name="introduce" rows="5" cols="30">${param.introduce}</textarea>
</p>
<p>
	이미지A:<br/>
	<textarea name="imageA" rows="5" cols="30">${param.imageA}</textarea>
</p>
<p>
	이미지B:<br/>
	<textarea name="imageB" rows="5" cols="30">${param.imageB}</textarea>
</p>
<input type="submit" value="업체 등록">
</form>
</body>
</html>