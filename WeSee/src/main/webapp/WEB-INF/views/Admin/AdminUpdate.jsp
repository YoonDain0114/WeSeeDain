<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="AdminManagerTop.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

	<!-- 영화수정페이지입니다 -->

<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css"
	type="text/css" />
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>

<script type="text/javascript">

$(function() {
    //input을 datepicker로 선언
    $("#datepicker").datepicker({
        dateFormat: 'yy-mm-dd' //달력 날짜 형태
        ,showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
        ,showMonthAfterYear:true // 월- 년 순서가아닌 년도 - 월 순서
        ,changeYear: true //option값 년 선택 가능
        ,changeMonth: true //option값  월 선택 가능                
        ,showOn: "both" //button:버튼을 표시하고,버튼을 눌러야만 달력 표시 ^ both:버튼을 표시하고,버튼을 누르거나 input을 클릭하면 달력 표시  
        ,buttonImage: "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif" //버튼 이미지 경로
        ,buttonImageOnly: true //버튼 이미지만 깔끔하게 보이게함
        ,buttonText: "선택" //버튼 호버 텍스트              
        ,yearSuffix: "년" //달력의 년도 부분 뒤 텍스트
        ,monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 텍스트
        ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip
        ,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 텍스트
        ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'] //달력의 요일 Tooltip
        ,yearRange: 'c-50:c+0', // 년도 선택 셀렉트박스를 현재 년도에서 이전, 이후로 얼마의 범위를 표시할것인가
    });                               
});
	
	function onlyNumber(e){
		var regFormat = /[^0-9]/g;
		if(regFormat.test(e.value)){
			alert("숫자만 입력 가능합니다!");
			e.value = e.value.replace(/[^0-9]/g,'')
		}
	}
	
	function formSubmit(){
		if($("[name=genre]").val() == "" || $("[name=genre]").val() == null){
			alert("장르 항목은 필수 입력 값입니다.");
			$("[name=genre]").focus();
			return ;
		}
		if($("[name=opendate]").val() == "" || $("[name=opendate]").val() == null){
			alert("개봉일 항목은 필수 입력 값입니다.");
			$("[name=opendate]").focus();
			return ;
		}
		if($("[name=title]").val() == "" || $("[name=title]").val() == null){
			alert("제목 항목은 필수 입력 값입니다.");
			$("[name=title]").focus();
			return ;
		}
		if($("[name=times]").val() == "" || $("[name=times]").val() == null){
			alert("상영시간 항목은 필수 입력 값입니다.");
			$("[name=times]").focus();
			return ;
		}
		if($("[name=actor]").val() == "" || $("[name=actor]").val() == null){
			alert("배우 항목은 필수 입력 값입니다.");
			$("[name=actor]").focus();
			return ;
		}
		if($("[name=director]").val() == "" || $("[name=director]").val() == null){
			alert("감독 항목은 필수 입력 값입니다.");
			$("[name=director]").focus();
			return ;
		}
		
		if($("[name=moviefile]").val() == "" || $("[name=moviefile]").val() == null){
			alert("영상 항목은 필수 입력 값입니다.");
			$("[name=moviefile]").focus();
			return ;
		}
		$("#frm").submit();
	}
