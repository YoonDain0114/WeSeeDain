<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  

<%@ include file="AdminManagerTop.jsp"%>

<style>
	th, td {
	text-align: center;
    }
</style>

<script type = "text/javascript">
	function delete_ask(gnum, gimage)	{
		var ask = window.confirm("정말 삭제하시겠습니까?")
		
		if(ask){
			document.f.gnum.value = gnum
			document.f.gimage.value = gimage
			document.f.submit()
		}else{
			alert("취소되었습니다.")
		}
	}
</script>
		
	<div class="row">
		<div class="col-md-offset-5 col-md-2">
			<p style="text-align:center;font-size:30pt;">굿즈 목록</p>
		</div>
	</div>
	<br>	
	
	<div class="row">
		<form name="d" action="goAdminFindGoods.admin" method="post">
			<div class="col-md-offset-5 col-md-2">
				<input type="text" class="form-control" name="gname" placeholder="굿즈명" value="${gname}">
			</div>
			<div class="col-md-1">	
				<input type="submit" class="btn btn-secondary btn-block" value="검색"><br>
			</div>
		</form>
	</div>
	<br><br>
	
	<div class="row">
		<div class="col-md-offset-10 col-md-1">
			<div align="right">
				<font style="font-size:15pt;"><a href="goAdminGoodsInput.admin">등록</a></font>
			</div>
		</div>
	</div>
					
	<div class="row">
		<div class="col-md-offset-1 col-md-10">
			<table class="table table-hover">
				
				
				<thead class="table-primary">
					<tr>
						<th width="10%">번호</th>
						<th width="10%">굿즈명</th>
						<th width="15%">가격</th>
						<th width="10%">상품분류</th>
						<th width="10%">재고량</th>
						<th width="20%">이미지</th>
						<th width="10%"></th>
					</tr>
				</thead>
				
				<tbody class="table table-striped">
					<c:if test="${empty listGoods}">
						<tr>
							<th colspan="9">등록된 상품이 없습니다.</th>
						</tr>
					</c:if>
					
					<c:forEach items="${listGoods}" var="dto">
					<tr>
						<td>${dto.gnum}</td>
						<td>${dto.gname}</td>
						<td><fmt:formatNumber value="${dto.gprice}" pattern="###,###"/> 원</td>
						<td>${dto.gcategory}</td>
						<td>${dto.gqty} 개</td>
						<td>
							<a href="goAdminGoodsView.admin?gnum=${dto.gnum}">
								<img src="filePath1/${dto.gimage}" style="width:50px;height:50px;">
							</a>
						</td>
						<td>
							<a href="goAdminGoodsUpdate.admin?gnum=${dto.gnum}">수정</a> | 
							<a href="javascript:delete_ask('${dto.gnum}','${dto.gimage}' )">삭제</a>
						</td>
					</tr>		
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	
	<form name = "f" action = "doAdminGoodsDelete.admin" method = "post">
		<input type="hidden" name = "gnum" />
		<input type="hidden" name = "gimage" />
	</form>
	 
	<div class="row">
		<div class="col-md-offset-4 col-md-4" align="center">
			<c:if test="${rowCount>0}">			
				<c:if test="${startPage>1}">
					[<a href="goAdminGoodsList.admin?pageNum=${startPage-1}">이전</a>]	 &nbsp;		
				</c:if>
				
				<c:forEach var="i" begin="${startPage}" end="${endPage}">
					[<a href="goAdminGoodsList.admin?pageNum=${i}"><c:out value="${i}"/></a>] &nbsp;	
				</c:forEach>
				
				<c:if test="${endPage<pageCount}">
					[<a href="goAdminGoodsList.admin?pageNum=${endPage+1}">다음</a>]			
				</c:if>
			</c:if>
		</div>
	</div>
	
	<br><br>
	
</body>
</html>