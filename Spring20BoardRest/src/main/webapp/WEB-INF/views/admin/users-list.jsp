<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head><title>사용자 목록</title></head>
<body>
	<h1>사용자 목록</h1>
	<table>
		<thead>
			<tr>
				<th>사용자 번호</th>
				<th>이메일</th>
				<th>이름</th>
				<th>첨부파일</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ list }" var="users">
				<tr>
					<td>${ users.no }</td>
					<td>${ users.email }</td>
					<td>${ users.name }</td>
					<td><img width="100" height="100" src="${ uploadPath }/${ users.attachment }"></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>