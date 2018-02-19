<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head><title>게시판 목록</title></head>
<body>
	<h1>게시판 목록</h1>
	<a href="board-add.do">글쓰기</a>
	<table>
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자 번호</th>
				<th>작성일</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ list }" var="board">
				<tr>
					<td>${ board.no }</td>
					<td><a href="board-detail.do?no=${ board.no }">${ board.title }</a></td>
					<td>${ board.userNo }</td>
					<td>${ board.regdate }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>