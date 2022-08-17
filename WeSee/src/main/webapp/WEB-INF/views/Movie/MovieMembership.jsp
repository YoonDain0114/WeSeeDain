<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>   
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>

	<!-- Membership.jsp -->

<%@include file="MovieTop.jsp" %>

		<script type = "text/javascript">
 			function stop_ask()	{
 				var ask = window.confirm("정말 해지하시겠습니까?");
 				
 				if(!ask){
 					alert("매월 자동 결제가 유지됩니다.");
 					return false;
 				}else{
 					document.f.submit;
 				}
 			}
 			function check(){
 				if(UpdateFe.fe.value == ""){
 					alert("원하는 요금제를 선택해주세요.")
 					return false
 				}
 				return true
 			}

		</script>
		
		<style type="text/css">
			th, td {
			text-align: center;
		    }
		</style> 
	
	<br>
	<br>
	<div class="row">
		<div class="col-md-offset-5 col-md-2" align="center">
			<font size="8">요금제</font>		
		</div>
	</div>
	
	<br>
	
	<c:if test="${loginData.fe ne 0}">
		<div class="row">
			<div class="col-md-offset-3 col-md-8">
				<font size="4">
					<c:if test="${loginData.autopay eq 0}">
						(현재 사용하고 계신 요금제는 ${memberFe}이며, 자동 결제 서비스를 사용하지 않고 계십니다.)
					</c:if>
					<c:if test="${loginData.autopay eq 1}">
						(현재 사용하고 계신 요금제는 ${memberFe}이며, 자동 결제 서비스를 사용중이십니다.)
					</c:if>
				</font>
			</div>
		</div>
	</c:if>
	
	<br>
	<br>
	
	<form name="UpdateFe" method="post" action="doMembership.movie">
	<div class="row" style="height:150px;">
		<div class="col-md-offset-3 col-md-6">
			
			<div class="row" style="height:50px;">
				<div class="col-md-6">
					<label>
						기본요금제 : 월 9900원<br>
						WeSee에서 제공하는 영화를 저화질로 보실 수 있습니다.
					</label>
				</div>
				<div class="col-md-offset-2 col-md-2">
					<input type="radio" name="fe" value="1">기본 요금제 선택</input>
				</div>
			</div>
			<div class="row" style="height:50px;">
				<div class="col-md-6">
					<label>
						Pro 요금제 : 월 12900원<br>
						WeSee에서 제공하는 영화를 중화질로 보실 수 있습니다.
					</label>
				</div>
				<div class="col-md-offset-2 col-md-2">
					<input type="radio" name="fe" value="2">Pro 요금제 선택</input>
				</div>
			</div>
			<div class="row" style="height:50px;">
				<div class="col-md-6">
					<label>
						VIP 요금제 : 월 19900원<br>
						WeSee에서 제공하는 영화를 고화질로 보실 수 있습니다.
					</label>
				</div>
				<div class="col-md-offset-2 col-md-2">
					<input type="radio" name="fe" value="3">VIP 요금제 선택</input>
				</div>
			</div>
		</div>
	</div>
	
	<br>
	<br>
	<br>
	
	<div class="row">
		<table align="center" border="1" style="width:800px;">
			<caption>요금제 비교</caption>
			<tr height="100px">
				<th width="500px">제공하는 서비스</th>
				<th width="100px">기본</th>
				<th width="100px">Pro</th>
				<th width="100px">VIP</th>
			</tr>
			
			<tr align="center" height="50px">
				<th>저화질(360화질)</th>
				<th>O</th>
				<th>O</th>
				<th>O</th>
			</tr>
			
			<tr align="center"  height="50px">
				<th>중화질(720화질)</th>
				<th>X</th>
				<th>O</th>
				<th>O</th>
			</tr>
			
			<tr align="center"  height="50px">
				<th align="left">고화질(1080화질)</th>
				<th>X</th>
				<th>X</th>
				<th>O</th>
			</tr>
		</table>
	</div>
	
	
	<br><br><br><br><br>
	
	
	<div class="row">
		<div class="col-md-offset-4 col-md-1">
			<input class="btn btn-success" type="submit" value="요금제 변경하기"/>
		</div>
	</form>
	
		<div class="col-md-1">
			<button class="btn btn-success" onclick="loaction.href='goMyPage.movie'">마이페이지로</button>
		</div>
	
	<c:if test="${loginData.autopay eq '1'}">
		<div class="col-md-1">
			<form name="f" method="post" action="doMembershipStop.movie" onsubmit="return stop_ask()">
				<input class="btn btn-success" type="submit" value="요금제 자동 결제 해지"/>
			</form>
		</div>
	</c:if>	
	</div>
	
	<br>
	<br>
	<br>
	