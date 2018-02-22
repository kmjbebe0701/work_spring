<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head><title>도서 추가 페이지</title></head>
<body>
	<h1>도서 추가</h1>
	<form action="book-add.do" method="post" enctype="multipart/form-data">
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
	
	<a href="book-list.do"> 도서 목록</a>
	
	</form>
</body>
</html>