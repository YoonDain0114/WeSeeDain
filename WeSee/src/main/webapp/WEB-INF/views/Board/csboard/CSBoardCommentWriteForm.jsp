<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<!--CSBoardCommentWriteForm-->
	
<%@ include file="CSBoardTop.jsp" %>

	<form name="f" action="commentForm_csboard.do" method="post">
		<input type="hidden" name="writer" value="${AdminloginData.name}"/>
		<input type="hidden" name="bno" value="${getCsBoard.csboardnum}"/>
	
	<div class="row">
		<div class="col-md-offset-4 col-md-4" align="center">
			<label>답 글 달 기</label>
		</div>
	</div>
	
	<div class="row">
		<div class="col-md-offset-2 col-md-8">
			<div class="col-md-2">
				작성자
			</div>
			<div class="col-md-8">
				<c:if test="${not empty AdminloginData}">
					<input class="form-control" type="text" value="${AdminloginData.name}" name="writer" size="50" disabled>
				</c:if>
			</div>
		</div>
	</div>
	<br>
	
	<div class="row">
		<div class="col-md-offset-2 col-md-8">
			<div class="col-md-2">
				내 용
			</div>
			<div class="col-md-8">
				<textarea class="form-control" name="content" rows="11" cols="50"></textarea>
			</div>
		</div>
	</div>
	<br>	
	
	<div class="row">
		<div class="col-md-offset-4 col-md-1">
			<input class="btn btn-primary btn-fluid" type="submit" value="답글쓰기">
		</div>
		<div class="col-md-1">
			<input class="btn btn-secondary btn-fluid" type="reset" value="다시작성">
		</div>
		<div class="col-md-1">
			<input class="btn btn-primary btn-info" type="button" value="목록보기" onclick="window.location='list_csboard.do'">
		</div>
	</div>
	<br><br>
	
	</form>
</body>
</html>