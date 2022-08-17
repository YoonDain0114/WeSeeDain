<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>   
<%@include file="MovieTop.jsp" %>

	<!-- MovieDetail.jsp -->
    
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
          slidesToScroll: 3,
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
    </style>
    
    <!-- 검색바 -->
    <div class="row">
		<form name="f" action="doSearchMovie.movie" method="post">
			<div class="col-md-offset-4 col-md-3">			
				<input type="text" class="form-control input-lg" name="word" value="${word}" size="50" placeholder="원하는 항목에 맞게 검색해주세요">
			</div>
			
			<div class="col-md-1">
		    	<select class="btn btn-info btn-lg form-select" name="search">
	                <option value="genre">장르</option>
	                <option value="title">제목</option>
	            </select>
		    </div>
			
			<div class="col-md-1">
				<input type="submit" class="btn btn-primary btn-lg" value="검색">
			</div>
		</form>
	</div>
	<br>
    
    <!-- 영화 장르별 부분 -->
    <c:if test="${empty searchmovie}">
       <c:if test="${not empty action}">
       <div class="container-fluid">
        <div class="row">
        	<div class="col-md-offset-2 col-md-3">
        		<font size="5">ACTION</font>
        	</div>
        </div>
        <br>
        <div class="row">       
          <div class="col-md-offset-1 slider" style="height:300px;">
              <c:forEach var="dto" items="${action}">
                <p><a href="goMovieContents.movie?movienum=${dto.movienum}">
                <img src="filePath1/${dto.image}"/></a>
                <span>${dto.title}</span>
             </c:forEach>
           </div>
        </div>
       </div>
           
        </c:if>
        
        <br><br>
        
        <c:if test="${not empty sf}">
        <div class="container-fluid">
        <div class="row">
        	<div class="col-md-offset-2 col-md-3">
        		<font size="5">SF</font>
        	</div>
        </div>
        <br>
        <div class="row">       
          <div class="col-md-offset-1 slider" style="height:300px;">
              <c:forEach var="dto" items="${sf}">
                <p><a href="goMovieContents.movie?movienum=${dto.movienum}">
                <img src="filePath1/${dto.image}"/></a>
                <span>${dto.title}</span>
             </c:forEach>
           </div>
        </div>
       </div>
          
        </c:if>
        
        <br><br>
        
        <c:if test="${not empty drama}">
         <div class="container-fluid">
        <div class="row">
        	<div class="col-md-offset-2 col-md-3">
        		<font size="5">DRAMA</font>
        	</div>
        </div>
        <br>
        <div class="row">       
          <div class="col-md-offset-1 slider" style="height:300px;">
              <c:forEach var="dto" items="${drama}">
                <p><a href="goMovieContents.movie?movienum=${dto.movienum}">
                <img src="filePath1/${dto.image}"/></a>
                <span>${dto.title}</span>
             </c:forEach>
           </div>
        </div>
       </div>
          
        </c:if>
        
        <br><br>
        
        <c:if test="${not empty comedy}">
        <div class="container-fluid">
        <div class="row">
        	<div class="col-md-offset-2 col-md-3">
        		<font size="5">COMEDY</font>
        	</div>
        </div>
        <br>
        <div class="row">       
          <div class="col-md-offset-1 slider" style="height:300px;">
              <c:forEach var="dto" items="${comedy}">
                <p><a href="goMovieContents.movie?movienum=${dto.movienum}">
                <img src="filePath1/${dto.image}"/></a>
                <span>${dto.title}</span>
             </c:forEach>
           </div>
        </div>
       </div>
          
        </c:if>
        
        <br><br>
        
        <c:if test="${not empty romance}">
        <div class="container-fluid">
        <div class="row">
        	<div class="col-md-offset-2 col-md-3">
        		<font size="5">ROMANCE</font>
        	</div>
        </div>
        <br>
        <div class="row">       
          <div class="col-md-offset-1 slider" style="height:300px;">
              <c:forEach var="dto" items="${romance}">
                <p><a href="goMovieContents.movie?movienum=${dto.movienum}">
                <img src="filePath1/${dto.image}"/></a>
                <span>${dto.title}</span>
             </c:forEach>
           </div>
        </div>
       </div>
           <p></p>
        </c:if>
        
        <br><br>
        
         <c:if test="${not empty thriller}">
         <div class="container-fluid">
        <div class="row">
        	<div class="col-md-offset-2 col-md-3">
        		<font size="5">THRILLER</font>
        	</div>
        </div>
        <br>
        <div class="row">       
          <div class="col-md-offset-1 slider" style="height:300px;">
              <c:forEach var="dto" items="${thriller}">
                <p><a href="goMovieContents.movie?movienum=${dto.movienum}">
                <img src="filePath1/${dto.image}"/></a>
                <span>${dto.title}</span>
             </c:forEach>
           </div>
        </div>
       </div>
           <p></p>
        </c:if>
    </c:if>
    
    <c:if test="${not empty searchmovie}">
        <div align="center" class="slider" style="height:300px;">
            <c:forEach var="dto" items="${searchmovie}">
                <p><a href="goMovieContents.movie?movienum=${dto.movienum}">
                <img src="filePath1/${dto.image}"/></a>
                <span>${dto.title}</span>
            </c:forEach> 
        </div> 
    </c:if>

<%@ include file="MovieBottom.jsp"%>