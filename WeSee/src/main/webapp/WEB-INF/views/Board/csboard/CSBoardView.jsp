<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
	<!-- csBoardView.jsp -->
	
<%@ include file="CSBoardTop.jsp" %>	
 
<script type="text/javascript">
	function send(){
		document.send.submit()
	}
</script>

	<div class="row">
		<div class="col-md-offset-5 col-md-2" align="center">
			<label>문의글 상세보기</label>
		</div>
	</div>
	<br><br>
	
	<div class="row">
		<div class="col-md-offset-3 col-md-6">
			<div class="row">
				<div class="col-md-1">
					<label>글쓴이</label>
				</div>
				<div class="col-md-5">
					${getCsBoard.csboardname}
				</div>
				
				<div class="col-md-1">
					<label>분류</label>
				</div>
				<div class="col-md-3">
					${getCsBoard.csoption}
				</div>
			</div>
			<br>
			
			<div class="row">
				<div class="col-md-1">
					<label>제 목</label>
				</div>
				<div class="col-md-11">
					${getCsBoard.csboardtitle}
				</div>
			</div>
			<br>
			
			<div class="row">
				<div class="col-md-1">
					<label>내 용</label>
				</div>
				<div class="col-md-11" style="height:200px">
					${getCsBoard.csboardcontents}
				</div>
			</div>
			<br>
			
			<div class="row">
				<div class="col-md-2">
					<label>첨부파일</label><br>
					<label>(이미지만)</label>
				</div>
				<div class="col-md-10">
					<c:if test = "${empty getCsBoard.csimage}">
						이미지가 없습니다
					</c:if>
					
					<c:if test= "${not empty getCsBoard.csimage}">
						<img src="filePath1/${getCsBoard.csimage}" style="width:200px;height:200px;">
					</c:if>
				</div>
			</div>
			<br><br>
			
			<form name="send" method="post" action="checkpw_csboard.do">
			 	<input type="hidden" name="csimage" value="${getCsBoard.csimage}"/>
			 	<input type="hidden" name="mode" value="godelete"/>
			 	<input type="hidden" name="csboardnum" value="${getCsBoard.csboardnum}"/>
			 	
			<div class="row">
				<div class="col-md-offset-5 col-md-1">
					<input class="btn btn-success btn-fluid" type="submit" value="삭제">
				</div>
				&nbsp;
				<div class="col-md-1">
					<input class="btn btn-info btn-fluid" type="button" value="돌아가기" onclick="window.location='list_csboard.do'">
				<!--  	<input type="button" value="목록보기" onclick="window.location='list_board.do'">  -->
				</div>
				&nbsp;
				<c:if test="${AdminloginData.name == 'admin'}">
					<div class="col-md-1">
						<input class="btn btn-info btn-fluid" type="button" value="답글쓰기" onclick="window.location='commentForm_csboard.do?csboardnum=${getCsBoard.csboardnum}'"/>
					<!--  	<input type="button" value="목록보기" onclick="window.location='list_board.do'">  -->
					</div>
				</c:if>
			</div>
			</form>
			<br><br>
			
			<div class="row">
				<div class="col-md-offset-3 col-md-6" align="center">
					<div class="col-md-2">
						no.
					</div>
					<div class="col-md-4">
						작성자
					</div>
					<div class="col-md-4">
						작성날짜
					</div>
				</div>
			</div>
			<br><br>
			
			<c:if test="${empty listComment}">
				<div class="row">
					<div class="col-md-offset-3 col-md-6" align="center">
						<label>등록된 답글이 없습니다.</label>
					</div>
				</div>
			</c:if>
			
			<c:if test="${not empty listComment}">	
				<c:forEach var="codto" items="${listComment}">
				
				<div class="row">
					<div class="col-md-offset-3 col-md-6" align="center">
						<div class="col-md-2">${codto.cno}</div>
						<div class="col-md-4">${codto.writer}</div>
						<div class="col-md-4">${codto.reg_date}</div>
					</div>
				</div>
				<br>
				
				<div class="row">
					<div class="col-md-offset-3 col-md-6">
						${codto.content}
					</div>
				</div>
				<br><br>
				
				<c:if test="${AdminloginData.name == 'admin'}">
					<div class="row">
						<div class="col-md-offset-3 col-md-6">
							<form name="send" method="post" action="commentDelete.do">
								<input type="hidden" name="cno" value="${codto.cno}"/>
								<input type="hidden" name="bno" value="${codto.bno}"/>
								<input class="btn btn-danger" type="submit" value="삭제"/> 
							</form>
						</div>
					</div>
				</c:if>
				</c:forEach> 	 
			</c:if>
			
			
			<br><br>
		</div>
	</div>
</body>
</html>