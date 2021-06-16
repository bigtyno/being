<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>게시글 쓰기</title>
</head>
<body>
<form action="write.do" method="post">
<p>
	제목:<br/><input type="text" name="name" value="${param.name}">
	<c:if test="${errors.name}">제목을 입력하세요.</c:if>
</p>

<p>
	썸네일:<br/>
	<textarea name="thumbnail" rows="5" cols="30">${param.thumbnail}</textarea>
</p>

<p>
	소개이미지:<br/>
	<textarea name="infoimage" rows="5" cols="30">${param.infoimage}</textarea>
</p>

<p>
	내용:<br/>
	<textarea name="introduce" rows="5" cols="30">${param.introduce}</textarea>
</p>

<p>
	가격:<br/>
	<textarea name="PRICE" rows="5" cols="30">${param.PRICE}</textarea>
</p>

<p>
	할인가:<br/>
	<textarea name="DCPRICE" rows="5" cols="30">${param.DCPRICE}</textarea>
</p>

<p>
	브랜드:<br/>
	<textarea name="BRAND" rows="5" cols="30">${param.BRAND}</textarea>
</p>

<p>
	키워드:<br/>
	<textarea name="KEYWD" rows="5" cols="30">${param.KEYWD}</textarea>
</p>


<div id="story-form-select">               
     <div id="story-category">
          <ul class="story-category">
          
        	  <li class="category=ct">
       		   	<select id="category" name="CATEGORY">
       		   	<option value="">-카테고리-</option>
                         <option value="책장">책장</option>
                         <option value="의자">의자</option>
                         <option value="소파">소파</option>
                         <option value="거울">거울</option>
                         <option value="식탁">식탁</option>
                         <option value="수납용품">수납용품</option>
                         <option value="기타">기타</option>
					
				</select>
         	 </li>
          
               <li class="freeyn-ct">
                   <select name = "FREEYN">
                        <option value="Y">무료배송</option>
                        <option value="N">유료배송</option>
                        
                    </select>
                </li>
                
                
                
          </ul>
	</div>  
</div> 

<p>
	링크:<br/>
	<textarea name="LINK" rows="5" cols="30">${param.LINK}</textarea>
</p>



<input type="submit" value="새 글 등록">
</form>
</body>
</html>