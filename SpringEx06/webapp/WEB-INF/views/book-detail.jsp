<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head><title>도서 상세 페이지</title></head>
<body>
	<h1>도서 상세 보기</h1>
	<dl>
		<dt>책 번호</dt><dd>${ book.isbn }</dd>
		<dt>책 제목</dt><dd>${ book.title }</dd>
		<dt>저자</dt><dd>${ book.author }</dd>
		<dt>출판사</dt><dd>${ book.publisher }</dd>
		<dt>가격</dt><dd>${ book.price }</dd>
		<dt>책 설명</dt><dd>${ book.description }</dd>
		<c:if test="${ !empty filename }">
			<dt>첨부파일</dt>
			<dd><a href="download.do?filename=${ board.attachment }">${ filename }</a></dd>
		</c:if>
		<c:if test="${ !empty imgPath }">
			<img src="${ imgPath }" alt="이미지 파일 출력 위치">
		</c:if>
	</dl>
	<a href="book-list.do"> 도서 목록 </a>
	<a href="book-remove.do?isbn=${ book.isbn }"> 도서 삭제 </a>
	<a href="book-modify.do?isbn=${ book.isbn }"> 도서 수정 </a>

</body>
</html>