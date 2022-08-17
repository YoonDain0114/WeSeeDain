<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<%@ include file="AdminSaleTop.jsp"%>

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script>
   $(function() {
       //input을 datepicker로 선언
       $("#datepicker").datepicker({
           dateFormat: 'yy-mm-dd' //달력 날짜 형태
           ,showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
           ,showMonthAfterYear:true // 월- 년 순서가아닌 년도 - 월 순서
           ,changeYear: true //option값 년 선택 가능
           ,changeMonth: true //option값  월 선택 가능                
           ,showOn: "both" //button:버튼을 표시하고,버튼을 눌러야만 달력 표시 ^ both:버튼을 표시하고,버튼을 누르거나 input을 클릭하면 달력 표시  
           ,buttonImage: "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif" //버튼 이미지 경로
           ,buttonImageOnly: true //버튼 이미지만 깔끔하게 보이게함
           ,buttonText: "선택" //버튼 호버 텍스트              
           ,yearSuffix: "년" //달력의 년도 부분 뒤 텍스트
           ,monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 텍스트
           ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip
           ,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 텍스트
           ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'] //달력의 요일 Tooltip
           ,minDate: "-5Y" //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
           ,maxDate: "+5y" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)  
       });                    
       
       //초기값을 오늘 날짜로 설정해줘야 합니다.
       $('#datepicker').datepicker('setDate', 'today'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)            
   });
   
   $.datepicker.setDefaults({
	   dateFormat: 'yy-mm-dd' //달력 날짜 형태
       ,showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
       ,showMonthAfterYear:true // 월- 년 순서가아닌 년도 - 월 순서
       ,changeYear: true //option값 년 선택 가능
       ,changeMonth: true //option값  월 선택 가능                
       ,showOn: "both" //button:버튼을 표시하고,버튼을 눌러야만 달력 표시 ^ both:버튼을 표시하고,버튼을 누르거나 input을 클릭하면 달력 표시  
       ,buttonImage: "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif" //버튼 이미지 경로
       ,buttonImageOnly: true //버튼 이미지만 깔끔하게 보이게함
       ,buttonText: "선택" //버튼 호버 텍스트              
       ,yearSuffix: "년" //달력의 년도 부분 뒤 텍스트
       ,monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 텍스트
       ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip
       ,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 텍스트
       ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'] //달력의 요일 Tooltip
   	,yearRange: 'c-50:c+0', // 년도 선택 셀렉트박스를 현재 년도에서 이전, 이후로 얼마의 범위를 표시할것인가
   });
</script>

<div class="row">
	<div class="col-md-offset-4 col-md-4" align="center">
		<font size="8">${date} 매출</font>
	</div>
</div>
<br><br>

<div class="row">
	<form name="f" method="POST" action="doDaySalelist.admin" onsubmit="return check()">
		<input type="hidden" name="mode" value="search"/>	
	
	<div class="col-md-offset-4 col-md-1" align="center">
		<label for="datepicker">날짜를 선택하세요</label>
		
	</div>
	
	<div class="col-md-3">
		<input class="form-control" type="text" name="date" id="datepicker" >
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
				<c:if test="${empty daySalelist}">
					<tr height="50px">
						<td colspan="4">해당 연월일의 매출이 존재하지 않습니다</td>
					</tr>
				</c:if>
				
				<c:forEach var="dto" items="${daySalelist}">
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
							총 매출 합계 : <fmt:formatNumber value="${daysum}" pattern="###,###"/>원 
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
				[<a href="doDaySalelist.admin?pageNum=${startPage-1}&mode=dateSearch&date=${date}">이전</a>]			
			</c:if>
			<c:forEach var="i" begin="${startPage}" end="${endPage}">
				[<a href="doDaySalelist.admin?pageNum=${i}&mode=dateSearch&date=${date}"><c:out value="${i}"/></a>]	
			</c:forEach>
			<c:if test="${endPage<pageCount}">
				[<a href="doDaySalelist.admin?pageNum=${endPage+1}&mode=dateSearch&date=${date}">다음</a>]			
			</c:if>
		</c:if>
	</div>
</div>
<br><br>
</body>
</html>