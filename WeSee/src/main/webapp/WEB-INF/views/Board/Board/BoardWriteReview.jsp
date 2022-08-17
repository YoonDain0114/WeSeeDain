<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

	<!-- writeForm.jsp -->
	
<%@ include file="BoardTop.jsp" %>

<c:set var="today" value="<%=new java.util.Date()%>" />
<c:set var="date"><fmt:formatDate value="${today}" pattern="yyyy-MM-dd hh:mm" /></c:set>
<link href="${pageContext.request.contextPath}/resources/css/star.css" rel="stylesheet"/>

<script type="text/javascript">

	function check(){
		if (f.boardtitle.value==""){
			alert("제목을 입력해 주세요!!")
			f.boardtitle.focus()
			return false
		}else if (f.boardcontents.value==""){
			alert("내용을 입력해주세요!!")
			f.boardcontents.focus()
			return false
		}	
		return true
	}
</script>

<style>
	th, td {
	text-align: center;
	vertical-align: middle;
    }
    select{
    color:black;
    vertical-align: left;
    padding:  .5em; /* 여백으로 높이 설정 */
    font-family: inherit;
    }
</style>

	<form name="f" action="writereview.do" method="post" onsubmit="return check()">
		<input type="hidden" name="boardname" value="${loginData.name}">
		<input type="hidden" name="writedate" value="${date}">
		
		<div class="row">
			<div class="col-md-offset-5 col-md-2" align="center">
				<label>리 뷰 작 성</label>
			</div>
		</div>
		<br>
		<br>
		
		<div class="row">
			<div class="col-md-offset-2 col-md-8">
				<table>
					<tr style="height:80px">
						<th style="width:200px">
							<label>제 목</label>
						</th>
						<td style="width:500px">
							<input class="form-control" type="text" name="boardtitle" size="50">
						</td>
						
						<th style="width:200px">
							<label>영 화 선 택</label>
						</th> 
						<div>
						<td style="width:500px" >
							<select required name="titlecate">
							 <option value="" disabled selected hidden>(영화 선택)</option>
								<c:forEach  items="${listMovie}" var="mdto">
								
									<option value = "${mdto.title}">[${mdto.title}]</option>
								
								</c:forEach>
							</select>
						</td>
					</tr>
					
					<tr style="height:80px">
						<th style="width:200px">
							<label>작성자</label>
						</th>
						<td style="width:500px">
							<input class="form-control" type="text" value="${loginData.name}" size="50" disabled>
						</td>
						
						<th style="width:200px">
							<label>별점</label>
						</th> 
						<td style="width:500px" id="good">
							<input type="radio" name="Star" value="5" id="rate1"><label for="rate1">★</label>
							<input type="radio" name="Star" value="4" id="rate2"><label for="rate2">★</label>
							<input type="radio" name="Star" value="3" id="rate3"><label for="rate3">★</label>
							<input type="radio" name="Star" value="2" id="rate4"><label for="rate4">★</label>
							<input type="radio" name="Star" value="1" id="rate5" checked="checked"><label for="rate5">★</label>
						</td>
					</tr>
					
					<tr style="height:400px">
						<th width="50">
							<label>내용</label>
						</th>
						<td colspan="3">
							<textarea class="form-control" name="boardcontents" rows="11" cols="50"></textarea>
							
						</td>
					</tr>
					
					<tr>
						<td align="center" colspan="4">
							<br>
							<input type="submit" class="btn btn-primary" value="글쓰기">
							<input type="reset" class="btn btn-info" value="다시작성">
							<input type="button" class="btn btn-info" value="목록보기" onclick="window.location='board.do'">
						</td>
					</tr>
				</table>
			</div>
		</div>
	</form>
</body>
</html>
