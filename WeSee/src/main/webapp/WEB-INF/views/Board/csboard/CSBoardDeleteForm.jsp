<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>

	<!-- deleteForm.jsp -->
	
   <html lang="ko-kr">
    <head>
    	<meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <!-- <meta name="viewport" content="width=device-width, initial-scale=1">
	     -->
	    <link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css">
	    
	    <title>글삭제</title>
	    
	</head>

<body>
	<div class="container">
		<br><br>
		<br><br>
		<br><br>
		<br><br>
		<br><br>
		
		<form class="form-inline" name="passwdCheck" action="delete_csboard.do" method="post">
			<input type="hidden" name="csboardnum" value="${csboardnum}"/>
			<input type="hidden" name="csimage" value="${csimage}"/>
				
		<div class="row">
			<div class="col-md-offset-4 col-md-4" align="center">
				비밀번호 확인 : <input class="form-control"  type="password" name="passwd">
			</div>
		</div>
		<br>
		<div class="row" align="center">
			<input class="btn btn-primary" type="submit" value="글삭제"/>
			<input class="btn btn-warning" type="button" value="목록으로" onclick="window.location='list_csboard.do'"/>
		</div>
		</form>
	</div>
</body>
</html>