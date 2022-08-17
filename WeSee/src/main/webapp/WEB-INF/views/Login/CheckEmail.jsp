<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	
	<!-- CheckEmail.jsp -->
	
<html>
<head>
	<script type="text/javascript">
		function sendCheck(){
			var docu = opener.document.f;
			
			if(document.opt.checkResult.value=="N"){
				alert("다른 이메일을 입력하세요.")
				docu.email.focus();
				window.close();
			}else{
				docu.email.disabled=true;
				docu.idCheck.value="Y";
				docu.checkBT.disabled=true;
				docu.emailName.value = docu.email.value;
				window.close();
			}
		}
	</script>
</head>

<body>
	<form name="opt">
	<div align="center">
	<c:if test="${empty email}">
		<label>이미 사용중인 이메일입니다.</label>
		<input type="hidden" name="checkResult" value="N"/>
	</c:if>
	
	<c:if test="${not empty email}">
		<label>사용 가능한 이메일입니다.</label>
		<input type="hidden" name="checkResult" value="Y"/>
	</c:if>
	</div>
	
	<br>
	
	<div align="center">
		<input type="button" onclick="window.close()" value="취소"/>&nbsp;
		<input type="button" onclick="sendCheck()" value="사용하기"/>
	</div>
	</form>
</body>
</html>	