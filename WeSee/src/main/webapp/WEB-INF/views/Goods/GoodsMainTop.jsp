<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%> 
    
   <link rel="stylesheet" href="resources/common.css">
   <link rel="stylesheet" href="resources/image.css">
   
<html lang="ko-kr">
    <head>
    	<meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <!-- <meta name="viewport" content="width=device-width, initial-scale=1">
	     -->
	    <link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css">    

<title>굿즈샵</title>

<style type="text/css">
nav ul { 
    text-align: justify; 
    list-style-type: none; 
    padding: 10px 20px; 
}
nav li { 
    margin: 0; 
    display: inline-block; 
    cursor: pointer; 
    font: 600 16px "Helvetica Neue"; 
}
.justify ul:after{ 
    content: ''; 
    width: 100%; 
     
    display: inline-block; 
}
.box {
    width: 40px;
    height: 40px; 
    border-radius: 70%;
    overflow: hidden;
}
.box:after {
    width: 100%;
    height: 100%;
    object-fit: cover;
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
				<a href ="goMainPage.movie">
				<svg xmlns="http://www.w3.org/2000/svg" style="width:50px;height:50px;color:000000;" fill="currentColor" class="bi bi-film" viewBox="0 0 16 16">
				  <path d="M0 1a1 1 0 0 1 1-1h14a1 1 0 0 1 1 1v14a1 1 0 0 1-1 1H1a1 1 0 0 1-1-1V1zm4 0v6h8V1H4zm8 8H4v6h8V9zM1 1v2h2V1H1zm2 3H1v2h2V4zM1 7v2h2V7H1zm2 3H1v2h2v-2zm-2 3v2h2v-2H1zM15 1h-2v2h2V1zm-2 3v2h2V4h-2zm2 3h-2v2h2V7zm-2 3v2h2v-2h-2zm2 3h-2v2h2v-2z"/>
				</svg><br>
				<span style="color:000000;">영화<br>페이지</span>
				</a>
			</div>
			
			<div align="center" class="col-md-1" style='height: 100px; width: 100px;'>
				<a href="goGoodsCategory.goods">
				<svg xmlns="http://www.w3.org/2000/svg" style="width:50px;height:50px;color:000000;" fill="currentColor" class="bi bi-border-all" viewBox="0 0 16 16">
				  <path d="M0 0h16v16H0V0zm1 1v6.5h6.5V1H1zm7.5 0v6.5H15V1H8.5zM15 8.5H8.5V15H15V8.5zM7.5 15V8.5H1V15h6.5z"/>
				</svg><br>
				<span style="color:000000;">카테고리</span>
				</a>
			</div>
			
			<div align="center" class="col-md-1" style='height: 100px; width: 100px;color:000000;'>
				<a href="goCart.goods">
				<svg xmlns="http://www.w3.org/2000/svg" style="width:50px;height:50px;color:000000;" fill="currentColor" class="bi bi-cart" viewBox="0 0 16 16">
				  <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l1.313 7h8.17l1.313-7H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
				</svg><br>
				<span style="color:000000;">장바구니</span>
				</a>
			</div>
			
			<div align="center" class="col-md-1" style='height: 100px; width: 100px;color:000000;'>
				<a href="goWishList.goods">
				<svg xmlns="http://www.w3.org/2000/svg" style="width:50px;height:50px;color:000000;" fill="currentColor" class="bi bi-patch-check" viewBox="0 0 16 16">
				  <path fill-rule="evenodd" d="M10.354 6.146a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708 0l-1.5-1.5a.5.5 0 1 1 .708-.708L7 8.793l2.646-2.647a.5.5 0 0 1 .708 0z"/>
				  <path d="m10.273 2.513-.921-.944.715-.698.622.637.89-.011a2.89 2.89 0 0 1 2.924 2.924l-.01.89.636.622a2.89 2.89 0 0 1 0 4.134l-.637.622.011.89a2.89 2.89 0 0 1-2.924 2.924l-.89-.01-.622.636a2.89 2.89 0 0 1-4.134 0l-.622-.637-.89.011a2.89 2.89 0 0 1-2.924-2.924l.01-.89-.636-.622a2.89 2.89 0 0 1 0-4.134l.637-.622-.011-.89a2.89 2.89 0 0 1 2.924-2.924l.89.01.622-.636a2.89 2.89 0 0 1 4.134 0l-.715.698a1.89 1.89 0 0 0-2.704 0l-.92.944-1.32-.016a1.89 1.89 0 0 0-1.911 1.912l.016 1.318-.944.921a1.89 1.89 0 0 0 0 2.704l.944.92-.016 1.32a1.89 1.89 0 0 0 1.912 1.911l1.318-.016.921.944a1.89 1.89 0 0 0 2.704 0l.92-.944 1.32.016a1.89 1.89 0 0 0 1.911-1.912l-.016-1.318.944-.921a1.89 1.89 0 0 0 0-2.704l-.944-.92.016-1.32a1.89 1.89 0 0 0-1.912-1.911l-1.318.016z"/>
				</svg><br>
				<span style="color:000000;">찜목록</span>
				</a>
			</div>
			
			<div class="col-md-offset-6 col-md-1">
				<a href="list_csboard.do">
				<svg xmlns="http://www.w3.org/2000/svg" style="width:50px;height:50px;color:000000;" fill="currentColor" class="bi bi-info-circle" viewBox="0 0 16 16">
				  <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
				  <path d="m8.93 6.588-2.29.287-.082.38.45.083c.294.07.352.176.288.469l-.738 3.468c-.194.897.105 1.319.808 1.319.545 0 1.178-.252 1.465-.598l.088-.416c-.2.176-.492.246-.686.246-.275 0-.375-.193-.304-.533L8.93 6.588zM9 4.5a1 1 0 1 1-2 0 1 1 0 0 1 2 0z"/>
				</svg><br>
				<span style="color:000000;">고객센터<br>게시판</span></a>
			</div>
			
			<div class="col-md-1">
				<a href="goMyPage.movie">
				&nbsp;&nbsp;&nbsp;<img src="filePath1/${loginData.memberimage}" style='width:50px;height:50px;' class="img-fluid img-circle">
				<br><span style="color:000000;">마이페이지</span>
				</a>
			</div>
		</div>
		<br>
		<br>
		<div class="row">
			<div class="col-md-offset-5 col-md-2">
				<a href="goods.goods">
					<img src="filePath1/WeSee.png" style='height: 200px; width: 200px;'/>
				</a>
			</div>
		</div>
		

			
