<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head><title>홈페이지</title>
</head>
<body>
	<h1>홈페이지</h1>
	<a href="<c:url value='/board/board-list.do'/> ">글 목록으로 이동</a>
	<a href="admin/users-list.do">사용자 목록으로 이동</a>
	
	<c:if test="${ isLogin == 'false' }">
	<a href="join.do">회원 가입</a>
	<a href="login.do">로그인</a>
	</c:if>
	<c:if test="${ isLogin == 'true' }">
	<a href="logout.do">로그아웃</a>
	</c:if>
</body>
</html>