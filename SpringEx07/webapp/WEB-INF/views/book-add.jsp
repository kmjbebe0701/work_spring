<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head><title>도서 추가 페이지</title></head>
<body>
	<h1>도서 추가</h1>
	<form action="<c:url value='/book/book-add.do'/> " method="post" enctype="multipart/form-data">
		<div>
			<span>작성자: </span>
			<span>${ users.name } (${ users.email })</span>
		</div>
		<div>
			<label>책 제목<input type="text" name="title">	</label>
		</div>
		<div>
			<label>저자<input type="text" name="author">	</label>
		</div>
		<div>
			<label>출판사<input type="text" name="publisher">	</label>
		</div>
		<div>
			<label>가격<input type="number" name="price">	</label>
		</div>
		<div>
			<label>책 내용<textarea name="description"></textarea>	</label>
		</div>
		<div>
			<label>첨부파일<input type="file" name="attachment"></label>
		</div>
		<input type="submit" value="도서등록">
		<input type="reset" value="입력한 내용 지우기"><br>
	
	<a href="<c:url value='book-list.do'/> "> 도서 목록</a>
	<input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }">
	<input type="hidden" name="userNo" value="${ users.no }"> 
	</form>
</body>
</html>