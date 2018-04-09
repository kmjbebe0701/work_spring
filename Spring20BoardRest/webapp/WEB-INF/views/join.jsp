<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title><spring:message code="join" /></title>
</head>
<body>
	<h1><spring:message code="join" /></h1>
	
	<form action="<c:url value='/join.do'/>" method="post" enctype="multipart/form-data">
			<label><spring:message code="email" /><input type="email" name="email"></label>

			<label><spring:message code="password" /><input type="password" name="password"></label>

			<label><spring:message code="name" /><input type="text" name="name"></label>

			<label><spring:message code="u_attachment" /> <input type="file" name="attachment"></label>

		<input type="submit" value="<spring:message code='join' />">
		<input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }">
	</form>
</body>
</html>