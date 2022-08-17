<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="GoodsMainTop.jsp" %>  

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script> 
	
	<!-- GoodsOrder.jsp -->
	
<style>
	th, td {
	text-align: center;
    }
</style>
	
<script>
    function execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                var addr = '';
                var extraAddr = '';
                if (data.userSelectedType === 'R') {
                    addr = data.roadAddress;
                } else {
                    addr = data.jibunAddress;
                }
                if(data.userSelectedType === 'R'){
                	if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    document.getElementById("extraAddress").value = extraAddr;
                } else {
                    document.getElementById("extraAddress").value = '';
                }
                document.getElementById('postcode').value = data.zonecode;
                document.getElementById("address").value = addr;
                document.getElementById("detailAddress").focus();
            }
        }).open();
    }
    
    function div_OnOff(v){
    	if(v == "신용카드"){
    		document.getElementById("신용카드").style.display = "";
    		document.getElementById("계좌이체").style.display = "none";
    		document.getElementById("휴대폰").style.display = "none";
    		document.getElementById("무통장").style.display = "none";
    	}else if(v == "계좌이체"){
    		document.getElementById("신용카드").style.display = "none";
    		document.getElementById("계좌이체").style.display = "";
    		document.getElementById("휴대폰").style.display = "none";
    		document.getElementById("무통장").style.display = "none";
    	}else if(v == "휴대폰"){
    		document.getElementById("신용카드").style.display = "none";
    		document.getElementById("계좌이체").style.display = "none";
    		document.getElementById("휴대폰").style.display = "";
    		document.getElementById("무통장").style.display = "none";
    	}else if(v == "무통장"){
    		document.getElementById("신용카드").style.display = "none";
    		document.getElementById("계좌이체").style.display = "none";
    		document.getElementById("휴대폰").style.display = "none";
    		document.getElementById("무통장").style.display = "";
    	}
    }
    
    function handleOnChange(e) {
    	  const value = e.value;// 선택된 데이터 가져오기
    	  document.getElementById('domainName').value = value;// 데이터 출력
    	}
    
    function check(){
        if(f.postcode.value == ""){
            alert("우편번호를 확인해 주세요!!");
            f.postcode.focus();
            return false;
        }
        if (f.address.value == ""){
            alert("주소를 확인해 주세요!!");
            f.address.focus();
            return false;
        } 
        if (f.detailAddress.value == ""){
            alert("상세 주소를 확인해 주세요!!");
            f.detailAddress.focus();
            return false;
        }
        if (f.recive.value == ""){
            alert("받는 분 성함을 확인해 주세요!!");
            f.recive.focus();
            return false;
        }
        if (f.hp1.value=="" || f.hp2.value=="" || f.hp3.value==""){
            alert("연락처를 전부 입력해 주세요.");
            f.hp1.focus();
            return false;
        }
        
        var payment = f.querySelectorAll('input[name="payment"]');
        var chkPay = false;
        for(var i = 0; i < payment.length; i++){
	        if (payment[i].checked){
	        	chkPay = true;
	        	break;
	        }
        }
        if(!chkPay){
            alert("결재 수단을 선택해 주세요");
            return false;
        }
        
        if (f.chkbox.checked == false){
            alert("구매 진행 동의를 체크해 주세요");
            return false;
        }
        return true;
    }
