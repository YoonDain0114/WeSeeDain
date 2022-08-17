<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>   
    
    <html lang="ko-kr">
    <head>
    	<meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <!-- <meta name="viewport" content="width=device-width, initial-scale=1">
	     -->
	    <link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css"> 
	    
		<!-- <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script> -->
		<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
		<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
		
		<link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<script>
      $(function(){
         $('#datepicker').datepicker();
      })
</script>

<script>
    function check_pw(){
        var pw = document.getElementById('passwd').value;
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
        if(document.getElementById('passwd').value !='' && document.getElementById('passwd2').value!=''){
            if(document.getElementById('passwd').value==document.getElementById('passwd2').value){
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
			if(f.idCheck.value == "N"){
				alert("이메일 중복 확인을 해 주세요.")
				f.email.focus()
				return false
			}
			if (f.emailName.value == ""){
				alert("아이디를 입력해 주세요!!")
				f.email.focus()
				return false
			}
			if (f.passwd.value == ""){
				alert("비밀번호를 입력해 주세요!!")
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
		function chkCharCode(event) {
			  const regExp = /[^0-9a-zA-Z]/g;
			  const ele = event.target;
			  if (regExp.test(ele.value)) {
			    ele.value = ele.value.replace(regExp, '');
			  }
			};
	</script>
	
	<script type="text/javascript">
		function goBack(){
			history.back()
		}
		function handleOnChange(e) {
	    	  // 선택된 데이터 가져오기
	    	  const value = e.value;
	    	  
	    	  // 데이터 출력
	    	  document.getElementById('domainName').value = value;
	    	}
		function fn_idChec(){
			var email = document.getElementById('email').value;
			var domain = document.getElementById('domainName').value;
			
			if(email.length==0 || email==""){
				alert("이메일을 입력하세요.");
				document.f.email.focus();
			}else if(domain.length==0 || domain==""){
				alert("이메일을 입력하세요.");
				document.f.domain.focus();
			}else{
				window.open("doCheckEmail.do?email="+email+"&domain="+domain,"이메일 중복확인","width=500, height=300");
			}
		}
		function doReset(){
			document.f.email.disabled=false;
			document.f.reset();
		}
	</script>
		
	<style>
		body{
			color:#ffffff;
			background-image: url('https://assets.nflxext.com/ffe/siteui/vlv3/5ea364b1-8e59-4693-8ad8-f0eaee32d1bf/fdbda4bf-7038-4889-9e5e-01ef45114e55/KR-ko-20220530-popsignuptwoweeks-perspective_alpha_website_large.jpg');
			background-repeat: no-repeat;
			background-color: #000000;
			background-size: cover;
		}
	</style>
	
</head>

	<body onload="f.id.focus()">
	
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		
		<div class="container" style="background-color:black">
		<form class="form-inline" role="form" name="f" method="POST" action="doNewMember.do" onsubmit="return check()">
			<div class="row">
				<div align="center">
					<label for="title">회원 가입 양식</label>
					<label for="title">* 항목은 필수 입력입니다</label>
				</div>
			</div>
			
			<p>
			<div class="row">
				<div class="col-md-offset-3 col-md-1">
					<label for="email">* email</label>
				</div>
				<div class="col-md-2">
					<input type="text" name="email" id="email" class="form-control" placeholder="이메일" onkeyup="chkCharCode(event)"/>
					<input type="hidden" name="emailName"/>
				</div>
				<div class="col-md-1" align="center">
					<label for="domain" style="size:20px">@</label>
				</div>
				<div class="col-md-1">
					<input type="text" name="domainName" id="domainName" class="form-control" placeholder="도메인"/>
				</div>
				<div class="col-md-1">
					<select name="" class="form-control" onchange="handleOnChange(this)">
						<option value="">-이메일 선택-</option>
						<option value="daum.net">daum.net</option>
						<option value="naver.com">naver.com</option>
						<option value="nate.com">nate.com</option>
						<option value="yahoo.com">yahoo.com</option>
						<option value="empas.com">empas.com</option>
						<option value="gmail.com">gmail.com</option>
					</select>
				</div>
				<div class="col-md-offset-1 col-md-1">
					<input type="button" name="checkBT" class="btn btn-primary" value="중복확인" onclick="fn_idChec()"/>
				</div>
			</div>
			<p>
			
			<div class="row">
				<div class="col-md-offset-3 col-md-1">
					<label for="passwd">* 비밀번호</label>
				</div>
				<div class="col-md-4">
					<input type="password" name="passwd" id="passwd" class="form-control" placeholder="비밀번호"/>
				</div>
			</div>
			
			<p>
			
			<div class="row">
				<div class="col-md-offset-3 col-md-1">
					<label for="passwd2">* 비밀번호 재확인</label>
				</div>
				<div class="col-md-6">
					<input type="password" name="passwd2" id="passwd2" class="form-control" placeholder="비밀번호 재확인" onchange="check_pw()"/>
					&nbsp;
					<span id="check"></span>
				</div>
			</div>
			
			<p>
			
			<div class="row">
				<div class="col-md-offset-3 col-md-1">
					<label for="name">* 이름</label>
				</div>
				<div class="col-md-4">
					<input type="text" name="name" id="name" class="form-control" placeholder="이름"/>
				</div>
			</div>
			
			<p>
			
			<div class="row">
				<div class="col-md-offset-3 col-md-1">
					<label for="datepicker">* 생년월일</label>
				</div>
				<div class="col-md-4">
					<input type="text" name="birth" id="datepicker" class="form-control" >
				</div>
			</div>
			
			<p>
			
			<div class="row">
				<div class="col-md-offset-3 col-md-1">
					<label for="hp1">* 연락처</label>
				</div>
				<td>
				<div class="col-md-1">
					<input type="text" name="hp1" id="hp1" class="form-control" placeholder="연락처" maxlength="3" size="3"
					oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"/> 
				</div>
				
				<div class="col-md-offset-1 col-md-1">
					<input type="text" name="hp2" class="form-control" placeholder="전화번호 앞번호" maxlength="4" size="12"
					oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"/>
				</div>
				
				<div class="col-md-offset-1 col-md-1">
					<input type="text" name="hp3" class="form-control" placeholder="전화번호 뒷번호" maxlength="4" size="12"
					oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"/>
				</div>
				</td>
			</div>
			
			<p><br>
			
			<div class="row">
				<div class="col-md-offset-3 col-md-1">
					<label for="addrbt">주소</label>
				</div>
				<div class="col-md-6">
					<input type="text" class="form-control" name="postcode" id="postcode" placeholder="우편번호" readonly>
					<input type="button" class="btn btn-primary" onclick="execDaumPostcode()" value="우편번호 찾기"><p><p>
					<input type="text" class="form-control" name="address" id="address" placeholder="주소" size="50" readonly><p><p>
					<input type="text" class="form-control" name="detailAddress" id="detailAddress" placeholder="상세주소" size="50">&nbsp;
					<input type="text" class="form-control" name="address" id="extraAddress" placeholder="참고항목" size="15">
				</div>
			</div>
			
			<p>
			
			<div class="form-group col-md-offset-5 col-md-3">
				<div align="center">
					<input type="submit" class="btn btn-primary" value="전송"/>
					<input type="button" class="btn btn-success" value="초기화" onclick="javascript:doReset()"/>
					<input type="button" class="btn btn-success" value="돌아가기" onclick="javascript:goBack()" />
				</div>
			</div>
			
			<br>
			<br>
			<br>
			
			<input type="hidden" name="idCheck" id="idCheck" value="N"/>
			
		</form>
		</div>
		<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	</body>
</html>