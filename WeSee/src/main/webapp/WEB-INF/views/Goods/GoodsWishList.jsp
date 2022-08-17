<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@	taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="GoodsMainTop.jsp"%>

<html lang="ko-kr">
   <head>
   	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">    
    <link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css"> 

	<link rel="stylesheet" href="resources/common.css">
	<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css" />
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick-theme.css" />
</head>

	<div class="container-fluid">	    
		<font size="4">내가 찜한 상품</font>
	</div><br>
			
	<c:if test="${empty goodswishList}">
		<tr style="height:450px">
			<th align="center"><h2>찜한 상품이 없습니다.</h2></th>
		</tr>
	</c:if>
				
	<c:if test="${not empty goodswishList}">
		<c:forEach var="gdto" items="${goodswishList}">
			<div class="col-md-offset-2 clo-md-2">
				<a href="go.goodsView.goods?gnum=${gdto.gnum}&gspec=${gdto.gspec}&gmovie=${gdto.gmovie}">
				<img src="filePath1/${gdto.gimage}"  width = "150" height = "150" />					
				</a>					
			</div>
			
			<div>
				<input type="button" value="x" onclick="location.href='doGoodsWishListDelete.goods?gnum=${gdto.gnum}'">
			</div>		
		</c:forEach>
	</c:if>