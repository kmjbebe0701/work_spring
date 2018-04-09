<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head><title>로그인 페이지</title></head>
<body>
	<h1>로그인 페이지</h1>
	<form action="<c:url value='/login-processing'/>" method="post">
		<input type="email" name="email" placeholder="이메일 입력" required>
		<input type="password" name="password" placeholder="비밀번호 입력" required>
		<input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }">
		<input type="submit" value="로그인">
	</form>
	<a href="<c:url value='/join.do'/>">회원가입</a>
	<c:if test="${ param.error == 'login' }">
		<p style="color:#FF0000">이메일 혹은 비밀번호를 잘못 입력하셨습니다.</p>
	</c:if>
	<c:if test="${ param.logout == 'true' }">
		<p style="color:#FF0000">로그아웃 하였습니다.</p>
	</c:if>
</body>
</html>