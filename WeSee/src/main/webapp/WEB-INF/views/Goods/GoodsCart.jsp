<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- goodswishList.jsp -->

<%@ include file="GoodsMainTop.jsp" %>
<link rel="stylesheet" href="resources/cartstyle.css">

<script type="text/javascript">
	// bool = this.checked : name이 all인 체크박스의 체크속성(true, false)
	function allselect(bool){
		chks = document.getElementsByName("chk");
		for(i = 0; i < chks.length; i++){
			chks[i].checked = bool;
		}
	}
	function checkDel(gnum, gimage) { //삭제 버튼 메세지
		var isDel = window.cofirm("상품을 삭제하시겠습니까?")
		if (isDel){
			document.f.gnum.value = gnum			
			document.f.submit()
		}
	}
	function onlyNumber(obj) {
		obj.value = obj.value.replace(/[^0-9]/g, "");
	}
</script>

<style>
	th, td {
	text-align: center;
    }
</style>
	
	<div class="row">
		<div class="col-md-offset-1 col-md-10">
		<table style="width:100%;" class="cart_list">
			<thead>
				<tr style="height:80px">
					<th width="30%"><p style="text-align:center;">이미지</p></th>
					<th width="20%"><p style="text-align:center;">상품명</p></th>
					<th width="15%"><p style="text-align:center;">수량</p></th>
					<th width="15%"><p style="text-align:center;">상품금액</p></th>
					<c:if test="${not empty listSize}">
						<th rowspan="${listSize}" width="10%"><p style="text-align:center;">배송비</p></th>
					</c:if>
					<c:if test="${empty listSize}">
						<th width="10%"><p style="text-align:center;">배송비</p></th>
					</c:if>
					<th width="10%"></th>
				</tr>
			</thead>
			
			<tbody>
				<c:if test="${empty cart}">
					<tr style="height:80px">
						<th colspan="7">
							<p style="text-align:center;">등록된 상품이 없습니다.</p>
						</th>
					</tr>
				</c:if>
				
				<c:if test="${not empty cart}">	
				<c:set var="cartTotlaPrice" value="0"/>	
						
					<c:forEach items="${cart}" var="gdto">
						<c:set var="cartTotlaPrice" value="${cartTotalPrice + (gdto.gprice * gdto.gqty)}"/>	
									
						<tr style="height:80px">
							<td>
								<a href="go.goodsView.goods?gnum=${gdto.gnum}&gspec=${gdto.gspec}&gmovie=${gdto.gmovie}">
									<img src="filePath1/${gdto.gimage}" style="width:100px;height:100px;">
								</a>
							</td>
							
							<td><p style="text-align:center;">${gdto.gname}</p></td>
							
							<form class="form-inline" action="doCartEdit.goods" method="post">
								<td width="10%">
									<div align="center">
										<input type="text" class="form-control" name="gqty" style="width:50px;" value="${gdto.gqty}" onKeyup="onlyNumber(this)"/>
									</div>
								</td>
								
								<td><p style="text-align:center;"><fmt:formatNumber value="${gdto.gprice}" pattern="###,###"/> 원</p></td>
								
								<td><p style="text-align:center;">3,000원</p></td> 	
								
								<td width="5%">
									<input type="submit" class="btn btn-secondary" value="수정"/>
									<input type="button" class="btn btn-secondary" value="삭제" onclick="window.location='doCartDelete.goods?gnum=${gdto.gnum}'">
									<input type="hidden" name="gnum" value="${gdto.gnum}"/>
									<input type="hidden" name="gqty" value="${gdto.gqty}"/>
								</td>
							</form>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
			
			<tfoot>
				<tr style="height:80px">
					<c:if test="${cartTotalPrice >= 30000}">
						<td colspan="7">
							<p style="text-align:right;">
								총 상품가격 <fmt:formatNumber value="${cartTotalPrice}" pattern="###,###"/>원
								 + 배송비 0원 = 합계 <fmt:formatNumber value="${cartTotalPrice}" pattern="###,###"/> 원
							</p>	 
						</td>
					</c:if>
					
					<c:if test="${cartTotalPrice < 30000}">
						<td colspan="7">
							<p style="text-align:right;">
								총 상품가격 <fmt:formatNumber value="${cartTotalPrice}" pattern="###,###"/>원
								 + 배송비 3,000원 = 합계 <fmt:formatNumber value="${cartTotalPrice+3000}" pattern="###,###"/> 원
							</p>	 
						</td>
					</c:if>
				</tr>					
			</tfoot>
		</table>
		</div>
	</div>
			
	<div class="row">
		<div class="col-md-offset-4 col-md-4">
			<div class="col-md-6">
       			<button class="btn btn-success btn-block" onclick="location.href='goods.goods'">굿즈샵으로</button>
       		</div>
       		<div class="col-md-6">
       			<form method="post" action="goOrderPage.goods">
           			<button class="btn btn-info btn-block" type="submit">주문하기</button>
           			<input type="hidden" name="mode" value="cartorder"/>
           		</form>
           	</div>
       </div>
	</div>
	<br><br>
			
			