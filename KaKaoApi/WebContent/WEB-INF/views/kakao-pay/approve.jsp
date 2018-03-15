<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<style>
		.table { border-collapse: collapse }
		.table th, .table td { border: 1px solid black }
	</style>
	<title>결제승인</title>
</head>
<body>
	<h1>결제승인</h1>
	<p>아래는 결제승인 결과입니다.</p>
	<a href="<c:url value='/kakao-pay/ready.do' />">카카오페이 주문하기 화면</a>
	
	<h2>기본 정보</h2>
	<table class="table">
		<tbody>
			<tr><th>Request 고유 번호</th><td>${ result.aid }</td></tr>
			<tr><th>결제 고유 번호</th><td>${ result.tid }</td></tr>
			<tr><th>가맹점 코드</th><td>${ result.cid }</td></tr>
			<tr><th>subscription id. 정기(배치)결제 CID로 결제요청한 경우 발급</th><td>${ result.sid }</td></tr>
			<tr><th>가맹점 주문번호</th><td>${ result.partner_order_id }</td></tr>
			<tr><th>가맹점 회원 id</th><td>${ result.partner_user_id }</td></tr>
			<tr><th>결제 수단. CARD, MONEY 중 하나</th><td>${ result.payment_method_type }</td></tr>
			<tr><th>상품 이름. 최대 100자</th><td>${ result.item_name }</td></tr>
			<tr><th>상품 코드. 최대 100자</th><td>${ result.item_code }</td></tr>
			<tr><th>상품 수량</th><td>${ result.quantity }</td></tr>
			<tr><th>결제 준비 요청 시각</th><td>${ result.created_at }</td></tr>
			<tr><th>결제 승인 시각</th><td>${ result.approved_at }</td></tr>
			<tr><th>Request로 전달한 값</th><td>${ result.payload }</td></tr>
		</tbody>
	</table>
	
	<h2>amount 정보</h2>
	<table class="table">
		<tbody>
			<tr><th>전체 결제 금액</th><td>${ result.amount.total }</td></tr>
			<tr><th>비과세 금액</th><td>${ result.amount.tax_free }</td></tr>
			<tr><th>부가세 금액</th><td>${ result.amount.vat }</td></tr>
			<tr><th>사용한 포인트 금액</th><td>${ result.amount.point }</td></tr>
			<tr><th>할인 금액</th><td>${ result.amount.discount }</td></tr>
		</tbody>
	</table>
	
	<h2>card_info 정보</h2>
	<table class="table">
		<tbody>
			<tr><th>매입카드사 한글명</th><td>${ result.card_info.purchase_corp }</td></tr>
			<tr><th>매입카드사 코드</th><td>${ result.card_info.purchase_corp_code }</td></tr>
			<tr><th>카드발급사 한글명</th><td>${ result.card_info.issuer_corp }</td></tr>
			<tr><th>카드발급사 코드</th><td>${ result.card_info.issuer_corp_code }</td></tr>
			<tr><th>카드 BIN</th><td>${ result.card_info.bin }</td></tr>
			<tr><th>카드타입</th><td>${ result.card_info.card_type }</td></tr>
			<tr><th>할부개월수</th><td>${ result.card_info.install_month }</td></tr>
			<tr><th>카드사 승인번호</th><td>${ result.card_info.approved_id }</td></tr>
			<tr><th>카드사 가맹점번호</th><td>${ result.card_info.card_mid }</td></tr>
			<tr><th>무이자할부 여부(Y/N)</th><td>${ result.card_info.interest_free_install }</td></tr>
			<tr><th>카드 상품 코드</th><td>${ result.card_info.card_item_code }</td></tr>
		</tbody>
	</table>

</body>
</html>