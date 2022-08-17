<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<%@ include file="AdminSaleTop.jsp"%>
	
	<!-- AdminSaleDetail.jsp -->
	
	<div class="row">
		<div class="col-md-offset-2 col-md-8">
			<table class="table">
				<caption>주문번호 : ${orderlistDTO.ordercode}<br>주문일자 : ${orderlistDTO.orderdate}</caption>
				<thead>
					<tr>
						<th colspan="5">
							<div align="left">구매회원 : ${mdto.name}</div>
						</th>
					</tr>
				</thead>
				
				<tbody>
					<tr>
						<th>이미지</th>
						<th>상품명</th>
						<th>단가</th>
						<th>주문수량</th>
						<th>주문가격</th>
					</tr>
					
					<c:forEach items="${ordergoodsList}" var="dto">
						<tr>
							<c:choose>
								<c:when test="${empty noImage}">
								<td>
									<img src="filePath1/${dto.gimage}" style="width:100px;height:100px">
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
						<td colspan="5" align="right">
							<div align="right">
								총 결제액 : <fmt:formatNumber value="${orderlistDTO.totalpay}" pattern="###,###"/>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
	</div>
	<br>
	
	<div class="row">
		<div class="col-md-offset-5 col-md-1">
			<input class="btn btn-primary btn-fluid" type="button" value="돌아가기" onclick="history.back()">
		</div>
	</div>
	<br>
</body>
</html>