<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script type="text/javascript" src="<%=basePath %>js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript">
		$(function(){
			$.ajax({
				url : "<%=basePath%>test",
				dataType : "json",
				success : function(e){
					alert(JSON.stringify(e));
				}
			});
		});
	</script>
	<a href="<%=basePath %>project/toProjectPlatePage">项目模板页面</a><br/>
	<a href="<%=basePath %>project/toProjectClassPage">项目类别页面</a><br/>
	<a href="<%=basePath %>project/toProjectPage">项目页面</a><br/>
	<a href="<%=basePath %>project/toProjectComboPage">项目套餐页面</a><br/>
	<a href="<%=basePath %>project/toPriceSetPage">价格设置页面</a>
</body>
</html>