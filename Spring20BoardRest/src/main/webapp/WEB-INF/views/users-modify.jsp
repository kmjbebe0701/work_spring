<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head><title>회원정보변경</title></head>
<body>
	<h1>회원정보변경</h1>
	<form action="<c:url value='/users-modify.do'/>" method="post" enctype="multipart/form-data">
		<span>이메일: ${ users.email }</span><br>
		<label>기존 비밀번호 <input type="password" name="oldPassword"></label><br>
		<label>변경할 비밀번호 <input type="password" name="newPassword"></label><br>
		<label>이름 <input type="text" name="name" value="${ users.name }"></label><br>
		<label>첨부 파일 <input type="file" name="attachment"></label><br>
		<img width="50" height="50" alt="기존프로필" src="${ uploadPath }/${ users.attachment }"><br>
		<input type="submit" value="회원정보변경"><br>
		<input type="hidden" name="${ _csrf.parameterName }" value="${ _csrf.token }">
	</form>
	<c:if test="${ param.error == 'password' }">
		<div style="color:#ff0000">기존 비밀번호를 다시 입력해주세요.</div>
	</c:if>
</body>
</html>