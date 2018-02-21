<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>회원가입 페이지</title>
</head>
<body>
	<h1>회원 가입</h1>
	
	<form action="<c:url value='/join.do'/>" method="post" enctype="multipart/form-data">
			<label>작성자 이메일<input type="email" name="email"></label>

			<label>비밀번호<input type="password" name="password"></label>

			<label>이름<input type="text" name="name"></label>

			<label>첨부파일 <input type="file" name="attachment"></label>

		<input type="submit" value="회원가입">
	</form>
</body>
</html>