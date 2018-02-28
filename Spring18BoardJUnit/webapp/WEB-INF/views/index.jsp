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
	<a href="<c:url value='admin/users-list.do'/> ">사용자 목록으로 이동</a>
	
	<sec:authorize access="!hasRole('ADMIN') and !hasRole('USER')">
	<a href="<c:url value='/join.do'/> ">회원 가입</a>
	<a href="<c:url value='/login.do'/> ">로그인</a>
	</sec:authorize>
	<sec:authorize access="hasRole('ADMIN') or hasRole('USER')">
	<a href="<c:url value='/users-modify.do'/> ">회원정보수정</a>
	<a href="<c:url value='/logout.do'/> ">로그아웃</a>
	</sec:authorize>
</body>
</html>