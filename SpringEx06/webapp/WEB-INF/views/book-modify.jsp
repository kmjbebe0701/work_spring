<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head><title>도서 수정 페이지</title></head>
<body>
	<h1>도서 수정</h1>

	<form action="book-modify.do" method="post" enctype="multipart/form-data">
		<div>책 번호: ${ book.isbn }</div>
		<div>
			<label>책 제목<input type="text" name="title" value="${ book.title }">	</label>
		</div>
		<div>
			<label>저자<input type="text" name="author" value="${ book.author }">	</label>
		</div>
		<div>
			<label>출판사<input type="text" name="publisher" value="${ book.publisher }">	</label>
		</div>
		<div>
			<label>가격<input type="number" name="price" value="${ book.price }">	</label>
		</div>
		<div>
			<label>책 내용<textarea name="description">${ book.description }</textarea>	</label>
		</div>
		<div>
			<label>첨부파일<input type="file" name="attachment"></label>
		</div>
		<input type="hidden" name="isbn" value="${ book.isbn }">
		<input type="submit" value="도서수정">
		<input type="reset" value="입력한 내용 지우기"><br>
	
	<a href="book-list.do"> 도서 목록</a>
	</form>

</body>
</html>