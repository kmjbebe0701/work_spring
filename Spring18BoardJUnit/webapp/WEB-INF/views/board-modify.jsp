<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head><title><spring:message code="modify" /></title></head>
<body>
	<h1><spring:message code="modify" /></h1>
	<form action="<c:url value='/board/board-modify.do'/> " method="post" enctype="multipart/form-data">
		<div><spring:message code="b_no" />: ${ board.no }</div>
		<div><spring:message code="u_no" />: ${ board.userNo }</div>
		<div>
			<label><spring:message code="title" /><input type="text" name="title" value="${ board.title }"></label>
		</div>
		<div>
			<label><spring:message code="content" /></label>
			<textarea name="content">${ board.content }</textarea>
		</div>
		<div>
			<label><spring:message code="b_attachment" /><input type="file" name="attachment"></label>
		</div>
		<input type="hidden" name="no" value="${ board.no }">
		<input type="submit" value=<spring:message code='modify' />">
		<input type="reset" value="<spring:message code='remove' />">
		<a href="<c:url value='/board/board-list.do'/> "><spring:message code="boardList" /></a>
		<input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }">
	</form>
</body>
</html>