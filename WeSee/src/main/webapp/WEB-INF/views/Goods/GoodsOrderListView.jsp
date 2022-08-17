<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	
	<!-- GoodsOrderListView.jsp -->

<%@ include file="GoodsMainTop.jsp"%>

	<div class="row">
		<div class="col-md-offset-5 col-md-2">
			<label>내 구매내역</label>
		</div>
	</div>
	<br><br>
	
	<div class="row">
		<div class="col-md-offset-2 col-md-8">
			<table class="table">
				<thead>
					<tr>
					<th style="width:20%">주문번호</th>
					<th style="width:40%">주문상품</th>
					<th style="width:20%">주문일자</th>
					<th style="width:20%">결제금액</th>
					</tr>
				</thead>
				
				<tbody>
					<c:if test="${empty orderlist}">
						<tr><td colspan="4">주문내역이 없습니다.</td></tr>
					</c:if>
					
					<c:forEach items="${orderlist}" var="odto">
						<tr>
							<td>${odto.ordercode}</td>
							<td><a href="goOrderlistDetail.goods?ordernum=${odto.ordernum}">${odto.ordergoods}</a></td>
							<td>${odto.orderdate}</td>
							<td>${odto.totalpay}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<br><br>
	
</body>
</html>