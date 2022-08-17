<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ include file="GoodsMainTop.jsp"%>

<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<script
	src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
<link rel="stylesheet"
	href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css" />
<link rel="stylesheet"
	href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick-theme.css" />
<script>
	$(document)
		.ready(
			function() {
				$('.slider')
					.slick(
							{
								//autoplay: true,
								//autoplaySpeed: 1000,
								arrows : true,
								prevArrow : "<button type='button' class='slick-prev'>Previous</button>",
								nextArrow : "<button type='button' class='slick-next'>Next</button>",
								slidesToShow : 4,
								slidesToScroll : 1,
							});
			});
</script>
<style>
img {
	max-width: 100%;
	height: auto;
}

.slider {
	width: 1000px;
	margin: 0px auto;
}

.slider .slick-slide {
	margin: 10px;
}

.slick-prev:before, .slick-next:before {
	color: red;
}
</style>

    <div class="row">
	    <form name="f" action="doSearch.goods" method="post">
		    <div class="col-md-offset-4 col-md-3">
			    <input type="text" class="form-control input-lg" name="search" value="${search}"
				    size="50" placeholder="상품명을 입력해주세요.">
		    </div>
		    <div class="col-md-1">
			    <input type="submit" class="btn btn-primary btn-lg" value="검색">
		    </div>
	    </form>
    </div>
    <br>
    <br>

    <div class="quick_wrap" style="padding-top: 50px;">
	    <div id="quick_menu" class="quick_menu" style="top: 665px;">
		    <h5>내가 본 상품</h5>
		    <c:forEach items="${sawlist}" var="saw">
			   <a href="go.goodsView.goods?gnum=${saw.gnum}&gspec=${saw.gspec}&gmovie=${saw.gmovie}"> <img
				    src="filePath1/${saw.gimage}" style="width: 70px; height: 70px;"></a>
			    <br>
		    </c:forEach>
		    <a href="#" class="qick_top">TOP</a>
	    </div>
    </div>

<!-- 굿즈 검색 -->
    <c:if test="${not empty findGoods}">
	    <div align="center" class="slider" style="height: 300px;">
		    <c:forEach items="${findGoods}" var="find">
			    <p>
				    <a href="go.goodsView.goods?gnum=${find.gnum}&gspec=${find.gspec}&gmovie=${find.gmovie}">
					    <img src="filePath1/${find.gimage}"
					    style="width: 250px; height: 250px;">
				    </a> <span>${find.gname}</span> <font color="red"> <fmt:formatNumber
						    value="${find.gprice}" pattern="###,###" />
				    </font>원<br>
				    <c:if test="${find.gqty <= 0}">
			 	 	  *품절된 상품입니다.
			 		 </c:if> 
				    <c:set var="count" value="${count+1}" />
				    <c:if test="${count%3==0}" />
				    <!-- /가 붙어있는 이유는? -->
		    </c:forEach>
	    </div>
    </c:if>

<!-- 메인페이지 -->
    <div class="container-fluid">
	    <br> <br>
	    <div class="row">
		    <div class="col-md-offset-1 col-md-10">
			    <hr color="green" width="100%">
			    <div class="col-md-offset-6">
				    <font size="5">BEST</font>
			    </div>
			    <hr color="green" width="100%">
		    </div>
	    </div>
	    <br>
	    <c:if test="${empty best}">
		     <th>BEST 상품이 없습니다.
		    <th>
		    </tr>
	    </c:if>
	    <c:if test="${not empty best}">
		    <div class="container-fluid">
			    <div class="row">
				    <div class="col-md-offset-1 col-md-1"></div>
			    </div>
		    </div>
		    <br>
		    <div class="row">
			    <div class="col-md-offset-1 slider" style="height: 300px;">
				    <c:forEach items="${best}" var="gdto">
					    <p>
						    <a href="go.goodsView.goods?gnum=${gdto.gnum}&gspec=best&gmovie=${gdto.gmovie}"> <img
							    src="filePath1/${gdto.gimage}"
							    style="width: 250px; height: 250px;"></a> <span>${gdto.gname}</span>
						    <font color="red"> <fmt:formatNumber value="${gdto.gprice}"
								    pattern="###,###" />
						    </font>원<br>
						    <c:if test="${gdto.gqty <= 0}">
			 	 		 	 *품절된 상품입니다.
			 				 </c:if> 
				    </c:forEach>
	    </c:if>
    </div>
    </div>
    <br>
    <br>
