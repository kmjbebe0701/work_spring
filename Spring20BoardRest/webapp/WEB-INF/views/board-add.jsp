<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head><title><spring:message code="add" /></title></head>
<body>
	<h1><spring:message code="add" /></h1>
	<form action="<c:url value='/board/board-add.do'/>" method="post" enctype="multipart/form-data">

		<div>
			<span><spring:message code="user" />: </span>
			<span>${ users.name } (${ users.email })</span>
		</div>
		<div>
			<label><spring:message code="title" /><input type="text" name="title"></label>
		</div>
		<div>
			<label><spring:message code="content" /></label>
			<textarea name="content"></textarea>
		</div>
		<div>
			<label><spring:message code="b_attachment" /> <input type="file" name="attachment"></label>
		</div>
		<input type="submit" value="<spring:message code='add' />">
		<input type="reset" value="<spring:message code="remove" />">
		<a href="<c:url value='/board/board-list.do'/> "><spring:message code="boardList" /></a>
		<input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }">
		<input type="hidden" name="userNo" value="${ users.no }"> 
	</form>
</body>
</html>