<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<!-- CSBoardList -->
	
<c:choose>
	<c:when test="${not empty AdminloginData}">
		<html lang="ko-kr">
		    <head>
		    	<meta charset="utf-8">
			    <meta http-equiv="X-UA-Compatible" content="IE=edge">
			    <!-- <meta name="viewport" content="width=device-width, initial-scale=1">
			     -->
			    <link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css">
			    
			<title>관리자 고객센터 게시판</title>
			
			</head>
			<body>
				<div class="container-fluid">
					<div class="row">
					<div align="right" style="font-size: 20pt;">
						<a href="doAdminLogout.movie">로그아웃</a>
					</div>
						<div class="col-md-offset-5 col-md-2">
							<a href="goAdminManagerPage.admin">
								<img src="filePath1/WeSee.png" style='height: 200px; width: 200px;'/>
							</a>
						</div>
					</div><p>
	</c:when>
	
	<c:otherwise>
		<%@ include file="CSBoardTop.jsp" %>
	</c:otherwise>
</c:choose>

<style>
	th, td {
	text-align: center;
    }
</style>	

	
	<div class="row">
		<form name="f" action="list_csboard.do" method="post">
		<div class="col-md-offset-3 col-md-1">
			<select name = "search" class="form-search">
				<option value = "요금제">요금제</option>
				<option value = "영상">영상</option>
				<option value = "굿즈문의">굿즈문의</option>
			</select>
		</div>
		
		<div class="col-md-3">
			<input class="form-control" size="50" type = "text" placeholder="제목을 입력해주세요." value="${searchString}" name="searchString">
			<input type="hidden" name="mode" value="find"/>
		</div>
		
		<div class="col-md-1">
			<input class="btn btn-primary btn-fluid" type = "submit" value="찾기">
		</div>
		</form>
	</div>
	<br>
	
	<div class="row">
		<div class="col-md-offset-9 col-md-1" align="right">
			<a href= "writeForm.do">글쓰기</a>
		</div>
	</div>
	<br>
	<div class="row">
		<div class="col-md-offset-2 col-md-8">
			<table class="table table-hover">
				<thead>
				<tr>
					<th width="10%">게시글<br>번호</th>
					<th width="10%">문의종류</th>
					<th width="30%">제목</th>
					<th width="20%">작성자</th>
					<th width="10%">작성일</th>
					<th width="10%">비밀글<br>여부</th>
					<th width="10%">답글<br>여부</th>
				</tr>
				</thead>
				
				<tbody>
					<c:if test="${empty listCSBoard}">
						<tr>
							<td colspan="7">등록된 게시글이 없습니다.</td>
						</tr>
					</c:if>	
					
					<c:forEach var="csdto" items="${listCSBoard}">
						<tr>
							<td>${csdto.csboardnum}</td>
							<td>${csdto.csoption}</td>
							<td>
								<a href="content_csboard.do?csboardnum=${csdto.csboardnum}&secret=${csdto.secret}&${commentOk}">
									${csdto.csboardtitle}
								</a>
							</td>
							<td>${csdto.csboardname}</td>
							<td>${csdto.cswritedate}</td>
							<td>${csdto.secret}</td>
							<td>${csdto.recomment}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<br>
	
	<div class="row">
		<div class="col-md-offset-6 col-md-2">
			<c:if test="${rowCount>0}">			
				<c:if test="${startPage>1}">
					[<a href="list_csboard.do?pageNum=${startPage-1}">이전</a>]			
				</c:if>
				<c:forEach var="i" begin="${startPage}" end="${endPage}">
					[<a href="list_csboard.do?pageNum=${i}"><c:out value="${i}"/></a>]	
				</c:forEach>
				<c:if test="${endPage<pageCount}">
					[<a href="list_csboard.do?pageNum=${endPage+1}">다음</a>]			
				</c:if>
			</c:if>
		</div>
	</div>
	<br><br><br>
</body>
</html>