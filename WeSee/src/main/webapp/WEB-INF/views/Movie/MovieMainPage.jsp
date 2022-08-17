<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>   
<%@ include file="MovieTop.jsp"%>

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
          slidesToShow: 6,
          slidesToScroll: 2,
        } );
      } );
    </script>
    <style>
      img {
        max-width: 100%;
        height: auto;    
      }
      .slider {
        width: 1300px;
        margin: 0px auto;
      }
      .slider .slick-slide {
        margin: 10px;
      }
      .slick-prev:before, .slick-next:before {
        color:  rgba($bk, 0.5);
      }
      span{
      	text-align: center;
      }
    </style>

<script type="text/javascript">
	function titleCheck(){
	if(f.title.value == ""){
		alert("영화제목을 입력해주세요!")
		return false;
	}
		return true;	
	}
	
	history.pushState(null, null, location.href);
	window.onpopstate = function(event){
		history.go(1);
	};

</script>

	<div class="row">
		<form name="f" action="goMovieNameSearch.movie" method="post" onsubmit="return titleCheck();">
			<div class="col-md-offset-4 col-md-3">			
				<input type="text" class="form-control input-lg" name="title" size="50" placeholder="영화 제목을 검색해주세요">
			</div>
			<div class="col-md-1">
				<input type="submit" class="btn btn-primary btn-lg" value="검색">
			</div>
		</form>
	</div>
	<br> 
	<div class="container-fluid">
        <div class="row">
        <div class="col-md-offset-2 col-md-3">
        	<font size="5">추천영화</font>
        </div>
    </div>
    <br>
    <div class="row">
	  <div class="col-md-offset-1 slider"style="height:300px;">
			<c:forEach var="rlist" items="${recMovie}">
				<p><a href="goMovieContents.movie?movienum=${rlist.movienum}">
					<img src ="filePath1/${rlist.image}"/></a>
				    <span>${rlist.title}</span>
			</c:forEach>
	  </div>
     </div>
    </div>
    <br><br><br>
     
	<div class="container-fluid">
	    <div class="row">
		<div class="col-md-offset-2 col-md-3">
			<font size="5">최신 개봉작</font>
		</div>
	</div>
	<br>
	<div class="row">
		<div class="col-md-offset-1 slider" style="height:300px;">
			<c:forEach var="dto" items="${yearlyMovie}">
			  <p><a href="goMovieContents.movie?movienum=${dto.movienum}">
					<img src="filePath1/${dto.image}"/></a>
				    <span>${dto.title}</span>
			</c:forEach>
		</div>
	 </div>
	</div>
	<br><br><br>
		
	<div class="container-fluid">
	    <div class="row">
		<div class="col-md-offset-2 col-md-3">
			<font size="5">찜한 영상</font>
		</div>
	</div>
	<br>
	<div class="row">
		<div class="col-md-offset-1 slider" style="height:300px;">
			<c:if test="${empty wishList}"><font size="5">찜한 영상이 없습니다.</font></c:if>
				
			<c:if test="${not empty wishList}">
				<c:forEach var="dto" items="${wishList}">
					<p><a href="goMovieContents.movie?movienum=${dto.movienum}">
						<img src="filePath1/${dto.image}"/></a>
					    <span>${dto.title}</span>
				</c:forEach>
		     </div>
	      </div>
	    </c:if>
	   <br><br>
	
<%@ include file="MovieBottom.jsp"%>