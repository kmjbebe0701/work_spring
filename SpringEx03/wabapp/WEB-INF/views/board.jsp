<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head><title>게시물 정보</title></head>
<body>
	<h1>게시물 정보</h1>
	<ul>
		<li>${ board.no }</li>
		<li>${ board.title }</li>
		<li>${ board.content }</li>
		<li>${ board.userNo }</li>
		<li>${ board.regdate }</li>
	</ul>
</body>
</html>