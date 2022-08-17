<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<!--상세보기 페이지입니다.-->

<%@ include file="AdminManagerTop.jsp"%>

	<div class="row">
		<div class="col-md-offset-5 col-md-2">
			<p style="text-align:center;font-size:25pt;">영상 상세보기</p>
		</div>
	</div>
	<br>
	<br>

	<form id="frm" name="f" action="doAdminUpload.admin" method="post"
		enctype="multipart/form-data">
		
	<div class="row">
		<div class="col-md-offset-3 col-md-3">
			<div class="col-md-4">
				<p style="text-align:center;font-size:15pt;">영상제목</p>
			</div>
			<div class="col-md-8">
				${getMovie.title}
			</div>
		</div>
		
		<div class="col-md-3">
			<div class="col-md-4">
				<p style="text-align:center;font-size:15pt;">개봉일</p>
			</div>
			<div class="col-md-8">
				${getMovie.opendate}
			</div>
		</div>
	</div><br>
	
	<div class="row">
		<div class="col-md-offset-3 col-md-3">
			<div class="col-md-4">
				<p style="text-align:center;font-size:15pt;">상영시간</p>
			</div>
			<div class="col-md-8">
				${getMovie.times}분
			</div>
			
		</div>
		
		<div class="col-md-3">
			<div class="col-md-4">
				<p style="text-align:center;font-size:15pt;">감독</p>
			</div>
			<div class="col-md-8">
				${getMovie.director}
			</div>
		</div>
	</div><br>
	
	<div class="row">
		<div class="col-md-offset-3 col-md-3">
			<div class="col-md-4">
				<p style="text-align:center;font-size:15pt;">장르</p>
			</div>
			<div class="col-md-8">
				${getMovie.genre}
			</div>
			
		</div>
		
		<div class="col-md-3">
			<div class="col-md-4">
				<p style="text-align:center;font-size:15pt;">관람제한가</p>
			</div>
			<div class="col-md-8">
				${getMovie.age}세
			</div>
		</div>
	</div><br>
	
	<div class="row">
		<div class="col-md-offset-3 col-md-3">
			<div class="col-md-4">
				<p style="text-align:center;font-size:15pt;">영화포스터</p>
			</div>
			<div class="col-md-8">
				<img src="filePath1/${getMovie.image}" width="400" heigth="400">
			</div>
			
		</div>
	</div><br>
	
	<div class="row">
		<div class="col-md-offset-3 col-md-1">
			<p style="text-align:center;font-size:15pt;">출연진</p>
		</div>
		<div class="col-md-5">
			${getMovie.actor}
		</div>
	</div><br>
	
	<c:if test="${not empty getMovie.videofile}">
	<div class="row">
		<div class="col-md-offset-3 col-md-3">
			<div class="col-md-4">
				<p style="text-align:center;font-size:15pt;">영상플레이360</p>
			</div>
			<div class="col-md-8">
				<video id="videofile" width="400" height="300" muted controls autoplay>
					<source src="filePath1/${getMovie.videofile}" type="video/mp4">
				</video>
			</div>
			
		</div>
	</div>
	<div class="row">
		<div class="col-md-offset-3 col-md-3">
			<div class="col-md-4">
				<p style="text-align:center;font-size:15pt;">영상플레이720</p>
			</div>
			<div class="col-md-8">
				<video id="videofile2" width="400" height="300" muted controls autoplay>
					<source src="filePath1/${getMovie.videofile2}" type="video/mp4">
				</video>
			</div>
			
		</div>
	</div>
	<div class="row">
		<div class="col-md-offset-3 col-md-3">
			<div class="col-md-4">
				<p style="text-align:center;font-size:15pt;">영상플레이1080</p>
			</div>
			<div class="col-md-8">
				<video id="videofile3" width="400" height="300" muted controls autoplay>
					<source src="filePath1/${getMovie.videofile3}" type="video/mp4">
				</video>
			</div>
			
		</div>
	</div>
	</c:if>
	<br>
	
	<div class="row">
		<div class="col-md-offset-3 col-md-1">
			<p style="text-align:center;font-size:15pt;">시놉시스</p>
		</div>
		<div class="col-md-5">
			<textarea class="form-control" name="moviecontents" rows="5" cols="60" style="height:150px;">${getMovie.moviecontents}</textarea>
		</div>
	</div><br>
	
	<div class="row">
		<div class="col-md-offset-5 col-md-1">
			<input type="button" class="btn btn-secondary btn-fluid" value="돌아가기" onclick="window.location='goAdminList.admin'">
		</div>
	</div>
	<br>
	<br>
		
	</form>
</body>
</html>