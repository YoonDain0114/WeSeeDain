<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
	
	<!-- OrderConfirm.jsp -->

<%@ include file="GoodsMainTop.jsp"%>
	
	<div class="row">
		<div class="col-md-offset-4 col-md-4">
			<label>고객님의 주문이 정상적으로 완료되었습니다.</label><br>
			<label>주문내역 및 배송에 관한 안내는 
			<a href="">주문조회</a>를 통하여 확인 가능합니다.
			</label><br>
			<label>주문번호 : ${ordercode}</label><br>
			<label>주문일자 : ${orderdate[0]}년 ${orderdate[1]}월 ${orderdate[2]}일 ${orderdate[3]} : ${orderdate[4]} : ${orderdate[5]}</label>
		</div>
	</div>
	
	<div class="row">
		<div class="col-md-offset-5 col-md-1">
			<button class="btn btn-fluid btn-primary" onclick="location.href='goods.goods'">
				홈으로
			</button>
		</div>
		<div class="col-md-1">
			<button class="btn btn-fluid btn-success" onclick="location.href='goOrderListView.goods'">
				구매내역 보기
			</button>
		</div>
	</div>
	