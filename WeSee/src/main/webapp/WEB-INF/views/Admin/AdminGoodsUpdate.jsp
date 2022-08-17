<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<%@ include file="AdminManagerTop.jsp"%>

<script type="text/javascript">
		function check(){
			if (f.gname.value == ""){
				alert("굿즈명을 입력해 주세요!!")
				f.gname.focus()
				return false
			}
			if (f.gimage.value == ""){
				alert("이미지를 삽입해 주세요!!")
				f.gimage.focus()
				return false
			}
			if (f.gprice.value == ""){
				alert("가격을 입력해 주세요!!")
				f.gprice.focus()
				return false
			}
			if (f.gqty.value == ""){
				alert("수량을 입력해 주세요!!")
				f.gqty.focus()
				return false
			}
			if (f.gcategory.value == ""){
				alert("카테고리를 정해 주세요!!")
				f.gqty.focus()
				return false
			}
			if (f.gmovie.value == ""){
				alert("관련 영화를 골라주세요!!")
				f.gqty.focus()
				return false
			}
			if (f.gspec.value == ""){
				alert("상품스펙을 입력해 주세요!!")
				f.gqty.focus()
				return false
			}
			return true
		}
</script>

<script>
function inputImage(obj) {
	var changeImage = document.getElementById(obj).value;
	f.obj.value = changeImage;
	}
</script>

	<div class="row">
		<div class="col-md-offset-5 col-md-2">
			<p style="text-align:center;font-size:25pt;">굿즈 등록</p><br>
			<p style="text-align:center;font-size:15pt;">모든 항목을 기입해 주세요.</p>
		</div>
	</div>
	<br>
	<br>

	<form name="f" action="doAdminGoodsUpdate.admin" 
			method="post" enctype="multipart/form-data" onsubmit="return check()">
	<input type="hidden" name = "gnum" value = "${updateGoods.gnum}" />	
		
	<div class="row">
		<div class="col-md-offset-3 col-md-3">
			<div class="col-md-4">
				<p style="text-align:center;font-size:15pt;">굿즈명</p>
			</div>
			<div class="col-md-8">
				<input class="form-control" type="text" name="gname" value="${updateGoods.gname}">
			</div>
		</div>
		
		<div class="col-md-3">
			<div class="col-md-4">
				<p style="text-align:center;font-size:15pt;">수량</p>
			</div>
			<div class="col-md-8">
				<input class="form-control" type="text" name="gqty" value="${updateGoods.gqty}">
			</div>
		</div>
	</div><br>
	
	<div class="row">
		<div class="col-md-offset-3 col-md-3">
			<div class="col-md-4">
				<p style="text-align:center;font-size:15pt;">가격</p>
			</div>
			<div class="col-md-8">
				<input class="form-control" type="text" name="gprice" value="${updateGoods.gprice}">
			</div>
			
		</div>
		
		<div class="col-md-3">
			<div class="col-md-4">
				<p style="text-align:center;font-size:15pt;">상품분류</p>
			</div>
			<div class="col-md-8">
				<select name="gcategory">
					<c:forEach var="getGcategory" items="${gcategory}">
						<c:choose>
							<c:when test="${getGcategory == updateGoods.gcategory}">
								<option value="${getGcategory}" selected>${getGcategory}</option>
							</c:when>

							<c:otherwise>
								<option value="${getGcategory}">${getGcategory}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</div>
		</div>
	</div><br>
	
	<div class="row">
		<div class="col-md-offset-3 col-md-3">
			<div class="col-md-4">
				<p style="text-align:center;font-size:15pt;">관련 영화</p>
			</div>
			<div class="col-md-8">
				<select class="form-select" name="gmovie">
					<option value="" selected>--선택--</option>
					<c:forEach var="mdto" items="${listMovie}">
						<option value="${mdto.movienum}">${mdto.title}</option>
					</c:forEach>
				</select>
			</div>
			
		</div>
		
		<div class="col-md-3">
			<div class="col-md-4">
				<p style="text-align:center;font-size:15pt;">상품스팩</p>
			</div>
			<div class="col-md-8">
				<select class="form-select" name="gspec">
					<c:forEach var="getGspec" items="${gspec}">
						<c:choose>
							<c:when test="${getGspec == updateGoods.gspec}">
								<option value="${getGspec}" selected>${getGspec}</option>
							</c:when>
	
							<c:otherwise>
								<option value="${getGspec}">${getGspec}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</div>
		</div>
	</div><br>
	
	<div class="row">
		<div class="col-md-offset-3 col-md-1">
			<p style="text-align:center;font-size:15pt;">이미지</p>
		</div>
		<div class="col-md-4">
			<input class="form-control" type="text" name="gimage" value="${updateGoods.gimage}"  onchange="inputImage(this)" readonly>
		</div>
		<div class="col-md-1">
			<input class="form-control" type="file" id="gimage">
		</div>
	</div><br>
	
	<div class="row">
		<div class="col-md-offset-3 col-md-1">
			<p style="text-align:center;font-size:15pt;">이미지2</p>
		</div>
		<div class="col-md-5">
			<input class="form-control" type="file" name="gimage2" value="${updateGoods.gimage2}">
		</div>
	</div><br>
	
	<div class="row">
		<div class="col-md-offset-3 col-md-1">
			<p style="text-align:center;font-size:15pt;">이미지3</p>
		</div>
		<div class="col-md-5">
			<input class="form-control" type="file" name="gimage3" value="${updateGoods.gimage3}">
		</div>
	</div><br>
	
	<div class="row">
		<div class="col-md-offset-3 col-md-1">
			<p style="text-align:center;font-size:15pt;">이미지4</p>
		</div>
		<div class="col-md-5">
			<input class="form-control" type="file" name="gimage4" value="${updateGoods.gimage4}">
		</div>
	</div><br>
	
	<div class="row">
		<div class="col-md-offset-3 col-md-1">
			<p style="text-align:center;font-size:15pt;">이미지5</p>
		</div>
		<div class="col-md-5">
			<input class="form-control" type="file" name="gimage5" value="${updateGoods.gimage5}">
		</div>
	</div><br>
	
    <br>
	
	<div class="row">
		<div class="col-md-offset-3 col-md-1">
			<p style="text-align:center;font-size:15pt;">상품소개</p>
		</div>
		<div class="col-md-5">
			<textarea class="form-control" name="gcontents" rows="5" cols="60" style="height:150px;">${updateGoods.gcontents}</textarea>
		</div>
	</div><br>
	
	<div class="row">
		<div class="col-md-offset-5 col-md-1">
			<input class="btn btn-primary btn-block" type="submit" value="등록">
		</div>
		<div class="col-md-1">
			<input class="btn btn-secondary btn-block" type="button" value="취소" onclick="location.href='goAdminGoodsList.admin'">
		</div>
	</div>
	<br>
	<br>
		
	</form>
</body>
</html>