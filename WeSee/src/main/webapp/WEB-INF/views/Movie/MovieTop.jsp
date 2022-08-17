<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>   
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
    
    <html lang="ko-kr">
    <head>
    	<meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <!-- <meta name="viewport" content="width=device-width, initial-scale=1">
	     -->
	    <link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css"> 
	    
    	<title>WeSee</title>
    	
	<style>
		body{
			color:#ffffff;
			background-repeat: no-repeat;
			background-color: #000000;
			background-size: cover;
		}
	</style>
	
	<style type="text/css"> 
		a { text-decoration:none !important} 
	</style>

</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div align="center" class="col-md-1" style='height: 100px; width: 100px;'>
				<a href="goMovieDetail.movie">
				<svg xmlns="http://www.w3.org/2000/svg" style="width:50px;height:50px;color:#ffffff;" fill="currentColor" class="bi bi-text-indent-right" viewBox="0 0 16 16">
				  <path d="M2 3.5a.5.5 0 0 1 .5-.5h11a.5.5 0 0 1 0 1h-11a.5.5 0 0 1-.5-.5zm10.646 2.146a.5.5 0 0 1 .708.708L11.707 8l1.647 1.646a.5.5 0 0 1-.708.708l-2-2a.5.5 0 0 1 0-.708l2-2zM2 6.5a.5.5 0 0 1 .5-.5h6a.5.5 0 0 1 0 1h-6a.5.5 0 0 1-.5-.5zm0 3a.5.5 0 0 1 .5-.5h6a.5.5 0 0 1 0 1h-6a.5.5 0 0 1-.5-.5zm0 3a.5.5 0 0 1 .5-.5h11a.5.5 0 0 1 0 1h-11a.5.5 0 0 1-.5-.5z"/>
				</svg><br>
				<span style="color:#ffffff;text-align:center;">장르별<br>검색</span>
				</a>
			</div>
			
			<div align="center" class="col-md-1" style='height: 100px; width: 100px;'>
				<a href="board.do">
				<svg xmlns="http://www.w3.org/2000/svg" style="width:50px;height:50px;color:#ffffff;" fill="currentColor" class="bi bi-pencil" viewBox="0 0 16 16">
				  <path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"/>
				</svg><br>
				<span style="color:#ffffff;text-align:center;">리뷰<br>게시판</span>
				</a>
			</div>
			
			<div align="center" class="col-md-1" style='height: 100px; width: 100px;'>
				<a href="goods.goods">
				<svg xmlns="http://www.w3.org/2000/svg" style="width:50px;height:50px;color:#ffffff;" fill="currentColor" class="bi bi-shop" viewBox="0 0 16 16">
				  <path d="M2.97 1.35A1 1 0 0 1 3.73 1h8.54a1 1 0 0 1 .76.35l2.609 3.044A1.5 1.5 0 0 1 16 5.37v.255a2.375 2.375 0 0 1-4.25 1.458A2.371 2.371 0 0 1 9.875 8 2.37 2.37 0 0 1 8 7.083 2.37 2.37 0 0 1 6.125 8a2.37 2.37 0 0 1-1.875-.917A2.375 2.375 0 0 1 0 5.625V5.37a1.5 1.5 0 0 1 .361-.976l2.61-3.045zm1.78 4.275a1.375 1.375 0 0 0 2.75 0 .5.5 0 0 1 1 0 1.375 1.375 0 0 0 2.75 0 .5.5 0 0 1 1 0 1.375 1.375 0 1 0 2.75 0V5.37a.5.5 0 0 0-.12-.325L12.27 2H3.73L1.12 5.045A.5.5 0 0 0 1 5.37v.255a1.375 1.375 0 0 0 2.75 0 .5.5 0 0 1 1 0zM1.5 8.5A.5.5 0 0 1 2 9v6h1v-5a1 1 0 0 1 1-1h3a1 1 0 0 1 1 1v5h6V9a.5.5 0 0 1 1 0v6h.5a.5.5 0 0 1 0 1H.5a.5.5 0 0 1 0-1H1V9a.5.5 0 0 1 .5-.5zM4 15h3v-5H4v5zm5-5a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1v3a1 1 0 0 1-1 1h-2a1 1 0 0 1-1-1v-3zm3 0h-2v3h2v-3z"/>
				</svg><br>
				<span style="color:#ffffff;text-align:center;">굿즈샵</span>
				</a>
			</div>
			
			<div class="col-md-offset-7 col-md-1">
				<a href="list_csboard.do">
				<svg xmlns="http://www.w3.org/2000/svg" style="width:50px;height:50px;color:#ffffff;" fill="currentColor" class="bi bi-info-circle" viewBox="0 0 16 16">
				  <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
				  <path d="m8.93 6.588-2.29.287-.082.38.45.083c.294.07.352.176.288.469l-.738 3.468c-.194.897.105 1.319.808 1.319.545 0 1.178-.252 1.465-.598l.088-.416c-.2.176-.492.246-.686.246-.275 0-.375-.193-.304-.533L8.93 6.588zM9 4.5a1 1 0 1 1-2 0 1 1 0 0 1 2 0z"/>
				</svg><br>
				<span style="color:#ffffff;text-align:center;">고객센터<br>게시판</span></a>
			</div>
			
			<div class="col-md-1">
				<a href="goMyPage.movie">
					&nbsp;&nbsp;&nbsp;<img src="filePath1/${loginData.memberimage}" style='width:50px;height:50px;' class="img-fluid img-circle">
					<br><span style="color:#ffffff;text-align:center;">마이페이지</span>
				</a>
			</div>
		</div>
		<br>
		<br>
		<div class="row">
			<div class="col-md-offset-5 col-md-2">
				<a href="goMainPage.movie">
					<img src="filePath1/WeSee.png" style='height: 200px; width: 200px;'/>
				</a>
			</div>
		</div>

	