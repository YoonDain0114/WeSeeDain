<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>   
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>

	<!-- MovieMyPage.jsp -->
    
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
			th, td {
			padding: 15px;
			text-align: center;
		    }
		</style> 
		
		<style type="text/css"> 
			a { text-decoration:none !important} 
		</style> 
		
		<script type="text/javascript">
			function openPWCheck(){
				var popupWidth = 300;
				var popupHeight = 150;
				
				var popupX = (window.screen.width / 2) - (popupWidth / 2);
				// 만들 팝업창 width 크기의 1/2 만큼 보정값으로 빼주었음
				
				var popupY= (window.screen.height / 2) - (popupHeight / 2);
				// 만들 팝업창 height 크기의 1/2 만큼 보정값으로 빼주었음
				
				window.open('goPasswdCheck.movie', '비밀번호 확인', 'status=no, height=' + popupHeight  + ', width=' + popupWidth  + ', left='+ popupX + ', top='+ popupY);
			}
		</script>

</head>
</html>	

<div class="container-fluid">
	<div class="row">
		<div class="col-md-offset-8 col-md-1" style='height: 100px; width: 100px;'>
			<a href="goMembership.movie">
			<svg xmlns="http://www.w3.org/2000/svg" style="width:50px;height:50px;color:#ffffff;" fill="currentColor" class="bi bi-gear" viewBox="0 0 16 16">
			  <path d="M8 4.754a3.246 3.246 0 1 0 0 6.492 3.246 3.246 0 0 0 0-6.492zM5.754 8a2.246 2.246 0 1 1 4.492 0 2.246 2.246 0 0 1-4.492 0z"/>
			  <path d="M9.796 1.343c-.527-1.79-3.065-1.79-3.592 0l-.094.319a.873.873 0 0 1-1.255.52l-.292-.16c-1.64-.892-3.433.902-2.54 2.541l.159.292a.873.873 0 0 1-.52 1.255l-.319.094c-1.79.527-1.79 3.065 0 3.592l.319.094a.873.873 0 0 1 .52 1.255l-.16.292c-.892 1.64.901 3.434 2.541 2.54l.292-.159a.873.873 0 0 1 1.255.52l.094.319c.527 1.79 3.065 1.79 3.592 0l.094-.319a.873.873 0 0 1 1.255-.52l.292.16c1.64.893 3.434-.902 2.54-2.541l-.159-.292a.873.873 0 0 1 .52-1.255l.319-.094c1.79-.527 1.79-3.065 0-3.592l-.319-.094a.873.873 0 0 1-.52-1.255l.16-.292c.893-1.64-.902-3.433-2.541-2.54l-.292.159a.873.873 0 0 1-1.255-.52l-.094-.319zm-2.633.283c.246-.835 1.428-.835 1.674 0l.094.319a1.873 1.873 0 0 0 2.693 1.115l.291-.16c.764-.415 1.6.42 1.184 1.185l-.159.292a1.873 1.873 0 0 0 1.116 2.692l.318.094c.835.246.835 1.428 0 1.674l-.319.094a1.873 1.873 0 0 0-1.115 2.693l.16.291c.415.764-.42 1.6-1.185 1.184l-.291-.159a1.873 1.873 0 0 0-2.693 1.116l-.094.318c-.246.835-1.428.835-1.674 0l-.094-.319a1.873 1.873 0 0 0-2.692-1.115l-.292.16c-.764.415-1.6-.42-1.184-1.185l.159-.291A1.873 1.873 0 0 0 1.945 8.93l-.319-.094c-.835-.246-.835-1.428 0-1.674l.319-.094A1.873 1.873 0 0 0 3.06 4.377l-.16-.292c-.415-.764.42-1.6 1.185-1.184l.292.159a1.873 1.873 0 0 0 2.692-1.115l.094-.319z"/>
			</svg><br>
			<label style="color:#ffffff;text-align:center;">요금제<br>변경</label></a>
     	</div>
	
		<div class="col-md-1" style='height: 100px; width: 100px;'>
			<a href="goOrderListView.goods">
			<svg xmlns="http://www.w3.org/2000/svg" style="width:50px;height:50px;color:#ffffff;" fill="currentColor" class="bi bi-credit-card" viewBox="0 0 16 16">
			  <path d="M0 4a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v8a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V4zm2-1a1 1 0 0 0-1 1v1h14V4a1 1 0 0 0-1-1H2zm13 4H1v5a1 1 0 0 0 1 1h12a1 1 0 0 0 1-1V7z"/>
			  <path d="M2 10a1 1 0 0 1 1-1h1a1 1 0 0 1 1 1v1a1 1 0 0 1-1 1H3a1 1 0 0 1-1-1v-1z"/>
			</svg><br>
			<label style="color:#ffffff;text-align:center;">주문내역<br>확인</label></a>
     	</div>
	
		<div class="col-md-1" style='height: 100px; width: 100px;'>
			<a href="javascript:openPWCheck()">
			<svg xmlns="http://www.w3.org/2000/svg" style="width:50px;height:50px;color:#ffffff;" fill="currentColor" class="bi bi-person-check" viewBox="0 0 16 16">
			  <path d="M6 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0zm4 8c0 1-1 1-1 1H1s-1 0-1-1 1-4 6-4 6 3 6 4zm-1-.004c-.001-.246-.154-.986-.832-1.664C9.516 10.68 8.289 10 6 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10z"/>
			  <path fill-rule="evenodd" d="M15.854 5.146a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708 0l-1.5-1.5a.5.5 0 0 1 .708-.708L12.5 7.793l2.646-2.647a.5.5 0 0 1 .708 0z"/>
			</svg><br>
			<label style="color:#ffffff;text-align:center;">회원정보<br>수정</label></a>
     	</div>
		
		<div class="col-md-1" style='height: 100px; width: 100px;'>
			<a href="doLogout.movie">
			<svg xmlns="http://www.w3.org/2000/svg" style="width:50px;height:50px;color:#ffffff;" fill="currentColor" class="bi bi-box-arrow-right" viewBox="0 0 16 16">
			  <path fill-rule="evenodd" d="M10 12.5a.5.5 0 0 1-.5.5h-8a.5.5 0 0 1-.5-.5v-9a.5.5 0 0 1 .5-.5h8a.5.5 0 0 1 .5.5v2a.5.5 0 0 0 1 0v-2A1.5 1.5 0 0 0 9.5 2h-8A1.5 1.5 0 0 0 0 3.5v9A1.5 1.5 0 0 0 1.5 14h8a1.5 1.5 0 0 0 1.5-1.5v-2a.5.5 0 0 0-1 0v2z"/>
			  <path fill-rule="evenodd" d="M15.854 8.354a.5.5 0 0 0 0-.708l-3-3a.5.5 0 0 0-.708.708L14.293 7.5H5.5a.5.5 0 0 0 0 1h8.793l-2.147 2.146a.5.5 0 0 0 .708.708l3-3z"/>
			</svg><br>
			<label style="color:#ffffff;text-align:center;">로그아웃</label></a>
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
	
	<div class="row">
		<div class="col-md-offset-2 col-md-8">
			<div class="col-md-offset-2 col-md-2">
				<img src="filePath1/${loginData.memberimage}" class="img-fluid img-circle"
				style='height: 200px; width: 200px;' >
			</div>
			
			<div class="col-md-offset-1 col-md-7">
				<c:if test="${loginData.fe eq 0}">
					<font size="6">가입하신 요금제가 없습니다.</font>
				</c:if>
				<c:if test="${loginData.fe ne 0}">
					<font size="6">회원님이 가입하신 요금제는<br>
					${memberFe}입니다.</font>
				</c:if>
				<br>
				
				<c:if test="${loginData.autopay eq 0}">
					<font size="6">요금제 이용 기한은	${loginData.paydate}<br>까지입니다.</font>
				</c:if>
				<c:if test="${loginData.autopay eq 1}">
					<font size="6">해당 요금제는${loginData.paydate}에<br>갱신될 예정입니다.</font>
				</c:if>
			</div>
		</div>
	</div>
	<br>
	<br>
	<br>
	
	<div class="row">
		<div class="col-md-offset-2 col-md-3">
			<table align="center" border="1" style="height:500px">
				<tr style="height:50px">
					<th width="500">
						<c:if test="${not empty wishList}">
							<a href="goWishList.movie"><span style="color:#ffffff;">내가 찜한 동영상</span></a>
						</c:if>
						<c:if test="${empty wishList}">
							<span style="color:#ffffff;">내가 찜한 동영상</span>
						</c:if>
					</th>
				</tr>
				
				<c:if test="${empty wishList}">
				<tr style="height:450px">
					<th align="center">
						<h2>
							<font size="5">찜한 영상이 없습니다.</font>
						</h2>
					</th>
				</tr>
				</c:if>
				
				<c:if test="${not empty wishList}">
				<c:forEach var="dto" items="${wishList}">
				<tr style="height:150px">
					<td align="center"><a href="goMovieContents.movie?movienum=${dto.movienum}">
						<img src="filePath1/${dto.image}"  width = "150" height = "150" />
					</a></td>
				</tr>
				</c:forEach>
				</c:if>
			</table>
		</div>
		
		<div class="col-md-offset-2 col-md-3">
			<table align="center" border="1" style="height:500px">
				<tr style="height:50px">
					<th width="500">
						<c:if test="${not empty watchList}">
							<a href="goWatchList.movie"><span style="color:#ffffff;">내가 본 동영상</span></a>
						</c:if>
						
						<c:if test="${empty watchList}">
							<span style="color:#ffffff;">내가 본 동영상</span>
						</c:if>
					</th>
				</tr>
				
				<c:if test="${empty watchList}">
				<tr style="height:450px">
					<td align="center" style="height:450px">
						<h2>
							<font size="5">본 영상이 없습니다.</font>
						</h2>
					</td>
				</tr>
				</c:if>
				
				<c:if test="${not empty watchList}">
				<c:forEach var="dto" items="${watchList}">
				<tr style="height:150px">
					<td align="center"><a href="goMovieContents.movie?movienum=${dto.movienum}">
						<img src="filePath1/${dto.image}"  width = "150" height = "150" />
					</a></td>
				</tr>
				</c:forEach>
				</c:if>
			</table>
		</div>
	</div>
	<br><br>	<br><br>
	
	
	