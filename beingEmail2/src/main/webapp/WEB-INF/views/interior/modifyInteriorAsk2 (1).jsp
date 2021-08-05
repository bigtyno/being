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
         <a href="${pageContext.request.contextPath}/index">
            <img src="${pageContext.request.contextPath}/resources/img/logo4.png" alt="" title="" width="75px" height="75px"/>
         Being House
         </a>
        	 
      </div>
   
</header>
<body>

<p class="box-title">인테리어 신청하기</p>

<div class="ask-box">
<form action="${pageContext.request.contextPath}/interiorAsk/modifyInteriorAsk2?num=${interiorAskVO.num}" method="post">
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

<p>
	연락처 :
	<input type="text" name="tel" value="${interiorAskVO.tel}">
</p>
<div class="ask-message">
<p>업체 답변:
	<input type="text" name="answer" value="${interiorAskVO.answer}">
</p>
</div>

<c:if test="${login.email == interiorAskVO.email}">
<div class="wrap">
    <h2>후기</h2>
        <p class="title_star">별점과 총평을 남겨주세요.</p>
 
        <div class="review_rating">
            <div class="warning_msg">별점을 선택해 주세요.</div>
            <div class="rating">
                <!-- 해당 별점을 클릭하면 해당 별과 그 왼쪽의 모든 별의 체크박스에 checked 적용 -->
                <input type="checkbox" name="grade" id="rating1" value="1" class="rate_radio" title="1점">
                <label for="rating1"></label>
                <input type="checkbox" name="grade" id="rating2" value="2" class="rate_radio" title="2점">
                <label for="rating2"></label>
                <input type="checkbox" name="grade" id="rating3" value="3" class="rate_radio" title="3점" >
                <label for="rating3"></label>
                <input type="checkbox" name="grade" id="rating4" value="4" class="rate_radio" title="4점">
                <label for="rating4"></label>
                <input type="checkbox" name="grade" id="rating5" value="5" class="rate_radio" title="5점">
                <label for="rating5"></label>
            </div>
        </div>
        <div class="review_contentOf">
            <div class="warning_msg">5자 이상으로 작성해 주세요.</div>
            <textarea rows="10" class="review_textarea" name="contentof">${modReq.contentof}</textarea>
        </div>   
</div>

<p>첨부파일 :
	<textarea name="answer" rows="5" cols="30">${modReq.imagea}</textarea>
</p>
</c:if>
<input type="submit" value="후기 등록">
</div>


</form>
</div>

</body>


</html>