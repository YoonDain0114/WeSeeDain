<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>   
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>

	<!-- MemberEdit.jsp -->
    
    <html lang="ko-kr">
    <head>
    	<meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <!-- <meta name="viewport" content="width=device-width, initial-scale=1">
	     -->
	    <link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css"> 
		<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
		<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
		<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
		<link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
		
		<title>회원정보수정</title>
<script>
      $(function(){
         $('#datepicker').datepicker();
      })
</script>

<script>
    function check_pw(){
        var pw = document.getElementById('pw').value;
        var SC = ["!","@","#","$","%"];
        var check_SC = 0;

        if(pw.length < 8 || pw.length>20){
            window.alert('비밀번호는 8글자 이상, 20글자 이하만 이용 가능합니다.');
            document.getElementById('pw').value='';
        }
        for(var i=0;i<SC.length;i++){
            if(pw.indexOf(SC[i]) != -1){
                check_SC = 1;
            }
        }
        if(document.getElementById('pw').value !='' && document.getElementById('pw2').value!=''){
            if(document.getElementById('pw').value==document.getElementById('pw2').value){
                document.getElementById('check').innerHTML='비밀번호가 일치합니다.'
                document.getElementById('check').style.color='white';
            }
            else{
                document.getElementById('check').innerHTML='비밀번호가 일치하지 않습니다.';
                document.getElementById('check').style.color='red';
            }
        }
    }
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
           ,minDate: "-5Y" //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
           ,maxDate: "+5y" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)  
       });           
     });
   
   $.datepicker.setDefaults({
       dateFormat: 'yy-mm-dd',
       prevText: '이전 달',
       nextText: '다음 달',
       monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
       monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
       dayNames: ['일', '월', '화', '수', '목', '금', '토'],
       dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
       dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
       showMonthAfterYear: true,
       yearSuffix: '년'
   });
</script>
<script>
    function execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                var addr = '';
                var extraAddr = '';

                if (data.userSelectedType === 'R') {
                    addr = data.roadAddress;
                } else {
                    addr = data.jibunAddress;
                }
				if(data.userSelectedType === 'R'){
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                   	if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    document.getElementById("extraAddress").value = extraAddr;
                } else {
                    document.getElementById("extraAddress").value = '';
                }
				document.getElementById('postcode').value = data.zonecode;
                document.getElementById("address").value = addr;
                document.getElementById("detailAddress").focus();
            }
        }).open();
    }
</script>

<script type="text/javascript">
		function check(){
			if (f.email.value == ""){
				alert("아이디를 입력해 주세요!!")
				f.email.focus()
				return false
			}
			if (f.passwd.value == ""){
				alert("비밀번호를 입력해 주세요!!")
				f.passwd.focus()
				return false
			}
			if (f.passwd2.value == ""){
				alert("비밀번호 확인을 입력해 주세요!!")
				f.passwd.focus()
				return false
			}
			if (f.name.value == ""){
				alert("이름을 입력해 주세요!!")
				f.name.focus()
				return false
			}
			if (f.byear.value == ""){
				alert("생년월일을 입력해 주세요!!")
				f.byear.focus()
				return false
			}
			if (f.hp1.value=="" || f.hp2.value=="" || f.hp3.value==""){
				alert("연락처를 전부 입력해 주세요.")
				f.hp1.focus()
				return false
			}
			if (f.passwd.value != f.passwd2.value){
				alert("비밀번호가 일치하지 않습니다. 다시 입력해 주세요!!")
				f.passwd.focus()
				return false
			}
			return true
		}
</script>

