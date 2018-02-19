<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head><title>게시물 수정</title></head>
<body>
	<h1>게시물 수정</h1>
	<form action="board-modify.do" method="post">
		<div>글 번호: ${ board.no }</div>
		<div>작성자 번호: ${ board.userNo }</div>
		<div>
			<label>제목<input type="text" name="title" value="${ board.title }"></label>
		</div>
		<div>
			<label>내용</label>
			<textarea name="content">${ board.content }</textarea>
		</div>
		<input type="hidden" name="no" value="${ board.no }">
		<input type="submit" value="글 수정">
		<input type="reset" value="입력한 내용 지우기">
		<a href="board-list.do">글 목록으로 이동</a>
	</form>
</body>
</html>