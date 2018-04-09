<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head><title><spring:message code="limit" /></title></head>
<body>
	<h1><spring:message code="limit" /></h1>
	<div>
		<span>[${ email }]<spring:message code="limit_p" /></span>
	</div>
</body>
</html>