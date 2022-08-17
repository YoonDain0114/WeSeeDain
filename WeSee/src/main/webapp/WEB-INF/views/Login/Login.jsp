<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    <html lang="ko-kr">
    <head>
    	<meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <!-- <meta name="viewport" content="width=device-width, initial-scale=1">
	     -->
	    <link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css"> 
	    
    	<title>WeSee</title>
    <style type="text/css">
    	.box {
		    width: 150px;
		    height: 150px; 
		    border-radius: 70%;
		    overflow: hidden;
		}
		.profile {
		    width: 100%;
		    height: 100%;
		    object-fit: cover;
		}

		.col-* {
		  
		  border: 1px solid gray;
		  text-align: center;
		}
	</style>
	
	<style>
		[class*="col-"] {
		  padding: 8px;
		  text-align: center;
		}
	</style>
	
	<style>
		body{
			color:#ffffff;
			background-repeat: no-repeat;
			background-color: #000000;
			background-size: cover;
		}
	</style>
		
	<script type="text/javascript">
			function check(){
				if (login.email.value == ""){
					alert("아이디를 입력해 주세요!!")
					login.email.focus()
					return
				}
				if (login.passwd.value == ""){
					alert("비밀번호를 입력해 주세요!!")
					login.passwd.focus()
					return
				}
				document.login.submit()
			}
			function chkCharCode(event) {
				  const regExp = /[^0-9a-zA-Z]/g;
				  const ele = event.target;
				  if (regExp.test(ele.value)) {
				    ele.value = ele.value.replace(regExp, '');
				  }
				};
	</script>
	
	</head>
	
	<!-- login.jsp -->
	
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
			<div class="col-md-offset-4 col-md-4">
				<font>로그인이 필요한 서비스입니다.</font>
			</div>
		</div>
		
		<br>
		
		<form class="form-inline" name="login" action="doLogin.do" method="post">
			
		<div class="row">
			<div class="col-md-offset-3 col-md-1">
				<label>E-mail</label>
			</div>
			<div class="col-md-4">
				<c:if test="${empty cookie.saveEmail}">
					<input class="form-control" type="text" name="email" size="40" placeholder="E-mail"/><!-- onkeyup="chkCharCode(event)" --> 
				</c:if>
				
				<c:if test="${not empty cookie.saveEmail}">
					<input class="form-control" type="text" name="email"  size="40" placeholder="E-mail" value="${cookie.saveEmail.value}">
				</c:if> 
			</div>
			<div class="col-md-2">
				<input type="submit" value="로그인" class="btn btn-primary btn-lg"/>
				<nobr>
					<c:choose>
					<c:when test="${empty cookie.saveEmail}">			
							<input type="checkbox" name="saveEmail">
					</c:when>
					<c:otherwise> <!--  test="${not empty cookie.saveEmail}" -->
							<input type="checkbox" name="saveEmail" checked>
					</c:otherwise>
					</c:choose>			
					<font face="굴림" size="2">이메일 기억하기</font>
				</nobr>
			</div>
		</div>
		
		<div class="row">
			<div class="col-md-offset-3 col-md-1">
				<label>password</label>
			</div>
			<div class="col-md-4">
				<input class="form-control" type="password" name="passwd" size="40" placeholder="password"/>
			</div>
		</div>
		
		<br>
		
		<div class="row">
			<div align="center">
				<input type="button" value="회원가입" class="btn btn-success" onclick="location.href='goNewMember.do'">&nbsp;
				<input type="button" value="이메일찾기" class="btn btn-info" onclick="location.href='goSearch.do?option=email'">&nbsp;
				<input type="button" value="비밀번호찾기" class="btn btn-info" onclick="location.href='goSearch.do?option=passwd'">
			</div>
		</div>
		</form>
		<br>
		<br>
		
	</div>
	</body>
</html>