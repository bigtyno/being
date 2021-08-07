<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>시공 후기</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/interior.css">
<link href="https://fonts.googleapis.com/css2?family=Squada+One&display=swap" rel="stylesheet">

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

<p class="box-title">인테리어 신청서</p>

<div class="ask-box">
<form action="${pageContext.request.contextPath}/interiorAsk/readInteriorAsk" method="post">
<input type="hidden" name="email" value="${login.email}">
<input type="hidden" name="name" value="${login.name}">
<div class="all-ask">
<p>
	제목 :
	<input type="text" name="title" value="${interiorAskVO.title}">
</p>
<p>신청자 :
	<input type="text" name="name" value="${interiorAskVO.name}"> 
</p>
<p>업체명 :
	<input type="text" name="cname" value="${interiorAskVO.cname}"> 
</p>
<p>평수 :
	<input type="text" name="area" value="${interiorAskVO.area}"> 
</p>
<p>시공 분야 :
	<input type="text" name="fieldof" value="${interiorAskVO.fieldof}"> 
</p>
<p>시공 희망일 :
	<input type="text" name="datestart" value="${interiorAskVO.datestart}"> 
</p>
<p>시공 완료일 :
	<input type="text" name="datedone" value="${interiorAskVO.datedone}"> 
</p>
<p>시공 주소 :
	<input type="text" name="address" value="${interiorAskVO.address}"> 
</p>

<p>희망 예산 :
<input type="text" name="budget" value="${interiorAskVO.budget}"> 
</p>

<div class="ask-message">
<p>전달사항 :
	<input type="text" name="message" value="${interiorAskVO.message}">
</p>
</div>

<p>연락처 :
	<input type="text" name="tel" value="${interiorAskVO.tel}">
</p>
<div class="ask-message">
<p>업체답변 :
	<input type="text" name="answer" value="${interiorAskVO.answer}">
</p>
</div>
<p>별점 :
	<input type="text" name="grade" value="${interiorAskVO.grade}">
</p>
<p>총평 :
	<input type="text" name="contentof" value="${interiorAskVO.contentof}">
</p>
<p>첨부파일 :
	<input type="text" name="imagea" value="${interiorAskVO.imagea}">
</p>
</div>


<div>
	<c:if test="${UserVO.lvl==1 || UserVO.lvl==3}">
		<a class="btn" href="${pageContext.request.contextPath}/interiorAsk/modifyInteriorAsk2?num=${interiorAskVO.num}">후기 수정</a>
	</c:if>	
	<c:if test="${UserVO.lvl==1}">	
		<a class="btn" href="${pageContext.request.contextPath}/interiorAsk/remove?num=${interiorAskVO.num}">게시글 삭제</a>
	</c:if>
</div>


</form>
</div>

</body>


</html>