<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head><title>글 삭제 확인화면</title></head>
<body>
	<h1>글 삭제 확인</h1>
	<p>정말로 ${ no }번 글을 삭제하시겠습니까?</p>
	<!--
		원래 삭제할 때에는 HTTP Method delete를 사용해야 하지만
		html의 form은 get과 post 두가지만 제공하므로
		delete 대신 post를 사용한다.
	 -->
	<form action="board-remove.do" method="post">
		<input type="hidden" name="no" value="${ no }">
		<input type="submit" value="삭제하기">
	</form>
	<a href="board-list.do">글 목록으로 이동</a>
</body>
</html>