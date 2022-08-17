<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@ include file="AdminManagerTop.jsp"%>
	<br><br>
	
	<div class="row" style="height:300px;">
		<div class="col-md-offset-3 col-md-6" align="center">
			<div class="col-md-4">
				<a href="goAdminList.admin">
					<svg xmlns="http://www.w3.org/2000/svg" style="width:200px;height:200px;" fill="currentColor" class="bi bi-files" viewBox="0 0 16 16">
					  <path d="M13 0H6a2 2 0 0 0-2 2 2 2 0 0 0-2 2v10a2 2 0 0 0 2 2h7a2 2 0 0 0 2-2 2 2 0 0 0 2-2V2a2 2 0 0 0-2-2zm0 13V4a2 2 0 0 0-2-2H5a1 1 0 0 1 1-1h7a1 1 0 0 1 1 1v10a1 1 0 0 1-1 1zM3 4a1 1 0 0 1 1-1h7a1 1 0 0 1 1 1v10a1 1 0 0 1-1 1H4a1 1 0 0 1-1-1V4z"/>
					</svg>
					<p style="text-align:center;font-size:30pt;">영상관리</p>
				</a>
			</div>
			
			<div class="col-md-4" align="center">
				<a href="goAdminGoodsList.admin">
					<svg xmlns="http://www.w3.org/2000/svg" style="width:200px;height:200px;" fill="currentColor" class="bi bi-bag-plus" viewBox="0 0 16 16">
					  <path fill-rule="evenodd" d="M8 7.5a.5.5 0 0 1 .5.5v1.5H10a.5.5 0 0 1 0 1H8.5V12a.5.5 0 0 1-1 0v-1.5H6a.5.5 0 0 1 0-1h1.5V8a.5.5 0 0 1 .5-.5z"/>
					  <path d="M8 1a2.5 2.5 0 0 1 2.5 2.5V4h-5v-.5A2.5 2.5 0 0 1 8 1zm3.5 3v-.5a3.5 3.5 0 1 0-7 0V4H1v10a2 2 0 0 0 2 2h10a2 2 0 0 0 2-2V4h-3.5zM2 5h12v9a1 1 0 0 1-1 1H3a1 1 0 0 1-1-1V5z"/>
					</svg>
					<p style="text-align:center;font-size:30pt;">굿즈관리</p>
				</a>
			</div>
			
			<div class="col-md-4" align="center">
				<a href="doYearSalelist.admin?searchString=2022">
					<svg xmlns="http://www.w3.org/2000/svg" style="width:200px;height:200px;" fill="currentColor" class="bi bi-bar-chart-line" viewBox="0 0 16 16">
					  <path d="M11 2a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1v12h.5a.5.5 0 0 1 0 1H.5a.5.5 0 0 1 0-1H1v-3a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1v3h1V7a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1v7h1V2zm1 12h2V2h-2v12zm-3 0V7H7v7h2zm-5 0v-3H2v3h2z"/>
					</svg>
					<p style="text-align:center;font-size:30pt;">매출관리</p>
				</a>
			</div>
		</div>
	</div>
	<br>
	
	<div class="row">
		<div class="col-md-offset-3 col-md-6">
			<div class="col-md-5" align="center">
				<a href="goMemberList.member">
					<svg xmlns="http://www.w3.org/2000/svg" style="width:200px;height:200px;" fill="currentColor" class="bi bi-person-lines-fill" viewBox="0 0 16 16">
					  <path d="M6 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm-5 6s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H1zM11 3.5a.5.5 0 0 1 .5-.5h4a.5.5 0 0 1 0 1h-4a.5.5 0 0 1-.5-.5zm.5 2.5a.5.5 0 0 0 0 1h4a.5.5 0 0 0 0-1h-4zm2 3a.5.5 0 0 0 0 1h2a.5.5 0 0 0 0-1h-2zm0 3a.5.5 0 0 0 0 1h2a.5.5 0 0 0 0-1h-2z"/>
					</svg>
					<p style="text-align:center;font-size:30pt;">회원관리</p>
				</a>
			</div>
			
			<div class="col-md-offset-2 col-md-5" align="center">
				<a href="list_csboard.do">
					<svg xmlns="http://www.w3.org/2000/svg" style="width:200px;height:200px;" fill="currentColor" class="bi bi-journal-text" viewBox="0 0 16 16">
					  <path d="M5 10.5a.5.5 0 0 1 .5-.5h2a.5.5 0 0 1 0 1h-2a.5.5 0 0 1-.5-.5zm0-2a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5zm0-2a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5zm0-2a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 0 1h-5a.5.5 0 0 1-.5-.5z"/>
					  <path d="M3 0h10a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H3a2 2 0 0 1-2-2v-1h1v1a1 1 0 0 0 1 1h10a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H3a1 1 0 0 0-1 1v1H1V2a2 2 0 0 1 2-2z"/>
					  <path d="M1 5v-.5a.5.5 0 0 1 1 0V5h.5a.5.5 0 0 1 0 1h-2a.5.5 0 0 1 0-1H1zm0 3v-.5a.5.5 0 0 1 1 0V8h.5a.5.5 0 0 1 0 1h-2a.5.5 0 0 1 0-1H1zm0 3v-.5a.5.5 0 0 1 1 0v.5h.5a.5.5 0 0 1 0 1h-2a.5.5 0 0 1 0-1H1z"/>
					</svg>
					<p style="text-align:center;font-size:30pt;">고객센터</p>
				</a>
			</div>
		</div>
	</div>