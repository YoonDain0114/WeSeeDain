<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<!-- CSBoardTop.jsp -->
	
<html lang="ko-kr">
    <head>
    	<meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <!-- <meta name="viewport" content="width=device-width, initial-scale=1">
	     -->
	    <link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css">
	    
	<title>고객센터게시판</title>
	
	<style type="text/css"> 
		a { text-decoration:none !important} 
	</style>	
	
	</head>
<body>

	<div class="container-fluid">
		<div class="row">
			<div align="center" class="col-md-1" style='height: 100px; width: 100px;'>
				<a href ="goMainPage.movie">
				<svg xmlns="http://www.w3.org/2000/svg" style="width:50px;height:50px;" fill="currentColor" class="bi bi-film" viewBox="0 0 16 16">
				  <path d="M0 1a1 1 0 0 1 1-1h14a1 1 0 0 1 1 1v14a1 1 0 0 1-1 1H1a1 1 0 0 1-1-1V1zm4 0v6h8V1H4zm8 8H4v6h8V9zM1 1v2h2V1H1zm2 3H1v2h2V4zM1 7v2h2V7H1zm2 3H1v2h2v-2zm-2 3v2h2v-2H1zM15 1h-2v2h2V1zm-2 3v2h2V4h-2zm2 3h-2v2h2V7zm-2 3v2h2v-2h-2zm2 3h-2v2h2v-2z"/>
				</svg><br>
				<span>영화<br>페이지</span>
				</a>
			</div>
			
			<div align="center" class="col-md-1" style='height: 100px; width: 100px;'>
				<a href="goods.goods">
				<svg xmlns="http://www.w3.org/2000/svg" style="width:50px;height:50px;" fill="currentColor" class="bi bi-shop" viewBox="0 0 16 16">
				  <path d="M2.97 1.35A1 1 0 0 1 3.73 1h8.54a1 1 0 0 1 .76.35l2.609 3.044A1.5 1.5 0 0 1 16 5.37v.255a2.375 2.375 0 0 1-4.25 1.458A2.371 2.371 0 0 1 9.875 8 2.37 2.37 0 0 1 8 7.083 2.37 2.37 0 0 1 6.125 8a2.37 2.37 0 0 1-1.875-.917A2.375 2.375 0 0 1 0 5.625V5.37a1.5 1.5 0 0 1 .361-.976l2.61-3.045zm1.78 4.275a1.375 1.375 0 0 0 2.75 0 .5.5 0 0 1 1 0 1.375 1.375 0 0 0 2.75 0 .5.5 0 0 1 1 0 1.375 1.375 0 1 0 2.75 0V5.37a.5.5 0 0 0-.12-.325L12.27 2H3.73L1.12 5.045A.5.5 0 0 0 1 5.37v.255a1.375 1.375 0 0 0 2.75 0 .5.5 0 0 1 1 0zM1.5 8.5A.5.5 0 0 1 2 9v6h1v-5a1 1 0 0 1 1-1h3a1 1 0 0 1 1 1v5h6V9a.5.5 0 0 1 1 0v6h.5a.5.5 0 0 1 0 1H.5a.5.5 0 0 1 0-1H1V9a.5.5 0 0 1 .5-.5zM4 15h3v-5H4v5zm5-5a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1v3a1 1 0 0 1-1 1h-2a1 1 0 0 1-1-1v-3zm3 0h-2v3h2v-3z"/>
				</svg><br>
				<span>굿즈샵</span>
				</a>
			</div>
			
			<div class="col-md-offset-9 col-md-1">
				<a href="goMyPage.movie">
				&nbsp;&nbsp;&nbsp;<img src="filePath1/${loginData.memberimage}" style='width:50px;height:50px;' class="img-fluid img-circle">
				<br><span>마이페이지</span>
				</a>
			</div>
		</div>
		<br>
		

		<div class="row">
			<div class="col-md-offset-5 col-md-2">
				<a href="list_csboard.do">
					<img src="filePath1/WeSee.png" style='height: 200px; width: 200px;'/>
				</a>
			</div>
		</div><p>
		