<script type="text/javascript">
	function goBack(){
		history.back()
	}
	function openDelete(){
		var popupWidth = 200;
		var popupHeight = 100;
		
		var popupX = (window.screen.width / 2) - (popupWidth / 2);
		// 만들 팝업창 width 크기의 1/2 만큼 보정값으로 빼주었음
		
		var popupY= (window.screen.height / 2) - (popupHeight / 2);
		// 만들 팝업창 height 크기의 1/2 만큼 보정값으로 빼주었음
		
		window.open('goAskDelete.movie', '회원탈퇴 확인', 'status=no, height=' + popupHeight  + ', width=' + popupWidth  + ', left='+ popupX + ', top='+ popupY);
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

<body onload="f.id.focus()">
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-offset-11 col-md-1" style='height: 100px; width: 100px;color:#ffffff;'>
				<a href="javascript:openDelete()">
				<svg xmlns="http://www.w3.org/2000/svg" style="width:50px;height:50px;" fill="currentColor" class="bi bi-x-circle" viewBox="0 0 16 16">
				  <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
				  <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
				</svg><br>
				<span style="color:#ffffff;text-align:center;">회원탈퇴</span></a>
	     	</div>
		</div>
		<br>
		
		<div class="row">
			<div class="col-md-offset-5 col-md-2">
				<a href="goMainPage.movie">
					<img src="filePath1/WeSee.png" style='height: 200px; width: 200px;'/>
				</a>
			</div>
		</div>
		
		<br>
		<br>
		<br>
		
<form name="f" method="POST" action="doMemberEdit.movie" onsubmit="return check()" enctype="multipart/form-data">		
		<div class="row">
			<div align="center">
				<font size="5">회원정보수정</font>
			</div>
		</div>
		<br><br>
		
		<div class="row">
			<div class="col-md-offset-4 col-md-2">
				<div align = "center" class="box"
					class="img-fluid img-circle">
					<img class="profile" id="profile" src="filePath1/${loginData.memberimage}" style='height: 200px; width: 200px;'>
				</div>
			</div>
			
			<div class="col-md-2">
				<div class="row">
					<div align="center">
						<br>
						<label>프로필 이미지 수정</label>
						
						<br>
						<input class="form-control" type="file" name="image" id="image"/>
					</div>
				</div>
			</div>
		</div>
		<br>
		
		<div class="row">
			<div class="col-md-offset-4 col-md-1">
				<label>E-mail</label>
			</div>
			
			<div class="col-md-3">
				<input class="form-control" type="text" name="email" value="${loginData.email}" disabled>
			</div>
		</div>
		<br>
		
		<div class="row">
			<div class="col-md-offset-4 col-md-1">
				<label>비밀번호</label>
			</div>
			
			<div class="col-md-3">
				<input class="form-control" type="password" id="pw" name="passwd" onchange="check_pw()">
			</div>
		</div>
		<br>
		
		<div class="row">
			<div class="col-md-offset-4 col-md-1">
				<label>비밀번호 재확인</label>
			</div>
			
			<div class="col-md-3">
				<input type="password" id="pw2" name="passwd2"
						 class="form-control" onchange="check_pw()">&nbsp;<span id="check"></span>
			</div>
		</div>
		<br>
		
		<div class="row">
			<div class="col-md-offset-4 col-md-1">
				<label>이름</label>
			</div>
			
			<div class="col-md-3">
				<input class="form-control" type="text" name="name" value="${loginData.name}" disabled>
			</div>
		</div>
		<br>
		
		<div class="row">
			<div class="col-md-offset-4 col-md-1">
				<label>생년월일</label>
			</div>
			
			<div class="col-md-3">
				<input class="form-control" type="text" name="birth" id="datepicker" value="${loginData.birth}">
			</div>
		</div>
		<br>
		
		<div class="row">
			<div class="col-md-offset-4 col-md-1">
				<label>연락처</label>
			</div>
			
			<div class="col-md-1">
				<input type="text" name="hp1" id="hp1" class="form-control" value="${loginData.hp1}" maxlength="3" size="3"
					oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"/> 
			</div>
			<div class="col-md-1">
				<input type="text" name="hp2" class="form-control" value="${loginData.hp2}" maxlength="4" size="12"
					oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"/>
			</div>
			<div class="col-md-1">
				<input type="text" name="hp3" class="form-control" value="${loginData.hp3}" maxlength="4" size="12"
					oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"/>
			</div>
		</div>
		<br>
		
		<div class="row">
			<div class="col-md-offset-4 col-md-1">
				<label for="addrbt">주소</label>
			</div>
			
			<div class="col-md-6">
				<div class="row">
					<div class="col-md-4">
						<input type="text" class="form-control" name="postcode" id="postcode" placeholder="우편번호" value="${addr[0]}" readonly>
					</div>
					<div class="col-md-2">
						<input type="button" class="btn btn-primary" onclick="execDaumPostcode()" value="우편번호 찾기">
					</div>
				</div><br>
				
				<div class="row">
					<div class="col-md-6">
						<input type="text" class="form-control" name="address" id="address" placeholder="주소" value="${addr[1]}" readonly>
					</div>
				</div><br>
				
				<div class="row">
					<div class="col-md-4">
						<input type="text" class="form-control" name="detailAddress" id="detailAddress" placeholder="상세주소" value="${addr[2]}">
					</div>
					<div class="col-md-2">
						<input type="text" class="form-control" name="address" id="extraAddress" placeholder="참고항목">
					</div>
				</div>
			</div>
		</div>
		<br>
		
		<div class="row">
			<div class="col-md-offset-5 col-md-3">
				<input class="btn btn-primary" type="submit" value="수정"/>
				<input class="btn btn-success" type="reset" value="초기화" />
				<input class="btn btn-success" type="button" value="돌아가기" onclick="javascript:goBack()"/>
			</div>
		</div>
		<br>
</form>
	</div>
</body>
</html>