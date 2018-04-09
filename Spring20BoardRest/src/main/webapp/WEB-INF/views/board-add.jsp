<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head><title>게시물 작성</title></head>
<body>
	<h1>게시물 작성</h1>
	<form action="<c:url value='/board/board-add.do'/>" method="post" enctype="multipart/form-data">
		<div>
			<span>작성자: </span>
			<span>${ users.name }(${ users.email })</span>
		</div>
		<div>
			<label>제목<input type="text" name="title"></label>
		</div>
		<div>
			<label>내용</label>
			<textarea name="content"></textarea>
		</div>
		<div>
			<label>첨부파일 <input type="file" name="attachment"></label>
		</div>
		<input type="submit" value="글 작성">
		<input type="reset" value="입력한 내용 지우기">
		<a href="<c:url value='/board/board-list.do'/>">글 목록으로 이동</a>
		<input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }">
		<input type="hidden" name="userNo" value="${ users.no }">
	</form>
</body>
</html>