<!-- newest -->
    <div class="container-fluid">
	    <br> <br>
	    <div class="row">
		    <div class="col-md-offset-1 col-md-10">
			    <hr color="green" width="100%">
			    <div class="col-md-offset-6">
				    <font size="5">NEW</font>
			    </div>
			    <hr color="green" width="100%">
		    </div>
	    </div>
	    <br>
	    <c:if test="${empty newest}">
		    <th>NEW 상품이 없습니다.
		    <th>
		    </tr>
	    </c:if>
	    <c:if test="${not empty newest}">
		    <div class="container-fluid">
			    <div class="row">
				    <div class="col-md-offset-1 col-md-1"></div>
			    </div>
		    </div>
		    <br>
		    <div class="row">
			    <div class="col-md-offset-1 slider" style="height: 300px;">
				    <c:forEach items="${newest}" var="gdto">
					    <p>
						    <a href="go.goodsView.goods?gnum=${gdto.gnum}&gspec=newest&gmovie=${gdto.gmovie}"> <img
							    src="filePath1/${gdto.gimage}"
							    style="width: 250px; height: 250px;"></a> <span>${gdto.gname}</span>
						    <font color="red"> <fmt:formatNumber value="${gdto.gprice}"
								    pattern="###,###" />
						    </font>원<br>
						    <c:if test="${gdto.gqty <= 0}">
			 	 		 	 *품절된 상품입니다.
			 				 </c:if> 
				    </c:forEach>
	    </c:if>
    </div>
    </div>
    <br>
    <br>

<!-- season -->
    <div class="container-fluid">
	    <br> <br>
	    <div class="row">
		    <div class="col-md-offset-1 col-md-10">
			    <hr color="green" width="100%">
			    <div class="col-md-offset-6">
				    <font size="5">SEASON</font>
			    </div>
			    <hr color="green" width="100%">
		    </div>
	    </div>
	    <br>
	    <c:if test="${empty season}">
		    <th>SEASON 상품이 없습니다.
		    <th>
		    </tr>
	    </c:if>
	    <c:if test="${not empty season}">
		    <div class="container-fluid">
			    <div class="row">
				    <div class="col-md-offset-1 col-md-1"></div>
			    </div>
		    </div>
		    <br>
		    <div class="row">
			     <div class="col-md-offset-1 slider" style="height: 300px;">
				    <c:forEach items="${season}" var="gdto">
					    <p>
						     <a href="go.goodsView.goods?gnum=${gdto.gnum}&gspec=season&gmovie=${gdto.gmovie}"> <img
							    src="filePath1/${gdto.gimage}"
							    style="width: 250px; height: 250px;"></a> <span>${gdto.gname}</span>
						    <font color="red"> <fmt:formatNumber value="${gdto.gprice}"
								    pattern="###,###" />
						    </font>원<br>
						    <c:if test="${gdto.gqty <= 0}">
			 	 		 	 *품절된 상품입니다.
			 				 </c:if> 
				    </c:forEach>
	    </c:if>
    </div>
    </div>
    <br>
    <br>

<!-- normal -->
    <div class="container-fluid">
	    <br> <br>
	    <div class="row">
		    <div class="col-md-offset-1 col-md-10">
			     <hr color="green" width="100%">
			    <div class="col-md-offset-6">
				    <font size="5">NORMAL</font>
			    </div>
			    <hr color="green" width="100%">
		    </div>
	    </div>
	    <br>
	    <c:if test="${empty normal}">
		 <th>NORMAL 상품이 없습니다.
		    <th>
		    </tr>
	    </c:if>
	    <c:if test="${not empty normal}">
		    <div class="container-fluid">
			    <div class="row">
				    <div class="col-md-offset-1 col-md-1"></div>
			    </div>
		    </div>
		    <br>
		    <div class="row">
			    <div class="col-md-offset-1 slider" style="height: 300px;">
				    <c:forEach items="${normal}" var="gdto">
					    <p>
						    <a href="go.goodsView.goods?gnum=${gdto.gnum}&gspec=normal&gmovie=${gdto.gmovie}"> <img
							    src="filePath1/${gdto.gimage}"
							    style="width: 250px; height: 250px;"></a> <span>${gdto.gname}</span>
						    <font color="red"> <fmt:formatNumber value="${gdto.gprice}"
								    pattern="###,###" />
						    </font>원<br>
						    <c:if test="${gdto.gqty <= 0}">
			 	 		 	 *품절된 상품입니다.
			 				 </c:if> 
				    </c:forEach>
	    </c:if>
    </div>
    </div>
    </div>
    </body>
    </html>
