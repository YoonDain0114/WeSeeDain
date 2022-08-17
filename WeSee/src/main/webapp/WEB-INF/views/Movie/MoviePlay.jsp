<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
   	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- <meta name="viewport" content="width=device-width, initial-scale=1">
     -->
    <link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css"> 
	    
	<title>${getMovie.title}</title>

<script type="text/javascript">
history.pushState(null, null, location.href);
window.onpopstate = function(event){
	history.go(1);
};

function video1(){
	document.getElementById("videofile").src = "filePath1/${getMovie.videofile}";
}

function video2(){
	document.getElementById("videofile").src = "filePath1/${getMovie.videofile2}";
}

function video3(){
	document.getElementById("videofile").src = "filePath1/${getMovie.videofile3}";
}

</script>

<style>
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
		<br><br>
	</div>
	<br>
	
	<form name = "f" action="doViewMovie.movie" method="post">
	<input type = "hidden" name ="movienum" value="${getMovie.movienum}">
	
	<div class="row">
		<div class="col-md-10" align="right">
			<video id="videofile" width="1200" height="900" muted controls autoplay>
				<source src="filePath1/${getMovie.videofile}" type="video/mp4">
			</video>
		</div>
		
		<div class="col-md-2">
			<br><br><br><br><br><br>
			<br><br><br><br><br><br>
			<br><br><br>
			<div align="center">
				<c:if test="${loginData.fe eq 1}">
					<input class="btn btn-danger" type="button" value = "360p" onclick="video1()"><br><br>
					<input class="btn btn-success" type="button" value = "720p" disabled><br><br>
					<input class="btn btn-primary" type="button" value = "1080p" disabled><br><br>
				</c:if>
				
				<c:if test="${loginData.fe eq 2}">
					<input class="btn btn-danger" type = "button" value = "360p" onclick="video1()"><br><br>
					<input class="btn btn-success" type = "button" value = "720p" onclick="video2()"><br><br>
					<input class="btn btn-primary" type = "button" value = "1080p" disabled><br><br>
				</c:if>
				
				<c:if test="${loginData.fe eq 3}">
					<input class="btn btn-danger" type = "button" value = "360p" onclick="video1()"><br><br>
					<input class="btn btn-success" type = "button" value = "720p" onclick="video2()"><br><br>
					<input class="btn btn-primary" type = "button" value = "1080p" onclick="video3()"><br><br>
				</c:if>
				
				<input class="btn btn-primary" type = "submit" value = "시청종료">
			</div>
		</div>
	</div>
	</form>
	<br><br>
</div>

</body>
</html>