</script>

	<form name="f" method="post" action="goOrderConfirm.goods" onsubmit="return check()">
	<div class="row">
		<div class="col-md-offset-1 col-md-10">
			<div class="col-md-offset-1 col-md-1">
				<label>장바구니</label>
			</div>
			<div class="col-md-offset-10 col-md-1">
				<label>홈 > 장바구니</label>
			</div>
		</div>
	</div>
	<br>
	
	<div class="row">
		<div class="col-md-offset-1 col-md-10">
			<table class="table">
				<thead>
					<tr height="50px">
						<th width="">이미지</th>
						<th width="">상품명</th>
						<th width="">수량</th>
						<th rowspan="${listSize}" width="">상품금액</th>
						<th rowspan="${listSize}" width="">배송비</th>
					</tr>
				</thead>
				
				<tbody>
					<c:set var="totalpay" value="0"/>
				
					<c:choose>
						<c:when	test="${not empty goOrder}">
							<input type="hidden" name="dtoNum" value="${goOrder.gnum}"/>
							<tr height="50">
								<td><img style="width: 45%;" src="filePath1/${goOrder.gimage}" /></td>
								
								<td><fmt:formatNumber value="${goOrder.gprice}" pattern="###,###"/></td>
								<td>
									${goOrder.gqty}
									<input type="hidden" name="gqty" value="${goOrder.gqty}"/>
								</td>
								
								<td><fmt:formatNumber value="${goOrder.gprice * goOrder.gqty}" pattern="###,###"/></td>
								<c:set var="totalpay" value="${goOrder.gprice * goOrder.gqty}"/>
							</tr>	
						</c:when>
						
						<c:when test="${empty goOrder}">
							<c:forEach items="${cart}" var="gdto">
							<tr style="height:80px">
								<td>
									<a href="go.goodsView.goods?gnum=${gdto.gnum}&gspec=${gdto.gspec}&gmovie=${gdto.gmovie}">
										<img src="filePath1/${gdto.gimage}" style="width:100px;height:100px;">
									</a>
								</td>
								
								<td><p style="text-align:center;">${gdto.gname}</p></td>
								<td width="10%">${gdto.gqty} 개</td>
								<td><p style="text-align:center;">${gdto.gprice}원</p></td>	<%--상품가격--%>
								<td><p style="text-align:center;">3,000원</p></td>
								
								<c:set var="totalpay" value="${totalpay + (gdto.gprice * gdto.gqty)}"/>	
							</tr>
							</c:forEach>
						</c:when>
						
						<c:otherwise>
							<tbody>
								<tr height="40">
									<td colspan="7" align="center">주문할 상품이 없습니다.</td>
								</tr>	
							</tbody>
						</c:otherwise>
					</c:choose>	
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
				
				<input type="hidden" name="totalpay" value="${totalpay}"/>
			</table>
		</div>
	</div>
			
	<br>
	<div class="row">
		<div class="col-md-offset-1 col-md-10">
			<hr style="width:100%">	
		</div>
	</div>
	<br>
	
	<!-- 배송정보 -->
	<div class="row">
		<div class="col-md-offset-2 col-md-10">
			<label>배송정보</label>
		</div>
	</div>
	
	<br>
	<div class="row">
		<div class="col-md-offset-1 col-md-10">
			<hr style="width:100%">	
		</div>
	</div>
	<br>
	
	<div class="row">
		<div class="col-md-offset-1 col-md-5">
			<div class="col-md-offset-2 col-md-1">
				<label>주소</label>
			</div>
			<div class="col-md-9">
				<div class="row">
					<div class="col-md-4">
						<input class="form-control" type="text" name="postcode" id="postcode" placeholder="우편번호" value="${addr[0]}" readonly>
					</div>
					<div class="col-md-4">
						<input class="btn btn-secondary btn-fluid" type="button" onclick="execDaumPostcode()" value="우편번호 찾기"><br>
					</div>
				</div>
				<br>
				
				<div class="row">
					<input class="form-control form-fluid" type="text" name="address" id="address" placeholder="주소" size="50" value="${addr[1]}" readonly><br>
				</div>
				
				<div class="row">
					<div class="col-md-8">
						<input class="form-control" type="text" name="detailAddress" id="detailAddress" placeholder="상세주소" value="${addr[2]}">
					</div>
					<div class="col-md-4">
						<input class="form-control" type="text" name="extraAddress" id="extraAddress" placeholder="참고항목" readonly>
					</div>
				</div>
			</div>
		</div>
		
		<div class="col-md-6">
			<div class="row">
				<div class="col-md-1">
					<label>성함</label>
				</div>
				<div class="col-md-2">
					<input class="form-control" type="text" name="recive" value="${loginData.name}" readonly/>
				</div>
			</div>
			<br>
				
			<div class="row">
				<div class="col-md-1">
					<label>연락처</label>
				</div>
				<div class="col-md-1">
					<input type="text" name="hp1" class="form-control" value="${loginData.hp1}" maxlength="3" readonly>
				</div>
				<div class="col-md-1">
					<input type="text" name="hp2" class="form-control" value="${loginData.hp2}" maxlength="4" readonly>
				</div>
				<div class="col-md-1">
					<input type="text" name="hp3" class="form-control" value="${loginData.hp3}" maxlength="4" readonly>
				</div>
			</div>
		</div>
	</div>
	<br>
	
	<div class="row">
		<div class="col-md-offset-1 col-md-10" style="height:80px;">
			<div class="col-md-offset-1 col-md-1">
				<label>배송메시지</label>
			</div>
			<div class="col-md-8">
				<textarea class="form-control" name="memo" cols="50" rows="5"></textarea>
			</div>
		</div>
	</div>
			
	<br>
	<div class="row">
		<div class="col-md-offset-1 col-md-10">
			<hr style="width:100%">	
		</div>
	</div>
	<br>
	
	<div class="row">
		<div class="col-md-offset-1 col-md-10">
			<div class="col-md-8">
				<div class="row" style="height:80px" align="center">
					<input type="radio" name="payment" value="신용카드" onclick="div_OnOff(this.value);">카드결제</input>&nbsp;
					<input type="radio" name="payment" value="계좌이체" onclick="div_OnOff(this.value);">실시간 계좌 이체</input>&nbsp;
					<input type="radio" name="payment" value="휴대폰" onclick="div_OnOff(this.value);">휴대폰 결제</input>&nbsp;
					<input type="radio" name="payment" value="무통장" onclick="div_OnOff(this.value);">무통장 입금</input>
				</div>
				<hr style="width:100%">	
				
				<div class="row" style="height:80px" align="center">
					<div id="신용카드" style="display:none">
						최소 결제 가능 금액은 결제금액에서 배송비를 제외한 금액입니다.<br>
						소액 결제의 경우 PG사 정책에 따라 결제 금액 제한이 있을 수 있습니다.
					</div>
					
					<div id="계좌이체" style="display:none">
						<label>예금주명</label><br>
						<input type="text" name="bankName"/>
					</div>
					
					<div id="휴대폰" style="display:none">
						&nbsp;소액 결제의 경우 PG사 정책에 따라 결제 금액 제한이 있을 수 있습니다.
					</div>
					
					<div id="무통장" style="display:none">
						<div class="col-md-6">
							<label>입금자명</label><br>
							<input type="text" name="bankerName"/><p>
						</div>
						
						<div class="col-md-6">
							<label>입금은행</label><br>
							<select name="bankname">
								<option value="1">위씨은행:222-333-456611990</option>
							</select>
						</div>
					</div>
				</div>	
			</div>
			
			<div class="col-md-4">
				<div class="row" style="height:50px">
					<label>최종결제 금액 : ${totalpay}</label>
					<hr style="width:100%">
				</div>
				<br>
				
				<div class="row" style="height:50px">
					<input type="checkbox" name="chkbox"/>
					<label>결제 정보를 확인하였으며, 구매 진행에 동의합니다.</label>
				</div>
				
				<div class="row" style="height:60px">
					<input type="submit" value="결제하기"/>
					<hr style="width:100%">
				</div>
			</div>
		</div>
	</div>
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	</form>