</script>

	<div class="row">
		<div class="col-md-offset-5 col-md-2">
			<p style="text-align:center;font-size:25pt;">영상 업로드</p><br>
			<p style="text-align:center;font-size:15pt;">* 항목은 반드시 입력해주세요.</p>
		</div>
	</div>
	<br>
	<br>

	<form id="frm" name="f" action="doAdminUpdate.admin" method="post"
		enctype="multipart/form-data">
	<input type="hidden" name="movienum" value="${getMovie.movienum}">
		
	<div class="row">
		<div class="col-md-offset-3 col-md-3">
			<div class="col-md-4">
				<p style="text-align:center;font-size:15pt;">* 영상제목</p>
			</div>
			<div class="col-md-8">
				<input class="form-control" type="text" id="title" name="title" maxlength="25" value="${getMovie.title}">
			</div>
		</div>
		
		<div class="col-md-3">
			<div class="col-md-4">
				<p style="text-align:center;font-size:15pt;">* 개봉일</p>
			</div>
			<div class="col-md-8">
				<input type="text" class="form-control" id="datepicker"
				readonly="readonly" name="opendate" value="${getMovie.opendate}">
			</div>
		</div>
	</div><br>
	
	<div class="row">
		<div class="col-md-offset-3 col-md-3">
			<div class="col-md-4">
				<p style="text-align:center;font-size:15pt;">* 상영시간</p>
			</div>
			<div class="col-md-8">
				<input class="form-control" type="text" id="times"
					name="times" onchange="onlyNumber(this)" placeholder="분" value="${getMovie.times}">
			</div>
			
		</div>
		
		<div class="col-md-3">
			<div class="col-md-4">
				<p style="text-align:center;font-size:15pt;">* 감독</p>
			</div>
			<div class="col-md-8">
				<input class="form-control" type="text" name="director" value="${getMovie.director}">
			</div>
		</div>
	</div><br>
	
	<div class="row">
		<div class="col-md-offset-3 col-md-3">
			<div class="col-md-4">
				<p style="text-align:center;font-size:15pt;">* 장르</p>
			</div>
			<div class="col-md-8">
				<input class="form-control" type="text" id="genre" name="genre" value="${getMovie.genre}">
			</div>
			
		</div>
		
		<div class="col-md-3">
			<div class="col-md-4">
				<p style="text-align:center;font-size:15pt;">* 관람제한가</p>
			</div>
			<div class="col-md-8">
				<select name="age">
					<c:forTokens items="all,12,15,18" delims="," var="ages">
						<c:if test="${ages == age}">
							<option value="${ages}" selected>${fn:toUpperCase(ages)}</option>
						</c:if>
						<c:if test="${ages != age}">
							<option value="${ages}">${fn:toUpperCase(ages)}</option>
						</c:if>
					</c:forTokens>
				</select>
			</div>
		</div>
	</div><br>
	
	<div class="row">
		<div class="col-md-offset-3 col-md-3">
			<div class="col-md-4">
				<p style="text-align:center;font-size:15pt;">* 영화포스터</p>
			</div>
			
			<div class="col-md-8">
				<img src="filePath1/${getMovie.image}" style="width:250px;height:200px;"><br><br>
				<input class="form-control" type="file" name="image">
				<input type="hidden" name="image2" value="${getMovie.image}">
			</div>
			
		</div>
		
		<div class="col-md-3">
			<div class="col-md-4">
				<p style="text-align:center;font-size:15pt;">* 영상</p>
			</div>
			<div class="col-md-8">
				<textarea class="form-control" rows="5" cols="50" name="moviefile">${getMovie.moviefile}</textarea>
			</div>
		</div>
	</div><br>
	
	<div class="row">
		<div class="col-md-offset-3 col-md-6">
			<div class="col-md-2">
				<p style="text-align:center;font-size:15pt;">재생 영상 360p</p>
			</div>
			<div class="col-md-8">
				<input class="form-control" type="file" name="videofile" value="${getMovie.videofile}">
			</div>
		</div>
	</div><br>
	
	<div class="row">
		<div class="col-md-offset-3 col-md-6">
			<div class="col-md-2">
				<p style="text-align:center;font-size:15pt;">재생 영상 720p</p>
			</div>
			<div class="col-md-8">
				<input class="form-control" type="file" name="videofile2" value="${getMovie.videofile2}">
			</div>
		</div>
	</div><br>
	
	<div class="row">
		<div class="col-md-offset-3 col-md-6">
			<div class="col-md-2">
				<p style="text-align:center;font-size:15pt;">재생 영상 1080p</p>
			</div>
			<div class="col-md-8">
				<input class="form-control" type="file" name="videofile3" value="${getMovie.videofile3}">
			</div>
		</div>
	</div><br>
	
	<div class="row">
		<div class="col-md-offset-3 col-md-1">
			<p style="text-align:center;font-size:15pt;">* 출연진</p>
		</div>
		<div class="col-md-5">
			<input class="form-control" type="text" name="actor" value="${getMovie.actor}">
		</div>
	</div><br>
	
	<div class="row">
		<div class="col-md-offset-3 col-md-1">
			<p style="text-align:center;font-size:15pt;">시놉시스</p>
		</div>
		<div class="col-md-5">
			<textarea class="form-control" name="moviecontents" rows="5" cols="60" style="height:150px;">${getMovie.moviecontents}</textarea>
		</div>
	</div><br>
	
	<div class="row">
		<div class="col-md-offset-5 col-md-1">
			<input class="btn btn-primary btn-block" type="button" value="수정" onclick="formSubmit();">
		</div>
		<div class="col-md-1">
			<input class="btn btn-secondary btn-block" type="button" value="취소" onclick="location.href='goAdminList.admin'">
		</div>
	</div>
	<br>
	<br>
		
	</form>
</body>
</html>