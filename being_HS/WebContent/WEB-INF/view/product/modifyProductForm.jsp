<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>게시글 수정</title>
</head>
<body>
<form action="modify.do" method="post">
<input type="hidden" name="no" value="${modReq.num}">
<p>
	번호:<br/>${modReq.num}
</p>
<p>
	제목:<br/><input type="text" name="name" value="${modReq.name}">
	<c:if test="${errors.name}">제목을 입력하세요.</c:if>
</p>
<p>
	내용:<br/>
	<textarea name="thumbnail" rows="5" cols="30">${modReq.thumbnail}</textarea>
</p>

<div id="story-form-select">               
     <div id="story-category">
          <ul class="story-category">
          
        	  <li class="type=ct">
       		   	<select id="type" name="infoimage" size="1">
       		   	<option value="">-주거형태-</option>
                         <option value="원룸&오피스텔">원룸,오피스텔</option>
                         <option value="아파트">아파트</option>
                         <option value="빌라&연합">빌라,연합</option>
                         <option value="단독주택">단독주택</option>
                         <option value="사무공간">사무공간</option>
                         <option value="상업공간">상업공간</option>
                         <option value="기타">기타</option>
					
				</select>
         	 </li>
          
               <li class="acreage-ct">
                   <select name = "introduce">
                        <option value="">-평수-</option>
                        <option value="10평 미만">10평 미만</option>
                        <option value="10평대">10평대</option>
                        <option value="20평대">20평대</option>
                        <option value="30평대">30평대</option>
                        <option value="40평대">40평대</option>
                        <option value="50평대 이상">50평대 이상</option>
                    </select>
                </li>
                
                 <li class="budget-ct">
                   <select name = "price">
                        <option value="">1000</option>
                        <option value="1백만원 미만">100</option>
                        <option value="1백만원대">100</option>
                        <option value="2백만원대">200</option>
                        <option value="3백만원대">300</option>
                        <option value="4백만원대">400</option>
                        <option value="5백만원 이상">500</option>
                    </select>
                </li>
                
                
                <li class="field-ct">
                    <select name="dcprice">
                        <option value="">40</option>
						<option value="리모델링">100</option>
						<option value="홈스타일링">200</option>
						<option value="부분 공사">300</option>
                     </select>
                </li>
                
                
                <li class="space-ct">
                   <select name = "brand">
                        <option value="">-공간별-</option>
                        <option value="화장실">화장실</option>
                        <option value="거실">거실</option>
                        <option value="방">방</option>
                        <option value="베란다">베란다</option>
                    </select>
                </li>
                
                
          </ul>
	</div>  
</div> 

<input type="submit" value="글 수정">
</form>
</body>


</html>