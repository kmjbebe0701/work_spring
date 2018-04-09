<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head><title>가입을 환영합니다.</title></head>
<body>
	<h1>${ name }님 가입을 환영합니다.</h1>
	<a href="<c:url value='/index.do'/>">홈으로 이동</a>
</body>
</html>