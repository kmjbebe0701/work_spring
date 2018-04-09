<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head><title><spring:message code="removeCon" /></title></head>
<body>
	<h1><spring:message code="removeCon" /></h1>
	<p><spring:message code="post1" /> ${ no }<spring:message code="post2" /></p>
	<!--
		원래 삭제할 때에는 HTTP Method delete를 사용해야 하지만
		html의 form은 get과 post 두가지만 제공하므로
		delete 대신 post를 사용한다.
	 -->
	<form action="<c:url value='/board/board-remove.do'/> " method="post">
		<input type="hidden" name="no" value="${ no }">
		<input type="submit" value="<spring:message code="remove" />">
		<input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }">
	</form>
	
	<a href="<c:url value='/board/board-list.do'/> "><spring:message code="boardList" /></a>
	
</body>
</html>