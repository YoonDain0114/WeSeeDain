<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<%@ include file="AdminSaleTop.jsp"%>

<script type="text/javascript">
	function check(){
		if(f.searchString.value == ""){
			alert("4자리 년도를 입력하세요")
			f.searchString.focus()
			return false
		}
		return true
	};
    function check_year(){
        var searchString = document.getElementById('searchString').value;
        if(searchString.length != 4){
            window.alert('4자리 년도를 입력해주세요');
            document.getElementById('searchString').value='';
    }
</script>

<div class="row">
	<div class="col-md-offset-4 col-md-4" align="center">
		<font size="8">${year}년  ${month}월 매출</font>
	</div>
</div>
<br><br>

<div class="row">
	<form name="f" method="POST" action="doMonthSalelist.admin" onsubmit="return check()">
	<input type="hidden" name="mode" value="search"/>	
	
	<div class="col-md-offset-4 col-md-4" align="center">
		<input class="form-control" type="text" name="year" placeholder="4자리 년도 입력" />
			
	</div>
	<div class="col-md-1" align="center">
		<select name="month">
			<option value="01">01</option>
			<option value="02">02</option>
			<option value="03">03</option>
			<option value="04">04</option>
			<option value="05">05</option>
			<option value="06">06</option>
			<option value="07">07</option>
			<option value="08">08</option>
			<option value="09">09</option>
			<option value="10">10</option>
			<option value="11">11</option>
			<option value="12">12</option>
		</select> 월
	</div>
	<div class="col-md-1">
		<input class="btn btn-primary" type="submit" value="확인"/>
	</div>
	</form>
</div>
<br><br>

<div class="row">
	<div class="col-md-offset-2 col-md-8">
		<table class="table">
			<thead>
				<tr height="80px">
					<th width="10%">주문번호</th>
					<th width="25%">주문일자</th>
					<th width="25%">결제방식</th>
					<th width="10%">총구매금액</th>
				</tr>
			</thead>
			
			<tbody>
				<c:if test="${empty monthSalelist}">
					<tr height="50px">
						<td colspan="4">해당년월의 매출이 존재하지 않습니다</td>
					</tr>
				</c:if>
				
				<c:forEach var="dto" items="${monthSalelist}">
					<tr height="50px">
						<td>
							<a href="goAdminSaleDetail.admin?ordernum=${dto.ordernum}">${dto.ordernum}</a>
						</td>
						<td>${dto.orderdate}</td>
						<td>${dto.payment}</td>
						<td><fmt:formatNumber value="${dto.totalpay}" pattern="###,###"/> 원</td>
					</tr>
				</c:forEach>
			</tbody>
			
			<tfoot>
				<tr height="50px">
					<td colspan="4">
						<div align="right">
							총 매출 합계 : <fmt:formatNumber value="${monthsum}" pattern="###,###"/>원 
						</div>
					</td>
				</tr>
			</tfoot>
		</table>
	</div>
</div>
<br><br>

<div class="row">
	<div class="col-md-offset-6 col-md-2">
		<c:if test="${rowCount>0}">			
			<c:if test="${startPage>1}">
				[<a href="doYearSalelist.admin?pageNum=${startPage-1}&mode=search&searchString=${searchString}">이전</a>]			
			</c:if>
			<c:forEach var="i" begin="${startPage}" end="${endPage}">
				[<a href="doYearSalelist.admin?pageNum=${i}&mode=search&searchString=${searchString}"><c:out value="${i}"/></a>]	
			</c:forEach>
			<c:if test="${endPage<pageCount}">
				[<a href="doYearSalelist.admin?pageNum=${endPage+1}&mode=search&searchString=${searchString}">다음</a>]			
			</c:if>
		</c:if>
	</div>
</div>
<br><br>
</body>
</html>