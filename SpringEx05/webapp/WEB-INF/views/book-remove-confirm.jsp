<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head><title>도서 삭제 확인 페이지</title></head>
<body>
	<h1>도서 삭제 확인</h1>
	<p> ${ isbn }번 글을 삭제하시겠습니까?</p>
	
	<form action="book-remove.do" method="post">
		<input type="hidden" name="isbn" value="${ isbn }">
		<input type="submit" value="삭제하기">
	</form>
	
	<a href="book-list.do"> 도서 목록 </a>
</body>
</html>