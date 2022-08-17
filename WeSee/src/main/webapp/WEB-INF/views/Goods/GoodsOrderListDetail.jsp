<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	
	<!-- GoodsOrderListView.jsp -->

<%@ include file="GoodsMainTop.jsp"%>

	<div class="row">
		<div class="col-md-offset-5 col-md-2">
			<label>구매내역 상세 보기</label>
		</div>
	</div>
	<br><br>
	
	<div class="row">
		<div class="col-md-offset-2 col-md-8">
			<table class="table">
				<caption>주문번호 : ${orderlistDTO.ordercode}<br>주문일자 : ${orderlistDTO.orderdate}</caption>
				<thead>
					<tr>
						<th>이미지</th>
						<th>상품명</th>
						<th>단가</th>
						<th>주문수량</th>
						<th>주문가격</th>
					</tr>
				</thead>
				
				<tbody>
					<c:forEach items="${ordergoodsList}" var="dto">
						<tr>
							<c:choose>
								<c:when test="${empty noImage}">
								<td>
									<a href="go.goodsView.goods?gnum=${dto.gnum}&gspec=${dto.gspec}&gmovie=${dto.gmovie}">
										<img src="filePath1/${dto.gimage}" style="width:100px;height:100px">
									</a>
								</td>
								</c:when>
								
								<c:otherwise>
								<td>
									<label>이미지가 없는<br>상품입니다.</label>
								</td>
								</c:otherwise>
							</c:choose>
							<td>${dto.gname}</td>
							<td><fmt:formatNumber value="${dto.gprice}" pattern="###,###"/></td>
							<td>${dto.gqty}</td>
							<td><fmt:formatNumber value="${dto.gqty * dto.gprice}" pattern="###,###"/></td>
						</tr>
					</c:forEach>
				</tbody>
				
				<tfoot>
					<tr>
						<td colspan="5" align="right">총 결제액 : <fmt:formatNumber value="${orderlistDTO.totalpay}" pattern="###,###"/></td>
					</tr>
				</tfoot>
			</table>
		</div>
	</div>
	<br>
	
	<div class="row">
		<div class="col-md-offset-5 col-md-1">
			<input class="btn btn-primary btn-fluid" type="button" value="구매 목록으로" onclick="window.location='goOrderListView.goods'">
		</div>
		
		<div class="col-md-1">
			<input class="btn btn-success btn-fluid" type="button" value="굿즈샵 홈으로" onclick="window.location='goods.goods'">
		</div>
	</div>
	<br>
</body>
</html>