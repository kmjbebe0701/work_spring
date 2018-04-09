<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head><title>게시물 수정</title></head>
<body>
	<h1>게시물 수정</h1>
	<form action="<c:url value='/board/board-modify.do'/>" method="post" enctype="multipart/form-data">
		<div>글 번호: ${ board.no }</div>
		<div>작성자 번호: ${ board.userNo }</div>
		<div>
			<label>제목<input type="text" name="title" value="${ board.title }"></label>
		</div>
		<div>
			<label>내용</label>
			<textarea name="content">${ board.content }</textarea>
		</div>
		<div>
			<label>첨부파일 <input type="file" name="attachment"></label>
		</div>
		<input type="hidden" name="no" value="${ board.no }">
		<input type="submit" value="글 수정">
		<input type="reset" value="입력한 내용 지우기">
		<a href="<c:url value='/board/board-list.do'/>">글 목록으로 이동</a>
		<input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }">
	</form>
</body>
</html>