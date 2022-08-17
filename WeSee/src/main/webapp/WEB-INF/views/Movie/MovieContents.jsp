<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>   
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@include file="MovieTop.jsp" %>

	<!-- contents.jsp -->
    
<html lang="ko-kr">
   <head>
   	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- <meta name="viewport" content="width=device-width, initial-scale=1">
     -->
    <link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css"> 

	<link rel="stylesheet" href="resources/common.css">
	<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css" />
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick-theme.css" />

<script>
  $( document ).ready( function() {
    $( '.slider' ).slick( {
      //autoplay: true,
      //autoplaySpeed: 1000,
       arrows : true,
       prevArrow : "<button type='button' class='slick-prev'>Previous</button>",  
       nextArrow : "<button type='button' class='slick-next'>Next</button>",
      slidesToShow: 5,
      slidesToScroll: 2,
    } );
  } );
</script>

<script type="text/javascript">
	function check(){
		alert("요금제를 가입하셔야 이용하실 수 있습니다.");
		return false;
	}
	
	function openWatch(){
		var popupWidth = 300;
		var popupHeight = 150;
		
		var popupX = (window.screen.width / 2) - (popupWidth / 2);
		var popupY= (window.screen.height / 2) - (popupHeight / 2);
		window.open('goPasswdCheck.movie', '비밀번호 확인', 'status=no, height=' + popupHeight  + ', width=' + popupWidth  + ', left='+ popupX + ', top='+ popupY);
	}
</script>

    <style>
      img {
        max-width: 100%;
        height: auto;    
      }
      .slider {
        width: 1100px;
        margin: 0px auto;
      }
      .slider .slick-slide {
        margin: 10px;
      }
      .slick-prev:before, .slick-next:before {
        color: #444444;
      }
      video {
      	max-width: 80%; display: block; margin: 20px auto;
      }
		body{
			color:#ffffff;
			background-repeat: no-repeat;
			background-color: #000000;
			background-size: cover;
		}
    </style>
</head>    
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-offset-2 col-md-6">
				${getMovie.moviefile}
			</div>
		</div>
		
		<br>
		<br>
		
		<div class="row" style="height:400px;">
			<div class="col-md-offset-2 col-md-2">
				<img src="filePath1/${getMovie.image}">
			</div>
			
			<div class="col-md-6">
				<br>
				<br>
				<br>
				<div class="row">
					<div class="col-md-6">
						<font size="5">${getMovie.title}</font>	
					</div>
					
					<div class="col-md-6">
						<font size="5" style="float:center">개봉일 : ${getMovie.opendate}</font>	
					</div>
				</div>
				<br><br>
				
				<div class="row">	
					<div class="col-md-12">
						<font size="5">장르 : ${getMovie.genre}</font>	
					</div>
				</div>
				<br><br>
				
				<div class="row">
					<div class="col-md-12">
						<font size="5">출연 : ${getMovie.actor}</font>
					</div>
				</div>
				<br><br>
				
				<div class="row">
					<div class="col-md-12">
						<font size="5">감독 : ${getMovie.director}</font>
					</div>
				</div>
				<br><br>
			</div>
		</div>
		<br><br>
		
		<div class="row">
			<div class="col-md-offset-3 col-md-3">
				<c:if test="${loginData.fe eq 0}">
				<input type="button" class="btn btn-primary btn-lg" value="보러가기" onclick = "check();">
				</c:if>
				<c:if test="${loginData.fe ne 0}">
				<a href="goMoviePlay.movie?movienum=${getMovie.movienum}"><input type="button" class="btn btn-primary btn-lg" value="보러가기"></a>
				</c:if>
			</div>
			
			<div class="col-md-3">
				<c:choose>
				<c:when test="${empty already}">
					<input type="button" class="btn btn-primary btn-lg" value="찜하기" onclick="location.href='doMovieWish.movie?movienum=${getMovie.movienum}'">
				</c:when>
				<c:otherwise>
					<input type="button" class="btn btn-danger btn-lg" value="찜 제거" onclick="location.href='doMovieWishDelete.movie?movienum=${getMovie.movienum}'">
				</c:otherwise>
				</c:choose>
			</div>
		</div>
		<br><br>
		
		<div class="row">
			<div class="col-md-offset-2 col-md-8">
				<font style="float:left" size="5">시놉시스</font>
			</div>
		</div>
		<br>
		
		<div class="row">		
			<div class="col-md-offset-2 col-md-8">
				<font style="float:left" size="5">${getMovie.moviecontents}</font>
			</div>
		</div>
	</div>
	<br>
	<br>
	<c:if test="${empty samegenre}"><font size="5">동일한 장르 영화가 없습니다.</font></c:if>
	
    <c:if test="${not empty samegenre}">
    	<div class="row">
    		<div class="col-md-offset-2 col-md-3">
   				<font size="5">동일한 장르 영화</font>
    		</div>
       </div>
       <br>
       <div class="row">       
          <div class="col-md-offset-1 slider" style="height:300px;">	
     		<c:forEach var="dto" items="${samegenre}">
				    <p><a href="goMovieContents.movie?movienum=${dto.movienum}">
				    <img src="filePath1/${dto.image}"/></a>
				    <span>${dto.title}</span>
			    </c:forEach>
     	  </div>
        </div>
    </c:if>
    <br><br>
        
<%@ include file="MovieBottom.jsp"%>