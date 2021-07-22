<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>업체 답변</title>
</head>
<body>
<form role="form" method="post">
<input type="hidden" name="num" value="${interiorAskVO.num}">
<p>
	번호:<br/>${interiorAskVO.num}
</p>
<p>
	신청 제목:<br/>${interiorAskVO.title}
</p>
<p>
	업체명:<br/>
	${interiorAskVO.name}
</p>

<p>
	공급 면적<br/>
	${interiorAskVO.area}
</p>

<p>
	시공 분야<br/>
	${interiorAskVO.fieldof}
</p>
<p>
	시공 주소<br/>
	${interiorAskVO.address}
</p>

<p>
	시공 희망일<br/>
	${interiorAskVO.datestart}
</p>

<p>
	완료 희망일<br/>
	${interiorAskVO.datedone}
</p>

<p>
     예산<br/>
	${interiorAskVO.budget}
</p>

<p>
	전달 사항<br/>
	${interiorAskVO.message}
</p>

<p>
	전화번호:<br/>
	${interiorAskVO.tel}
</p>

<u:isAdmin>
<p>
	업체 답변:<br/>
	<textarea name="answer" rows="5" cols="30">${interiorAskVO.answer}</textarea>
</p>
</u:isAdmin>

<u:isGeneral>
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
            <textarea rows="10" class="review_textarea" name="contentOf">${interiorAskVO.contentof}</textarea>
        </div>   
</div>
</u:isGeneral>
<input type="submit" value="답변 등록">
</form>
</body>

</html>