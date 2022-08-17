<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  

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
	
<div class="row">
		<div class="col-md-offset-5 col-md-2">
			<p style="text-align:center;font-size:25pt;">굿즈 등록</p><br>
			<p style="text-align:center;font-size:15pt;">모든 항목을 기입해 주세요.</p>
		</div>
	</div>
	<br>
	<br>

	<form name="f" action="doAdminGoodsInput.admin" 
			method="post" enctype="multipart/form-data" onsubmit="return check()">
		
	<div class="row">
		<div class="col-md-offset-3 col-md-3">
			<div class="col-md-4">
				<p style="text-align:center;font-size:15pt;">굿즈명</p>
			</div>
			<div class="col-md-8">
				<input class="form-control" type="text" name="gname">
			</div>
		</div>
		
		<div class="col-md-3">
			<div class="col-md-4">
				<p style="text-align:center;font-size:15pt;">수량</p>
			</div>
			<div class="col-md-8">
				<input class="form-control" type="text" name="gqty">
			</div>
		</div>
	</div><br>
	
	<div class="row">
		<div class="col-md-offset-3 col-md-3">
			<div class="col-md-4">
				<p style="text-align:center;font-size:15pt;">가격</p>
			</div>
			<div class="col-md-8">
				<input class="form-control" type="text" name="gprice">
			</div>
			
		</div>
		
		<div class="col-md-3">
			<div class="col-md-4">
				<p style="text-align:center;font-size:15pt;">상품분류</p>
			</div>
			<div class="col-md-8">
				<select class="form-select" name="gcategory">
					<option value="" selected>--선택--</option>
					<option value="ecobag">에코백</option>
					<option value="cup">컵</option>
					<option value="poster">포스터</option>
					<option value="griptok">그립톡</option>
					<option value="keyring">키링</option>
					<option value="filmmark">필름마크</option>
					<option value="stationery">문구류</option>
					<option value="etc">기타</option>
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
					<option value="best">베스트 굿즈</option>
					<option value="newest">최신 굿즈</option>
					<option value="season">시즌별 굿즈</option>
					<option value="normal">일반 굿즈</option>
				</select>
			</div>
		</div>
	</div><br>
	
	<div class="row">
		<div class="col-md-offset-3 col-md-1">
			<p style="text-align:center;font-size:15pt;">이미지</p>
		</div>
		<div class="col-md-5">
			<input class="form-control" type="file" name="gimage">
		</div>
	</div><br>
	
	<div class="row">
		<div class="col-md-offset-3 col-md-1">
			<p style="text-align:center;font-size:15pt;">이미지2</p>
		</div>
		<div class="col-md-5">
			<input class="form-control" type="file" name="gimage2">
		</div>
	</div><br>
	
	<div class="row">
		<div class="col-md-offset-3 col-md-1">
			<p style="text-align:center;font-size:15pt;">이미지3</p>
		</div>
		<div class="col-md-5">
			<input class="form-control" type="file" name="gimage3">
		</div>
	</div><br>
	
	<div class="row">
		<div class="col-md-offset-3 col-md-1">
			<p style="text-align:center;font-size:15pt;">이미지4</p>
		</div>
		<div class="col-md-5">
			<input class="form-control" type="file" name="gimage4">
		</div>
	</div><br>
	
	<div class="row">
		<div class="col-md-offset-3 col-md-1">
			<p style="text-align:center;font-size:15pt;">이미지5</p>
		</div>
		<div class="col-md-5">
			<input class="form-control" type="file" name="gimage5">
		</div>
	</div><br>
	
	<div class="row">
		<div class="col-md-offset-3 col-md-1">
			<p style="text-align:center;font-size:15pt;">상품소개</p>
		</div>
		<div class="col-md-5">
			<textarea class="form-control" name="gcontents" rows="5" cols="60" style="height:150px;"></textarea>
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