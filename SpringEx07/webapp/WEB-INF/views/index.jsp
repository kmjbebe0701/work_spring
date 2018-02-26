<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head><title>도서 관리 프로그램</title>
</head>
<body>
	<h1>도서 관리 프로그램</h1>
	
	<a href="<c:url value='/book/book-list.do'/> ">도서 목록</a><br>
	
	<sec:authorize access="hasRole('ADMIN')">
	<a href="<c:url value='/admin/users-list.do'/> ">사용자 목록</a><br>
	</sec:authorize>
	
	<sec:authorize access="!hasRole('ADMIN') and !hasRole('USER')">
	<a href="<c:url value='/join.do'/> ">회원가입</a><br>
	<a href="<c:url value='/login.do'/> ">로그인</a><br>
	</sec:authorize>
	
	<sec:authorize access="hasRole('ADMIN') or hasRole('USER')">
	<a href="<c:url value='/users-modify.do'/> ">회원정보수정</a><br>
	<a href="<c:url value='/logout.do'/> ">로그아웃</a><br>
	</sec:authorize>


</body>
</html>