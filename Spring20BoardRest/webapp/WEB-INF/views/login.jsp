<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head><title><spring:message code="login" /></title></head>
<body>
	<h1><spring:message code="login" /></h1>
	<form action="<c:url value='/login-processing' />" method="post">
		<input type="email" name="email" placeholder="<spring:message code="email" />" required>
		<input type="password" name="password" placeholder="<spring:message code="password" />" required>
		<input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }">
		<input type="submit" value="<spring:message code="login" />">

	</form>
	<a href="join.do"><spring:message code="join" /></a>
	<c:if test="${ param.error == 'login' }">
		<p style="color:#ff0000">이메일 혹은 비밀번호를 잘못 입력하셨습니다.</p>
	</c:if>
	<c:if test="${ param.logout == 'true' }">
		<p style="color:#ff0000">로그아웃 하셨습니다.</p>
	</c:if>
</body>
</html>