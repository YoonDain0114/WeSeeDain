<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
<%@ include file="MovieTop.jsp"%>

<style>
	th, td {
	padding: 5px;
    }
</style>

<script type="text/javascript">
    function titleCheck(){
    if(f.title.value == ""){
        alert("영화제목을 입력해주세요!")
        return false;
    }
        return true;    
    }
    
</script>


	<div class="row">
		<form name="f" action="goMovieNameSearch.movie" method="post" onsubmit="return titleCheck();">
			<div class="col-md-offset-4 col-md-3">			
				<input type="text" class="form-control input-lg" name="title" value="${title}" size="50">
			</div>
			<div class="col-md-1">
				<input type="submit" class="btn btn-primary btn-lg" value="검색">
			</div>
		</form>
	</div>
	<br>
	
	<div class="row">
		<div class="col-md-offset-2">
			<label>검색 결과</label>
		</div>
	</div>
	<br>
	
	<div class="row">
		<div class="col-md-offset-2">
			<c:if test="${empty findMovie}">
				<span>검색 결과, 해당하는 영화가 없습니다.</span>
			</c:if>
			
			<table>
			<tr>
			<c:set var="lineSpace" value="0"/>
			<c:forEach var="mdto" items="${findMovie}">
				<c:set var="lineSpace" value="${lineSpace+1}"/>
				
				<th>
					<a href="goMovieContents.movie?movienum=${mdto.movienum}">
					<img src="filePath1/${mdto.image}" style="width:200px;height:300px;"/><br>
					<span>${mdto.title}</span>
					</a>
				</th>
				
				<c:if test="${lineSpace > 4}">
				<c:set var="lineSpace" value="0"/>
					</tr>
					<tr>
				</c:if>
					
			</c:forEach>
			</tr>
			</table>
		</div>
	</div>