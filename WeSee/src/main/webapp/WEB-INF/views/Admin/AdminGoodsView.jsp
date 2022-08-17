<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
    
    <!-- AdminGoodsView.jsp -->
    
<%@ include file="AdminManagerTop.jsp"%>

<div class="row">
		<div class="col-md-offset-5 col-md-2">
			<p style="text-align:center;font-size:25pt;">굿즈 상세보기</p><br>
		</div>
	</div>
	<br>
	<br>
		
	<div class="row">
		<div class="col-md-offset-3 col-md-3">
			<div class="col-md-4">
				<p style="text-align:center;font-size:15pt;">굿즈명</p>
			</div>
			<div class="col-md-8">
				<label>${getAGoods.gname}</label>
			</div>
		</div>
		
		<div class="col-md-3">
			<div class="col-md-4">
				<p style="text-align:center;font-size:15pt;">수량</p>
			</div>
			<div class="col-md-8">
				<label>${getAGoods.gqty} 개</label>
			</div>
		</div>
	</div><br>
	
	<div class="row">
		<div class="col-md-offset-3 col-md-3">
			<div class="col-md-4">
				<p style="text-align:center;font-size:15pt;">가격</p>
			</div>
			<div class="col-md-8">
				<label><fmt:formatNumber value="${getAGoods.gprice}" pattern="###,###"/> 원</label>
			</div>
			
		</div>
		
		<div class="col-md-3">
			<div class="col-md-4">
				<p style="text-align:center;font-size:15pt;">상품분류</p>
			</div>
			<div class="col-md-8">
				<label>${getAGoods.gcategory}</label>
			</div>
		</div>
	</div><br>
	
	<div class="row">
		<div class="col-md-offset-3 col-md-3">
			<div class="col-md-4">
				<p style="text-align:center;font-size:15pt;">관련 영화</p>
			</div>
			<div class="col-md-8">
				${getAGoods.gmovie}
			</div>
			
		</div>
		
		<div class="col-md-3">
			<div class="col-md-4">
				<p style="text-align:center;font-size:15pt;">상품스팩</p>
			</div>
			<div class="col-md-8">
				${getAGoods.gspec}
			</div>
		</div>
	</div><br>
	
	<div class="row">
		<div class="col-md-offset-3 col-md-1" style="height:300px">
			<p style="text-align:center;font-size:15pt;">이미지</p>
		</div>
		<div class="col-md-5" style="height:200px">
			<img src="filePath1/${getAGoods.gimage}" style="쟝소:300px;height:290px">
		</div>
	</div><br>
	
	<div class="row">
		<div class="col-md-offset-3 col-md-1">
			<p style="text-align:center;font-size:15pt;">상품소개</p>
		</div>
		<div class="col-md-5">
			<textarea class="form-control" name="gcontents" rows="5" cols="60" style="height:150px;" readonly>${getAGoods.gcontents}</textarea>
		</div>
	</div><br>
	
	<div class="row">
		<div class="col-md-offset-6 col-md-1">
			<input class="btn btn-secondary btn-block" type="button" value="돌아가기" onclick="window.location='goAdminGoodsList.admin'">
		</div>
	</div>
	<br>
	<br>
		
</body>
</html>