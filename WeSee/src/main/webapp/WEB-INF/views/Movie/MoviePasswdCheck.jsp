<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>   
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>

	<!-- PasswdCheck.jsp -->
    
    <html lang="ko-kr">
    <head>
    	<meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <!-- <meta name="viewport" content="width=device-width, initial-scale=1">
	     -->
	    <link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css"> 
	</head>

<body>
	<div class="container">
		<form class="form-inline" name="passwdCheck" action="doPasswdCheck.movie" method="post">
		<div class="row">
			<div>
				비밀번호 재확인 : <input class="form-control"  type="password" name="passwd">
			</div>
		</div>
		<div class="row" align="center">
			<input class="btn btn-primary" type="submit" value="확인"/>
			<input class="btn btn-warning" type="button" value="취소" onclick="window.close()"/>
		</div>
		</form>
	</div>
</body>
</html>	