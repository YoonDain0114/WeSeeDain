<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   
	<!-- board -->
	
<%@ include file="BoardTop.jsp" %>

<style>
	th, td {
	text-align: center;
	vertical-align: middle;
    }
</style>
 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/netflix.css"></head>
	
	<div class="row">
		<div class="col-md-offset-5 col-md-2" align="center">
			<label>리뷰 게시판</label>
		</div>
	</div>
	<br>
	
	<div class="row">
		<form name="f" action="find.do" method="post">
			<div class="col-md-offset-4 col-md-3">
				<input class="form-control" type="text" name="boardtitle" class="search" value="${boardtitle}" placeholder="글 제목 검색" size="50">
			</div>
			<div class="col-md-1">
				<input class="btn btn-primary btn-fluid" type="submit" value="검색">	
			</div>
		</form>
	</div>
	<br>
	
	<div class="row">
		<div class="col-md-offset-10 col-md-1">
			<input type="button" class="sign" value="글쓰기" onclick="window.location='writereview.do'">
		</div>
	</div>
	<br>
	
	<div class="row">
		<div class="col-md-offset-2 col-md-8" style="background-color:#202023">
			<table class="table table-hover">
				<thead>
					<tr style="height:80px;">
						<th width="5%">글번호</th>
						<th width="20%">영화제목</th>
						<th width="20%">글제목</th>
						<th width="10%">별점 </th>
						<th width="10%">작성자</th>
						<th width="10%">조회</th>
						<th width="15%">작성날짜</th>
						<th width="10%">추천</th>
					</tr>
				</thead>
				
				<tbody>
					<c:if test="${empty listBoard}">
						<tr style="height:80px;">
							<td colspan="8">등록된 게시글이 없습니다.</td>
						</tr>
					</c:if>	
					
					<c:forEach var="dto" items="${listBoard}">
						<c:set var="pageNum" value="${pageNum-1}"/>
						
						<tr style="height:80px;">
							<td>${dto.boardnum}</td>
							<td>${dto.titlecate}</td>
							<td><a href="boardcontent.do?boardnum=${dto.boardnum}">${dto.boardtitle}</a></td>
							<td align="left">
							<c:choose>
								<c:when test="${dto.star==1}"><font color="yellow">★</font></c:when>
								<c:when test="${dto.star==2}"><font color="yellow">★★</font></c:when>
								<c:when test="${dto.star==3}"><font color="yellow">★★★</font></c:when>
								<c:when test="${dto.star==4}"><font color="yellow">★★★★</font></c:when>
								<c:when test="${dto.star==5}"><font color="yellow">★★★★★</font></c:when>
							
								<c:otherwise>
									<font color="white">선택안함</font>
								</c:otherwise>
							</c:choose>
							</td>
							<td>${dto.boardname}</td>
							<td>${dto.readcount}</td>
							<td>${dto.writedate}</td>
							<td>${dto.good}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<br><br>
	
	<div class="row">
		<div class="col-md-offset-5">
			<c:if test="${rowCount>0}">	
		 		<a href="board.do">처음으로</a>&nbsp;
				<c:if test="${startPage>1}">
					<a href="board.do?pageNum=${startPage-1}">이전</a>	&nbsp;	
				</c:if>
				<c:forEach var="i" begin="${startPage}" end="${endPage}">
					<a href="board.do?pageNum=${i}"><c:out value="${i}"/></a>&nbsp;	
				</c:forEach>
				<c:if test="${endPage<pageCount}">
					<a href="board.do?pageNum=${endPage+1}">다음</a>			
				</c:if>
			</c:if>
		</div>
	</div>
	<br><br>
</body>
</html>