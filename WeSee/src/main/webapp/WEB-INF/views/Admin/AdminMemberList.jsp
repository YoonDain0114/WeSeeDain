<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %> 

<%@ include file="AdminManagerTop.jsp"%>

	<!-- memberPage.jsp -->
	
<style>
	th, td {
	text-align: center;
    }
</style>

<script type="text/javascript">
	function checkDel(membernum, memberimage){
		var isDel = window.confirm("정말로 삭제하시겠습니까?")
		if (isDel){
			document.f.membernum.value = membernum
			document.f.memberimage.value = memberimage
			document.f.submit()
		}
	}
</script>

	<div class="row">
		<div class="col-md-offset-5 col-md-2">
			<p style="text-align:center;font-size:30pt;">회원 관리</p>
		</div>
	</div>
	<br>
	
	<div class="row">
		<form name="d" action="doMemberFind.member" method="post">
			<div class="col-md-offset-5 col-md-2">
				<input type="text" class="form-control" name="name" value="${name}" placeholder="회원명">
			</div>
			<div class="col-md-1">	
				<input type="submit" class="btn btn-secondary btn-block" value="검색"><br>
			</div>
		</form>
	</div>
	<br><br>
	
	<div class="row">
		<div class="col-md-offset-1 col-md-10">
			<table class="table table-hover">
				
				<thead class="table-primary">
					<tr>
						<th width="10%">번호</th>
						<th width="10%">이름</th>
						<th width="15%">생년월일</th>
						<th width="10%">전화번호</th>
						<th width="10%">이메일</th>
						<th width="20%">가입 요금제</th>
						<th width="10%"></th>
					</tr>
				</thead>
				
				<tbody class="table table-striped">
					<c:if test="${empty listMember}">
						<tr>
							<th colspan="7">등록된 회원이 없습니다.</th>
						</tr>
					</c:if>
					
					<c:forEach items="${listMember}" var="dto">
					<tr>
						<td>${dto.membernum}</td>
						<td>${dto.name}</td>
						<td>${dto.birth}</td>
						<td>${dto.allhp}</td>
						<td>${dto.email}</td>
						<td>${dto.fe}</td>
						<td>
							<a href="javascript:checkDel('${dto.membernum}','${dto.memberimage}')">삭제</a>
						</td>
					</tr>		
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	
	<form name="f" action="doMemberDelete.member" method="post">
		<input type="hidden" name="membernum"/>
		<input type="hidden" name="memberimage"/>
	</form>
	
	<div class="row">
		<div class="col-md-offset-4 col-md-4" align="center">
			<c:if test="${rowCount>0}">			
				<c:if test="${startPage>1}">
					[<a href="goMemberList.member?pageNum=${startPage-1}">이전</a>]	 &nbsp;		
				</c:if>
				
				<c:forEach var="i" begin="${startPage}" end="${endPage}">
					[<a href="goMemberList.member?pageNum=${i}"><c:out value="${i}"/></a>] &nbsp;	
				</c:forEach>
				
				<c:if test="${endPage<pageCount}">
					[<a href="goMemberList.member?pageNum=${endPage+1}">다음</a>]			
				</c:if>
			</c:if>
		</div>
	</div>
	
	<br><br>
	
</body>
</html>