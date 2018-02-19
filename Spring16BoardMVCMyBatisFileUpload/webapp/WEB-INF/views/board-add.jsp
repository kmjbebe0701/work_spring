<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head><title>글 작성 페이지</title></head>
<body>
	<h1> 글 작성 페이지 </h1>
	<form action="board-add.do" method="post">
		<div>
			<label>작성자 번호<input type="text" name="userNo"></label>
		</div>
		<div>
			<label>제목<input type="text" name="title"></label>
		</div>
		<div>
			<label>내용</label><textarea name="content"></textarea>
		</div>
		<input type="submit" value="글작성">
		<input type="reset" value="입력한 내용 지우기"><br>
	
	<a href="board-list.do"> 글 목록</a>
	</form>
	
</body>
</html>