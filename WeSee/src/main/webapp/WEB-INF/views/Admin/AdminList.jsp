<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
   
<%@ include file="AdminManagerTop.jsp"%>

	<!-- 영화 목록페이지입니다 -->
	
<script type="text/javascript">
	function checkDel(movienum, image){
		var isDel = window.confirm("정말로 삭제하시겠습니까?")
		if (isDel){
			document.f.movienum.value = movienum
			document.f.image.value = image
			document.f.submit()
		}
	}
</script>

<style>
	th, td {
	text-align: center;
    }
</style>

	<div class="row">
		<div class="col-md-offset-5 col-md-2">
			<p style="text-align:center;font-size:30pt;">영상목록</p>
		</div>
	</div>
	<br>
	
	<div class="row">
		<form name="d" action="goAdminFind.admin" method="post">
			<div class="col-md-offset-5 col-md-2">
				<input type="text" class="form-control" name="title" value="${title}" placeholder="영화 제목">
			</div>
			<div class="col-md-1">	
				<input type="submit" class="btn btn-secondary btn-block" value="검색"><br>
			</div>
		</form>
	</div>
	<br><br>
	
	<div class="row">
		<div class="col-md-offset-10 col-md-1">
			<div align="right">
				<font style="font-size:15pt;"><a href="goAdminUpload.admin">등록</a></font>
			</div>
		</div>
	</div>
					
	<div class="row">
		<div class="col-md-offset-1 col-md-10">
			<table class="table table-hover">
				
				
				<thead class="table-primary">
					<tr>
						<th width="5%">번호</th>
						<th width="10%">개봉일</th>
						<th width="15%">장르</th>
						<th width="15%">제목</th>
						<th width="20%">출연진</th>
						<th width="10%">감독</th>	
						<th width="10">영화포스터</th>
						<th width="5%">런타임</th>
						<th width="10%"></th>
					</tr>
				</thead>
				
				<tbody class="table table-striped">
					<c:if test="${empty listAdmin}">
						<tr>
							<th colspan="9">등록된 영상이 없습니다. 영상을 등록해주세요</th>
						</tr>
					</c:if>
					
					<c:forEach items="${listAdmin}" var="dto">
					<tr>
						<td>${dto.movienum}</td>
						<td>${dto.opendate}</td>
						<td>${dto.genre}</td>
						<td>${dto.title}</td>
						<td>${dto.actor}</td>
						<td>${dto.director}</td>
						<td>
							<a href="goAdminView.admin?movienum=${dto.movienum}">
								<img src="filePath1/${dto.image}" style="width:50px;height:50px;">
<%-- 								<img src="${upPath}/${dto.image}" style="width:50px;height:50px;"> --%>
							</a>
						</td>
						<td>${dto.times}분</td>
						<td>
							<a href="goAdminUpdate.admin?movienum=${dto.movienum}">수정</a> | 
							<a href="javascript:checkDel('${dto.movienum}','${dto.image}')">삭제</a>
						</td>
					</tr>		
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	
	<form name="f" action="doAdmindelete.admin" method="post">
		<input type="hidden" name="movienum"/>
		<input type="hidden" name="image"/>
	</form>
	
	<div class="row">
		<div class="col-md-offset-4 col-md-4" align="center">
			<c:if test="${rowCount>0}">			
				<c:if test="${startPage>1}">
					[<a href="goAdminList.admin?pageNum=${startPage-1}">이전</a>]	 &nbsp;		
				</c:if>
				
				<c:forEach var="i" begin="${startPage}" end="${endPage}">
					[<a href="goAdminList.admin?pageNum=${i}"><c:out value="${i}"/></a>] &nbsp;	
				</c:forEach>
				
				<c:if test="${endPage<pageCount}">
					[<a href="goAdminList.admin?pageNum=${endPage+1}">다음</a>]			
				</c:if>
			</c:if>
		</div>
	</div>
	
	<br><br>
	
</body>
</html>