<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head><title>글 상세 페이지</title></head>
<body>
	<h1>글 상세 보기</h1>
	<dl>
		<dt>번호</dt><dd>${ board.no }</dd>
		<dt>작성자 번호</dt><dd>${ board.userNo }</dd>
		<dt>작성일</dt><dd>${ board.regdate }</dd>
		<dt>제목</dt><dd>${ board.title }</dd>
		<dt>내용</dt><dd>${ board.content }</dd>
	</dl>
	<a href="board-list.do"> 글 목록 </a>
	<a href="board-remove.do?no=${ board.no }"> 글 삭제 </a>
	<a href="board-modify.do?no=${ board.no }"> 글 수정 </a>

</body>
</html>