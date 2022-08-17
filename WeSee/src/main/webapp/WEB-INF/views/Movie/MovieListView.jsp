<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>   
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %> 
	
	<!-- MovieSearch.jsp -->
	
	<%@ include file="MovieTop.jsp" %>
	
<style>
	th, td {
	padding: 5px;
    }
    .text_center{
    	text-align: center;
    }
</style>

	<div class="container-fluid">
	<div class="row">
		<form name="f" action="goMovieNameSearch.movie" method="post">
			<div class="col-md-offset-4 col-md-3">			
				<input type="text" class="form-control input-lg" name="title" size="50">
			</div>
			<div class="col-md-1">
				<input type="submit" class="btn btn-primary btn-lg" value="검색">
			</div>
		</form>
	</div>
	<br>
	
		<c:choose>
			<c:when test="${not empty wishList}">
			<c:set var="lineSpace" value="0"/>
			<div class="row">
				<div class="col-md-offset-5 col-md-2">
					<label>내가 찜한 영상</label>
				</div>
			</div>
			
			<div class="row">
				<div class="col-md-offset-1">
					<table>
						<tr>
							<c:forEach var="mdto" items="${wishList}">
								<c:set var="lineSpace" value="${lineSpace+1}"/>
								
								<th>
									<a href="goMovieContents.movie?movienum=${mdto.movienum}">
									<img src="filePath1/${mdto.image}" style="width:250px;height:300px;"/><br>
									<label class="text_center">${mdto.title}</label>
									</a>
								</th>
								
								<c:if test="${lineSpace > 5}">
								<c:set var="lineSpace" value="0"/>
									</tr>
									<tr>
								</c:if>
									
							</c:forEach>
						</tr>
					</table>
				</div>
			</div>
			</c:when>
			
			<c:otherwise>
				<c:set var="lineSpace" value="0"/>
				<div class="row">
					<div class="col-md-offset-5 col-md-2">
						<label>내가 본 영상</label>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-offset-1">
						<table>
							<tr>
								<c:forEach var="mdto" items="${watchList}">
									<c:set var="lineSpace" value="${lineSpace+1}"/>
									
									<th>
										<a href="goMovieContents.movie?movienum=${mdto.movienum}">
										<img src="filePath1/${mdto.image}" style="width:250px;height:300px;"/><br>
										<span class="text_center">${mdto.title}</span>
										</a>
									</th>
									
									<c:if test="${lineSpace > 5}">
									<c:set var="lineSpace" value="0"/>
										</tr>
										<tr>
									</c:if>
										
								</c:forEach>
							</tr>
						</table>
					</div>
				</div> 
			</c:otherwise>
		</c:choose>		
	</div>
	
<%@ include file="MovieBottom.jsp"%>	