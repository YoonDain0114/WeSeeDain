<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>

<%@ include file="GoodsMainTop.jsp"%>

	<div class="row">
		<form name="f" action="doSearch.goods" method="post">
			<div class="col-md-offset-4 col-md-3">			
				<input type="text" class="form-control input-lg" name="search" size="50"
				placeholder="상품명을 입력해주세요." >
			</div>
			<div class="col-md-1">
				<input type="submit" class="btn btn-primary btn-lg" value="검색">
			</div>
		</form>
	</div>
	<br><br>
	
	<div class="row">
		<div class="col-md-offset-1 col-md-10">
		<hr color="green" width="100%">
			<div class="col-md-offset-6">
				<c:choose>
					<c:when test="${gcategory eq 'ecobag'}">
						<font size="5">에코백</font>
					</c:when>
					<c:when test="${gcategory eq 'cup'}">
						<font size="5">컵</font>
					</c:when>
					<c:when test="${gcategory eq 'poster'}">
						<font size="5">포스터</font>
					</c:when>
					<c:when test="${gcategory eq 'griptok'}">
						<font size="5">그립톡</font>
					</c:when>
					<c:when test="${gcategory eq 'keyring'}">
						<font size="5">키링</font>
					</c:when>
					<c:when test="${gcategory eq 'filmmark'}">
						<font size="5">필름마크</font>
					</c:when>
					<c:when test="${gcategory eq 'stationery'}">
						<font size="5">문구류</font>
					</c:when>
					<c:when test="${gcategory eq 'etc'}">
						<font size="5">기타</font>
					</c:when>
				</c:choose>
			</div>
		<hr color="green" width="100%">
		</div>
	</div><br>

	<div class="row">
		<div class="col-md-offset-2 col-md-8">
			<table><tr style="height:280px">
				<c:forEach var="list" items="${cateList}" varStatus="status">
					<th>
						<a href="go.goodsView.goods?gnum=${list.gnum}&gspec=${list.gspec}&gmovie=${list.gmovie}">
							<img src= "filePath1/${list.gimage}" style="width:250px;height:250px;">
						</a><br><br>
						<p style="text-align:center;">${list.gname}</p>
						<p style="text-align:center;color:red">${list.gprice}원</p>
						<c:if test="${gdto.gqty <= 0}">
			 	 			  *품절된 상품입니다.
			 			</c:if>
					</th>
					
					<c:if test="${status.index%4==0}">
						</tr>
						<tr style="height:280px">
					</c:if>
				</c:forEach>
			</tr></table>
		</div>
	</div>