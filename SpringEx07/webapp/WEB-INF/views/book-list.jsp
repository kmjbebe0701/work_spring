<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head><title>도서 목록 페이지</title></head>
<body>
	<h1>도서 목록</h1>
	<a href="<c:url value='/book/book-add.do'/> ">도서 추가</a>
	<table>
		<thead>
			<tr>
				<th>책 번호</th>
				<th>책 제목</th>
				<th>저자</th>
				<th>출판사</th>
				<th>가격</th>
				<th>책 설명</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ list }" var="book">
				<tr>
					<td>${ book.isbn }</td>
					<td><a href="<c:url value='/book/book-detail.do?isbn=${ book.isbn }'/> ">${ book.title }</a></td>
					<td>${ book.author }</td>
					<td>${ book.publisher }</td>
					<td>${ book.price }</td>
					<td>${ book.description }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="<c:url value='/index.do'/> ">홈으로</a>
</body>
</html>