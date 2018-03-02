<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head><title><spring:message code="boardList" /></title></head>
<body>
	<h1><spring:message code="boardList" /></h1>
	<a href="<c:url value='/board/board-add.do'/> "><spring:message code="add" /></a>
	<table>
		<thead>
			<tr>
				<th><spring:message code="b_no" /></th>
				<th><spring:message code="title" /></th>
				<th><spring:message code="user" /></th>
				<th><spring:message code="regdate" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ list }" var="board">
				<tr>
					<td>${ board.no } </td>
					<td><a href="<c:url value='/board/board-detail.do?no=${ board.no }'/> ">${ board.title } </a></td>
					<td>${ board.users.name } (${ board.users.email }) </td>
					<td>${ board.regdate } </td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>