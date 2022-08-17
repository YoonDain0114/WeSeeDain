<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<!-- writeForm.jsp -->
	
<%@ include file="CSBoardTop.jsp" %>

<script type="text/javascript">
	function check(){
		if (f.csboardcontents.value==""){
			alert("내용을 입력해 주세요!!")
			f.csboardcontentst.focus()
			return false
		}
	}
	
	function is_checked() {
		  // 1. checkbox element를 찾습니다.
		  var checkbox = document.getElementById('targetChkbox').checked;
		  //const checkbox = document.getElementById('targetChkbox');
		  if(checkbox){
			  document.getElementById('secret').value='1';
		  }else{
			  document.getElementById('secret').value='0';
		  }
	}
	  
</script>

	<div class="row">
		<div class="col-md-offset-5 col-md-2" align="center">
			<label>문의글 작성</label>
		</div>
	</div>
	<br><br>
	
	<form name="f" action="gowriteForm.do" method="post" enctype="multipart/form-data" onsubmit="return check()">
		<c:choose>
			<c:when test="${AdminloginData.name == 'admin'}">
				<input type="hidden" name="csboardname" value="${AdminloginData.name}"/>
			</c:when>
			
			<c:otherwise>
				<input type="hidden" name="csboardname" value="${loginData.name}"/>
			</c:otherwise>
		</c:choose>
		
		<input type="hidden" name="secret" id="secret"/>
	
	<div class="row">
		<div class="col-md-offset-3 col-md-6">
			<div class="row">
				<div class="col-md-1">
					<label>글쓴이</label>
				</div>
				<div class="col-md-5">
					<c:if test="${not empty AdminloginData}">
						<input class="form-control" type="text" value="${AdminloginData.name}" name="csboardname" size="30"  disabled>
					</c:if>
					
					<c:if test="${empty AdminloginData}">
						<input class="form-control" type="text" value="${loginData.name}" name="csboardname" size="30"  disabled>
					</c:if>
				</div>
				
				<div class="col-md-1">
					<label>분류</label>
				</div>
				<div class="col-md-3">
					<select class="form-select" name="csoption">
						<option value="요금제">요금제</option>
						<option value="영화">영화</option>
						<option value="굿즈문의">굿즈문의</option>
					</select>
				</div>
			</div>
			<br>
			
			<div class="row">
				<div class="col-md-1">
					<label>제 목</label>
				</div>
				<div class="col-md-11">
					<input class="form-control" type="text" name="csboardtitle" size="50">
				</div>
			</div>
			<br>
			
			<div class="row">
				<div class="col-md-1">
					<label>내 용</label>
				</div>
				<div class="col-md-11" style="height:200px">
					<textarea class="form-control" name="csboardcontents" rows="11" cols="50"></textarea>
				</div>
			</div>
			<br><br><br>
			
			<div class="row">
				<div class="col-md-2">
					<label>첨부파일</label><br>
					<label>(이미지만)</label>
				</div>
				<div class="col-md-5">
					<input class="form-control" type="file" name="csimage">
				</div>
				
				<div class="col-md-1">
					<label>비밀글 여부</label>
				</div>
				<div class="col-md-3">
					<input type="checkbox" name = "targetChkbox" id="targetChkbox" onclick = "is_checked()">
				</div>
			</div>
			<br><br>
			
			<div class="row">
				<div class="col-md-offset-5 col-md-1">
					<input class="btn btn-success btn-fluid" type="submit" value="글쓰기">
				</div>
				&nbsp;
				<div class="col-md-1">
					<input class="btn btn-secondary btn-fluid" type="reset" value="다시작성">
				</div>
				&nbsp;
				<div class="col-md-1">
					<input class="btn btn-info btn-fluid" type="button" value="목록보기" onclick="window.location='list_csboard.do'">
				<!--  	<input type="button" value="목록보기" onclick="window.location='list_board.do'">  -->
				</div>
			</div>
			<br><br>
		</div>
	</div>
</body>
</html>