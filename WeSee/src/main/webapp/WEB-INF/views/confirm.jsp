<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
	
	<!-- confirm.jsp -->
	
	<script type="text/javascript">
		if(window.confirm('${msg}')){
			 opener.document.location.href='${url}';
			 window.close();
		}else{
			window.close();
		}
	</script>