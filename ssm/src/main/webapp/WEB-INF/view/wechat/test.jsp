<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="<%=basePath %>js/ajax.js"></script>
<script type="text/javascript" src="<%=basePath %>js/jquery-1.9.1.min.js"></script>
<body>
<button onclick="getJson()">请求json数据</button>
<script type="text/javascript">
<!-- 接受view中数据-->
var jsonObject = '${userName}';
console.log(jsonObject);

var baseUrl = '<%=basePath%>';
function getJson(){
	jQuery.ajax({
		url: baseUrl+"wechat/upload",
        data: "menuName=Mybatis",
        type:"POST",
        success: function(data) {
           console.log(data.menuName);
        }
	});
}
</script>
</body>
</html>