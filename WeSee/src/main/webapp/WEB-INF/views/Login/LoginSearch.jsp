<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<!-- search.jsp -->
    
    <html lang="ko-kr">
    <head>
    	<meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <!-- <meta name="viewport" content="width=device-width, initial-scale=1">
	     -->
	    <link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
		
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script>
      $(function(){
         $('#datepicker').datepicker();
      })
</script>

<script>

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
       
       //초기값을 오늘 날짜로 설정해줘야 합니다.
       $('#datepicker').datepicker('setDate', 'today'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)            
   });
   
   $.datepicker.setDefaults({
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
</script>

<script type="text/javascript">
	function goBack(){
		history.back()
	}
	function check(){
		if (f.name.value==""){
			alert("이름을 입력해 주세요!!")
			f.name.focus()
			return false
		}
		if (f.hp1.value=="" || f.hp2.value=="" || f.hp3.value==""){
			alert("연락처를 전부 입력해 주세요.")
			f.hp1.focus()
			return false
		}
		if (f.option.value=="passwd" && f.id.value==""){
			alert("아이디를 입력해 주세요!!")
			f.id.focus()
			return false
		}
		return true
	}
</script>
	<style>
		body{
			color:#ffffff;
			background-repeat: no-repeat;
			background-color: #000000;
			background-size: cover;
		}
	</style>
</head>

<body>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	
	<div class="container">
		<div class="col-md-offset-4 col-md-4">
			<div align="center">
				<img src="filePath1/WeSee.png" style="width:200px;height:200px"/>
			</div>
		</div>
	
	<br>
	<br>
	
		<div class="row">
			<c:choose>
				<c:when test="${option eq 'email'}">
					<div class="col-md-offset-5 col-md-2">	
						<span style="color:white;size:10;">아이디찾기</span>
					</div>
				</c:when>
				
				<c:otherwise>
					<div class="col-md-offset-5 col-md-2">	
						<span style="color:white;size:10;">비밀번호찾기</span>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
		
		<br>
		<br>
		
		<form class="form-inline" name="f" action="doSearch.do" method="post" onsubmit="return check()">
			<input type="hidden" name="option" value="${option}"/>
		
			<c:if test="${option eq 'passwd'}">	
				<div class="row">
					<div class="col-md-offset-3 col-md-1">
						<label>이메일</label>
					</div>
					
					<div class="col-md-2">
						<input type="text" name="email" class="form-control" size="20">
					</div>
				</div>
				<br>
			</c:if>	
																	
			<div class="row">
				<div class="col-md-offset-3 col-md-1">
					<label>이름</label>
				</div>
				
				<div class="col-md-2">
					<input type="text" name="name" class="form-control" size="20">
				</div>
			</div>
			<br>		
			
			<div class="row">
				<div class="col-md-offset-3 col-md-1">
					<label>생년월일</label>
				</div>
				
				<div class="col-md-2">
					<input type="text" name="birth" class="form-control" size="20" id="datepicker">
				</div>
			</div>
			<br>	
			
			<div class="row">
				<div class="col-md-offset-3 col-md-1">
					<label>핸드폰<br>번호</label>
				</div>
				
				<div class="col-md-1">
					<input type="text" name="hp1" class="form-control" maxlength="3" size="3">
				</div>
				<div class="col-md-2">
					<input type="text" name="hp2" class="form-control" maxlength="4" size="15">
				</div>
				<div class="col-md-2">
					<input type="text" name="hp3" class="form-control" maxlength="4" size="15">
				</div>
			</div>
			<br>	
			
			<div class="row">
				<div class="col-md-offset-5 col-md-5">
					<input class="btn btn-primary" type="submit" value="찾기">&nbsp;
					<input class="btn btn-success" type="reset" value="다시입력">&nbsp;
					<input type="button" class="btn btn-success" value="돌아가기" onclick="javascript:goBack()" />
				</div>
			</div>	
		</form>
	</div>
</body>
</html>