<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="ko-kr">
    <head>
    	<meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <!-- <meta name="viewport" content="width=device-width, initial-scale=1">
	     -->
	    <link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css">
		<!-- Favicon-->
		<link rel="icon" type="image/x-icon" href="assets/favicon.ico">
		<!-- Bootstrap icons-->
		<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">
		
	<title>매출 관리 페이지</title>

<style>
	th, td {
	padding: 5px;
	text-align: center;
    }
</style>

</head>
<body>
<div class="container-fluid">
	<div class="row">
		<div align="right" style="font-size:20pt;">
			<a href="doAdminLogout.movie">로그아웃</a>
		</div>
			
		<div align="center">
			<h2>
				<a href="goAdminManagerPage.admin">
					<img src="resources/images/WeSee1.jpg" style='height: 200px; width: 200px;' alt="로고">
				</a>
			</h2>
		</div>
	</div>
	<br><br>
	
	<div class="row">
		<div class="col-md-offset-2 col-md-8">
			<table class="table">
			<thead>
				<tr>
					<th width="33%"><a href="doYearSalelist.admin?searchString=2022">연단위</a></th>
					<th width="33%"><a href="doMonthSalelist.admin">월단위</a></th>
					<th width="33%"><a href="doDaySalelist.admin">일단위</a></th>
				</tr>
			</thead>
			</table>
		</div>
	</div>
	<br><br>