<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head><title>유효성 검사 오류 페이지</title></head>
<body>
	<h1>유효성 검사 오류 페이지</h1>
	<p>
		<spring:bind path="customer.name">
			${ status.expression }:${ status.errorMessages[0] }
		</spring:bind>
	</p>
	<p>
		<spring:bind path="customer.age">
			${ status.expression }:${ status.errorMessages[0] }
		</spring:bind>
	</p>
	
	
</body>
</html>