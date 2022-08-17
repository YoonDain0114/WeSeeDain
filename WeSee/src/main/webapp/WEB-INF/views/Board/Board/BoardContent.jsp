<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<!-- content.jsp -->
	
<%@ include file="BoardTop.jsp" %>
	
	<br><br>
	
  
	<div class="row">
		<div class="col-md-offset-5 col-md-2" align="center">
			<label>글내용 보기</label>
		</div>
	</div>
	<br><br>
	
	<div class="row">
		<div class="col-md-offset-3 col-md-6">
			<div class="col-md-2" style="border: 1px solid grey;">
				<label>글제목</label>
			</div>
			<div class="col-md-4" style="border: 1px solid grey;">
				<label>${getBoard.boardtitle}</label>
			</div>
			
			<div class="col-md-2" style="border: 1px solid grey;">
				<label>리뷰영화</label>
			</div>
			<div class="col-md-4" style="border: 1px solid grey;">
				<label>${getBoard.titlecate}</label>
			</div>
		</div>
	</div>
	<br>
	
	<div class="row">
		<div class="col-md-offset-3 col-md-6">
			<div class="col-md-2" style="border: 1px solid grey;">
				<label>작성자</label>
			</div>
			<div class="col-md-4" style="border: 1px solid grey;">
				<label>${getBoard.boardname}</label>	
			</div>
				
			<div class="col-md-2" style="border: 1px solid grey;">
				<label>작성일</label>
			</div>
			<div class="col-md-4" style="border: 1px solid grey;">
				<label>${getBoard.writedate}</label>
			</div>
		</div>
	</div>
	<br>
	
	<div class="row">
		<div class="col-md-offset-3 col-md-6">
			<div class="col-md-2" style="border: 1px solid grey;">
				<label>조회수</label>
			</div>
			<div class="col-md-2" style="border: 1px solid grey;">
				<label>${getBoard.readcount}</label>	
			</div>
				
			<div class="col-md-2" style="border: 1px solid grey;">
				<label>추천수</label>
			</div>
			<div class="col-md-2" style="border: 1px solid grey;">
				<label>${getBoard.good}</label>
			</div>
			
			<div class="col-md-2" style="border: 1px solid grey;">
				<label>추천하기</label>
			</div>
			<div class="col-md-2" style="border: 1px solid grey;">
				<a onclick="return confirm('추천하시겠습니까?')" href="good_board.do?boardnum=${getBoard.boardnum}">
					<img src="filePath1/images/good.png" width="27" height="27">
				</a>
			</div>
		</div>
	</div>
	<br>
	
	<div class="row">
		<div class="col-md-offset-3 col-md-6" style="overflow:scroll;height:800px;border: 1px solid grey;">
			${getBoard.boardcontents}
					
		</div>
	</div>
	<br><br>
	
	<div class="row">
		<div class="col-md-offset-5 col-md-1">
			<input type="button" class="btn btn-success btn-fluid" value="글수정"
			onclick="window.location='update_board.do?boardnum=${getBoard.boardnum}'">
		</div>
		<div class="col-md-1">
			<input type="button" class="btn btn-warning btn-fluid" value="글삭제" 
			onclick="window.location='delete_board.do?boardnum=${getBoard.boardnum}'">
		</div>
		<div class="col-md-1">
			<input type="button" class="btn btn-secondary btn-fluid" value="글목록" onclick="window.location='board.do'">
		</div>
	</div>
	<br><br>
</div>
</body>
</html>