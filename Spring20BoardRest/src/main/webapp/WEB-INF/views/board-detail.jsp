<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head><title>글 상세 보기</title></head>
<body>

	<h1>글 상세 보기</h1>
	<dl>
		<dt>번호</dt><dd>${ board.no }</dd>
		<dt>작성자</dt><dd>${ board.users.name }(${ board.users.email })</dd>
		<dt>작성자 사진</dt><dd><img src="${ uploadPath }/${ board.users.attachment }"></dd>
		<dt>작성일</dt><dd>${ board.regdate }</dd>
		<dt>제목</dt><dd>${ board.title }</dd>
		<dt>내용</dt><dd>${ board.content }</dd>
		<c:if test="${ !empty filename }">
			<dt>첨부파일</dt>
			<dd><a href="<c:url value='/download.do?filename=${ board.attachment }'/>">${ filename }</a></dd>
		</c:if>
		<c:if test="${ !empty imgPath }">
			<img src="${ imgPath }" alt="이미지 파일 출력위치">
		</c:if>
	</dl>
	<a href="<c:url value='/board/board-list.do'/>">글 목록으로 이동</a>
	<a href="<c:url value='/board/board-modify.do?no=${ board.no }'/>">수정하기</a>
	<a href="<c:url value='/board/board-remove.do?no=${ board.no }'/>">삭제하기</a>
</body>
</html>