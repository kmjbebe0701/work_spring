<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head><title><spring:message code="detail" /></title></head>
<body>

	<h1><spring:message code="detail" /></h1>
	<dl>
		<dt><spring:message code="b_no" /></dt><dd>${ board.no }</dd>
		<dt><spring:message code="user" /></dt><dd>${ board.users.name } (${ board.users.email })</dd>
		<dt><spring:message code="u_attachment" /></dt><dd><img src="${ uploadPath }/${ board.users.attachment }" alt="작성자 파일 출력 위치"></dd>
		<dt><spring:message code="regdate" /></dt><dd>${ board.regdate }</dd>
		<dt><spring:message code="title" /></dt><dd>${ board.title }</dd>
		<dt><spring:message code="content" /></dt><dd>${ board.content }</dd>
		<c:if test="${ !empty filename }">
			<dt><spring:message code="b_attachment" /></dt>
			<dd><a href="<c:url value='/download.do?filename=${ board.attachment }'/> ">${ filename }</a></dd>
		</c:if>
		<c:if test="${ !empty imgPath }">
			<img src="${ imgPath }" alt="이미지 파일 출력 위치">
		</c:if>
	</dl>
	<a href="<c:url value='/board/board-list.do'/> "><spring:message code="boardList" /></a>
	<a href="<c:url value='/board/board-modify.do?no=${ board.no }'/> "><spring:message code="modify" /></a>
	<a href="<c:url value='/board/board-remove.do?no=${ board.no }'/> "><spring:message code="remove" /></a>
</body>
</html>